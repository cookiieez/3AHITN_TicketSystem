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
        PrioView.setItems(Priority.loadFile("priorities.csv"));
    }

    public void saveEntry(ActionEvent actionEvent) {
            if(!priovalue.getText().isEmpty() &&!descvalue.getText().isEmpty()){
                selectedItem.desc = descvalue.getText();
                selectedItem.priority = priovalue.getText();

                PrioView.refresh();

                PrioView.setItems(PrioView.getItems());
            } else {
                Priority p = new Priority();

                p.priority = priovalue.getText();
                p.desc = descvalue.getText();

                PrioView.getItems().add(p);

                Priority.writeFile("priorities.csv", PrioView.getItems());
                PrioView.refresh();
            }
    }


    public void newEntry(ActionEvent actionEvent) {
        descvalue.clear();
        priovalue.clear();
    }

    public void delEntry(ActionEvent actionEvent) {
        descvalue.clear();
        priovalue.clear();
        PrioView.getItems().remove(selectedItem);
        Priority.writeFile("priorities.csv", PrioView.getItems());
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
