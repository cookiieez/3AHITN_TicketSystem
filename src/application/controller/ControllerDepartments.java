package application.controller;

import application.model.Departments;
import application.model.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;

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
        if(selectedItem != null){
            selectedItem.department = depField.getText();

            depList.refresh();

            depList.setItems(liste);
        } else {
            if(!depField.getText().isEmpty()){
                Departments d = new Departments();

                d.department = depField.getText();
                d.nummer = num + 1;

                liste.add(d);
            }
            num++;
        }
        writeFile();
    }

    public void löschenClicked(ActionEvent actionEvent) {
        depField.clear();
        num--;
        liste.remove(selectedItem);
        writeFile();
    }

    public void neuClicked(ActionEvent actionEvent) {
        depField.clear();

        selectedItem = null;
    }

    public void abbrechenClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) abbrechenButton.getScene().getWindow();
        stage.close();
    }

    private void writeFile() {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("departments.csv"));
            try {

                for (Departments d : liste) {
                    bw.write(d.nummer + ";" + d.department + "\n");
                }

                bw.flush();
            } finally {
                bw.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
