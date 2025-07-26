package librarymanagment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Librarymanagment extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Load your home.fxml as the first screen
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Library Management System");
        stage.setScene(scene);
        stage.setResizable(false);  // Optional: Fix window size if you want
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
