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
    private Player current_player;
    private Main current_game;


    public void startGame(ActionEvent actionEvent) {
        try {
            System.out.println(2);
            String dataFile = current_player.getUsername();
            dataFile += "database.ser";
            current_game = Main.deserialize(dataFile);
//            current_game = new Main();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PlayPage.fxml"));
            Scene sc = new Scene((AnchorPane) fxmlLoader.load());
            PlayPageController playPage = fxmlLoader.getController();
            playPage.setCurrent_player(current_player);
            if (current_game == null) System.out.println("confirm");
            if (current_game != null) playPage.setRunningGame(current_game);
            sc.getStylesheets().add(getClass().getResource("stylize.css").toExternalForm());
            ((Stage)mainframe.getScene().getWindow()).setScene(sc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToLeaderboard(ActionEvent actionEvent) {
        try {
            Scene sc = new Scene((AnchorPane) FXMLLoader.load(getClass().getResource("LeaderboardPage.fxml")));
            sc.getStylesheets().add(getClass().getResource("stylize.css").toExternalForm());
            ((Stage)mainframe.getScene().getWindow()).setScene(sc);
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
