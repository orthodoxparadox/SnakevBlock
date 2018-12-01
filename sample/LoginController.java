package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private Button signin;
    @FXML
    private Button signup;
    @FXML
    private AnchorPane mainframe;
    private String input_username;
    private Player returning_player;

    public void make_account(ActionEvent actionEvent) {
        try {
            Scene sc = new Scene((AnchorPane) FXMLLoader.load(getClass().getResource("Make_Account.fxml")));
            sc.getStylesheets().add(getClass().getResource("stylize.css").toExternalForm());
            ((Stage) mainframe.getScene().getWindow()).setScene(sc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login(ActionEvent actionEvent) {
        if (username.getText() != null)
            input_username = username.getText();
        returning_player = getPlayer();
        if (returning_player == null) return;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main_page.fxml"));
            Scene sc = new Scene((AnchorPane) fxmlLoader.load());
            MainPageController mainPage = fxmlLoader.getController();
            mainPage.setCurrent_player(returning_player);
            sc.getStylesheets().add(getClass().getResource("stylize.css").toExternalForm());
            ((Stage) mainframe.getScene().getWindow()).setScene(sc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Player getPlayer() {
        String dataFile = input_username;
        return Player.deserialize(dataFile);
    }
}
