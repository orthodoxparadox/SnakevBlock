package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Arrays;

public class Token extends Circle {
    private final int width = Constants.width;
    private final int height = Constants.height;
    private final int rows = Constants.rows;
    private int tokenKind;
    private static ArrayList<Color> colors = new ArrayList<Color>(Arrays.asList(Color.AQUAMARINE, Color.DARKGREY, Color.CYAN, Color.CHOCOLATE));
    Token(double x, double y, int tokenKind)
    {
        super(x, y, 8);
        this.setFill(colors.get(tokenKind-1));
        this.tokenKind = tokenKind;
    }
    public void pull(double dist)
    {
        this.setTranslateY(this.getTranslateY() + dist);
    }

    public int getTokenKind() {
        return tokenKind;
    }

    public void setTokenKind(int tokenKind) {
        this.tokenKind = tokenKind;
    }
}
