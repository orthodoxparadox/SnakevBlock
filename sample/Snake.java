package sample;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Snake {
    private final int width = Constants.width;
    private final int height = Constants.height;
    private final int rows = Constants.rows;
    private int sz;
    private Pane mainframe;
    private double xc;
    private double yc;
    private ArrayList<SnakeBall> balls = new ArrayList<SnakeBall>();
    private SnakeBall head;

    Snake(int sz, Pane mainframe, double xc, double yc) {
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
        }
        head = balls.get(0);
    }

    public void removeSnakeBalls(int strength)
    {
        for(int i = 0; i < strength; i++)
        {
            mainframe.getChildren().removeAll(balls.get(sz-1));
            balls.remove(sz-1); sz--;
            if(sz == 0) System.exit(0);
        }
        head = balls.get(0);
    }

    public void moveTo(double v) {
        balls.forEach(s -> s.moveTo(v));
    }
}
