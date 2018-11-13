package sample;

import javafx.scene.layout.Pane;

public class Player {
    private final int width = Constants.width;
    private final int height = Constants.height;
    private final int rows = Constants.rows;
    Snake snake;
    Pane mainframe;

    public Player(Pane mainframe) {
        this.snake = new Snake(5, mainframe, width/2, height/2.0);
        this.mainframe = mainframe;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }
}
