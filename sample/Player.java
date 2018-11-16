package sample;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.Serializable;

public class Player implements Serializable {
    private final int width = Constants.width;
    private final int height = Constants.height;
    private final int rows = Constants.rows;
    private Snake snake;
    private transient Pane mainframe;
    private int score;
    private transient Label scoreLabel;
    private int coins = 0;
    private transient Label coinsLabel;

    public Player(Pane mainframe) {
        this.score = 0;
        this.coins = 0;
        this.coinsLabel = new Label("0");
        this.coinsLabel.setTextFill(Color.WHITE);
        this.scoreLabel = new Label("0");
        this.scoreLabel.setTextFill(Color.WHITE);
        this.snake = new Snake(5, mainframe, width/2, height/2.0);
        this.mainframe = mainframe;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getRows() {
        return rows;
    }

    public Pane getMainframe() {
        return mainframe;
    }

    public int getScore() {
        return score;
    }

    public Snake getSnake() {
        return snake;
    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public void increaseScore(int i) {
        this.score += i;
        this.scoreLabel.setText(Integer.toString(this.score));
    }

    public int getCoins() {
        return coins;
    }

    public Label getCoinsLabel() {
        return coinsLabel;
    }

    public void addCoin() {
        this.coins++;
        this.coinsLabel.setText(Integer.toString(this.coins));
    }

    public void setScore(int i) {
        score = i;
        scoreLabel.setText(Integer.toString(score));
    }

    public void restore()
    {
        scoreLabel = new Label(Integer.toString(score));
        coinsLabel = new Label(Integer.toString(coins));
        coinsLabel.setTextFill(Color.WHITE);
        scoreLabel.setTextFill(Color.WHITE);
    }
}
