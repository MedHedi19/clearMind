package tn.esprit.clearmind.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tn.esprit.clearmind.models.Data;
import tn.esprit.clearmind.models.DataBase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import tn.esprit.clearmind.models.Users;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PatientPageController implements Initializable {

    @FXML
    private AnchorPane login_form;
    @FXML
    private AnchorPane register_form;
    @FXML
    private TextField login_patientID;
    @FXML
    private PasswordField login_password;
    @FXML
    private CheckBox login_checkBox;
    @FXML
    private TextField register_email;
    @FXML
    private TextField register_patientID;
    @FXML
    private PasswordField register_password;
    @FXML
    private CheckBox register_checkBox;
    @FXML
    private ComboBox<?> login_user;

    private Connection connect;
    private PreparedStatement prepare;

    @FXML
    void loginAccount(ActionEvent event) {
        if (login_patientID.getText().isEmpty() || login_password.getText().isEmpty()) {
            showAlert("Error", "Incorrect Patient ID/Password");
            return;
        }

        String sql = "SELECT * FROM patient WHERE patient_id = ? AND password = ? AND date_delete IS NULL";
        connect = DataBase.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, login_patientID.getText());
            prepare.setString(2, login_password.getText());
            if (prepare.executeQuery().next()) {
                Data.patient_id = Integer.parseInt(login_patientID.getText());

                showAlert("Success", "Login Successfully!");
                // LINK YOUR PATIENT MAIN FORM
                    Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/clearmind/PatientMainForm.fxml"));
                    Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

                // TO HIDE YOUR LOGIN FORM
                login_patientID.getScene().getWindow().hide();
            } else {
                showAlert("Error", "Incorrect Patient ID/Password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void registerAccount(ActionEvent event) {
        if (register_patientID.getText().isEmpty() || register_email.getText().isEmpty() || register_password.getText().isEmpty()) {
            showAlert("Error", "All fields are required");
            return;
        }

        String sql = "INSERT INTO patient (patient_id, email, password, status, date) VALUES (?, ?, ?, ?, NOW())";
        connect = DataBase.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, register_patientID.getText());
            prepare.setString(2, register_email.getText());
            prepare.setString(3, register_password.getText());
            prepare.setString(4, "Active"); // Set a default status, e.g., 'active'
            prepare.executeUpdate();
            Data.patient_id = Integer.parseInt(register_patientID.getText());

            showAlert("Success", "Account registered successfully!");
            Stage stage = new Stage();
            ADHDForm adhdForm = new ADHDForm();
            Scene adhdScene = adhdForm.getScene();
            stage.setScene(adhdScene);
            stage.setTitle("ADHD Test Form");
            stage.show();
            register_patientID.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Registration failed");
        }
    }

    @FXML
    void switchForm(ActionEvent event) {
        boolean loginVisible = login_form.isVisible();
        login_form.setVisible(!loginVisible);
        register_form.setVisible(loginVisible);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userList();
    }

    @FXML
    void switchPage(ActionEvent event) {

        if (login_user.getSelectionModel().getSelectedItem() == "Admin Portal") {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/clearmind/FXMLDocument.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Hospital Management System");

                stage.setMinWidth(340);
                stage.setMinHeight(580);

                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (login_user.getSelectionModel().getSelectedItem() == "Doctor Portal") {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/clearmind/DoctorPage.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Hospital Management System");

                stage.setMinWidth(340);
                stage.setMinHeight(580);

                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (login_user.getSelectionModel().getSelectedItem() == "Patient Portal") {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/clearmind/PatientPage.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Hospital Management System");

                stage.setMinWidth(340);
                stage.setMinHeight(580);

                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        login_user.getScene().getWindow().hide();

    }

    public void loginShowPassword(ActionEvent actionEvent) {
    }

    public void registerShowPassword(ActionEvent actionEvent) {
    }

    public void userList() {

        List<String> listU = new ArrayList<>();

        for (String data : Users.user) {
            listU.add(data);
        }

        ObservableList listData = FXCollections.observableList(listU);
        login_user.setItems(listData);
    }
}
