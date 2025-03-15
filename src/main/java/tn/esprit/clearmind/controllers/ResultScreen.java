package tn.esprit.clearmind.controllers;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.clearmind.models.Data;
import tn.esprit.clearmind.models.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ResultScreen {
    private int totalQuestions = 15; // Total ADHD-related questions
    private double adhdPercentage = 0.0;

    public void showResultScreen(Stage stage, String category) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));

        Label titleLabel = new Label("ADHD Risk Assessment");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        ProgressBar progressBar = new ProgressBar(0);
        Label resultLabel = new Label("Calculating...");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            stage.close();
            redirectToMainScreen();
        });

        root.getChildren().addAll(titleLabel, progressBar, resultLabel, closeButton);

        // Fetch user answers and calculate risk
        calculateAdhdRisk(progressBar, resultLabel, category);

        Scene scene = new Scene(root, 400, 250);
        stage.setScene(scene);
        stage.setTitle("ADHD Assessment Result");
        stage.show();
    }

    private void calculateAdhdRisk(ProgressBar progressBar, Label resultLabel, String category) {
        String query = "SELECT pregnancy_complicated, premature_birth, birth_complications, learning_delay, " +
                "hyperactive, sleep_issues, family_tensions, family_adhd, screen_time, concentration_issue, " +
                "loses_items, impatience, impulsive, emotional_issues " +
                "FROM adhd_responses WHERE user_id = ? ORDER BY id DESC LIMIT 1";

        try (Connection conn = DataBase.connectDB();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, String.valueOf(Data.patient_id));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int yesCount = 0;

                // Iterate over all ADHD-related boolean columns
                for (int i = 1; i <= 14; i++) { // 14 columns in the query
                    if (rs.getBoolean(i)) {
                        yesCount++;
                    }
                }

                adhdPercentage = ((double) yesCount / 14) * 100; // Correct percentage calculation
                progressBar.setProgress(adhdPercentage / 100);

                String resultText = adhdPercentage >= 50 ? "High Risk of ADHD" : "Low Risk of ADHD";
                resultLabel.setText("ADHD Probability: " + String.format("%.2f", adhdPercentage) + "%\n" + resultText);
                resultLabel.setStyle(adhdPercentage >= 50 ? "-fx-text-fill: red;" : "-fx-text-fill: green;");
            } else {
                resultLabel.setText("No data found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultLabel.setText("Error calculating ADHD risk.");
        }
    }

    private void redirectToMainScreen() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/clearmind/PatientMainForm.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
