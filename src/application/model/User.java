package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class User {
    public int userId;
    public String abtnumemr = "";
    public String title = "";
    public String name = "";
    public String street = "";
    public String zip;
    public String city = "";

    private static int num = 0;

    public String toString() {
        return title + " " + name;
    }

    public static ObservableList<User> loadFile(String filename) {
        ObservableList<User> list = FXCollections.observableArrayList();
        String zeile = null;
        BufferedReader br = null;


        try {
            Connection connection = AccessDd.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM user");

            while (result.next()) {
                User u = new User();
                u.userId = result.getInt("user_id");
                u.name = result.getString("name");
                u.abtnumemr = result.getString("department_id");
                u.city = result.getString("city");
                u.street = result.getString("street");
                u.title = result.getString("title");
                u.zip = result.getString("zip");

                list.add(u);
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
            statement.executeUpdate("DELETE FROM user WHERE user_id =" + userId);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(){
        try{
            Connection connection = AccessDd.getConnection();

            PreparedStatement statement = null;
            statement = connection.prepareStatement("UPDATE user SET name = ?, title = ?, street = ?, zip = ?, department_id = ? where user_id = ?");
            statement.setString(1, name);
            statement.setString(2, title);
            statement.setString(3, street);
            statement.setString(4, zip);
            statement.setString(5, abtnumemr);
            statement.setInt(6, userId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void writeFile(String filename, ObservableList<User> liste) {
        int i = 1;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("users.csv"));
            try {

                for (User u : liste) {
                    bw.write(i + ";" + u.title + ";" + u.name + ";" + u.street + ";" + u.zip +";" + u.city + ";" + u.abtnumemr + "\n");
                    i++;
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
