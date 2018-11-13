package sample;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Shield extends Token{
    private final int width = Constants.width;
    private final int height = Constants.height;
    private final int rows = Constants.rows;
    public Shield(double x, double y) {
        super(x, y, 4);
        Image img = new Image("sample/shield.png");
        this.setFill(new ImagePattern(img));
    }
}
