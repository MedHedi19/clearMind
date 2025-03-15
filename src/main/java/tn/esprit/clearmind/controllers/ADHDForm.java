package tn.esprit.clearmind.controllers;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import tn.esprit.clearmind.models.Data;
import tn.esprit.clearmind.models.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ADHDForm {
    private ComboBox<String> categoryBox;
    private VBox parentForm, adultForm;
    private VBox pregnancyBox, behaviorBox;


    public Scene getScene() {
        categoryBox = new ComboBox<>();
        categoryBox.getItems().addAll("Parent pour enfant (-16 ans)", "Adult");
        categoryBox.setPromptText("Select your category");
        categoryBox.setOnAction(e -> toggleForms());

        parentForm = createParentForm();
        adultForm = createAdultForm();

        VBox formContainer = new VBox(20);
        formContainer.setPadding(new Insets(20));
        formContainer.getChildren().addAll(categoryBox, parentForm);
        formContainer.setStyle("-fx-background-color: #f4f4f4; -fx-padding: 15px;");

        ScrollPane scrollPane = new ScrollPane(formContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        return new Scene(scrollPane, 450, 650);
    }

    private void toggleForms() {
        VBox formContainer = (VBox) categoryBox.getParent();
        formContainer.getChildren().removeIf(node -> node instanceof VBox);
        formContainer.getChildren().add(categoryBox.getValue().equals("Parent pour enfant (-16 ans)") ? parentForm : adultForm);
    }

    private VBox createParentForm() {
        VBox form = new VBox(10);
        form.getChildren().add(new Label("Parent ADHD Form"));

        // Section 1: Grossesse et Naissance
        pregnancyBox = new VBox(10);
        pregnancyBox.getChildren().add(new Label("1) Grossesse et Naissance"));
        pregnancyBox.getChildren().add(createRadioButtonQuestion("La grossesse a-t-elle été compliquée (problèmes de santé, hospitalisation) ?", "Oui", "Non"));
        pregnancyBox.getChildren().add(createRadioButtonQuestion("Votre enfant est-il né prématurément ou avec un faible poids ?", "Oui", "Non"));
        pregnancyBox.getChildren().add(createRadioButtonQuestion("A-t-il eu des complications médicales à la naissance ?", "Oui", "Non"));
        form.getChildren().add(pregnancyBox);

        // Section 2: Développement Précoce (0-3 ans)
        VBox developmentBox = new VBox(10);
        developmentBox.getChildren().add(new Label("2) Développement Précoce (0-3 ans)"));
        developmentBox.getChildren().add(createRadioButtonQuestion("A-t-il eu un retard dans l’apprentissage de la marche ou du langage ?", "Oui", "Non"));
        developmentBox.getChildren().add(createRadioButtonQuestion("Était-il souvent agité ou difficile à calmer bébé ?", "Oui", "Non"));
        developmentBox.getChildren().add(createRadioButtonQuestion("A-t-il eu des troubles du sommeil fréquents ?", "Oui", "Non"));
        form.getChildren().add(developmentBox);

        // Section 3: Environnement Familial
        VBox familyBox = new VBox(10);
        familyBox.getChildren().add(new Label("3) Environnement Familial"));
        familyBox.getChildren().add(createRadioButtonQuestion("Y a-t-il eu des tensions familiales importantes ou des événements stressants (séparation, déménagement) ?", "Oui", "Non"));
        familyBox.getChildren().add(createRadioButtonQuestion("Un membre proche de la famille a-t-il déjà présenté des signes de TDAH ou d’hyperactivité ?", "Oui", "Non"));
        familyBox.getChildren().add(createRadioButtonQuestion("Votre enfant passe-t-il plus de 2 heures par jour devant un écran ?", "Oui", "Non"));
        form.getChildren().add(familyBox);

        // Section 4: Comportement Actuel de l’Enfant
        behaviorBox = new VBox(10);
        behaviorBox.getChildren().add(new Label("4) Comportement Actuel de l’Enfant"));
        behaviorBox.getChildren().add(createRadioButtonQuestion("A-t-il du mal à rester concentré sur une tâche ?", "Oui", "Non"));
        behaviorBox.getChildren().add(createRadioButtonQuestion("Perd-il souvent ses affaires ou oublie-t-il des consignes ?", "Oui", "Non"));
        behaviorBox.getChildren().add(createRadioButtonQuestion("A-t-il des difficultés à attendre son tour ou interrompt-il souvent les autres ?", "Oui", "Non"));
        behaviorBox.getChildren().add(createRadioButtonQuestion("Est-il impulsif et agit-il souvent sans réfléchir ?", "Oui", "Non"));
        behaviorBox.getChildren().add(createRadioButtonQuestion("A-t-il des difficultés à gérer ses émotions (colères, frustration) ?", "Oui", "Non"));
        behaviorBox.getChildren().add(createRadioButtonQuestion("Présente-t-il des problèmes relationnels avec d’autres enfants ?", "Oui", "Non"));
        form.getChildren().add(behaviorBox);

        form.getChildren().add(createSubmitButton("parent"));
        return form;
    }

    private VBox createAdultForm() {
        VBox form = new VBox(10);
        form.getChildren().add(new Label("Adult ADHD Form"));

        // Section 1: Grossesse et Naissance
        pregnancyBox = new VBox(10);
        pregnancyBox.getChildren().add(new Label("1) Grossesse et Naissance"));
        pregnancyBox.getChildren().add(createRadioButtonQuestion("La grossesse a-t-elle été compliquée (problèmes de santé, hospitalisation) ?", "Oui", "Non"));
        pregnancyBox.getChildren().add(createRadioButtonQuestion("Votre enfant est-il né prématurément ou avec un faible poids ?", "Oui", "Non"));
        pregnancyBox.getChildren().add(createRadioButtonQuestion("A-t-il eu des complications médicales à la naissance ?", "Oui", "Non"));
        form.getChildren().add(pregnancyBox);

        // Section 2: Développement Précoce (0-3 ans)
        VBox developmentBox = new VBox(10);
        developmentBox.getChildren().add(new Label("2) Développement Précoce (0-3 ans)"));
        developmentBox.getChildren().add(createRadioButtonQuestion("A-t-il eu un retard dans l’apprentissage de la marche ou du langage ?", "Oui", "Non"));
        developmentBox.getChildren().add(createRadioButtonQuestion("Était-il souvent agité ou difficile à calmer bébé ?", "Oui", "Non"));
        developmentBox.getChildren().add(createRadioButtonQuestion("A-t-il eu des troubles du sommeil fréquents ?", "Oui", "Non"));
        form.getChildren().add(developmentBox);

        // Section 3: Environnement Familial
        VBox familyBox = new VBox(10);
        familyBox.getChildren().add(new Label("3) Environnement Familial"));
        familyBox.getChildren().add(createRadioButtonQuestion("Y a-t-il eu des tensions familiales importantes ou des événements stressants (séparation, déménagement) ?", "Oui", "Non"));
        familyBox.getChildren().add(createRadioButtonQuestion("Un membre proche de la famille a-t-il déjà présenté des signes de TDAH ou d’hyperactivité ?", "Oui", "Non"));
        familyBox.getChildren().add(createRadioButtonQuestion("Votre enfant passe-t-il plus de 2 heures par jour devant un écran ?", "Oui", "Non"));
        form.getChildren().add(familyBox);

        // Section 4: Comportement Actuel de l’Enfant
        behaviorBox = new VBox(10);
        behaviorBox.getChildren().add(new Label("4) Comportement Actuel de l’Enfant"));
        behaviorBox.getChildren().add(createRadioButtonQuestion("A-t-il du mal à rester concentré sur une tâche ?", "Oui", "Non"));
        behaviorBox.getChildren().add(createRadioButtonQuestion("Perd-il souvent ses affaires ou oublie-t-il des consignes ?", "Oui", "Non"));
        behaviorBox.getChildren().add(createRadioButtonQuestion("A-t-il des difficultés à attendre son tour ou interrompt-il souvent les autres ?", "Oui", "Non"));
        behaviorBox.getChildren().add(createRadioButtonQuestion("Est-il impulsif et agit-il souvent sans réfléchir ?", "Oui", "Non"));
        behaviorBox.getChildren().add(createRadioButtonQuestion("A-t-il des difficultés à gérer ses émotions (colères, frustration) ?", "Oui", "Non"));
        behaviorBox.getChildren().add(createRadioButtonQuestion("Présente-t-il des problèmes relationnels avec d’autres enfants ?", "Oui", "Non"));
        form.getChildren().add(behaviorBox);

        form.getChildren().add(createSubmitButton("adult"));
        return form;
    }

    private HBox createRadioButtonQuestion(String question, String option1, String option2) {
        HBox hBox = new HBox(10);
        Label questionLabel = new Label(question);
        RadioButton rbOption1 = new RadioButton(option1);
        RadioButton rbOption2 = new RadioButton(option2);
        ToggleGroup group = new ToggleGroup();
        rbOption1.setToggleGroup(group);
        rbOption2.setToggleGroup(group);
        hBox.getChildren().addAll(questionLabel, rbOption1, rbOption2);
        return hBox;
    }

    private Button createSubmitButton(String obj) {
        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-padding: 10px 15px;");
        submitButton.setOnAction(e -> handleSubmit(obj));
        return submitButton;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void handleSubmit(String obj) {
        String category = categoryBox.getSelectionModel().getSelectedItem();
        boolean pregnancyComplicated = getSelectedValue(pregnancyBox, 0);
        boolean prematureBirth = getSelectedValue(pregnancyBox, 1);
        boolean birthComplications = getSelectedValue(pregnancyBox, 2);
        boolean concentrationIssue = getSelectedValue(behaviorBox, 0);
        if(obj.equals("adult")){
            saveFormDataAdult(Data.patient_id,category, pregnancyComplicated, prematureBirth, birthComplications,
                    false, false, false, false, false, false, concentrationIssue, false, false, false, false);
        }else{
            saveFormDataParent(Data.patient_id,category, pregnancyComplicated, prematureBirth, birthComplications,
                    false, false, false, false, false, false, concentrationIssue, false, false, false, false);
        }
    }

    private boolean getSelectedValue(VBox box, int index) {
        HBox hBox = (HBox) box.getChildren().get(index + 1);
        ToggleGroup group = ((RadioButton) hBox.getChildren().get(1)).getToggleGroup();
        if (group.getSelectedToggle() != null) {
            return ((RadioButton) group.getSelectedToggle()).getText().equals("Oui");
        }
        return false;
    }
    private void saveFormDataParent(int userId, String category, boolean pregnancyComplicated, boolean prematureBirth, boolean birthComplications,
                                    boolean learningDelay, boolean hyperactive, boolean sleepIssues, boolean familyTensions,
                                    boolean familyADHD, boolean screenTime, boolean concentrationIssue, boolean losesItems,
                                    boolean impatience, boolean impulsive, boolean emotionalIssues) {

        String query = "INSERT INTO adhd_responses (user_id, category, pregnancy_complicated, premature_birth, birth_complications, " +
                "learning_delay, hyperactive, sleep_issues, family_tensions, family_adhd, screen_time, concentration_issue, " +
                "loses_items, impatience, impulsive, emotional_issues) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataBase.connectDB();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.setString(2, "Parent");
            stmt.setBoolean(3, pregnancyComplicated);
            stmt.setBoolean(4, prematureBirth);
            stmt.setBoolean(5, birthComplications);
            stmt.setBoolean(6, learningDelay);
            stmt.setBoolean(7, hyperactive);
            stmt.setBoolean(8, sleepIssues);
            stmt.setBoolean(9, familyTensions);
            stmt.setBoolean(10, familyADHD);
            stmt.setBoolean(11, screenTime);
            stmt.setBoolean(12, concentrationIssue);
            stmt.setBoolean(13, losesItems);
            stmt.setBoolean(14, impatience);
            stmt.setBoolean(15, impulsive);
            stmt.setBoolean(16, emotionalIssues);

            stmt.executeUpdate();
            System.out.println("Form data saved successfully.");

            showAlert("Success", "Data saved successfully!");
            redirectToNextScreen();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to save data.");
        }
    }

    private void redirectToNextScreen() {
        try {
            // Get the current stage
            Stage stage = (Stage) categoryBox.getScene().getWindow();

            // Call the ResultScreen to display the ADHD percentage result
            ResultScreen resultScreen = new ResultScreen();
            resultScreen.showResultScreen(stage, categoryBox.getSelectionModel().getSelectedItem());
            behaviorBox.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the result screen.");
        }
    }



    private void saveFormDataAdult(int userId, String category, boolean pregnancyComplicated, boolean prematureBirth, boolean birthComplications,
                                   boolean learningDelay, boolean hyperactive, boolean sleepIssues, boolean familyTensions,
                                   boolean familyADHD, boolean screenTime, boolean concentrationIssue, boolean losesItems,
                                   boolean impatience, boolean impulsive, boolean emotionalIssues) {

        String query = "INSERT INTO adhd_responses (user_id, category, pregnancy_complicated, premature_birth, birth_complications, " +
                "learning_delay, hyperactive, sleep_issues, family_tensions, family_adhd, screen_time, concentration_issue, " +
                "loses_items, impatience, impulsive, emotional_issues) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataBase.connectDB();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.setString(2, "Adult");
            stmt.setBoolean(3, pregnancyComplicated);
            stmt.setBoolean(4, prematureBirth);
            stmt.setBoolean(5, birthComplications);
            stmt.setBoolean(6, learningDelay);
            stmt.setBoolean(7, hyperactive);
            stmt.setBoolean(8, sleepIssues);
            stmt.setBoolean(9, familyTensions);
            stmt.setBoolean(10, familyADHD);
            stmt.setBoolean(11, screenTime);
            stmt.setBoolean(12, concentrationIssue);
            stmt.setBoolean(13, losesItems);
            stmt.setBoolean(14, impatience);
            stmt.setBoolean(15, impulsive);
            stmt.setBoolean(16, emotionalIssues);

            stmt.executeUpdate();
            System.out.println("Form data saved successfully.");

            showAlert("Success", "Data saved successfully!");
            redirectToNextScreen();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to save data.");
        }
    }
}