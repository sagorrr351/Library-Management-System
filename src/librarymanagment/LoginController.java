package librarymanagment;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import librarymanagment.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Button btnLogin;
    @FXML private Label lblError;

    @FXML
    private void initialize() {
        // Any initialization if needed
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
           
            String fullname = rs.getString("fullname");

            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent homeRoot = loader.load();
            HomeController homeController = loader.getController();
            homeController.setWelcomeMessage(fullname);

           
            Stage stage = (Stage) txtUsername.getScene().getWindow();
            stage.setScene(new Scene(homeRoot));
            stage.setTitle("Home");
            stage.show();

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
        lblError.setStyle("-fx-text-fill: blue;");
        lblError.setText("Forgot Password feature is not implemented yet.");
        lblError.setVisible(true);
    }
}
