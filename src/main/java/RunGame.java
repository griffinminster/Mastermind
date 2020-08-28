package main.java;

public class RunGame {
    public static void main(String[] args) {
        Game g = new Game();
        while(true) {
            boolean playAgain = g.willPlayAgain();
            if (playAgain) {
                g = new Game();
            }
        }
    }
}