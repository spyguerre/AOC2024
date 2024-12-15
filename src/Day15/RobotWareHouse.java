package Day15;

import utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * A class describing a lanternfish's mad robot warehouse or something like that. (see subject)
 */
public class RobotWareHouse extends Map2D<Character> {
    /**
     * The current coordinates of the robot.
     */
    private Coordinate robotCoos;
    /**
     * The list of moves predefined for the robot.
     */
    public final List<Character> moves;
    /**
     * The index used to read through the this.moves list.
     */
    private int moveIndex;

    /**
     * Constructor for a Robot WareHouse.
     * @param matrix The matrix representing the map of the Warehouse.
     * @param moves The list of moves predefined for the robot.
     */
    public RobotWareHouse(List<List<Character>> matrix, List<Character> moves) {
        super(matrix);
        this.moves = new ArrayList<>(moves);
        this.moveIndex = 0;
        initRobotCoos();
    }

    /**
     * Initializes the coordinates for the robot by reading the this.matrix.
     */
    private void initRobotCoos() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                if (this.get(i, j) == '@') {robotCoos = new Coordinate(i, j); return;}
            }
        }
        throw new RuntimeException("Couldn't find robot in this.matrix.");
    }

    /**
     * Computes the GPS (as described in the subject) of a crate found at coordinates (i, j).
     * @param i The first coordinate of the crate.
     * @param j The second coordinate of the crate.
     * @return the GPS of the crate.
     */
    private int getGPS(int i, int j) {
        return 100*i + j;
    }

    /**
     * Reads the next direction of the robot's moves.
     * @return the next direction of the robot's moves.
     */
    private int getNextDirection() {
        Character c = moves.get(moveIndex);
        moveIndex++;
        if (c == '>') {
            return 0;
        } else if (c == 'v') {
            return 1;
        } else if (c == '<') {
            return 2;
        } else if (c == '^') {
            return 3;
        }
        throw new IllegalArgumentException("Invalid direction " + c);
    }

    /**
     * Computes whether the robot can push the boxes in front of it.
     * @param i The first coordinate of the object that is trying to push.
     *          The said object is initially the robot, then each crate in the path in recursive calls.
     * @param j The second coordinate of the object that is trying to push.
     *          The said object is initially the robot, then each crate in the path in recursive calls.
     * @param dir The direction index in which the object is being pushed.
     * @param seenOtherPartOfBox Whether the other part of a wide box has already been computed (avoids infinite recursion).
     * @return whether the robot can push the boxes in front of it.
     */
    private boolean canPush(int i, int j, int dir, boolean seenOtherPartOfBox) {
        int[] dij = directions[dir];
        Character symbol = this.get(i + dij[0], j + dij[1]);

        if (symbol == '#') {return false;}
        else if (symbol == '.') {return true;}
        else if (symbol == 'O') {return canPush(i + dij[0], j + dij[1], dir);}
        else if (symbol == '[') {
            if (dir%2 == 1 && !seenOtherPartOfBox) {
                return canPush(i+dij[0], j+dij[1], dir) && canPush(i+dij[1], j+dij[1]+1, dir, true);
            } else {
                return canPush(i+dij[0], j+dij[1], dir);
            }
        }  else if (symbol == ']') {
            if (dir%2 == 1 && !seenOtherPartOfBox) {
                return canPush(i+dij[0], j+dij[1], dir) && canPush(i+dij[1], j+dij[1]-1, dir, true);
            } else {
                return canPush(i+dij[0], j+dij[1], dir);
            }
        }
        throw new IllegalArgumentException("Invalid map symbol " + symbol);
    }

    /**
     * Computes whether the robot can push the boxes in front of it.
     * @param i The first coordinate of the object that is trying to push.
     *          The said object is initially the robot, then each crate in the path in recursive calls.
     * @param j The second coordinate of the object that is trying to push.
     *          The said object is initially the robot, then each crate in the path in recursive calls.
     * @param dir The direction index in which the object is being pushed.
     * @return whether the robot can push the boxes in front of it.
     */
    private boolean canPush(int i, int j, int dir) {
        return canPush(i, j, dir, false);
    }

    /**
     * Pushes the robot by one tile in a direction, and pushes every crate in its path.
     * @param i The first coordinate of the object that is pushing.
     *          The said object is initially the robot, then each crate in the path in recursive calls.
     * @param j The second coordinate of the object that is pushing.
     *          The said object is initially the robot, then each crate in the path in recursive calls.
     * @param dir The direction index in which the object is being pushed.
     * @param seenOtherPartOfBox Whether the other part of a wide box has already been computed (avoids infinite recursion).
     */
    private void push(int i, int j, int dir, char lastSymbol, boolean seenOtherPartOfBox) {
        int[] dij = directions[dir];
        Character symbol = this.get(i + dij[0], j + dij[1]);

        if (symbol == '#') {
            throw new RuntimeException("Cannot push.");
        }
        else if (symbol == '.') {
            this.set(i + dij[0], j + dij[1], lastSymbol);
        }
        else if (symbol == 'O') {
            push(i + dij[0], j + dij[1], dir, symbol);
            this.set(i + dij[0], j + dij[1], lastSymbol);
        }
        else if (symbol == '[') {
            if (dir%2 == 1 && !seenOtherPartOfBox) {
                push(i+dij[0], j+dij[1], dir, symbol);
                push(i+dij[1], j+dij[1]+1, dir, '.', true);
                this.set(i + dij[0], j + dij[1], lastSymbol);
            } else {
                push(i+dij[0], j+dij[1], dir, symbol);
                this.set(i + dij[0], j + dij[1], lastSymbol);
            }
        }  else if (symbol == ']') {
            if (dir%2 == 1 && !seenOtherPartOfBox) {
                push(i+dij[0], j+dij[1], dir, symbol);
                push(i+dij[1], j+dij[1]-1, dir, '.', true);
                this.set(i + dij[0], j + dij[1], lastSymbol);
            } else {
                push(i+dij[0], j+dij[1], dir, symbol);
                this.set(i + dij[0], j + dij[1], lastSymbol);
            }
        } else {throw new IllegalArgumentException("Invalid map symbol " + symbol);}
    }

    /**
     * Pushes the robot by one tile in a direction, and pushes every crate in its path.
     * @param i The first coordinate of the object that is pushing.
     *          The said object is initially the robot, then each crate in the path in recursive calls.
     * @param j The second coordinate of the object that is pushing.
     *          The said object is initially the robot, then each crate in the path in recursive calls.
     * @param dir The direction index in which the object is being pushed.
     */
    private void push(int i, int j, int dir, char lastSymbol) {
        push(i, j, dir, lastSymbol, false);
    }

    /**
     * Computes the robot's next move (whether it can actually move or not).
     */
    private void move() {
        int dir = getNextDirection();
        if (canPush(robotCoos.i, robotCoos.j, dir)) {
            this.set(robotCoos.i, robotCoos.j, '.');
            push(robotCoos.i, robotCoos.j, dir, '@');
            int[] dij = directions[dir];
            robotCoos.i += dij[0]; robotCoos.j += dij[1];
        }
    }

    /**
     * Moves the robot by its entire sequence of this.moves.
     */
    public void moveSequence() {
        for (int i = 0; i < this.moves.size(); i++) {
            move();
        }
    }

    /**
     * Computes the current GPS sum of every crate in the this.matrix map.
     * @return the current GPS sum of every crate in the this.matrix map.
     */
    public int getGPSSum() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                if (Set.of('O', '[').contains(this.get(i, j))) {
                    sum += getGPS(i, j);
                }
            }
        }
        return sum;
    }
}
