package sample;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Block extends Rectangle {
    private final int width = Constants.width;
    private final int height = Constants.height;
    private final int rows = Constants.rows;
    private int strength;
    private Label label;
    double xc;
    double yc;
    private static ArrayList<Color> colors = new ArrayList<Color>(Arrays.asList(Color.FUCHSIA, Color.GREENYELLOW, Color.SKYBLUE, Color.RED, Color.TURQUOISE));
    public Block(double xc, double yc, int strength)
    {
        super(xc, yc, 60, 60);
        this.setArcHeight(20.0);
        this.setArcWidth(20.0);
        this.strength = strength;
        label = new Label(Integer.toString(strength));
        this.label.setTextFill(Color.BLACK);
        this.label.setLayoutX(xc  + 25);
        this.label.setLayoutY(yc  + 20);
        this.label.setAlignment(Pos.CENTER);
        this.setFill(colors.get(ThreadLocalRandom.current().nextInt(5)));
    }
    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public double getXc() {
        return xc;
    }

    public void setXc(double xc) {
        this.xc = xc;
    }

    public double getYc() {
        return yc;
    }

    public void setYc(double yc) {
        this.yc = yc;
    }

    public void decreaseStrength(int i) {
        strength -= i;
        this.label.setText(Integer.toString(strength));
    }
}
