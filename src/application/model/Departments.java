package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.*;

public class Departments {

    public int nummer = 0;
    public String department = "";

    private static int num = 0;

    public String toString() {
        return nummer + "-" + department;
    }

    public void delete(){
        try{
            Connection connection = AccessDd.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM departments WHERE department_id =" + nummer);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Departments getById(int id){
        Departments p = new Departments();
        try {
            Connection connection = AccessDd.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM departments WHERE department_id =" + id);

            if (result.next()) {
                p.department = result.getString("Name");
                p.nummer = result.getInt("department_id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return p;
    }

    public void update(){
        try{
            Connection connection = AccessDd.getConnection();

            PreparedStatement statement = null;
            statement = connection.prepareStatement("UPDATE departments SET name = ? where department_id = ?");
            statement.setString(1, String.valueOf(nummer));
            statement.setString(2, department);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ObservableList<Departments> loadList() {
        ObservableList<Departments> list = FXCollections.observableArrayList();

        try {
            Connection connection = AccessDd.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM departments");

            while (result.next()) {
                Departments p = new Departments();
                p.department = result.getString("Name");
                p.nummer = result.getInt("department_id");

                list.add(p);
            }
        } catch (SQLException throwables) {

        }
        return list;
    }

    public static ObservableList<Departments> loadFile(String filename) {
        ObservableList<Departments> result = FXCollections.observableArrayList();
        String zeile = null;
        BufferedReader br = null;


        try {
            br = new BufferedReader(new FileReader(filename));

            try {
                while ((zeile = br.readLine()) != null) {
                    String[] split = zeile.split(";");

                    Departments d = new Departments();
                    d.nummer = Integer.parseInt(split[0]);
                    d.department = split[1];

                    result.add(d);
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

    public static void writeFile(String filename, ObservableList<Departments> liste) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("departments.csv"));
            try {

                for (Departments d : liste) {
                    bw.write(d.nummer + ";" + d.department + "\n");
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
