package librarymanagment;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManagebookController implements Initializable {

    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, Integer> colBookId;
    @FXML
    private TableColumn<Book, String> colTitle;
    @FXML
    private TableColumn<Book, String> colAuthor;
    @FXML
    private TableColumn<Book, Integer> colYear;
    @FXML
    private TableColumn<Book, Integer> colQuantity;

    @FXML
    private TextField bookIdField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField yearField;
    @FXML
    private TextField quantityField;

    private ObservableList<Book> bookList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize the table columns with property names
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        bookList = FXCollections.observableArrayList();

        // Add some demo data (optional)
        bookList.add(new Book(1, "Java Basics", "John Doe", 2018, 5));
        bookList.add(new Book(2, "Advanced JavaFX", "Jane Smith", 2020, 3));

        bookTable.setItems(bookList);
    }

    @FXML
    private void handleAddBook(ActionEvent event) {
        try {
            int bookId = Integer.parseInt(bookIdField.getText());
            String title = titleField.getText();
            String author = authorField.getText();
            int year = Integer.parseInt(yearField.getText());
            int quantity = Integer.parseInt(quantityField.getText());

            if(title.isEmpty() || author.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Title and Author cannot be empty.");
                return;
            }

            // Check if book with same ID exists
            Optional<Book> existing = bookList.stream().filter(b -> b.getBookId() == bookId).findFirst();
            if (existing.isPresent()) {
                showAlert(Alert.AlertType.ERROR, "Duplicate Book ID", "A book with this ID already exists.");
                return;
            }

            Book book = new Book(bookId, title, author, year, quantity);
            bookList.add(book);
            clearFields();

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter valid numbers for Book ID, Year, and Quantity.");
        }
    }

    @FXML
    private void handleUpdateBook(ActionEvent event) {
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a book to update.");
            return;
        }

        try {
            int bookId = Integer.parseInt(bookIdField.getText());
            String title = titleField.getText();
            String author = authorField.getText();
            int year = Integer.parseInt(yearField.getText());
            int quantity = Integer.parseInt(quantityField.getText());

            if(title.isEmpty() || author.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Title and Author cannot be empty.");
                return;
            }

            // Update properties
            selectedBook.setBookId(bookId);
            selectedBook.setTitle(title);
            selectedBook.setAuthor(author);
            selectedBook.setYear(year);
            selectedBook.setQuantity(quantity);

            bookTable.refresh();
            clearFields();

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter valid numbers for Book ID, Year, and Quantity.");
        }
    }

    @FXML
    private void handleDeleteBook(ActionEvent event) {
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a book to delete.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the selected book?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            bookList.remove(selectedBook);
            clearFields();
        }
    }

    @FXML
    private void handleClearFields(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        bookIdField.clear();
        titleField.clear();
        authorField.clear();
        yearField.clear();
        quantityField.clear();
        bookTable.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
