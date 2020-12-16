package application.controller;

import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    public ListView<User> userList;
    ObservableList<User> liste = FXCollections.observableArrayList();

    public User selectedItem;

    public void sendUser(ActionEvent actionEvent) {
    }
    public void initialize(){
        userList.setItems(User.loadFile("users.csv"));
    }

    public void userClicked(MouseEvent mouseEvent) {
        nameField.setText(userList.getSelectionModel().getSelectedItem().name);

        selectedItem = userList.getSelectionModel().getSelectedItem();
    }
}
