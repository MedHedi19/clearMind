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
        String query = "SELECT price, status_pay FROM patient WHERE patient_id = ?";

        try {
            prepare = connect.prepareStatement(query);
            prepare.setString(1, Integer.toString(Data.patient_id));
            result = prepare.executeQuery();

            if (result.next()) {
                String statusPay = result.getString("status_pay");
                String price = result.getString("price");

                if ("Paid".equalsIgnoreCase(statusPay)) {
                    totalPriceLabel.setText("You have already completed your payment.");
                    payButton.setDisable(true); // Disable the button
                } else if (price == null || price.trim().isEmpty()) {
                    totalPriceLabel.setText("Your invoice is currently being processed by Clear Mind's team. Please check back later for the final amount.");
                    payButton.setDisable(true); // Disable the button
                } else {
                    // Clean the price string by removing any "dt" or non-numeric parts
                    String cleanPrice = price.replaceAll("[^0-9.]", ""); // Keep only numbers and dots
                    totalPriceLabel.setText("Total Price: " + cleanPrice + " dt");
                    payButton.setDisable(false); // Enable the button
                }
            } else {
                totalPriceLabel.setText("Patient record not found.");
                payButton.setDisable(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paymentButton() {
        String displayedPrice = totalPriceLabel.getText().replace("Total Price: ", "").replace("dt", "").trim();

        try {
            double amount = Double.parseDouble(displayedPrice);
            String paymentUrl = StripePaymentService.createCheckoutSession(amount, this);

            WebView webView = new WebView();
            webView.getEngine().load(paymentUrl);

            Stage stage = new Stage();
            stage.setScene(new Scene(webView, 800, 600));
            stage.setTitle("Paiement Stripe");

            // Add listener for when the payment window closes
            stage.setOnHidden(event -> {
                // Here you should verify payment success with Stripe
                // For this example, we'll assume success when window closes
                // In production, use Stripe webhooks or API verification
                updatePaymentStatus();
            });

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

    private void updatePaymentStatus() {
        connect = DataBase.connectDB();
        String query = "UPDATE patient SET status_pay = 'Paid' WHERE patient_id = ?";

        try {
            prepare = connect.prepareStatement(query);
            prepare.setString(1, Integer.toString(Data.patient_id));
            int rowsAffected = prepare.executeUpdate();

            if (rowsAffected > 0) {
                alert.successMessage("Payment status updated successfully");
                fetchPatientPrice(); // Refresh the UI
            }
        } catch (Exception e) {
            e.printStackTrace();
            alert.errorMessage("Failed to update payment status");
        } finally {
            try {
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
