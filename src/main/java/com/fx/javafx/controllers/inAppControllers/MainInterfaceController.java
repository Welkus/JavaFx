package com.fx.javafx.controllers.inAppControllers;

import com.fx.javafx.model.UserModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@Data
public class MainInterfaceController implements Initializable {


    private UserModel userModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
