package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageController {

    @FXML
    private Button leaderboardbutton;
    @FXML
    private Button startbutton;
    @FXML
    private AnchorPane mainframe;

    public void startGame(ActionEvent actionEvent) {
        try {
            Scene sc = new Scene((AnchorPane) FXMLLoader.load(getClass().getResource("PlayPage.fxml")));
            sc.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
            ((Stage)mainframe.getScene().getWindow()).setScene(sc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToLeaderboard(ActionEvent actionEvent) {
        try {
            Scene sc = new Scene((AnchorPane) FXMLLoader.load(getClass().getResource("LeaderboardPage.fxml")));
            sc.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
            ((Stage)mainframe.getScene().getWindow()).setScene(sc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
