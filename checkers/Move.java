package checkers;

public class Move {
    private final int row;
    private final int col;
    private final int destRow;
    private final int destCol;
    public Move(int row, int col, int destRow, int destCol) {
        this.row = row;
        this.col = col;
        this.destCol = destCol;
        this.destRow = destRow;
    }

    public int r() {
        return row;
    } 

    public int c() {
        return col;
    }

    public int dr() {
        return destRow;
    }

    public int dc() {
        return destCol;
    }

}