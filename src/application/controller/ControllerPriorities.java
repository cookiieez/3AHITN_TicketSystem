package application.controller;

import application.model.Priority;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ControllerPriorities {
    public TextField priovalue;
    public ListView PrioView;
    public ArrayList<Priority> allArticles = new ArrayList<>();

    public void initialize() {
        readDocument();
    }

    private void readDocument() {
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
                    PrioView.getItems().add(a);
                    allArticles.add(a);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sendName(ActionEvent actionEvent) {
    }

    public void saveEntry(ActionEvent actionEvent) {
    }

    public void newEntry(ActionEvent actionEvent) {
    }

    public void delEntry(ActionEvent actionEvent) {
    }
}
