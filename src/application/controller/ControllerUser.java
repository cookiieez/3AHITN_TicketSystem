package application.controller;

import application.model.Departments;
import application.model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ControllerUser extends User {
    public TextField titleField;
    public TextField nameField;
    public TextField streetField;
    public TextField zipField;
    public ComboBox<Departments> departmentIdCombo;
    public ListView<User> userView;

    public User selectedItem;
    public Button saveButton;
    public Button delButton;
    public Button newButton;


    public void initialize(){
        userView.setItems(User.loadFile());
        departmentIdCombo.setItems(Departments.loadList());
    }

    public void userClicked(MouseEvent mouseEvent) {
        nameField.setText(userView.getSelectionModel().getSelectedItem().name);
        titleField.setText(userView.getSelectionModel().getSelectedItem().title);
        streetField.setText(userView.getSelectionModel().getSelectedItem().street);
        zipField.setText(userView.getSelectionModel().getSelectedItem().zip);
        departmentIdCombo.setValue(userView.getSelectionModel().getSelectedItem().department);

        selectedItem = userView.getSelectionModel().getSelectedItem();
    }

    public void saveButtonClicked(ActionEvent actionEvent) {
        if(selectedItem != null){
            selectedItem.name = nameField.getText();
            selectedItem.title = titleField.getText();
            selectedItem.street = streetField.getText();
            selectedItem.zip = zipField.getText();

            userView.refresh();

            selectedItem.update();
            userView.refresh();
        }
    }

    public void delButtonClicked(ActionEvent actionEvent) {
        titleField.clear();
        nameField.clear();
        streetField.clear();
        zipField.clear();

        userView.getItems().remove(selectedItem);
        selectedItem.delete();
    }

    public void newButtonClicked(ActionEvent actionEvent) {
    }
}
