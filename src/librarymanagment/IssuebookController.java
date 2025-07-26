package librarymanagment;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class IssuebookController implements Initializable {

    @FXML private TextField txtMemberID;
    @FXML private TextField txtBookID;
    @FXML private DatePicker dateIssue;
    @FXML private DatePicker dateDue;
    @FXML private Button btnIssue;
    @FXML private Label lblStatus;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblStatus.setText("");
    }

    @FXML
    private void handleIssueBook(ActionEvent event) {
        String memberID = txtMemberID.getText().trim();
        String bookID = txtBookID.getText().trim();
        LocalDate issueDate = dateIssue.getValue();
        LocalDate dueDate = dateDue.getValue();

        if (memberID.isEmpty() || bookID.isEmpty() || issueDate == null || dueDate == null) {
            lblStatus.setStyle("-fx-text-fill: red;");
            lblStatus.setText("Please fill in all fields.");
            return;
        }

        if (dueDate.isBefore(issueDate)) {
            lblStatus.setStyle("-fx-text-fill: red;");
            lblStatus.setText("Due date cannot be before issue date.");
            return;
        }

        String sql = "INSERT INTO issued_books (member_id, book_id, issue_date, due_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, memberID);
            stmt.setString(2, bookID);
            stmt.setDate(3, java.sql.Date.valueOf(issueDate));
            stmt.setDate(4, java.sql.Date.valueOf(dueDate));

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                lblStatus.setStyle("-fx-text-fill: green;");
                lblStatus.setText("Book issued successfully to member " + memberID + ".");
                clearFields();
            } else {
                lblStatus.setStyle("-fx-text-fill: red;");
                lblStatus.setText("Failed to issue book. Please try again.");
            }

        } catch (SQLException e) {
            lblStatus.setStyle("-fx-text-fill: red;");
            lblStatus.setText("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleClear(ActionEvent event) {
        clearFields();
        lblStatus.setText("");
    }

    private void clearFields() {
        txtMemberID.clear();
        txtBookID.clear();
        dateIssue.setValue(null);
        dateDue.setValue(null);
    }
}
