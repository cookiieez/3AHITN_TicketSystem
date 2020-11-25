package application.controller;

import application.model.Priority;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ControllerPriorities {
    public TextField priovalue;
    public TextField descvalue;
    public ListView PrioView;
    public ArrayList<Priority> allArticles = new ArrayList<>();
    public Button closebutton;

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
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveEntry(ActionEvent actionEvent) {
    }

    public void newEntry(ActionEvent actionEvent) {
    }

    public void delEntry(ActionEvent actionEvent) {
    }

    public void windowClose(ActionEvent actionEvent) {
        Stage stage = (Stage) closebutton.getScene().getWindow();
        stage.close();
    }
}
