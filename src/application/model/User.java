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
    public String title = "";
    public String name = "";
    public String street = "";
    public String zip = "";
    public String city = "";
    public Departments department;

    private static int num = 0;

    public User(int id, String title, String name, String street, String zip, String city, int departmentId) {
        userId = id;
        this.title = title;
        this.name = name;
        this.street = street;
        this.zip = zip;
        this.city = city;

        this.department = Departments.getById(departmentId);

    }

    public User(){
        userId = 0;
        this.title = null;
        this.name = null;
        this.street = null;
        this.zip = null;
        this.city = null;

        this.department = null;
    }

    public String toString() {
        return title + " " + name;

    }

    public static ObservableList<User> loadFile() {
        ObservableList<User> list = FXCollections.observableArrayList();
        String zeile = null;
        BufferedReader br = null;


        try {
            Connection connection = AccessDd.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM user");

            while (result.next()) {
                User u = new User(result.getInt("user_id"), result.getString("title"), result.getString("name"), result.getString("street"), result.getString("zip"), result.getString("city"), result.getInt("department_id"));
                /**
                u.userId = result.getInt("user_id");
                u.name = result.getString("name");
                u.city = result.getString("city");
                u.street = result.getString("street");
                u.title = result.getString("title");
                u.zip = result.getString("zip");
                u.department = Departments.getById(result.getInt("department_id"));
                 */

                list.add(u);
            }
        } catch (SQLException throwables) {

        }
        return list;
    }

    public static User getById(int id){
        User u = null;
        try {
            Connection connection = AccessDd.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM user WHERE user_id =" + id);

            if (result.next()) {
                u = new User(result.getInt("user_id"), result.getString("title"), result.getString("name"), result.getString("street"), result.getString("zip"), result.getString("city"), result.getInt("department_id"));
                /**
                 s.nummer = result.getInt("status_id");
                 s.status = result.getString("name");
                 **/
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return u;
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
            statement.setInt(5, department.nummer);
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
                    bw.write(i + ";" + u.title + ";" + u.name + ";" + u.street + ";" + u.zip +";" + u.city + ";" + u.department.nummer + "\n");
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
