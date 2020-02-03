package rubik;

import java.util.*;

public class RubiksCube {
    
    private final Map<Colour, Character> COLOUR_TO_STR = new HashMap<>(Map.of(
        Colour.WHITE, 'W',
        Colour.RED, 'R',
        Colour.GREEN, 'G',
        Colour.ORANGE, 'O',
        Colour.YELLOW, 'Y',
        Colour.BLUE, 'B'
    )); 

    private final Map<Integer, Colour> INIT_SIDES = new HashMap<>(Map.of(
        0, Colour.WHITE,  // top
        1, Colour.RED,    // left 
        2, Colour.BLUE,   // front
        3, Colour.ORANGE, // right
        4, Colour.GREEN,  // back
        5, Colour.YELLOW  // bot
    ));

    private Cube[][][] cubes;
    private final int CUBE_SIZE;
    public RubiksCube(int size) {
        CUBE_SIZE = size;
        cubes = new Cube[CUBE_SIZE][CUBE_SIZE][CUBE_SIZE];

        for (int i = 0; i < CUBE_SIZE; i++) {
            for (int j = 0; j < CUBE_SIZE; j++) {
                initSingleCube(0, i, j);
                initSingleCube(CUBE_SIZE - 1, i, j);
                initSingleCube(i, 0, j);
                initSingleCube(i, CUBE_SIZE - 1, j);
                initSingleCube(i, j, 0);
                initSingleCube(i, j, CUBE_SIZE - 1);
            }
        }
        resetColours();
    }

    private void initSingleCube(int x, int y, int z) {
        if (cubes[x][y][z] == null) {
            cubes[x][y][z] = new Cube();
        }
    }

    public void resetColours() {
        for (int i = 0; i < CUBE_SIZE; i++) {
            for (int j = 0; j < CUBE_SIZE; j++) {
                cubes[0][i][j].set(0, INIT_SIDES.get(0)); // top
                cubes[CUBE_SIZE - 1][i][j].set(5, INIT_SIDES.get(5)); // bot
                cubes[i][0][j].set(1, INIT_SIDES.get(1)); // left
                cubes[i][CUBE_SIZE - 1][j].set(3, INIT_SIDES.get(3)); // right
                cubes[i][j][0].set(4, INIT_SIDES.get(4)); // back
                cubes[i][j][CUBE_SIZE - 1].set(2, INIT_SIDES.get(2)); // front
            }
        }
    }

    public RubiksCube() {
        this(3);
    }


    public void rotateTop() {
        Colour[] buffer = new Colour[CUBE_SIZE];
        
    }



    public String toString() {
        StringBuilder str = new StringBuilder();

        // top
        for (int i = 0; i < CUBE_SIZE; i++) {
            for (int j = 0; j < CUBE_SIZE; j++) {
                str.append(' ');
            }
            for (int j = 0; j < CUBE_SIZE; j++) {
                str.append(COLOUR_TO_STR.get(cubes[0][j][i].getTop()));
            }
            str.append('\n');
        }

        // left - front - right - back
        for (int i = 0; i < CUBE_SIZE; i++) {
            for (int j = 0; j < CUBE_SIZE; j++) {
                str.append(COLOUR_TO_STR.get(cubes[i][0][j].getLeft()));
            }
            for (int j = 0; j < CUBE_SIZE; j++) {
                str.append(COLOUR_TO_STR.get(cubes[i][j][CUBE_SIZE - 1].getFront()));
            }
            for (int j = 0; j < CUBE_SIZE; j++) {
                str.append(COLOUR_TO_STR.get(cubes[i][CUBE_SIZE - 1][CUBE_SIZE - 1 - j].getRight()));
            }
            for (int j = 0; j < CUBE_SIZE; j++) {
                str.append(COLOUR_TO_STR.get(cubes[i][CUBE_SIZE - 1 - j][0].getBack()));
            }
            str.append('\n');
        } 

        // bot
        for (int i = 0; i < CUBE_SIZE; i++) {
            for (int j = 0; j < CUBE_SIZE; j++) {
                str.append(' ');
            }
            for (int j = 0; j < CUBE_SIZE; j++) {
                str.append(COLOUR_TO_STR.get(cubes[CUBE_SIZE - 1][j][CUBE_SIZE - 1 - i].getBot()));
            }
            str.append('\n');
        }

        return str.toString();
    }



}