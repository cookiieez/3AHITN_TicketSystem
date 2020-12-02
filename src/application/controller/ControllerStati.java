package application.controller;

import application.model.Status;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;


public class ControllerStati {
    public TextField nameField;
    public Button abbrechenButton;
    private int num = 0;

    public ListView<Status> statiList;
    ObservableList<Status> liste = FXCollections.observableArrayList();

    private Status selectedItem = null;

    public void initialize(){loadFile();}

    public void loadFile() {
        String zeile = null;
        BufferedReader br = null;


            try {
                br = new BufferedReader(new FileReader("stati.csv"));

                try {
                    while ((zeile = br.readLine()) != null) {
                        String[] split = zeile.split(";");

                        Status s = new Status();
                        s.nummer = Integer.parseInt(split[0]);
                        s.status = split[1];

                        liste.add(s);
                        num++;

                    }
                } finally {
                    br.close();
                }
            } catch (IOException io) {
                io.printStackTrace();
            }
            statiList.setItems(liste);
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
        writeFile();
    }

    public void löschenClicked(ActionEvent actionEvent) {
        nameField.clear();
        num--;
        liste.remove(selectedItem);
        writeFile();
    }


    private void writeFile() {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("stati.csv"));
            try {

                for (Status s : liste) {
                    bw.write(s.nummer + ";" + s.status + "\n");
                }

                bw.flush();
            } finally {
                bw.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void abbrechenClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) abbrechenButton.getScene().getWindow();
        stage.close();
    }
}
