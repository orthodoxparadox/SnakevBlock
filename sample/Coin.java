package sample;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Coin extends Token {
    private final int width = Constants.width;
    private final int height = Constants.height;
    private final int rows = Constants.rows;
    public Coin(double x, double y) {
        super(x, y, 1);
        Image img = new Image("sample/coin.png");
        this.setFill(new ImagePattern(img));
    }
}
