package sample;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Arrays;

public class Ball extends Circle {
    private final int width = Constants.width;
    private final int height = Constants.height;
    private final int rows = Constants.rows;
    private Label label;
    private int strength;
    Ball(double x, double y, int strength)
    {
        super(x, y, 8, Color.YELLOW);
        this.strength = strength;
        this.label = new Label(Integer.toString(this.strength));
        this.label.setFont(new Font(10));
        this.label.setLayoutX(x-4);
        this.label.setLayoutY(y-6.5);
        this.label.setAlignment(Pos.CENTER);
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
}
