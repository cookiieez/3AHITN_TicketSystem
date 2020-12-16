package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class User {
    public String abtnumemr;
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
        ObservableList<User> result = FXCollections.observableArrayList();
        String zeile = null;
        BufferedReader br = null;


        try {
            br = new BufferedReader(new FileReader(filename));

            try {
                while ((zeile = br.readLine()) != null) {
                    String[] split = zeile.split(";");

                    User u = new User();
                    u.abtnumemr = split[0];
                    u.title = split[1];
                    u.name = split[2];
                    u.zip = split[3];
                    u.street = split[4];
                    u.city = split[5];
                    u.abtnumemr = split[6];

                    result.add(u);
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
