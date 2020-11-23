package application.controller;

import application.model.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ControllerStati {
    public TextField nameField;
    public ListView<Status> statiList;
    ObservableList<Status> liste = FXCollections.observableArrayList();

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

                    }
                } finally {
                    br.close();

                }
            } catch (IOException io) {
                io.printStackTrace();
            }
            statiList.setItems(liste);
        }


    public void speichernClicked(ActionEvent actionEvent) {
    }

    public void löschenClicked(ActionEvent actionEvent) {
    }

    public void neuClicked(ActionEvent actionEvent) {
    }
}
