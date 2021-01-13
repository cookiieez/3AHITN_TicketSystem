package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Status {

    public int nummer = 0;
    public String status = "";

    private static int num = 0;

    public String toString() {
        return nummer + "-" + status;
    }


    public static ObservableList<Status> loadFile(String filename) {
        ObservableList<Status> list = FXCollections.observableArrayList();

        try {
            Connection connection = AccessDd.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM stati");

            while (result.next()) {
                Status s = new Status();
                s.nummer = result.getInt("status_id");
                s.status = result.getString("name");
                list.add(s);
            }
        } catch (SQLException throwables) {

        }
        return list;
}

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
