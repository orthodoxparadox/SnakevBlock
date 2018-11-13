package sample;


import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class BrickBuster extends Token{
    private final int width = Constants.width;
    private final int height = Constants.height;
    private final int rows = Constants.rows;
    public BrickBuster(double x, double y) {
        super(x, y, 3);
        Image img = new Image("sample/Brick_block.png");
        this.setFill(new ImagePattern(img));
    }
}
