package application.model;

import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Priority {
    public String priority;
    public String desc;

    @Override
    public String toString() {
        return priority + ": " + desc;
    }

    public static void readDocument(ListView<Priority> Listview) {

        String s;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("priorities.csv"));
            try {
                while ((s = br.readLine()) != null) {
                    s = s.replace("\"", "");
                    String[] words = s.split(";");

                    Priority a = new Priority();
                    a.priority = words[0];
                    a.desc = words[1];
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
