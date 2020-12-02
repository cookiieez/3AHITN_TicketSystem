package application.controller;

import application.model.Priority;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ControllerUser {
    public TextField titleField;
    public TextField nameField;
    public TextField streetField;
    public TextField zipField;
    public ComboBox departmentIdCombo;
    public ListView userView;

    public void sendUser(ActionEvent actionEvent) {
    }
    public void initialize(){
        readDocument();
    }

    private void readDocument() {
        String s;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("user.csv"));
            try {
                while ((s = br.readLine()) != null) {
                    s = s.replace("\"", "");
                    String[] words = s.split(";");

                    Priority a = new Priority();
                    a.priority = words[0];
                    a.desc = words[1];
                    userView.getItems().add(a);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
