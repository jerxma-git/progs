package checkers;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(new CheckersBoard(), new HumanPlayer(), new HumanPlayer());
        int winner = game.play();
    }
}