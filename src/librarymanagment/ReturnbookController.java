package librarymanagment;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class ReturnbookController {

    @FXML
    private TextField txtMemberID;
    @FXML
    private TextField txtBookID;
    @FXML
    private DatePicker dateReturn;
    @FXML
    private Label lblStatus;

    @FXML
    private void handleReturnBook(ActionEvent event) {
        String memberId = txtMemberID.getText().trim();
        String bookId = txtBookID.getText().trim();
        LocalDate returnDate = dateReturn.getValue();

        if (memberId.isEmpty() || bookId.isEmpty() || returnDate == null) {
            showStatus("Please fill in all fields.", "red");
            return;
        }

        // Perform database update to mark the book as returned
        String sql = "UPDATE issued_books SET return_date = ? WHERE member_id = ? AND book_id = ? AND return_date IS NULL";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, java.sql.Date.valueOf(returnDate));
            stmt.setString(2, memberId);
            stmt.setString(3, bookId);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                showStatus("Book returned successfully.", "green");
            } else {
                showStatus("No matching issued book found or already returned.", "red");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showStatus("Database error: " + e.getMessage(), "red");
        }
    }

    @FXML
    private void handleClear(ActionEvent event) {
        txtMemberID.clear();
        txtBookID.clear();
        dateReturn.setValue(null);
        lblStatus.setText("");
    }

    private void showStatus(String message, String color) {
        lblStatus.setText(message);
        lblStatus.setStyle("-fx-text-fill: " + color + "; -fx-font-weight: bold;");
    }
}
