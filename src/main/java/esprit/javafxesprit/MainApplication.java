package esprit.javafxesprit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/esprit/javafxesprit/adminInterface/FXMLDocument.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 330, 550));
        primaryStage.show();
        // Load sender chat window
       /** FXMLLoader senderLoader = new FXMLLoader(getClass().getResource("/esprit/javafxesprit/chatBox/chatBox.fxml"));
        Parent senderRoot = senderLoader.load();
        Stage senderStage = new Stage();
        senderStage.setTitle("Sender Chat");
        senderStage.setScene(new Scene(senderRoot, 330, 550));
        senderStage.show();

        // Load receiver chat window
        FXMLLoader receiverLoader = new FXMLLoader(getClass().getResource("/esprit/javafxesprit/chatBox/chatBox.fxml"));
        Parent receiverRoot = receiverLoader.load();
        Stage receiverStage = new Stage();
        receiverStage.setTitle("Receiver Chat");
        receiverStage.setScene(new Scene(receiverRoot, 330, 550));
        receiverStage.show();
*/


    }

    public static void main(String[] args) {
        launch(args);
    }}