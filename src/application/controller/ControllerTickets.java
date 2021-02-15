package application.controller;

import application.model.Priority;
import application.model.Status;
import application.model.Ticket;
import application.model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class ControllerTickets {
    public TextArea commentarea;
    public TextField titlefield;
    public ComboBox<Status> ticketStati;
    public ComboBox<Priority> ticketPriority;
    public ComboBox<User> userComboBox;
    public ListView choosnUserListview;

    private Ticket ticket;

    public void setTicket(Ticket t) {
        if (t == null) {
            t = new Ticket();
        }

        titlefield.setText(t.name);
        commentarea.setText(t.desc);
        ticketStati.setItems(Status.loadFile());
        ticketPriority.setItems(Priority.loadList());
        userComboBox.setItems(User.loadFile());

        for (Status s : ticketStati.getItems()) {
            if (s.nummer == t.status.nummer) {
                ticketStati.getSelectionModel().select(s);
                break;
            }
        }

        for (Priority p : ticketPriority.getItems()) {
            if (p.priority == t.priority.priority) {
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


        return ticket;
    }


    public void addButtonClicked(ActionEvent actionEvent) {
        if(userComboBox.getSelectionModel().getSelectedItem() != null && !choosnUserListview.getItems().contains(userComboBox.getSelectionModel().getSelectedItem())){
            choosnUserListview.getItems().add(userComboBox.getSelectionModel().getSelectedItem());
        }
    }

    public void delButtonClicked(ActionEvent actionEvent) {
        if (choosnUserListview.getSelectionModel().getSelectedItem() != null) {
            choosnUserListview.getItems().remove(choosnUserListview.getSelectionModel().getSelectedItem());
        }
    }

    public ListView getChoosnUserListview() {
        return choosnUserListview;
    }
}
