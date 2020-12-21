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
    public ComboBox<Status> ticketStati;
    public ComboBox<Priority> ticketPriority;

    private Ticket ticket;

    public void setTicket(Ticket t) {
        if (t == null) {
            t = new Ticket();
        }

        titlefield.setText(t.name);
        commentarea.setText(t.desc);
        ticketStati.setItems(Status.loadFile("stati.csv"));
        //ticketPriority.setItems(Priority.readDocument("priorities.csv"););

        for (Status s : ticketStati.getItems()) {
            if (s.nummer == t.status.nummer) {
                ticketStati.getSelectionModel().select(s);
                break;
            }
        }

        for (Priority p : ticketPriority.getItems()) {
            if (p.priority.equals(t.priority.priority)) {
                ticketPriority.getSelectionModel().select(p);
            }
        }


        ticket = t;
    }

    public Ticket getTicket() {
        /**
         * aktualisieren der Ticket -Daten
         */

        ticket.name = titlefield.getText();
        ticket.desc = commentarea.getText();
        ticket.status = ticketStati.getValue();
        ticket.priority = ticketPriority.getValue();
        ticket.id = Ticket.getNum();
        //.....

        return ticket;
    }

    public void sendComment(ActionEvent actionEvent) {
    }
}
