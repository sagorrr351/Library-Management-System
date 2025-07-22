package librarymanagment;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Button btnLogin;
    @FXML private Label lblError;

    @FXML
    private void initialize() {
       
    }

   @FXML
public void HandleLogin() {
    String usernameOrEmail = txtUsername.getText().trim();
    String password = txtPassword.getText();

    if (usernameOrEmail.isEmpty() || password.isEmpty()) {
        showError("Please enter username/email and password.");
        return;
    }

    String sql = "SELECT * FROM students WHERE (username = ? OR email = ?) AND password = ?";

    try (Connection conn = DBUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, usernameOrEmail);
        stmt.setString(2, usernameOrEmail);
        stmt.setString(3, password);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            lblError.setStyle("-fx-text-fill: green;");
            lblError.setText("Login successful! Welcome, " + rs.getString("fullname"));
            lblError.setVisible(true);
            // TODO: Navigate to next scene
        } else {
            showError("Invalid username/email or password.");
        }

    } catch (Exception ex) {
        showError("Database error: " + ex.getMessage());
        ex.printStackTrace();
    }
}

    private void showError(String message) {
        lblError.setStyle("-fx-text-fill: red;");
        lblError.setText(message);
        lblError.setVisible(true);
    }
    @FXML
public void HandleforgetPassword() {
    // Example action for forget password
    lblError.setStyle("-fx-text-fill: blue;");
    lblError.setText("Forgot Password feature is not implemented yet.");
    lblError.setVisible(true);
    
    // Or: open a password recovery scene
}
}
