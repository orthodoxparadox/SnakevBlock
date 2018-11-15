package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MakeAccountController {

    @FXML
    private Button signupbutton;
    @FXML
    private TextField username_tf;
    private String username;

    public void makeNewAccount(ActionEvent actionEvent) {
        username = username_tf.getText();
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Scene sc = null;
        try {
            sc = new Scene((AnchorPane) FXMLLoader.load(getClass().getResource("Main_Page.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        Stage stage = new Stage();
        stage.setScene(sc);
        stage.show();
    }
}