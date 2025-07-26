package librarymanagment;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.application.Platform;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class HomeController implements Initializable {

    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnBooks;
    @FXML
    private Button btnMembers;
    @FXML
    private Button btnIssue;
    @FXML
    private Button btnReturn;

    @FXML
    private Button btnLogin;
    @FXML
    private Button btnSignUp;
    @FXML
    private Button btnLogout;

    @FXML
    private Label welcomeLabel;

    private boolean loggedIn = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateUIForLoginState();
    }

    public void setWelcomeMessage(String name) {
        welcomeLabel.setText("Welcome, " + name + "!");
        loggedIn = true;
        updateUIForLoginState();
    }

    private void updateUIForLoginState() {
        if (loggedIn) {
            btnLogin.setVisible(false);
            btnSignUp.setVisible(false);
            btnLogout.setVisible(true);
        } else {
            btnLogin.setVisible(true);
            btnSignUp.setVisible(true);
            btnLogout.setVisible(false);
            welcomeLabel.setText("Welcome!");
        }
    }

    private void openScene(String fxmlFile, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void hanldemanagebooks(ActionEvent event) {
        try {
            openScene("managebook.fxml", "Manage Books");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlemanagemembers(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("managemembers.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Log In");
            stage.setScene(new Scene(root));
            stage.show();

            // Close current home window
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleissuebooks(ActionEvent event) {
try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("issuebook.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Log In");
            stage.setScene(new Scene(root));
            stage.show();

            // Close current home window
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlereturnbook(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("returnbook.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Log In");
            stage.setScene(new Scene(root));
            stage.show();

            // Close current home window
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlelogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Log In");
            stage.setScene(new Scene(root));
            stage.show();

            // Close current home window
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlesignup(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Sign Up");
            stage.setScene(new Scene(root));
            stage.show();

            // Close current home window
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Library Management System");
            stage.setScene(new Scene(root));
            stage.show();

            // Close current stage
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
