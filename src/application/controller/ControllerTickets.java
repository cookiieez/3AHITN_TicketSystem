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

    private Ticket ticket;

    public void setTicket(Ticket t) {
        titlefield.setText(t.name);
        commentarea.setText(t.desc);
        ticketStati.setItems(Status.loadFile("stati.csv"));
        //ticketPriority.setItems(Priority.readDocument("priorities.csv"););

        for (Status s : ticketStati.getItems()){
            if(s.nummer == t.status.nummer){
                ticketStati.getSelectionModel().select(s);
                break;
            }
        }



        ticketPriority.getSelectionModel().select(t.priority.priority);

    }

    public Ticket getTicket(){
        /**
         * aktualisieren der Ticket -Daten
         */

        ticket.name = titlefield.getText();
        //.....

        return ticket;
    }

    public void sendComment(ActionEvent actionEvent) {
    }
}
