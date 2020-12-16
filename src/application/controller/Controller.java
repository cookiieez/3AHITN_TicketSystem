package application.controller;

import application.MyFXMLLoader;
import application.model.Departments;
import application.model.Priority;
import application.model.Status;
import application.model.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Controller {
    public ListView<Ticket> ticketListView;
    public AnchorPane contentPain;
    // neue Felder
    /**
     * Filter müssen UND - Verknüpft werden!
     */
    ObservableList<Ticket> liste = FXCollections.observableArrayList();

    public TextField filterNameTextField; // filtern nach name des Todos
    ObservableList<Ticket> searchNameList = FXCollections.observableArrayList();

    public ComboBox<Status> filterStatusComboBox; // filtern nach Status
    ObservableList<Ticket> searchStatiList = FXCollections.observableArrayList();

    public ComboBox<Priority> filterPriorityComboBox; // filtern nach Priorität

    private ControllerTickets active = null;


    public void initialize() {

        filterStatusComboBox.setItems(Status.loadFile("stati.csv"));
        ticketListView.setItems(Ticket.loadFile("tickets.csv"));
        liste = Ticket.loadFile("tickets.csv");
    }

    public void editStartiClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/Stati.fxml", "Stati bearbeiten!");
    }

    public void editPrioClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/Priorities.fxml", "Prioritäten bearbeiten");
    }

    public void editUserClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/user.fxml", "User bearbeiten");
    }

    public void editDepClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/departments.fxml", "Departments bearbeiten");
    }

    public void ticketListViewClicked(MouseEvent mouseEvent) {

        MyFXMLLoader loader = new MyFXMLLoader();
        Parent root = loader.loadFXML("view/tickets.fxml");
        contentPain.getChildren().add(root);

        active = (ControllerTickets) loader.getController();
        active.setTicket(ticketListView.getSelectionModel().getSelectedItem());

    }

    public void searchReleased(KeyEvent keyEvent) {
        String searched = filterNameTextField.getText();

        searchNameList.clear();

        for(Ticket t : liste){
            if(t.name.contains(searched) || Integer.toString(t.id).contains(searched)){
                searchNameList.add(t);
            }
        }

        ticketListView.setItems(searchNameList);
    }

    public void statiSearch(MouseEvent mouseEvent) {
        int searched = filterStatusComboBox.getSelectionModel().getSelectedItem().nummer;

        searchStatiList.clear();

        for(Ticket t : liste){
            if(t.id == searched){
                searchStatiList.add(t);
            }
        }

        ticketListView.setItems(searchStatiList);
    }

    public void saveClicked(ActionEvent actionEvent) {
        //Wenn Ticket neu -> laden des Tickets und hinzufügen zur Liste
        //Datei aktualisieren
    }

    public void newClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        Parent root = loader.loadFXML("view/ticket.fxml");
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
        contentPain.getChildren().add(root);

        active = (ControllerTickets) loader.getController();
        active.setTicket(null);
    }

    public void delClicked(ActionEvent actionEvent) {
        /**
         * laden des Tickets
         * Entfernen aus Listview
         * Datei aktualisieren
         */
    }
}
