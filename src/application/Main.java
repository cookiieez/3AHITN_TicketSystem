package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * @ToDo edit run configuration
     * !! FIRST TRY TO RUN YOU APPLICATION FROM THE LAUNCHER CLASS !!
     *
     *
     *
     * If this does not work try the following:
     * Open "Run" -> "Edit Configurations..."
     * Add this to your VM options (just copy & paste the following line):
     *
     */

    /**
     * main method
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        MyFXMLLoader.setPrimaryStage(primaryStage);

        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/scene.fxml", "Ticketsystem");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
