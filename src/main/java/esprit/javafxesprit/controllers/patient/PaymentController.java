package esprit.javafxesprit.controllers.patient;

import esprit.javafxesprit.utils.AlertMessage;
import esprit.javafxesprit.utils.StripePaymentService;
import esprit.javafxesprit.models.Data;
import esprit.javafxesprit.models.DataBase;
import esprit.javafxesprit.models.PatientsData;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
public class PaymentController {

    @FXML
    private Label totalPriceLabel;

    @FXML
    private Button payButton;



    // Set your secret key here (DO NOT expose this key in a production app)
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private PatientsData patientData;
    private AlertMessage alert = new AlertMessage();

    @FXML
    public void initialize() {
        fetchPatientPrice();
    }

    private void fetchPatientPrice() {
        connect = DataBase.connectDB();
        String query = "SELECT price FROM patient WHERE patient_id = ?";

        try {
            prepare = connect.prepareStatement(query);
            prepare.setString(1, Integer.toString(Data.patient_id));
            result = prepare.executeQuery();

            if (result.next()) {
                totalPriceLabel.setText("Total Price: " + result.getString("price"));
            } else {
                totalPriceLabel.setText("Total Price: Not Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paymentButton() {
        String displayedPrice = totalPriceLabel.getText().replace("Total Price: ", "").trim();

        // Remove non-numeric characters (assuming "dt" is always at the end)
        if (displayedPrice.endsWith("dt")) {
            displayedPrice = displayedPrice.substring(0, displayedPrice.length() - 3).trim();
        }


        try {
            // Convertir la chaîne en double
            double amount = Double.parseDouble(displayedPrice);

            // Créer une session de paiement Stripe
            String paymentUrl = StripePaymentService.createCheckoutSession(amount);

            // Ouvrir l'URL de paiement dans un WebView
            WebView webView = new WebView();
            webView.getEngine().load(paymentUrl);

            // Afficher le WebView dans une nouvelle fenêtre
            Stage stage = new Stage();
            stage.setScene(new Scene(webView, 800, 600));
            stage.setTitle("Paiement Stripe");
            stage.show();
        } catch (NumberFormatException e) {
            alert.errorMessage("Erreur de saisie"); 
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            alert.errorMessage("Erreur de paiement");
            System.out.println(e.getMessage());
        }
    }
}
