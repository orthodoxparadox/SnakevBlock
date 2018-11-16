package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Snake implements Serializable {
    private final int width = Constants.width;
    private final int height = Constants.height;
    private final int rows = Constants.rows;
    private int sz;
    private transient Label sizeLabel;
    private transient Pane mainframe;
    private double xc;
    private double yc;
    private ArrayList<SnakeBall> balls = new ArrayList<SnakeBall>();
    private SnakeBall head;
    private boolean[] powers;
    private long[] powertime;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getRows() {
        return rows;
    }

    public SnakeBall getHead() {
        return head;
    }

    public void setHead(SnakeBall head) {
        this.head = head;
    }

    public boolean[] getPowers() {
        return powers;
    }

    public void setPowers(boolean[] powers) {
        this.powers = powers;
    }

    public long[] getPowertime() {
        return powertime;
    }

    public void setPowertime(long[] powertime) {
        this.powertime = powertime;
    }

    public Label getSizeLabel() {
        return sizeLabel;
    }

    Snake(int sz, Pane mainframe, double xc, double yc) {
        this.powers = new boolean[5];
        this.powertime = new long[5];
        this.sizeLabel = new Label(Integer.toString(4));
        this.sizeLabel.setTextFill(Color.WHITE);
        this.sz = sz;
        this.mainframe = mainframe;
        this.xc = xc;
        this.yc = yc;
        double cury = yc;
        for(int i = 0; i < sz; i++)
        {
            SnakeBall B = new SnakeBall(xc, cury);
            balls.add(B);
            cury += 8;
            mainframe.getChildren().add(B);
        }
        head = balls.get(0);
    }

    public void moveRight(double dist)
    {
        balls.forEach(s -> s.moveRight(dist));
        head = balls.get(0);
    }

    public void moveLeft(double dist)
    {
        balls.forEach(s -> s.moveLeft(dist));
        head = balls.get(0);
    }

    public int getSz() {
        return sz;
    }

    public void setSz(int sz) {
        this.sz = sz;
    }

    public Pane getMainframe() {
        return mainframe;
    }

    public void setMainframe(Pane mainframe) {
        this.mainframe = mainframe;
    }

    public double getXc() {
        return head.getTranslateX();
    }

    public void setXc(double xc) {
        this.xc = xc;
    }

    public double getYc() {
        return head.getTranslateY();
    }

    public void setYc(double yc) {
        this.yc = yc;
    }

    public ArrayList<SnakeBall> getBalls() {
        return balls;
    }

    public void setBalls(ArrayList<SnakeBall> balls) {
        this.balls = balls;
    }

    public boolean checkIntersection(Node node) {
        return sz > 0 && head.getBoundsInParent().intersects(node.getBoundsInParent());
    }

    public void addSnakeBalls(int strength) {
        System.out.println(strength);
        for(int i = 0; i < strength; i++)
        {
            SnakeBall s = new SnakeBall(head.getTranslateX(), yc + 8*sz);
            mainframe.getChildren().add(s);
            balls.add(s);
            sz++;
            this.sizeLabel.setText(Integer.toString(sz));
        }
        head = balls.get(0);
    }

    public void save()
    {
        xc = head.getTranslateX();
        yc = head.getTranslateY();
    }

    public void removeSnakeBalls(int strength)
    {
        for(int i = 0; i < strength; i++)
        {
            mainframe.getChildren().removeAll(balls.get(sz-1));
            balls.remove(sz-1); sz--;
            this.sizeLabel.setText(Integer.toString(sz));
            if(sz == 0)
            {
                Scene sc = null;
                try {
                    sc = new Scene(FXMLLoader.load(getClass().getResource("ScoreDisplay.fxml")));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                sc.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
                ((Stage) mainframe.getScene().getWindow()).setScene(sc);
                return;
            }
        }
        head = balls.get(0);
    }

    public void moveTo(double v) {
        balls.forEach(s -> s.moveTo(v));
        head = balls.get(0);
    }

    public void givePowerup(int k, long t) {
        powers[k] = true;
        powertime[k] = t;
    }

    public void reducePowerups() {
        for(int i = 1; i < 5; i++)
        {
            if(powers[i])
            {
                if(System.currentTimeMillis() > powertime[i] + 10000)
                {
                    System.out.println(System.currentTimeMillis() + " " + powertime[i] + " " + i);
                    powers[i] = false;
                }
            }
        }
    }
    public void endPowerup(int k)
    {
        powers[k] = false;
        powertime[k] = 0;
    }

    public boolean havePowerup(int i) {
        return powers[i];
    }

    public void ressurect(Pane mainframe) {
        this.mainframe = mainframe;
        balls.clear();
        double cury = yc;
        for(int i = 0; i < sz; i++)
        {
            SnakeBall B = new SnakeBall(xc, cury);
            balls.add(B);
            cury += 8;
            mainframe.getChildren().add(B);
        }
        sizeLabel = new Label(Integer.toString(sz));
        sizeLabel.setTextFill(Color.WHITE);
    }
}
