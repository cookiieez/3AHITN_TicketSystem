package application.controller;

import application.model.Priority;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerPriorities extends Priority{
    public TextField priovalue;
    public TextField descvalue;
    public ListView<Priority> PrioView;
    public Button closebutton;
    public Priority selectedItem;

    public void initialize() {
        readDocument(PrioView);
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

    public void priorityClicked(MouseEvent mouseEvent) {
        selectedItem = PrioView.getSelectionModel().getSelectedItem();
        priovalue.setText(selectedItem.priority);
        descvalue.setText(selectedItem.desc);
    }
}
