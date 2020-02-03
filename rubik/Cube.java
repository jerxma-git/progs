package rubik;

import java.util.*;

public class Cube {
    private List<Colour> sides;
    // 0 - top
    // 1 - left
    // 2 - front
    // 3 - right
    // 4 - back
    // 5 - bot

    

    public Cube(Colour top, Colour left, Colour front, Colour right, Colour back, Colour bot) {
        sides = new ArrayList<>();
        sides.add(top);
        sides.add(left);
        sides.add(front);
        sides.add(right);
        sides.add(back);
        sides.add(bot);
    }

    public Cube() {
        this(null, null, null, null, null, null);
    }

    public Colour get(int index) {
        return sides.get(index);
    }

    public void set(int index, Colour colour) {
        sides.set(index, colour);
    }


    public Colour getTop() {
        return get(0);
    }

    public Colour getLeft() {
        return get(1);
    }

    public Colour getFront() {
        return get(2);
    }

    public Colour getRight() {
        return get(3);
    }

    public Colour getBack() {
        return get(4);
    }


    public Colour getBot() {
        return get(5);
    }


}