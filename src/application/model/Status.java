package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class Status {

    public int nummer = 0;
    public String status = "";

    private static int num = 0;

    public Status(int id, String status){
        nummer = id;
        this.status = status;
    }

    public Status(){
        nummer = 0;
        this.status = null;
    }

    public String toString() {
        return nummer + "-" + status;
    }


    public static ObservableList<Status> loadFile() {
        ObservableList<Status> list = FXCollections.observableArrayList();

        try {
            Connection connection = AccessDd.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM stati");

            while (result.next()) {
                Status s = new Status(result.getInt("status_id"), result.getString("name"));
                /**
                s.nummer = result.getInt("status_id");
                s.status = result.getString("name");
                 */
                list.add(s);
            }
        } catch (SQLException throwables) {

        }
        return list;
}

    public void delete(){
        try{
            Connection connection = AccessDd.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM stati WHERE status_id =" + nummer);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(){
        try{
            Connection connection = AccessDd.getConnection();

            PreparedStatement statement = null;
            statement = connection.prepareStatement("UPDATE stati SET name = ? where status_id = ?");
            statement.setString(1, status);
            statement.setInt(2, nummer);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Status getById(int id){
        Status s = null;
        try {
            Connection connection = AccessDd.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM stati WHERE status_id =" + id);

            if (result.next()) {
                s = new Status(result.getInt("status_id"), result.getString("name"));
                /**
                s.nummer = result.getInt("status_id");
                s.status = result.getString("name");
                 **/
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return s;
    }
    //Hallo was geht

    public static void writeFile(String filename, ObservableList<Status> liste) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("stati.csv"));
            try {

                for (Status s : liste) {
                    bw.write(s.nummer + ";" + s.status + "\n");
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
