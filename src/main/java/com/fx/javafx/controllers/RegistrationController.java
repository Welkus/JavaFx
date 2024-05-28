package com.fx.javafx.controllers;

import com.fx.javafx.ui.ErrorMessage;
import com.fx.javafx.model.UserModel;
import com.fx.javafx.service.UserService;
import com.fx.javafx.ui.StageManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class RegistrationController implements Initializable {

    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonRegister;
    @FXML
    private Label errorMessage;
    @FXML
    private TextField regFirstName;

    @FXML
    private TextField regEmail;

    @FXML
    private TextField regLastName;

    @FXML
    private PasswordField regPassword;

    @FXML
    private PasswordField regPassword2;

    @FXML
    private TextField regUsername;

    private final StageManager stageManager;
    private final UserService userService;

    public RegistrationController(StageManager stageManager, UserService userService) {
        this.stageManager = stageManager;
        this.userService = userService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        errorMessage.setVisible(false);

        buttonRegister.setOnAction(r -> {
            if(!regPassword.getText().equals(regPassword2.getText())){
                errorMessage.setText("Password does not match");
                errorMessage.setVisible(true);
            } else {
                ErrorMessage registrationError = userService.registrationRequest(userForRegistration());
                if(registrationError == ErrorMessage.SUCCESS){
                    stageManager.changeSceneLogIn();
                } else {
                    errorMessage.setText(registrationError.getMessage());
                    errorMessage.setVisible(true);
                }
            }
        });


        buttonCancel.setOnAction( c -> stageManager.changeSceneLogIn());
    }

    private UserModel userForRegistration(){
       return UserModel.builder()
                .username(regUsername.getText())
                .userPassword(regPassword.getText())
                .firstName(regFirstName.getText())
                .lastName(regLastName.getText())
                .email(regEmail.getText())
                .build();
    }


}
