package com.fx.javafx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.fx.javafx.model.UserModel;
import com.fx.javafx.service.UserService;
import com.fx.javafx.ui.StageManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.stereotype.Controller;

@Controller
public class LogInController implements Initializable {

    @FXML
    private Button logInButton;

    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private Hyperlink registrationLink;

    private UserService userService;
    private StageManager stageManager;

    public LogInController(UserService userService, StageManager stageManager) {
        this.userService = userService;
        this.stageManager = stageManager;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorLabel.setVisible(false);
        logInButton.setOnAction(b -> {
            UserModel lookForUserModel = new UserModel();
            lookForUserModel.setUsername(usernameField.getText());
            lookForUserModel.setUserPassword(passwordField.getText());
            UserModel userModel = userService.checkCredentials(lookForUserModel);

            if(userModel != null){
               try {
                   stageManager.changeSceneMainApp(userModel);
               } catch (IOException e) {
                   throw new RuntimeException(e);
               }
           }
            errorLabel.setText("Incorrect");
        });

        registrationLink.setOnAction(n -> {
            try {
                stageManager.changeSceneRegistration();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }




}
