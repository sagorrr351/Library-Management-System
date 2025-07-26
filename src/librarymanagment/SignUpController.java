package librarymanagment;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import librarymanagment.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;

public class SignUpController {

    @FXML private TextField txtFullName;
    @FXML private TextField txtEmail;
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private PasswordField txtConfirmPassword;
    @FXML private Button btnSignUp;
    @FXML private Label lblError;
    @FXML private Hyperlink linkLogin;

    @FXML
    public void initialize() {
        lblError.setVisible(false); // Hide error label initially
    }

    private void showError(String message) {
        lblError.setStyle("-fx-text-fill: red;");
        lblError.setText(message);
        lblError.setVisible(true);
    }

    private void clearFields() {
        txtFullName.clear();
        txtEmail.clear();
        txtUsername.clear();
        txtPassword.clear();
        txtConfirmPassword.clear();
    }

    @FXML
    public void HandleSignup(ActionEvent event) {
        String fullName = txtFullName.getText().trim();
        String email = txtEmail.getText().trim();
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        if (fullName.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showError("Please fill in all fields.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showError("Passwords do not match.");
            return;
        }

        if (!email.contains("@")) {
            showError("Please enter a valid email.");
            return;
        }

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "INSERT INTO students (fullname, email, username, password) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, fullName);
            stmt.setString(2, email);
            stmt.setString(3, username);
            stmt.setString(4, password); // TODO: Hash password before saving!

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                lblError.setStyle("-fx-text-fill: green;");
                lblError.setText("Registration successful! Please login.");
                lblError.setVisible(true);
                clearFields();
            }
        } catch (SQLException ex) {
            if (ex.getMessage().toLowerCase().contains("duplicate")) {
                showError("Email or username already exists.");
            } else {
                showError("Database error: " + ex.getMessage());
            }
            ex.printStackTrace();
        }
    }
}
