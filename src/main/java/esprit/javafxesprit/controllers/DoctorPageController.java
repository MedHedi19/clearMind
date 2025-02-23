package esprit.javafxesprit.controllers;

import esprit.javafxesprit.models.Data;
import esprit.javafxesprit.models.DataBase;
import esprit.javafxesprit.models.Users;
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

public class DoctorPageController  implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private AnchorPane login_form;

    @FXML
    private TextField login_doctorID;

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

    @FXML
    private Hyperlink login_registerHere;

    @FXML
    private AnchorPane register_form;

    @FXML
    private TextField register_fullName;

    @FXML
    private TextField register_email;

    @FXML
    private TextField register_doctorID;

    @FXML
    private PasswordField register_password;

    @FXML
    private TextField register_showPassword;

    @FXML
    private CheckBox register_checkBox;

    @FXML
    private Button register_signupBtn;

    @FXML
    private Hyperlink register_loginHere;

    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

    @FXML
    void loginAccount() {

        if (login_doctorID.getText().isEmpty()
                || login_password.getText().isEmpty()) {
            alert.errorMessage("Incorrect Doctor ID/Password");
        } else {

            String sql = "SELECT * FROM doctor WHERE doctor_id = ? AND password = ? AND delete_date IS NULL";
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
                String checkStatus = "SELECT status FROM doctor WHERE doctor_id = '"
                        + login_doctorID.getText() + "' AND password = '"
                        + login_password.getText() + "' AND status = 'Confirm'";

                prepare = connect.prepareStatement(checkStatus);
                result = prepare.executeQuery();

                if (result.next()) {

                    alert.errorMessage("Need the confimation of the Admin!");
                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, login_doctorID.getText());
                    prepare.setString(2, login_password.getText());

                    result = prepare.executeQuery();

                    if (result.next()) {

                        Data.doctor_id = result.getString("doctor_id");
                        Data.doctor_name = result.getString("full_name");

                        alert.successMessage("Login Successfully!");

                        // LINK YOUR DOCTOR MAIN FORM
                        Parent root = FXMLLoader.load(getClass().getResource("/esprit/javafxesprit/DoctorMainForm.fxml"));
                        Stage stage = new Stage();

                        stage.setTitle("Hospital Management System | Doctor Main Form");
                        stage.setScene(new Scene(root));
                        stage.show();

                        // TO HIDE YOUR DOCTOR PAGE
                        login_loginBtn.getScene().getWindow().hide();

                    } else {
                        alert.errorMessage("Incorrect Doctor ID/Password");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    void loginShowPassword() {

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

    @FXML
    void registerAccount() {


    }

    @FXML
    void registerShowPassword() {

        if (register_checkBox.isSelected()) {
            register_showPassword.setText(register_password.getText());
            register_showPassword.setVisible(true);
            register_password.setVisible(false);
        } else {
            register_password.setText(register_showPassword.getText());
            register_showPassword.setVisible(false);
            register_password.setVisible(true);
        }

    }

    public void registerDoctorID() {
        String doctorID = "DID-";
        int tempID = 0;
        String sql = "SELECT MAX(id) FROM doctor";

        connect = DataBase.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempID = result.getInt("MAX(id)");
            }

            if (tempID == 0) {
                tempID += 1;
                doctorID += tempID;
            } else {
                doctorID += (tempID + 1);
            }

            register_doctorID.setText(doctorID);
            register_doctorID.setDisable(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void userList() {

        List<String> listU = new ArrayList<>();

        for (String data : Users.user) {
            listU.add(data);
        }

        ObservableList listData = FXCollections.observableList(listU);
        login_user.setItems(listData);
        System.out.println(login_user); // Debugging

    }

    public void switchPage() {

        if (login_user.getSelectionModel().getSelectedItem() == "Admin Portal") {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("/esprit/javafxesprit/FXMLDocument.fxml"));
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

                Parent root = FXMLLoader.load(getClass().getResource("/esprit/javafxesprit/DoctorForm.fxml"));
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

                Parent root = FXMLLoader.load(getClass().getResource("/esprit/javafxesprit/PatientPage.fxml"));
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

    @FXML
    void switchForm(ActionEvent event) {

        if (event.getSource() == register_loginHere) {
            login_form.setVisible(true);
            register_form.setVisible(false);
        } else if (event.getSource() == login_registerHere) {
            login_form.setVisible(false);
            register_form.setVisible(true);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registerDoctorID();
        userList();
    }

}