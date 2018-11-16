package sample;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

public class Wall extends Rectangle implements Serializable {
    private final int width = Constants.width;
    private final int height = Constants.height;
    private final int rows = Constants.rows;
    private double xc;
    private double yc;
    private double h;

    public Wall(double xc, double yc, double h) {
        super(0, 0, 1, h);
        this.xc = xc;
        this.yc = yc;
        this.setFill(Color.WHITE);
        this.h = h;
        this.setTranslateX(xc);
        this.setTranslateY(yc);
    }
    public void store()
    {
        xc = getTranslateX();
        yc = getTranslateY();
    }

    public void restore() {
        setTranslateX(xc);
        setTranslateY(yc);
        setWidth(1);
        setHeight(h);
        setFill(Color.WHITE);
    }
}
