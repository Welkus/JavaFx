package com.fx.javafx.controllers.inAppControllers;

import com.fx.javafx.model.CheckedOutModel;
import com.fx.javafx.model.UserModel;
import com.fx.javafx.service.CheckoutService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

@Component
public class UserTabController implements Initializable {

    //FXML imports===========================================================
    //Table info ============================================================

    @FXML
    private TableColumn<CheckedOutModel, String> bookAuthorTab;

    @FXML
    private TableColumn<CheckedOutModel, String> bookTitleTab;

    @FXML
    private TableView<CheckedOutModel> checkedOutBooksTable;

    @FXML
    private TableColumn<CheckedOutModel, LocalDateTime> docTab;
    @FXML
    private TableColumn<CheckedOutModel, LocalDateTime> rbdTab;

    @FXML
    private TableColumn<CheckedOutModel, Boolean> returnedTab;
    @FXML
    private Button refreshButton;

    //User info ========================================================

    @FXML
    private Label emailLabel;

    @FXML
    private Label fNameLabel;

    @FXML
    private Label lNameLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private Label userNameLabel;

    // Java fields =====================================================

    @Autowired
    private MainInterfaceController mainInterfaceController;
    @Autowired
    private CheckoutService checkoutService;
    private UserModel userModel;
    private ObservableList<CheckedOutModel> checkouts;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.userModel = mainInterfaceController.getUserModel();
        setUserInfo();
        lunchTable();
        refreshButton.setOnAction(r -> refreshTable());


    }

    private void setUserInfo() {
        this.userModel = mainInterfaceController.getUserModel();
        userNameLabel.setText(userModel.getUsername());
        fNameLabel.setText(userModel.getFirstName());
        lNameLabel.setText(userModel.getLastName());
        emailLabel.setText(userModel.getEmail());
        roleLabel.setText((userModel.getRole()).toString());

    }

    private void lunchTable() {
        checkouts = FXCollections.observableArrayList(checkoutService.getCheckOuts(userModel));

        bookTitleTab.setCellValueFactory(callback -> new SimpleStringProperty(callback.getValue().getBookModel().getBookTitle()));
        bookAuthorTab.setCellValueFactory(callback -> new SimpleStringProperty(callback.getValue().getBookModel().getBookAuthor()));
        docTab.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
        rbdTab.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        returnedTab.setCellValueFactory(new PropertyValueFactory<>("isReturned"));

        checkedOutBooksTable.setItems(checkouts);

    }

    private void refreshTable(){
        checkouts = FXCollections.observableArrayList(checkoutService.getCheckOuts(userModel));
        checkedOutBooksTable.setItems(checkouts);
    }
}
