package application.model;

import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class User {
    public String abtnumemr;
    public String title = "";
    public String name = "";
    public String street = "";
    public String zip;
    public String city = "";

    public String toString() {
        return title + " " + name;
    }

    public static void readDocument(ListView<User> Listview) {
        String s;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("users.csv"));
            try {
                while ((s = br.readLine()) != null) {
                    s = s.replace("\"", "");
                    String[] words = s.split(";");

                    User a = new User();
                    a.abtnumemr = words[0];
                    a.title = words[1];
                    a.name = words[2];
                    a.street = words[3];
                    a.zip = words[4];
                    a.city = words[5];

                    Listview.getItems().add(a);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
