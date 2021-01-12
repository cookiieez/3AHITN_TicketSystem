package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Priority {
    public String priority = "";
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
                p.priority = result.getString("priority_id");
                list.add(p);
            }
        } catch (SQLException throwables) {

        }
        return list;
    }

    public static ObservableList<Priority> loadFile(String filename) {
        ObservableList<Priority> result = FXCollections.observableArrayList();
        String zeile = "";
        int num = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            try {
                while ((zeile = br.readLine()) != null) {
                    String[] split = zeile.split(";");
                    Priority p = new Priority();
                    p.priority = split[0];
                    p.desc = split[1];

                    result.add(p);
                    num++;

                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        return result;
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
