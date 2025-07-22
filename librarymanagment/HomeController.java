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
    private Button btnExit;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnSignUp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnExit.setOnAction(e -> Platform.exit());

        btnLogin.setOnAction(e -> {
            try {
                openScene("login.fxml", "Login");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        btnSignUp.setOnAction(e -> {
            try {
                openScene("signup.fxml", "Sign Up");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // For demo, just print messages for other buttons:
        btnDashboard.setOnAction(e -> System.out.println("Dashboard clicked"));
        btnBooks.setOnAction(e -> System.out.println("Manage Books clicked"));
        btnMembers.setOnAction(e -> System.out.println("Manage Members clicked"));
        btnIssue.setOnAction(e -> System.out.println("Issue Book clicked"));
        btnReturn.setOnAction(e -> System.out.println("Return Book clicked"));
    }

    private void openScene(String fxmlFile, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
