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
        userView.setItems(User.loadFile("users.csv"));
    }

    public void userClicked(MouseEvent mouseEvent) {
        nameField.setText(userView.getSelectionModel().getSelectedItem().name);
        titleField.setText(userView.getSelectionModel().getSelectedItem().title);
        streetField.setText(userView.getSelectionModel().getSelectedItem().street);
        zipField.setText(userView.getSelectionModel().getSelectedItem().zip);

        selectedItem = userView.getSelectionModel().getSelectedItem();
    }
}
