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
        PrioView.setItems(Priority.loadList());
    }

    public void saveEntry(ActionEvent actionEvent) {
            if(selectedItem == null){
                Priority p = new Priority();

                p.desc = descvalue.getText();
                p.priority = priovalue.getText();

                PrioView.getItems().add(p);

                PrioView.refresh();
            } else {
                selectedItem.desc = descvalue.getText();
                selectedItem.priority = priovalue.getText();

                PrioView.refresh();

                selectedItem.update();
                PrioView.refresh();
            }
    }


    public void newEntry(ActionEvent actionEvent) {
        descvalue.clear();
        priovalue.clear();
        selectedItem = null;
    }

    public void delEntry(ActionEvent actionEvent) {
        descvalue.clear();
        priovalue.clear();
        PrioView.getItems().remove(selectedItem);
        selectedItem.delete();
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
