package application.controller;

import application.model.Priority;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    ObservableList<Priority> liste = FXCollections.observableArrayList();
    public Button closebutton;
    public Priority selectedItem;

    public void initialize() {
        PrioView.setItems(Priority.loadFile("priorities.csv"));
    }

    public void saveEntry(ActionEvent actionEvent) {
        if(selectedItem != null){
            selectedItem.priority = descvalue.getText();

            PrioView.refresh();

            PrioView.setItems(liste);
        } else {
            if(!descvalue.getText().isEmpty()){
                Priority p = new Priority();

                p.priority = priovalue.getText();
                p.desc = descvalue.getText();
                liste.add(p);
            }
        }
        Priority.writeFile("priorities.csv", liste);
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
