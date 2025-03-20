package esprit.javafxesprit.controllers.patient;

import esprit.javafxesprit.adhdform.ADHDForm;
import esprit.javafxesprit.utils.AlertMessage;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

    @FXML
    private Hyperlink login_forgetPassword;

    @FXML
    private AnchorPane forgot_password_form;

    @FXML
    private TextField forgot_email;

    @FXML
    private Button forgot_resetBtn;

    @FXML
    private Hyperlink forgot_backToLogin;

private Connection connect;
private PreparedStatement prepare;
private ResultSet result;

private AlertMessage alert = new AlertMessage();
// NOW, LETS CREATE OUR MAIN FORM

@FXML
void loginAccount(ActionEvent event) {
    if (login_patientID.getText().isEmpty() || login_password.getText().isEmpty()) {
        alert.errorMessage("Incorrect Patient ID/Password");
        return;
    }

    String sql = "SELECT * FROM patient WHERE patient_id = ? AND password = ? AND date_delete IS NULL";
    String checkStatus = "SELECT status FROM patient WHERE patient_id = ? AND password = ? AND status = 'Confirm'";
    String checkFirstConnection = "SELECT first_time FROM patient WHERE patient_id = ?";

    Connection connect = DataBase.connectDB();
    PreparedStatement prepare = null;
    ResultSet result = null;

    try {
        // Checking if the patient status is "Confirm"
        prepare = connect.prepareStatement(checkStatus);
        prepare.setString(1, login_patientID.getText());
        prepare.setString(2, login_password.getText());
        result = prepare.executeQuery();

        if (result.next()) {
            alert.errorMessage("Need the confirmation of the Admin!");
            return;
        }

        // Checking login credentials
        prepare = connect.prepareStatement(sql);
        prepare.setString(1, login_patientID.getText());
        prepare.setString(2, login_password.getText());
        result = prepare.executeQuery();

        if (result.next()) {
            Data.patient_id = Integer.parseInt(login_patientID.getText());
            alert.successMessage("Login Successfully!");

            // Checking first-time login
            prepare = connect.prepareStatement(checkFirstConnection);
            prepare.setString(1, login_patientID.getText());
            result = prepare.executeQuery();

            if (result.next()) {
                String status = result.getString("first_time");
                if ("Yes".equals(status)) {
                    Stage currentStage = (Stage) login_loginBtn.getScene().getWindow();
                    currentStage.close();
                    Stage stage = new Stage();
                    ADHDForm adhdForm = new ADHDForm();
                    Scene adhdScene = adhdForm.getScene();
                    stage.setScene(adhdScene);
                    stage.setTitle("ADHD Test Form");
                    stage.show();
Data.patient_id=Integer.parseInt(login_patientID.getText());
                    String updateStatusSQL = "UPDATE patient SET first_time = 'NO' WHERE patient_id = ?";
                    PreparedStatement updatePrepare = null;
                    try {
                        updatePrepare = connect.prepareStatement(updateStatusSQL);
                        updatePrepare.setString(1, login_patientID.getText());
                        updatePrepare.executeUpdate();
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }finally {
                        if (updatePrepare != null) {
                            try {
                                updatePrepare.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    } else {
                    // Redirect to Patient Main Form
                    Parent root = FXMLLoader.load(getClass().getResource("/esprit/javafxesprit/PatientInterface/PatientMainForm.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();

                    // Hide login form
                    ((Stage) login_loginBtn.getScene().getWindow()).close();
                }
            }
        } else {
            alert.errorMessage("Incorrect Patient ID/Password");
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (result != null) result.close();
            if (prepare != null) prepare.close();
            if (connect != null) connect.close();
        } catch (Exception ex) {
            ex.printStackTrace();
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

            Parent root = FXMLLoader.load(getClass().getResource("/esprit/javafxesprit/adminInterface/FXMLDocument.fxml"));
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

            Parent root = FXMLLoader.load(getClass().getResource("/esprit/javafxesprit/doctorInterface/DoctorPage.fxml"));
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

            Parent root = FXMLLoader.load(getClass().getResource("/esprit/javafxesprit/PatientInterface/PatientPage.fxml"));
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
    void forgotPassword() {
        login_form.setVisible(false);
        forgot_password_form.setVisible(true);
    }

    @FXML
    void resetPassword() {
        if (forgot_email.getText().isEmpty()) {
            alert.errorMessage("Please enter your phone number");
            return;
        }

        String checkEmail = "SELECT * FROM patient WHERE mobile_number = ?";
        connect = DataBase.connectDB();

        try {
            prepare = connect.prepareStatement(checkEmail);
            prepare.setString(1, forgot_email.getText());
            result = prepare.executeQuery();

            if (result.next()) {
                String newPassword = generateRandomPassword();
                String updatePassword = "UPDATE patient SET password = ? WHERE mobile_number = ?";
                prepare = connect.prepareStatement(updatePassword);
                prepare.setString(1, newPassword);
                prepare.setString(2, forgot_email.getText());
                prepare.executeUpdate();

                alert.successMessage("Password has been reset. New password: " + newPassword +
                        "\nPlease change it after logging in.");
                switchToLogin();
            } else {
                alert.errorMessage("Phone number not found in the system");
            }
        } catch (Exception e) {
            e.printStackTrace();
            alert.errorMessage("An error occurred while resetting password");
        } finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void switchToLogin() {
        forgot_password_form.setVisible(false);
        login_form.setVisible(true);
        forgot_email.clear();
    }

    private String generateRandomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }

@Override
public void initialize(URL location, ResourceBundle resources) {
    userList();
    if (forgot_password_form != null) {
        forgot_password_form.setVisible(false);
    }
}
}