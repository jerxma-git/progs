package checkers;

import java.util.Scanner;

public class HumanPlayer implements Player {
    
    public HumanPlayer() {

    }

    public Move makeMove(Position position) {
        System.out.println(position.toString());
        while (true) {
            Scanner scan = new Scanner(System.in);
            int[] coords = IntScanner.scanInts(scan, 4, "2 cell coords and 2 destination coords:", System.out);
            
            Move move = new Move(coords[0], coords[1], coords[2], coords[3]);
            if (position.isValid(move)) {
                return move;
            } 
            System.out.println("This move is invalid, please make a correct move:");
        }
    }

}