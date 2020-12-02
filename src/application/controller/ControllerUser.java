package application.controller;

import application.model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
    public ListView<User> userView;

    public User selectedItem;

    public void sendUser(ActionEvent actionEvent) {
    }
    public void initialize(){
        readDocument();
    }

    private void readDocument() {
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

                    userView.getItems().add(a);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void userClicked(MouseEvent mouseEvent) {
        selectedItem = userView.getSelectionModel().getSelectedItem();
        titleField.setText(selectedItem.title);
        nameField.setText(selectedItem.name);
        streetField.setText(selectedItem.street);
        zipField.setText(selectedItem.zip);
    }
}
