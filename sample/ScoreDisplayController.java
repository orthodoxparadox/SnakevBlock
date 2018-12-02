package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class ScoreDisplayController {
    @FXML
    private Button mainMenuButton;
    @FXML
    private Button newGameButton;
    @FXML
    private AnchorPane mainframe;
    @FXML
    private Label scoreLabel;
    private Main runningGame;
    private Player current_player;


    public void setScore(int score) {
        scoreLabel.setText((Integer.toString(score)));
        scoreLabel.setTextFill(Color.WHITE);
    }

    public void startNewGame(ActionEvent actionEvent) {
        try {
//            ((Node) actionEvent.getSource()).getScene().getWindow().hide();
//            runningGame = new Main();
//            System.out.println(runningGame.blocks.size());
//            runningGame.start(new Stage());
//            Stage stage = new Stage();
//            runningGame = new Main();
//            current_player.setPane(runningGame.mainframe);
//            runningGame.setP(current_player);
//            runningGame.start(stage);
//            runningGame.serialize();
            System.out.println(1);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main_Page.fxml"));
            Scene sc = new Scene(fxmlLoader.load());
            MainPageController mainPage = fxmlLoader.getController();
            mainPage.setCurrent_player(current_player);
            sc.getStylesheets().add(getClass().getResource("stylize.css").toExternalForm());
//            Stage stage = new Stage();
//            stage.setScene(sc);
//            stage.show();
            ((Stage) mainframe.getScene().getWindow()).setScene(sc);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToMainMenu(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main_Page.fxml"));
            Scene sc = new Scene(fxmlLoader.load());
            MainPageController mainPage = fxmlLoader.getController();
            mainPage.setCurrent_player(current_player);
            sc.getStylesheets().add(getClass().getResource("stylize.css").toExternalForm());
            ((Stage) mainframe.getScene().getWindow()).setScene(sc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Player getCurrent_player() {
        return current_player;
    }

    public void setCurrent_player(Player current_player) {
        this.current_player = current_player;
    }
}
