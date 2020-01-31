package checkers;

import java.util.*;

public class CheckersBoard implements Board, Position {
    private final int BOARD_SIZE = 8;
    private Cell[][] cells;
    private int whiteLeft;
    private int blackLeft;
    private boolean turn; // true - white, false - black
    private boolean haveToEat;

    private final Map<Cell, Character> CELL_TO_STR = new HashMap<>(Map.of(
        Cell.BC, 'B',
        Cell.WC, 'W',
        Cell.E, '.'
    ));

    public CheckersBoard() {
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        emptyBoard();
        initBlack();
        initWhite();      
        whiteLeft = blackLeft = 12;
        turn = true;  
        haveToEat = false;
    }

    private void emptyBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                cells[i][j] = Cell.E;
            }
        }
    }

    private void initBlack() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < (BOARD_SIZE / 2); col++) {
                cells[row][(row + 1) % 2 + col * 2] = Cell.BC;
            }
        }
    }

    private void initWhite() {
        for (int row = BOARD_SIZE - 3; row < BOARD_SIZE; row++) {
            for (int col = 0; col < (BOARD_SIZE / 2); col++) {
                cells[row][(row + 1) % 2 + col * 2] = Cell.WC;
            }
        }
    }

    @Override
    public Result move(Move move) {
        if (!isValid(move)) {
            return Result.LOSS;
        }

        cells[move.r()][move.c()] = Cell.E;
        cells[move.dr()][move.dc()] = turn ? Cell.WC : Cell.BC;
        if (abs(move.dr() - move.r()) == 2) {
            cells[(move.dr() + move.r()) / 2][(move.dc() + move.c()) / 2] = Cell.E;
            if (turn) {
                blackLeft--;
            } else {
                whiteLeft--;
            }
        }
        //check if next player has to eat
        turn = !turn;



        if (whiteLeft == 0 || blackLeft == 0) {
            return Result.WIN;
        }
        haveToEat = hasToEat();
        System.out.println("next does" + (haveToEat ? "" : "n't") + " have to eat\n");
        return Result.UNKNOWN;

    }

    // private boolean hasToEat(Move lastMove) {
    //     for (int i = -1; i <= 1; i++) {
    //         for (int j = -1; j <= 1; j++) {
    //             if ((abs(i) == 1 && abs(j) == 1) 
    //                 && isValid(new Move(
    //                     lastMove.dr() + i, lastMove.dc() + j,
    //                     lastMove.dr() - i, lastMove.dc() - j))
    //                 && isValid(new Move(
    //                     lastMove.r() + i * 2, lastMove.c() + j * 2,
    //                     lastMove.r(), lastMove.c()
    //                 ))) {
    //                     return true;
    //             }
    //         }
    //     }
    //     return false;
    // } 

    private boolean hasToEat() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (cells[i][j] == (turn ? Cell.WC : Cell.BC)) {
                    for (int di = -1; di <= 1; di++) {
                        for (int dj = -1; dj <= 1; dj++) {
                            if ((abs(di) == 1 && abs(dj) == 1) 
                                && isValid(new Move(i, j, i + 2 * di, j + 2 * dj))) {
                                    return true;
                                }
                        }
                    }
                }
            }
        }
        return false;
    }
    


    private int abs(int val) {
        return val < 0 ? -val : val;
    }

    @Override
    public boolean isValid(Move move) {
        int shift;
        return inBorder(move.r()) && inBorder(move.c()) && inBorder(move.dc()) && inBorder(move.dr())
            && cells[move.dr()][move.dc()] == Cell.E && cells[move.r()][move.c()] == (turn ? Cell.WC : Cell.BC) // correct player's checker at [r][c]
             && (shift = abs(move.c() - move.dc())) == abs(move.dr() - move.r()) // in diagonal
            && shift != 0 // not self 
             && ((shift == 1 && ((move.dr() > move.r()) ^ turn) && !haveToEat) //move 
                || shift == 2 
                && (cells[(move.dr() + move.r()) / 2][(move.dc() + move.c()) / 2] == (turn ? Cell.BC : Cell.WC)) //eat
            ); 
            // :))))
    }

    private boolean inBorder(int val) {
        return val >= 0 && val < BOARD_SIZE;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append('#');
        for (int c = 0; c < BOARD_SIZE; c++) {
            str.append(c);
        }
        str.append('\n');
        for (int r = 0; r < BOARD_SIZE; r++) {
            str.append(r);
            for (int c = 0; c < BOARD_SIZE; c++) {
                str.append(CELL_TO_STR.get(cells[r][c]));
            }
            str.append('\n');
        }
        return str.toString();
    }

    @Override
    public int getBoardSize() {
        return BOARD_SIZE;
    }

    @Override
    public Position getPosition() {
        return new ProxyPosition(this);
    }


}