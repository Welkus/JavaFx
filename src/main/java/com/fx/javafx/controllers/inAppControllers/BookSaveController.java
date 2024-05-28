package com.fx.javafx.controllers.inAppControllers;

import com.fx.javafx.model.BookModel;
import com.fx.javafx.service.BookService;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

@Controller
public class BookSaveController implements Initializable {

    //FXML fields ------------------------------------------------
    @FXML
    private TextField authorField;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<String> genreCombo;

    @FXML
    private Button saveButton;

    @FXML
    private ComboBox<String> stateCombo;

    @FXML
    private TextField titleField;

    @FXML
    private TextField yearField;

    //program fields -----------------------------------------------------------

    @Autowired
    private BookService bookService;
    private BookModel bookModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        saveButton.setOnAction(s -> {
            saveBookChanges();
            bookService.saveBook(bookModel);
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });

        cancelButton.setOnAction(c -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });

    }

    public void loadEditor(BookModel bookModel, List<String> genre, List<String> state){
        this.bookModel = bookModel;
        titleField.setText(bookModel.getBookTitle());
        authorField.setText(bookModel.getBookAuthor());
        yearField.setText(String.valueOf(bookModel.getYearOfPublication()));
        genreCombo.setItems(FXCollections.observableArrayList(genre));
        genreCombo.getSelectionModel().select(bookModel.getBookGenre());
        stateCombo.setItems(FXCollections.observableArrayList(state));
        stateCombo.getSelectionModel().select(bookModel.getBookState());

    }

    private void saveBookChanges(){
        bookModel.setBookTitle(titleField.getText());
        bookModel.setBookAuthor(authorField.getText());
        bookModel.setYearOfPublication(Integer.parseInt(yearField.getText()));
        bookModel.setBookGenre(genreCombo.getSelectionModel().getSelectedItem());
        bookModel.setBookState(stateCombo.getSelectionModel().getSelectedItem());
    }

    public void loadEditor(List<String> genre, List<String> state){
        stateCombo.setItems(FXCollections.observableArrayList(state));
        genreCombo.setItems(FXCollections.observableArrayList(genre));

        bookModel = new BookModel();
    }
}
