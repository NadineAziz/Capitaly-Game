package my.capitaly;

import java.nio.file.Paths;

public class Main {

    public static void main(String... args) throws Exception {
        PlayGround game1 = new PlayGround();
        game1.play(Paths.get("Game1.txt"));

        PlayGround game2 = new PlayGround();
        game2.play(Paths.get("Game2.txt"));

        PlayGround game3 = new PlayGround();
        game3.play(Paths.get("Game3.txt"));

        PlayGround game4 = new PlayGround();
        game4.play(Paths.get("Game4.txt"));

        PlayGround game5 = new PlayGround();
        game5.play(Paths.get("Game5.txt"));

    }
}
