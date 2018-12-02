package sample;

import java.io.*;
import java.util.ArrayList;

public class Leaderboard implements Serializable {
    public ArrayList<Player> getLeaders() {
        return leaders;
    }

    public void setLeaders(ArrayList<Player> leaders) {
        this.leaders = leaders;
    }

    private ArrayList<Player> leaders;

    Leaderboard() {
        this.leaders = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Player player = new Player("-");
            leaders.add(player);
        }
    }

    public void updateLeaderboard(Player current_player) {
        for (int i = 0; i < 3; i++) {
            if (current_player.getScore() > leaders.get(i).getScore()) {
                for (int j = 2; j > i; j--) {
                    leaders.set(j, leaders.get(j - 1));
                }
                leaders.set(i, current_player);
                break;
            }
        }
    }

    public void serialize() {
        ObjectOutputStream writer = null;
        try {
            writer = new ObjectOutputStream(new FileOutputStream("leaderboard.ser"));
            writer.writeObject(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ;
        }
    }

    public static Leaderboard deserialize() {
        ObjectInputStream reader = null;
        try {
            reader = new ObjectInputStream(new FileInputStream("leaderboard.ser"));
            return (Leaderboard) reader.readObject();
        } catch (FileNotFoundException e) {
            return new Leaderboard();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (NullPointerException e) {
                return new Leaderboard();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Leaderboard();
    }
}
