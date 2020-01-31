package checkers;


public interface Board {
    public Result move(Move move);
    public Position getPosition();
}