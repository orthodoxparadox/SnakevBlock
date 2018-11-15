package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LeaderboardPageController {
    @FXML
    private Button backbutton;
    @FXML
    private AnchorPane mainframe;
    public void goBackToMainPage(ActionEvent actionEvent) {
        try {
            Scene sc = new Scene((AnchorPane)FXMLLoader.load(getClass().getResource("Main_Page.fxml")));
            sc.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
            ((Stage) mainframe.getScene().getWindow()).setScene(sc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
