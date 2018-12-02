package sample;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.*;

public class Player implements Serializable {
    private static final long serialVersionUID = 42L;
    private final int width = Constants.width;
    private final int height = Constants.height;
    private final int rows = Constants.rows;
    private Snake snake;
    private transient Pane mainframe;
    private int score;
    private transient Label scoreLabel;
    private int coins = 0;
    private transient Label coinsLabel;
    private String username;

    public Player(Pane mainframe, String username) {
        this.username = username;
        this.score = 0;
        this.coins = 0;
        this.coinsLabel = new Label("0");
        this.coinsLabel.setTextFill(Color.WHITE);
        this.scoreLabel = new Label("0");
        this.scoreLabel.setTextFill(Color.WHITE);
        this.snake = new Snake(5, mainframe, width/2, height/2.0);
        this.mainframe = mainframe;
    }

    public Player(String username) {
        this.username = username;
        this.score = 0;
        this.coins = 0;
        this.coinsLabel = new Label("0");
        this.coinsLabel.setTextFill(Color.WHITE);
        this.scoreLabel = new Label("0");
        this.scoreLabel.setTextFill(Color.WHITE);
//        this.snake = new Snake(5, mainframe, width/2, height/2.0);
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

    public void serialize() {
        String dataFile = this.username;
        dataFile += ".ser";
        ObjectOutputStream writer = null;
        try {
            writer = new ObjectOutputStream(new FileOutputStream(dataFile));
            writer.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public static Player deserialize(String username) {
        String dataFile = username;
        dataFile += ".ser";
        ObjectInputStream reader = null;
        try {
            reader = new ObjectInputStream(new FileInputStream(dataFile));
            Player player = (Player) reader.readObject();
            player.scoreLabel = new Label(Integer.toString(player.score));
            player.coinsLabel = new Label(Integer.toString(player.coins));
            player.scoreLabel.setTextFill(Color.WHITE);
            player.coinsLabel.setTextFill(Color.WHITE);
            return player;
        } catch (IOException | ClassNotFoundException e) {
            //no player found, take care of this
            e.printStackTrace();
        }
        return null;
    }


    public void restore()
    {
        scoreLabel = new Label(Integer.toString(score));
        coinsLabel = new Label(Integer.toString(coins));
        coinsLabel.setTextFill(Color.WHITE);
        scoreLabel.setTextFill(Color.WHITE);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPane(Pane mainframe) {
        this.mainframe = mainframe;
        this.snake = new Snake(5, mainframe, width / 2, height / 2.0);
//        System.out.println("issue");
    }

    public Pane getPane() {
        return this.mainframe;
    }
}
