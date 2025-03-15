package tn.esprit.clearmind.controllers;

import tn.esprit.clearmind.models.Data;
import tn.esprit.clearmind.models.DataBase;
import tn.esprit.clearmind.models.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class PatientPageController implements Initializable {


@FXML
private AnchorPane main_form;

@FXML
private AnchorPane login_form;

@FXML
private TextField login_patientID;

@FXML
private PasswordField login_password;

@FXML
private TextField login_showPassword;

@FXML
private CheckBox login_checkBox;

@FXML
private Button login_loginBtn;

@FXML
private ComboBox<?> login_user;

private Connection connect;
private PreparedStatement prepare;
private ResultSet result;

private AlertMessage alert = new AlertMessage();
// NOW, LETS CREATE OUR MAIN FORM

@FXML
void loginAccount(ActionEvent event) {

    if (login_patientID.getText().isEmpty()
            || login_password.getText().isEmpty()) {
        alert.errorMessage("Incorrect Patient ID/Password");
    } else {

        String sql = "SELECT * FROM patient WHERE patient_id = ? AND password = ? AND date_delete IS NULL";
        connect = DataBase.connectDB();

        try {

            if (!login_showPassword.isVisible()) {
                if (!login_showPassword.getText().equals(login_password.getText())) {
                    login_showPassword.setText(login_password.getText());
                }
            } else {
                if (!login_showPassword.getText().equals(login_password.getText())) {
                    login_password.setText(login_showPassword.getText());
                }
            }

            // CHECK IF THE STATUS OF THE DOCTOR IS CONFIRM
            String checkStatus = "SELECT status FROM patient WHERE patient_id = '"
                    + login_patientID.getText() + "' AND password = '"
                    + login_password.getText() + "' AND status = 'Confirm'";

            prepare = connect.prepareStatement(checkStatus);
            result = prepare.executeQuery();

            if (result.next()) {

                if (!login_showPassword.isVisible()) {
                    if (!login_showPassword.getText().equals(login_password.getText())) {
                        login_showPassword.setText(login_password.getText());
                    }
                } else {
                    if (!login_showPassword.getText().equals(login_password.getText())) {
                        login_password.setText(login_showPassword.getText());
                    }
                }

                alert.errorMessage("Need the confimation of the Admin!");
            } else {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, login_patientID.getText());
                prepare.setString(2, login_password.getText());

                result = prepare.executeQuery();

                if (result.next()) {
                    Data.patient_id = Integer.parseInt(login_patientID.getText());

                    alert.successMessage("Login Successfully!");
                    // LINK YOUR PATIENT MAIN FORM
//                    Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/clearmind/PatientMainForm.fxml"));
//                    Stage stage = new Stage();
//
//                    stage.setScene(new Scene(root));
//                    stage.show();
//
//                    // TO HIDE YOUR LOGIN FORM
//                    login_loginBtn.getScene().getWindow().hide();


                    Stage stage = new Stage();
                    ADHDForm adhdForm = new ADHDForm();
                    Scene adhdScene = adhdForm.getScene();
                    stage.setScene(adhdScene);
                    stage.setTitle("ADHD Test Form");
                    stage.show();
                    login_loginBtn.getScene().getWindow().hide();
                } else {
                    alert.errorMessage("Incorrect Patient ID/Password");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

@FXML
void loginShowPassword(ActionEvent event) {

    if (login_checkBox.isSelected()) {
        login_showPassword.setText(login_password.getText());
        login_password.setVisible(false);
        login_showPassword.setVisible(true);
    } else {
        login_password.setText(login_showPassword.getText());
        login_password.setVisible(true);
        login_showPassword.setVisible(false);
    }

}

public void userList() {

    List<String> listU = new ArrayList<>();

    for (String data : Users.user) {
        listU.add(data);
    }

    ObservableList listData = FXCollections.observableList(listU);
    login_user.setItems(listData);
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

@Override
public void initialize(URL location, ResourceBundle resources) {
    userList();
}

}