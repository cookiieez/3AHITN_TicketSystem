package application.controller;

import application.MyFXMLLoader;
import application.model.Priority;
import application.model.Status;
import application.model.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

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
    ObservableList<Ticket> searchPrioList = FXCollections.observableArrayList();

    private ControllerTickets active = null;

    Ticket selcetedItem = null;

    ArrayList<Ticket> backup;

    public void initialize() {
        Status s = new Status();
        s.status = "Filter wählen";
        s.nummer = -1;
        filterStatusComboBox.getItems().add(s);
        filterStatusComboBox.getItems().addAll(Status.loadFile());

        Priority p = new Priority();
        p.priority = "-1";
        p.desc = "Filter wählen";
        filterPriorityComboBox.getItems().addAll(p);
        filterPriorityComboBox.getItems().addAll(Priority.loadList());

        ticketListView.setItems(Ticket.loadFile("tickets.csv"));
        liste = ticketListView.getItems();

        backup = new ArrayList<>(liste);
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

        selcetedItem = ticketListView.getSelectionModel().getSelectedItem();

    }

    public void searchReleased(KeyEvent keyEvent) {
        filter();
    }

    public void statiSearch(ActionEvent mouseEvent) {
        filter();
    }


    public void saveClicked(ActionEvent actionEvent) {
        //Wenn Ticket neu -> laden des Tickets und hinzufügen zur Liste
        //Datei aktualisieren
        Ticket newTicket = active.getTicket();

        if (selcetedItem == null) {
            liste.add(newTicket);
        }

        ticketListView.refresh();

        Ticket.writeFile("tickets.csv", liste);
    }

    public void newClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        Parent root = loader.loadFXML("view/tickets.fxml");
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
        contentPain.getChildren().add(root);

        Ticket t = new Ticket();
        active = (ControllerTickets) loader.getController();

        active.setTicket(t);

        selcetedItem = null;
    }

    public void delClicked(ActionEvent actionEvent) {
        /**
         * laden des Tickets
         * Entfernen aus Listview
         * Datei aktualisieren
         */
        Ticket delTicket = active.getTicket();
        liste.remove(delTicket);

        ticketListView.refresh();

        Ticket.writeFile("tickets.csv", liste);

    }


    public void prioSearch(ActionEvent actionEvent) {
        filter();
    }

    private void filter() {
        ObservableList<Ticket> filteredList = FXCollections.observableArrayList(backup);

        if (filterNameTextField.getText().length() > 0) {
            filteredList.removeIf(t -> !t.name.toLowerCase().contains(filterNameTextField.getText().toLowerCase()));
        }

        if (filterPriorityComboBox.getValue() != null && !filterPriorityComboBox.getValue().priority.equals("-1")) {
            filteredList.removeIf(t -> !t.priority.priority.equals(filterPriorityComboBox.getValue().priority));
        }

        if (filterStatusComboBox.getValue() != null && filterStatusComboBox.getValue().nummer != -1) {
            filteredList.removeIf(t -> t.status.nummer != filterStatusComboBox.getValue().nummer);
        }

        ticketListView.setItems(filteredList);
    }
}
