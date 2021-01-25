package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class Ticket {
    public int id = num;
    public String name = "";
    public String desc = "";
    public Status status = new Status();
    public Priority priority = new Priority();

    private static int num = 0;

    public String toString() {
        return  id + "-" + name;
    }

    public static ObservableList<Ticket> loadList() {
        ObservableList<Ticket> list = FXCollections.observableArrayList();

        try {
            Connection connection = AccessDd.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM tickets");

            while (result.next()) {
                Ticket t = new Ticket();
                t.id = result.getInt("ticket_id");
                t.name = result.getString("name");
                t.desc = result.getString("desc");
                t.priority = Priority.getById(result.getInt("priority_id"));
                t.status = Status.getById(result.getInt("status_id"));
                list.add(t);
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
            statement.executeUpdate("DELETE FROM priorities WHERE priority_id =" + priority);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(){
        try{
            Connection connection = AccessDd.getConnection();

            PreparedStatement statement = null;
            statement = connection.prepareStatement("UPDATE tickets SET name = ?, desc = ?, priority_id = ?, status_id = ? where ticket_id = ?");
            statement.setString(1, name);
            statement.setString(2, desc);
            statement.setInt(3, priority.priority);
            statement.setInt(4, status.nummer);
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

/**
    public static ObservableList<Ticket> loadFile(String filename) {
        ObservableList<Ticket> list = FXCollections.observableArrayList();
        String zeile = null;
        BufferedReader br = null;


        try {
            Connection connection = AccessDd.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM tickets");

            while (result.next()) {
                Ticket t = new Ticket();
                t.desc = result.getString("desc");
                t.id = result.getInt("ticket_id");
                t.name = result.getString("name");
                t.priority.priority = result.getString("priority_id");
                t.status.nummer = result.getInt("status_id");
                list.add(t);
            }
        } catch (SQLException throwables) {

        }
        return list;
    }
 **/

    public static void writeFile(String filename, ObservableList<Ticket> liste) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
            try {

                for (Ticket t : liste) {
                    bw.write(t.id + ";" + t.name + ";" + t.desc + ";" + t.status.nummer + ";" + t.priority.priority + "\n");
                }

                bw.flush();
            } finally {
                bw.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getNum() {
        return num;
    }

    public static void setNum(int num) {
        Ticket.num = num;
    }
}