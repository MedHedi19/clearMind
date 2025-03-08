package esprit.javafxesprit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Parent root = FXMLLoader.load(getClass().getResource("/esprit/javafxesprit/FXMLDocument.fxml"));
       Parent root = FXMLLoader.load(getClass().getResource("/esprit/javafxesprit/chat bot/chatBot.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 330, 550));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }}