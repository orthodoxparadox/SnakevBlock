package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Login extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent mainframe = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(mainframe, Color.BLACK);
        scene.getStylesheets().add(getClass().getResource("stylize.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
