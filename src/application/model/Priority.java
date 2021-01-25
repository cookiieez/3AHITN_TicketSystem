package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class Priority {
    public int priority = 0;
    public String desc = "";


    @Override
    public String toString() {
        return priority + "-" + desc;
    }

    public static ObservableList<Priority> loadList() {
        ObservableList<Priority> list = FXCollections.observableArrayList();

        try {
            Connection connection = AccessDd.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM priorities");

            while (result.next()) {
                Priority p = new Priority();
                p.desc = result.getString("name");
                p.priority = result.getInt("priority_id");
                list.add(p);
            }
        } catch (SQLException throwables) {

        }
        return list;
    }

    public static Priority getById(int id){
        Priority p = new Priority();
        try {
            Connection connection = AccessDd.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM priorities WHERE priority_id =" + id);

            if (result.next()) {
                p.priority = result.getInt("priority_id");
                p.desc = result.getString("name");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return p;
    }

    public void delete(){
        try{
            Connection connection = AccessDd.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM priorities WHERE priority_id =" + priority);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(){
        try{
            Connection connection = AccessDd.getConnection();

            PreparedStatement statement = null;
            statement = connection.prepareStatement("UPDATE priorities SET name = ? where priority_id = ?");
            statement.setString(1, desc);
            statement.setInt(2, priority);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void writeFile(String filename, ObservableList<Priority> liste) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("priorities.csv"));
            try {

                for (Priority p : liste) {
                    bw.write(p.priority + ";" + p.desc + "\n");
                }

                bw.flush();
            } finally {
                bw.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
