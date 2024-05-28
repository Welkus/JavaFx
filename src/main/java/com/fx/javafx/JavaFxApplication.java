package com.fx.javafx;

import com.fx.javafx.ui.StageManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class JavaFxApplication extends Application {

    private StageManager stageManager;
    private ConfigurableApplicationContext springContext;
    private Parent rootNode;


    public static void main(String[] args) {
    Application.launch(args);

    }

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(JavaFxApplication.class);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/LogIn.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        rootNode = fxmlLoader.load();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stageManager = springContext.getBean(StageManager.class);
        stageManager.setStage(stage);
        stage.setScene(new Scene(rootNode));
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        springContext.close();
    }



}
