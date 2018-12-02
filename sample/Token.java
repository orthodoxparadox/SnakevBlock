package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Token extends Circle implements Serializable {
    private static final long serialVersionUID = 42L;
    private final int width = Constants.width;
    private final int height = Constants.height;
    private final int rows = Constants.rows;
    private int tokenKind;
    private double xc;
    private double yc;
    private static transient ArrayList<Color> colors = new ArrayList<Color>(Arrays.asList(Color.AQUAMARINE, Color.DARKGREY, Color.CYAN, Color.CHOCOLATE));
    Token(double x, double y, int tokenKind)
    {
        super(x, y, 8);
        xc = x;
        yc = y;
        this.setFill(colors.get(tokenKind-1));
        this.tokenKind = tokenKind;
    }
    public void store(){
        xc = getTranslateX();
        yc = getTranslateY();
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

    public void restore() {
        setTranslateX(xc);
        setTranslateY(yc);
        this.setRadius(8);
        colors = new ArrayList<Color>(Arrays.asList(Color.AQUAMARINE, Color.DARKGREY, Color.CYAN, Color.CHOCOLATE));
        this.setFill(colors.get(tokenKind-1));
    }
}
