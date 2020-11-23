package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Controller {

    public void CommentsPressed(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("application.fxml/comments.application.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {

        }
    }

    public void DepartmentsPressed(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("application.fxml/departments.application.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {

        }
    }

    public void PrioritiesPressed(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("application.fxml/Priorities.application.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {

        }
    }

    public void StatiPressed(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("application.fxml/Stati.application.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {

        }
    }

    public void TicketsPressed(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("application.fxml/tickets.application.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {

        }
    }

    public void UserPressed(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("application.fxml/user.application.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {

        }
    }
}
