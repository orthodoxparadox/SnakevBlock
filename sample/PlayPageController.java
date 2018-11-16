package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PlayPageController {


    @FXML
    private Button resume;
    @FXML
    private Button start;
    @FXML
    private AnchorPane mainframe;
    private Main runningGame;


    public void resumeGame(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        runningGame = Main.deserialize();
        try {
            runningGame.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startGame(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        runningGame = new Main();
        try {
            Stage stage = new Stage();
            runningGame.start(stage);
            System.out.println("checker");
            runningGame.renewGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
