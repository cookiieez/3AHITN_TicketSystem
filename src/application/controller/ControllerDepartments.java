package application.controller;

import application.model.Departments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerDepartments {
    public TextField depField;
    public Button abbrechenButton;
    private int num = 0;

    public ListView<Departments> depList;
    ObservableList<Departments> liste = FXCollections.observableArrayList();

    private Departments selectedItem = null;

    public void initialize() {
        depList.setItems(Departments.loadFile("departments.csv"));
    }


    public void depClicked(MouseEvent mouseEvent) {
        depField.setText(depList.getSelectionModel().getSelectedItem().department);

        selectedItem = depList.getSelectionModel().getSelectedItem();
    }

    public void speichernClicked(ActionEvent actionEvent) {
        if(selectedItem == null){
            Departments d = new Departments();

            d.department = depField.getText();

            depList.getItems().add(d);

            Departments.writeFile("departments.csv", depList.getItems());
            depList.refresh();
        } else {
            selectedItem.department = depField.getText();

            depList.refresh();

            Departments.writeFile("departments.csv", depList.getItems());
            depList.refresh();
        }
    }

    public void löschenClicked(ActionEvent actionEvent) {
        num--;
        depField.clear();
        depList.getItems().remove(selectedItem);
        Departments.writeFile("departments.csv", depList.getItems());
        selectedItem = null;
    }

    public void neuClicked(ActionEvent actionEvent) {
        depField.clear();

        selectedItem = null;
    }

    public void abbrechenClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) abbrechenButton.getScene().getWindow();
        stage.close();
    }


}
