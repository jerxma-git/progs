package checkers;

public interface Position {
    public int getBoardSize();
    public boolean isValid(Move move);
    public String toString();
}