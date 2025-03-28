package esprit.javafxesprit.controllers.admin;



import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private AnchorPane login_form;

    @FXML
    private TextField login_username;

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
    private TextField register_email;

    @FXML
    private TextField register_username;

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

    public void loginAccount() {

        if (login_username.getText().isEmpty()
                || login_password.getText().isEmpty()) {
            alert.errorMessage("Incorrect Username/Password");
        } else {

            String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

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

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, login_username.getText());
                prepare.setString(2, login_password.getText());
                result = prepare.executeQuery();

                if (result.next()) {
                    // TO GET THE USERNAME
                    Data.admin_username = login_username.getText();
                    Data.admin_id = Integer.parseInt(result.getString("admin_id"));

                    // IF CORRECT USERNAME AND PASSWORD
                    alert.successMessage("Login Successfully!");

                    // LINK MAIN FORM FOR ADMIN
                    Parent root = FXMLLoader.load(getClass().getResource("/esprit/javafxesprit/adminInterface/AdminMainForm.fxml"));
                    Stage stage = new Stage();

                    stage.setTitle("Hospital Management System | Admin Portal");
                    stage.setScene(new Scene(root));
                    stage.show();

                    // TO HIDE YOUR ADMIN PAGE (LOGIN FORM)
                    login_loginBtn.getScene().getWindow().hide();
                } else {
                    // IF WRONG USERNAME OR PASSWORD
                    alert.errorMessage("Incorrect Username/Password");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void loginShowPassword() {
        if (login_checkBox.isSelected()) {
            login_showPassword.setText(login_password.getText());
            login_showPassword.setVisible(true);
            login_password.setVisible(false);
        } else {
            login_password.setText(login_showPassword.getText());
            login_showPassword.setVisible(false);
            login_password.setVisible(true);
        }
    }

    public void registerAccount() {

        if (register_email.getText().isEmpty()
                || register_username.getText().isEmpty()
                || register_password.getText().isEmpty()) {

            alert.errorMessage("Please fill all blank fields");
        } else {

            String checkUsername = "SELECT * FROM admin WHERE username = '"
                    + register_username.getText() + "'";

            connect = DataBase.connectDB();

            try {

                if (!register_showPassword.isVisible()) {
                    if (!register_showPassword.getText().equals(register_password.getText())) {
                        register_showPassword.setText(register_password.getText());
                    }
                } else {
                    if (!register_showPassword.getText().equals(register_password.getText())) {
                        register_password.setText(register_showPassword.getText());
                    }
                }

                prepare = connect.prepareStatement(checkUsername);
                result = prepare.executeQuery();

                if (result.next()) {
                    alert.errorMessage(register_username.getText() + " is already exist!");
                } else if (register_password.getText().length() < 8) { // CHECK IF THE CHARACTERS OF THE PASSWORD IS LESS THAN TO 8
                    alert.errorMessage("Invalid Password, at least 8 characters needed");
                } else {
                    String insertData = "INSERT INTO admin (email, username, password, date) VALUES(?,?,?,?)";

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, register_email.getText());
                    prepare.setString(2, register_username.getText());
                    prepare.setString(3, register_password.getText());
                    prepare.setString(4, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.successMessage("Registered Successfully!");
                    registerClear();

                    login_form.setVisible(true);
                    register_form.setVisible(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void registerClear() {
        register_email.clear();
        register_username.clear();
        register_password.clear();
        register_showPassword.clear();
    }

    public void registerShowPassword() {

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

    public void userList() {

        List<String> listU = new ArrayList<>();

        for (String data : Users.user) {
            listU.add(data);
        }

        ObservableList listData = FXCollections.observableList(listU);
        login_user.setItems(listData);
    }

    public void switchPage() {

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

                Parent root = FXMLLoader.load(getClass().getResource("/esprit/javafxesprit/doctorInterface/DoctorForm.fxml"));
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


    public void switchForm(ActionEvent event) {

        if (event.getSource() == login_registerHere) {
            // REGISTRATION FORM WILL SHOW
            login_form.setVisible(false);
            register_form.setVisible(true);
        } else if (event.getSource() == register_loginHere) {
            // LOGIN FORM WILL SHOW
            login_form.setVisible(true);
            register_form.setVisible(false);
        }

    }

    public void forgotPassword() {
        // Hide login form and show forgot password form
        login_form.setVisible(false);
        forgot_password_form.setVisible(true);
    }

    public void resetPassword() {
        if (forgot_email.getText().isEmpty()) {
            alert.errorMessage("Please enter your email address");
            return;
        }

        String checkEmail = "SELECT * FROM admin WHERE email = ?";
        connect = DataBase.connectDB();

        try {
            prepare = connect.prepareStatement(checkEmail);
            prepare.setString(1, forgot_email.getText());
            result = prepare.executeQuery();

            if (result.next()) {

                String newPassword = generateRandomPassword();
                String updatePassword = "UPDATE admin SET password = ? WHERE email = ?";

                prepare = connect.prepareStatement(updatePassword);
                prepare.setString(1, newPassword);
                prepare.setString(2, forgot_email.getText());
                prepare.executeUpdate();

                alert.successMessage("Password has been reset. New password: " + newPassword +
                        "\nPlease change it after logging in.");

                switchToLogin();
            } else {
                alert.errorMessage("Email not found in the system");
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

    public void switchToLogin() {
        forgot_password_form.setVisible(false);
        login_form.setVisible(true);
        forgot_email.clear();
    }

    private String generateRandomPassword() {
        // Simple random password generator
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }

        return password.toString();
    }
    public void initialize(URL url, ResourceBundle rb) {
        userList();
        // Ensure forgot password form is hidden initially
        if (forgot_password_form != null) {
            forgot_password_form.setVisible(false);
        }
    }

}
