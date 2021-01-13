package application.controller;

import application.model.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class ControllerStati {
    public TextField nameField;
    public Button abbrechenButton;
    private int num = 0;

    public ListView<Status> statiList;
    ObservableList<Status> liste = FXCollections.observableArrayList();

    private Status selectedItem = null;

    public void initialize(){
        statiList.setItems(Status.loadFile());
    }

    public void statiClicked(MouseEvent mouseEvent) {
        nameField.setText(statiList.getSelectionModel().getSelectedItem().status);

        selectedItem = statiList.getSelectionModel().getSelectedItem();
    }

    public void neuClicked(ActionEvent actionEvent) {
        nameField.clear();

        selectedItem = null;
    }

    public void speichernClicked(ActionEvent actionEvent) {
        if(selectedItem != null){
            selectedItem.status = nameField.getText();

            statiList.refresh();

            statiList.setItems(liste);
        } else {
            if(!nameField.getText().isEmpty()){
                Status s = new Status();

                s.status = nameField.getText();
                s.nummer = num + 1;

                liste.add(s);
            }
            num++;
        }
        Status.writeFile("stati.csv", liste);
    }

    public void löschenClicked(ActionEvent actionEvent) {
        nameField.clear();
        num--;
        liste.remove(selectedItem);
        Status.writeFile("stati.csv", liste);
    }

    public void abbrechenClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) abbrechenButton.getScene().getWindow();
        stage.close();
    }
}
