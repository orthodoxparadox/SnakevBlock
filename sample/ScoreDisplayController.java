package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ScoreDisplayController {
    @FXML
    private Button mainMenuButton;
    @FXML
    private Button newGameButton;
    @FXML
    private AnchorPane mainframe;
    private Main runningGame;

    public void startNewGame(ActionEvent actionEvent) {
        try {
//            ((Node) actionEvent.getSource()).getScene().getWindow().hide();
//            runningGame = new Main();
//            System.out.println(runningGame.blocks.size());
//            runningGame.start(new Stage());
            Scene sc = new Scene(FXMLLoader.load(getClass().getResource("PlayPage.fxml")));
            sc.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
            ((Stage) mainframe.getScene().getWindow()).setScene(sc);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToMainMenu(ActionEvent actionEvent) {
        try {
            Scene sc = new Scene((AnchorPane) FXMLLoader.load(getClass().getResource("Main_Page.fxml")));
            sc.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
            ((Stage) mainframe.getScene().getWindow()).setScene(sc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
