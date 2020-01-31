package checkers;

public class ProxyPosition implements Position {
    private Position position;

    public ProxyPosition(Position position) {
        this.position = position;
    }

    @Override
    public int getBoardSize() {
        return position.getBoardSize();
    }

    @Override
    public boolean isValid(Move move) {
        return position.isValid(move);
    }

    @Override
    public String toString() {
        return position.toString();
    }


}