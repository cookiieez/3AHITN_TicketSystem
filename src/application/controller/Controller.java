package application.controller;

import application.MyFXMLLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Controller {
    public ListView ticketListView;
    public AnchorPane contentPain;

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

        ControllerTickets controller = (ControllerTickets) loader.getController();


    }
}
