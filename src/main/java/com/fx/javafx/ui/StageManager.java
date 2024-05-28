package com.fx.javafx.ui;

import com.fx.javafx.controllers.inAppControllers.BookSaveController;
import com.fx.javafx.controllers.inAppControllers.MainInterfaceController;
import com.fx.javafx.model.BookModel;
import com.fx.javafx.model.UserModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Data
public class StageManager {

    private Stage stage;
    private ConfigurableApplicationContext springContext;
    private Parent rootNode;

    private MainInterfaceController mainInterfaceController;

    public StageManager(ConfigurableApplicationContext springContext, MainInterfaceController mainInterfaceController) {
        this.springContext = springContext;
        this.mainInterfaceController = mainInterfaceController;
    }

    public void changeSceneMainApp(UserModel userModel) throws IOException {
        mainInterfaceController.setUserModel(userModel);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainInterface/MainInterface.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        rootNode = fxmlLoader.load();
        stage.setScene(new Scene(rootNode));
    }

    public void changeSceneRegistration() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Registration.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        rootNode = fxmlLoader.load();
        stage.setScene(new Scene(rootNode));
    }

    public void changeSceneLogIn(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/LogIn.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        try {
            rootNode = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(new Scene(rootNode));
    }

    @SneakyThrows
    public void editBookWindow(BookModel bookModel, List<String> genre, List<String> state){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainInterface/BookEditor.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);

        Parent popUpRoot = fxmlLoader.load();
        BookSaveController controller = fxmlLoader.getController();
        controller.loadEditor(bookModel,genre,state);

        Stage popUpWindow = new Stage();
        popUpWindow.setScene(new Scene(popUpRoot));
        popUpWindow.showAndWait();
    }

    @SneakyThrows
    public void editBookWindow(List<String> genre, List<String> state){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainInterface/BookEditor.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);

        Parent popUpRoot = fxmlLoader.load();
        BookSaveController controller = fxmlLoader.getController();
        controller.loadEditor(genre,state);

        Stage popUpWindow = new Stage();
        popUpWindow.setScene(new Scene(popUpRoot));
        popUpWindow.showAndWait();
    }
}
