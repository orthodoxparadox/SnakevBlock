package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class Main extends Application {
    private static Pane mainframe = new Pane();
    ArrayList<Wall> walls = new ArrayList<Wall>();
    ArrayList<Ball> balls = new ArrayList<Ball>();
    ArrayList<Token> tokens = new ArrayList<Token>();
    ArrayList<Block> blocks = new ArrayList<Block>();
    Player P = new Player(mainframe);
    private double last = 0;
    private double t;

    private final int width = Constants.width;
    private final int height = Constants.height;
    private final int rows = Constants.rows;
    private static int debug = 0;


    public void makeRowOfBlocks(ArrayList<Integer> pos, ArrayList<Integer> strength)
    {
        int cnt = pos.size();
        for(int i = 0; i < cnt; i++)
        {
            Block b = new Block(pos.get(i)*width/rows, 60, strength.get(i));
            if(intersection(b)) continue;
            blocks.add(b);
            mainframe.getChildren().addAll(b, b.getLabel());
        }
    }

    public boolean addWall(int pos, double y, double height)
    {
        Wall to_add = new Wall(pos*width/rows, y, height);
        if(!intersection(to_add))
        {
            walls.add(to_add);
            mainframe.getChildren().addAll(to_add);
            return true;
        }
        return false;
    }

    public boolean intersection(Node to_add)
    {
        for(int i = 0; i < balls.size(); i++)
        {
            Bounds b = to_add.getBoundsInParent();
            if(balls.get(i).getBoundsInParent().intersects(b))
            {
                return true;
            }
        }
        for(int i = 0; i < walls.size(); i++)
        {
            Bounds b = to_add.getBoundsInParent();
            if(walls.get(i).getBoundsInParent().intersects(b))
            {
                return true;
            }
        }
        for(int i = 0; i < tokens.size(); i++)
        {
            Bounds b = to_add.getBoundsInParent();
            if(tokens.get(i).getBoundsInParent().intersects(b))
            {
                return true;
            }
        }
        for(int i = 0; i < blocks.size(); i++)
        {
            Bounds b = to_add.getBoundsInParent();
            if(blocks.get(i).getBoundsInParent().intersects(b))
            {
                return true;
            }
        }
        return false;
    }

    public void generateAndRefresh()
    {
        System.out.println("check" + " " + debug++);
        if(t > 10)
        {
            t = 0;
            Random r = new Random(System.currentTimeMillis());
            int num = r.nextInt(rows) + 1;
            ArrayList<Integer> pos = new ArrayList<Integer>();
            ArrayList<Integer> allpos = new ArrayList<Integer>();
            for(int i = 0; i < rows; i++) allpos.add(i);
            for(int i = 0; i < num; i++)
            {
                int next = r.nextInt(allpos.size());
                pos.add(allpos.get(next));
                allpos.remove(next);
            }
            ArrayList<Integer> strength = new ArrayList<Integer>();

            int smallestBlock = Integer.MAX_VALUE;
            int sumOfStrengths = 0;
            for(int i = 0; i < num; i++)
            {
                int value = (int)Math.abs(4*r.nextGaussian()) + 1;
                sumOfStrengths += value;
                smallestBlock = Math.min(smallestBlock, value);
                strength.add(value);
            }
            if(smallestBlock >= P.getSnake().getSz() && pos.size() == rows)
            {
                pos.remove(pos.size()-1);
                strength.remove(strength.size()-1);
            }
            makeRowOfBlocks(pos, strength);
            last -= sumOfStrengths*0.3;
        }
        Random r = new Random(System.currentTimeMillis());
        double model = Math.random();
        System.out.println(model);
        if(model >= 0.96)
        {
            last = 0;
            System.err.println("here");
            int wallCount = r.nextInt(3);
            for(int i = 0; i < wallCount; i++)
            {
                int pos = r.nextInt(rows-1) + 1;
                int wall_height = 180;
                double pro = Math.random();
                if(pro > 0.5)
                {
                    wall_height = 60;
                }
                else if(pro > 0.2)
                {
                    wall_height = 120;
                }
//                addWall(pos, height/10, width/10 + r.nextInt(width/10));
                addWall(pos, height/10, wall_height);
            }
            int tokenCount = r.nextInt(3);
            int cnt = 0;
            for(int i = 0; i < tokenCount; i++)
            {
                double probability = Math.random();
                if(probability > 0.6)
                {
                    //coins
                    while(!addToken(width/20 + r.nextInt(width-width/10), height/10 + r.nextInt(height/20), 1))
                    {
                        if(cnt++ > 10)
                        {
                            System.err.println("err1");
                            break;
                        }
                    }
//                    while(!addToken(30 + r.nextInt(440), 60 + r.nextInt(30), 1));
                }
                else if(probability > 0.2)
                {
                    //balls
                    while(!addBall(width/20 + r.nextInt(width-width/10), height/10 + r.nextInt(height/20), 3 + (int)Math.abs(r.nextGaussian())))
                    {
                        if(cnt++ > 10) {
                            System.err.println("err2");
                            break;
                        }
                    }
//                    while(!addBall(30 + r.nextInt(440), 60 + r.nextInt(30), 3 + (int)Math.abs(r.nextGaussian())));
                }
                else if(probability > 0.13)
                {
                    //magnet
                    while(!addToken(width/20 + r.nextInt(width-width/10), height/10 + r.nextInt(height/20), 2))
                    {
                        if(cnt++ > 10) {
                            System.err.println("err3");
                            break;
                        }
                    }
                }
                else if(probability > 0.065)
                {
                    //brickbuster
                    while(!addToken(width/20 + r.nextInt(width-width/10), height/10 + r.nextInt(height/20), 3))
                    {
                        if(cnt++ > 10){
                            System.err.println("err4");
                            break;
                        }
                    }
//                    while(!addToken(30 + r.nextInt(440), 60 + r.nextInt(30), 3));
                }
                else
                {
                    //shield
                    while(!addToken(width/20 + r.nextInt(width-width/10), height/10 + r.nextInt(height/20), 4))
                    {
                        if(cnt++ > 10) {
                            System.err.println("err5");
                            break;
                        }
                    }
//                    while(!addToken(30 + r.nextInt(440), 60 + r.nextInt(30), 4));
                }
            }
        }
        System.out.println("check" + debug--);
    }

    private boolean addBall(double x, double y, int strength) {
        Ball b = new Ball(x, y, strength);
        if(!intersection(b))
        {
            balls.add(b);
            mainframe.getChildren().addAll(b, b.getLabel());
            return true;
        }
        return false;
    }

    private boolean addToken(double x, double y, int type) {
        switch (type) {
            case 1:
                Coin c = new Coin(x, y);
                if (!intersection(c)) {
                    tokens.add(c);
                    mainframe.getChildren().addAll(c);
                    return true;
                }
                return false;
            case 2:
                Magnet m = new Magnet(x, y);
                if (!intersection(m)) {
                    tokens.add(m);
                    mainframe.getChildren().addAll(m);
                    return true;
                }
                return false;
            case 3:
                BrickBuster b = new BrickBuster(x, y);
                if (!intersection(b)) {
                    tokens.add(b);
                    mainframe.getChildren().addAll(b);
                    return true;
                }
                return false;
            case 4:
                Shield s = new Shield(x, y);
                if (!intersection(s)) {
                    tokens.add(s);
                    mainframe.getChildren().addAll(s);
                    return true;
                }
                return false;
        }
        return false;
    }

    private void move(int direction)
    {
        if(direction == 1)
        {
            //move towards Right
            P.getSnake().moveRight(5);
            for(Wall wall : walls)
            {
                if(P.getSnake().checkIntersection(wall))
                {
//                    P.getSnake().moveLeft(Math.abs(P.getSnake().getXc() - wall.getTranslateX() - 4));
//                    System.out.println(wall.getTranslateX() + " "  + P.getSnake().getXc() + " left");
//                    P.getSnake().moveLeft(Math.abs(P.getSnake().getXc() - wall.getTranslateX()) - 3);
                    P.getSnake().moveTo(wall.getTranslateX()-6);
                    return;
                }
            }
        }
        else
        {
            P.getSnake().moveLeft(5);
            for(Wall wall : walls)
            {
                if(P.getSnake().checkIntersection(wall))
                {
//                    System.out.println(wall.getTranslateX() + " "  + P.getSnake().getXc() + " right");
//                    P.getSnake().moveRight(Math.abs(P.getSnake().getXc() - wall.getTranslateX() + 4));
                    P.getSnake().moveTo(wall.getTranslateX()+8);
                    return;
                }
            }
            //move towards left
        }
    }

    private double assignGameSpeed() {
        double base_speed = 2.5;
        int snakeLength = P.getSnake().getSz();
        double intended_speed = Math.sqrt(snakeLength)/2;
        intended_speed *= 2;
        return Math.max(base_speed, intended_speed);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainframe.setPrefSize(width, height);
        last = 0;
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                refreshGUI();
            }
        };
        animationTimer.start();
        HBox hBox = new HBox();
        hBox.setPrefHeight(height/20);
        hBox.setPrefWidth(width);
        hBox.setStyle("-fx-background-color: #000000");
        hBox.setSpacing(300);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        mainframe.getChildren().add(hBox);
        mainframe.setStyle("-fx-background-color: #191970");
        Scene scene = new Scene(mainframe);
        scene.setOnKeyPressed(e ->
        {
            if(e.getCode() == KeyCode.RIGHT)
                move(1);
            if(e.getCode() == KeyCode.LEFT)
                move(-1);
        });
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void refreshGUI() {
//        System.out.println(walls.size() + " " + blocks.size() + " " + tokens.size() + " " + balls.size());
        Random r = new Random(System.currentTimeMillis());
        double refreshRate = assignGameSpeed();
        System.err.println(refreshRate + " " + 60/refreshRate);
        t += 0.04*refreshRate;
        if(t > 3)
        {
            generateAndRefresh();
        }
        ArrayList<Token> to_be_removedT = new ArrayList<Token>();
        IntStream.range(0, tokens.size()).forEachOrdered(i -> {
            if(P.getSnake().checkIntersection(tokens.get(i)))
            {
                String mediaFile = "src/sample/TokenSound.mp3";
                Media sound = new Media(new File(mediaFile).toURI().toString());
                MediaPlayer player = new MediaPlayer(sound);
                player.play();
                mainframe.getChildren().remove(tokens.get(i));
                to_be_removedT.add(tokens.get(i));
            }
        });
        for (Token aTo_be_removedT1 : to_be_removedT) {
            tokens.remove(aTo_be_removedT1);
        }
        ArrayList<Wall> to_be_removedW = new ArrayList<Wall>();
        IntStream.range(0, walls.size()).forEachOrdered(i -> {
            walls.get(i).setTranslateY(walls.get(i).getTranslateY() + refreshRate/2);
            if (walls.get(i).getTranslateY() > 800) {
                mainframe.getChildren().remove(walls.get(i));
                to_be_removedW.add(walls.get(i));
            }
        });
        for(Wall aTo_be_removedW : to_be_removedW)
        {
           walls.remove(aTo_be_removedW);
        }
        to_be_removedT.clear();
        IntStream.range(0, tokens.size()).forEachOrdered(i -> {
//            System.out.println(i);
//            tokens.get(i).setTranslateY(tokens.get(i).getTranslateY() + refreshRate/2);
            tokens.get(i).pull(refreshRate/2);
            if (tokens.get(i).getTranslateY() > 800) {
                mainframe.getChildren().remove(tokens.get(i));
                to_be_removedT.add(tokens.get(i));
            }
        });
        for (Token aTo_be_removedT : to_be_removedT) {
            tokens.remove(aTo_be_removedT);
        }
        ArrayList<Ball> to_be_removed = new ArrayList<Ball>();
        IntStream.range(0, balls.size()).forEachOrdered(i -> {
            balls.get(i).setTranslateY(balls.get(i).getTranslateY() + refreshRate/2);
            balls.get(i).getLabel().setTranslateY(balls.get(i).getTranslateY() + refreshRate/2);
            if(balls.get(i).getTranslateY() > 800)
            {
                mainframe.getChildren().remove(balls.get(i));
                to_be_removed.add(balls.get(i));
            }
        });
        for (Ball aTo_be_removed : to_be_removed) {
            balls.remove(aTo_be_removed);
        }
        ArrayList<Block> to_be_removedB = new ArrayList<Block>();
        IntStream.range(0, blocks.size()).forEachOrdered(i -> {
            blocks.get(i).setTranslateY(blocks.get(i).getTranslateY() + refreshRate/2);
            blocks.get(i).getLabel().setTranslateY(blocks.get(i).getLabel().getTranslateY() + refreshRate/2);
            if(blocks.get(i).getTranslateY() > 800)
            {
                mainframe.getChildren().remove(blocks.get(i));
                to_be_removedB.add(blocks.get(i));
            }
        });
        for (Block aTo_be_removedB : to_be_removedB)
        {
            blocks.remove(aTo_be_removedB);
        }
        BlockIntersection();
        WallIntersection();
        BallIntersection();
    }

    private void BallIntersection() {
        for (Ball ball : balls) {
            if (P.getSnake().checkIntersection(ball)) {
                P.getSnake().addSnakeBalls(ball.getStrength());
                mainframe.getChildren().removeAll(ball.getLabel(), ball);
                balls.remove(ball);
                return;
            }
        }
    }

    private void WallIntersection() {
        for(Wall wall : walls)
        {
            if(P.getSnake().checkIntersection(wall))
            {
                double delta = wall.getTranslateX() - P.getSnake().getXc();
                if(delta > 0) P.getSnake().moveTo(wall.getTranslateX()-6);
                else P.getSnake().moveTo(wall.getTranslateX() + 8);
                return;
            }
        }
    }

    private void BlockIntersection() {
        for(Block block : blocks)
        {
            if(P.getSnake().checkIntersection(block))
            {
                int cnt = block.getStrength();
                for(int i = 0; i < Math.min(block.getStrength(), P.getSnake().getSz()); i++)
                {
                    cnt--;
                    block.decreaseStrength(1);
                    P.getSnake().removeSnakeBalls(1);
                }
                if(cnt == 0)
                {
                    mainframe.getChildren().removeAll(block.getLabel(), block);
                    blocks.remove(block);
                    break;
                }
                if(P.getSnake().getSz() <= 0)
                {
                    System.exit(0);
                }
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
