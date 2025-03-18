package esprit.javafxesprit.controllers.doctor;

import esprit.javafxesprit.models.DoctorData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DoctorCardController implements Initializable {

    @FXML
    private Circle doctor_circle;

    @FXML
    private Label doctor_id;

    @FXML
    private Label doctor_fullName;

    @FXML
    private Label doctor_specialization;

    @FXML
    private Label doctor_email;

    private Image image;

    public void setData(DoctorData dData) {

        if (dData.getImage() != null) {
            image = new Image("File:" + dData.getImage(), 52, 52, false, true);
            doctor_circle.setFill(new ImagePattern(image));
        }

        doctor_id.setText(dData.getDoctorID());
        doctor_fullName.setText(dData.getFullName());
        doctor_specialization.setText(dData.getSpecialized());
        doctor_email.setText(dData.getEmail());
    }
    public void sendMessage(){
        try {
            // Open Sender Chat Window
            FXMLLoader senderLoader = new FXMLLoader(getClass().getResource("/esprit/javafxesprit/chatBox/chatBox.fxml"));
            Parent senderRoot = senderLoader.load();
            Stage senderStage = new Stage();
            senderStage.setTitle("Client");
            senderStage.setScene(new Scene(senderRoot, 330, 550));
            senderStage.show();

            // Open Receiver Chat Window
            FXMLLoader receiverLoader = new FXMLLoader(getClass().getResource("/esprit/javafxesprit/chatBox/chatBox.fxml"));
            Parent receiverRoot = receiverLoader.load();
            Stage receiverStage = new Stage();
            receiverStage.setTitle("Clear Mind's team");
            receiverStage.setScene(new Scene(receiverRoot, 330, 550));
            receiverStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
