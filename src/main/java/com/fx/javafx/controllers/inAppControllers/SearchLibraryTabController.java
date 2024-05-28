package com.fx.javafx.controllers.inAppControllers;

import com.fx.javafx.model.BookDto;
import com.fx.javafx.model.BookModel;
import com.fx.javafx.service.BookService;
import com.fx.javafx.service.CheckoutService;
import com.fx.javafx.ui.StageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.controlsfx.control.CheckComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class SearchLibraryTabController implements Initializable {

    // @FXML fields----------------------------------------

    @FXML
    private TableColumn<BookModel, String> bookAuthorTab;

    @FXML
    private TableColumn<BookModel, String> bookGenreTab;

    @FXML
    private TableColumn<BookModel, String> bookStateTab;

    @FXML
    private TableView<BookModel> bookTable;

    @FXML
    private TableColumn<BookModel, String> bookTitleTab;

    @FXML
    private TableColumn<BookModel, Integer> bookYopTab;

    @FXML
    private Button checkoutBookButton;

    @FXML
    private TextField searchAuthorField;

    @FXML
    private Button searchButton;


    @FXML
    private TextField searchTitleField;

    @FXML
    private TextField searchYearField;

    @FXML
    private CheckComboBox<String> selectGenreField;
    @FXML
    private ComboBox<String> selectStateField;

    //Admin Elements ----------------------------------------------
    @FXML
    private Button editBookButton;
    @FXML
    private Button addBookButton;

    @FXML
    private VBox adminBox;

    // BackEnd fields-----------------------------------------------

    @Autowired
    private MainInterfaceController mIc;
    @Autowired
    private BookService bookService;
    @Autowired
    private CheckoutService checkoutService;
    @Autowired
    private StageManager stageManager;

   private ObservableList<BookModel> observableList;
   private List<BookModel> allBooks;

    private List<String> dis;
    private List<String> bState;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpTableView();
        genreComboBox();
        statesComboBox();



        searchButton.setOnAction(sb -> searchForBook());

        bookTable.setOnMouseClicked( e ->{
            if(e.getClickCount() == 2
            && bookTable.getSelectionModel().getSelectedItem() != null){
                checkoutConfirmation(bookTable.getSelectionModel().getSelectedItem());
            }
        });

        checkoutBookButton.setOnAction(e -> {
            if(bookTable.getSelectionModel().getSelectedItem() != null){
                checkoutConfirmation(bookTable.getSelectionModel().getSelectedItem());
            }
        });


        editBookButton.setOnAction(e -> {
            if(bookTable.getSelectionModel().getSelectedItem() != null){
                stageManager.editBookWindow(bookTable.getSelectionModel().getSelectedItem(),dis,bState);
            }
        });

        addBookButton.setOnAction(s -> stageManager.editBookWindow(dis,bState));

    }


    private void checkoutConfirmation(BookModel bookModel){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Check Out Confirmation");
        alert.setHeaderText("Do you want to check out: " + bookModel.getBookTitle() + "?");
        if (alert.showAndWait().get() == ButtonType.OK){
            checkoutService.checkOutBook(mIc.getUserModel(),bookModel);
        }
    }

    private void setUpTableView() {
        this.allBooks = bookService.getAllBooks();
        observableList = FXCollections.observableArrayList(allBooks);
        bookTitleTab.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        bookAuthorTab.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));
        bookYopTab.setCellValueFactory(new PropertyValueFactory<>("yearOfPublication"));
        bookGenreTab.setCellValueFactory(new PropertyValueFactory<>("bookGenre"));
        bookStateTab.setCellValueFactory(new PropertyValueFactory<>("bookState"));
        bookTable.setItems(observableList);

    }


    private void searchForBook() {


        BookDto bookDto = new BookDto();

        bookDto.setBookTitle(searchTitleField.getText());
        bookDto.setBookAuthor(searchAuthorField.getText());
        bookDto.setBookGenre(selectGenreField.getCheckModel().getCheckedItems());

        if (!searchYearField.getText().isEmpty()){                                          // NumberException jei text field tuščias
            bookDto.setYearOfPublication(Integer.parseInt(searchYearField.getText()));}
        if (selectStateField.getSelectionModel().getSelectedItem() != null){
            bookDto.setBookState(selectStateField.getSelectionModel().getSelectedItem());}

        observableList = FXCollections.observableList(bookService.searchForBook(bookDto));
        bookTable.setItems(observableList);
    }

    private void genreComboBox() {
        dis = allBooks.stream()
                .map(BookModel::getBookGenre)
                .distinct()
                .sorted()
                .toList();

        selectGenreField.getItems().addAll(dis);
    }

    private void statesComboBox() {
        bState = new ArrayList<>(List.of("", "New", "Like New", "Good", "Used", "Bad"));
        selectStateField.getItems().addAll(bState);
    }



}
