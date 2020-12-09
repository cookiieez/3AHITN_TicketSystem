package application.controller;

import application.model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ControllerUser extends User {
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
        readDocument(userView);
    }

    public void userClicked(MouseEvent mouseEvent) {
        selectedItem = userView.getSelectionModel().getSelectedItem();
        titleField.setText(selectedItem.title);
        nameField.setText(selectedItem.name);
        streetField.setText(selectedItem.street);
        zipField.setText(selectedItem.zip);
    }
}
