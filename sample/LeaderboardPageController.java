package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LeaderboardPageController {
    @FXML
    private Button backbutton;
    @FXML
    private AnchorPane mainframe;
    @FXML
    private Label score1;
    @FXML
    private Label name1;
    @FXML
    private Label score2;
    @FXML
    private Label name2;
    @FXML
    private Label score3;
    @FXML
    private Label name3;

    public Label getScore1() {
        return score1;
    }

    public void setScore1(Label score1) {
        this.score1 = score1;
    }

    public Label getName1() {
        return name1;
    }

    public void setName1(Label name1) {
        this.name1 = name1;
    }

    public Label getScore2() {
        return score2;
    }

    public void setScore2(Label score2) {
        this.score2 = score2;
    }

    public Label getName2() {
        return name2;
    }

    public void setName2(Label name2) {
        this.name2 = name2;
    }

    public Label getScore3() {
        return score3;
    }

    public void setScore3(Label score3) {
        this.score3 = score3;
    }

    public Label getName3() {
        return name3;
    }

    public void setName3(Label name3) {
        this.name3 = name3;
    }

    public Player getCurrent_player() {
        return current_player;
    }

    public void setCurrent_player(Player current_player) {
        this.current_player = current_player;
    }

    Player current_player;
    public void goBackToMainPage(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main_Page.fxml"));
            Scene sc = new Scene(fxmlLoader.load());
            MainPageController mainPage = fxmlLoader.getController();
            mainPage.setCurrent_player(current_player);
//            Scene sc = new Scene((AnchorPane)FXMLLoader.load(getClass().getResource("Main_Page.fxml")));
            sc.getStylesheets().add(getClass().getResource("stylize.css").toExternalForm());
            ((Stage) mainframe.getScene().getWindow()).setScene(sc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLeaders(ArrayList<Player> leaders) {
        name1.setText(leaders.get(0).getUsername());
        name2.setText(leaders.get(1).getUsername());
        name3.setText(leaders.get(2).getUsername());
        score1.setText(Integer.toString(leaders.get(0).getScore()));
        score2.setText(Integer.toString(leaders.get(1).getScore()));
        score3.setText(Integer.toString(leaders.get(2).getScore()));
        name1.setTextFill(Color.WHITE);
        score1.setTextFill(Color.WHITE);
        name2.setTextFill(Color.WHITE);
        score2.setTextFill(Color.WHITE);
        name3.setTextFill(Color.WHITE);
        score3.setTextFill(Color.WHITE);
    }
}
