package application.controller;

import application.model.Priority;
import application.model.Status;
import application.model.Ticket;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControllerTickets {
    public TextArea commentarea;
    public TextField titlefield;
    public ComboBox ticketStati;
    public ComboBox ticketPriority;

    public void setTicket(Ticket t) {
        titlefield.setText(t.name);
        commentarea.setText(t.desc);

        ticketStati.getSelectionModel().select(t.status);
        ticketStati.setItems(Status.loadFile("stati.csv"));

        ticketPriority.getSelectionModel().select(t.priority);

    }

    public void sendComment(ActionEvent actionEvent) {
    }
}
