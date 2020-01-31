package checkers;

import java.util.*;

public class Game {
    private Board board;
    private List<Player> players;

    public Game(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
    }

    public Game(Board board, Player player1, Player player2) {
        this(board, List.of(player1, player2));
    }

    public int play() {
        Result res = Result.UNKNOWN;
        int turn = 0;
        while (true) {
            System.out.println("Player" + (turn + 1) + "'s move:");
            res = board.move(players.get(turn).makeMove(board.getPosition()));
            if (res == Result.WIN) {
                System.out.println("Player" + (turn + 1) + " wins");
                return turn + 1; // win
            }
            if (res == Result.LOSS) {
                return -(turn + 1); //cheat
            }
            turn ^= 1;
        }
    }
}