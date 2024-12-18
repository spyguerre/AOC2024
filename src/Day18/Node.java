package Day18;

import utils.Coordinate;
import utils.Map2D;

public class Node {
    public boolean isCorrupted = false;
    public Node origin = null;
    public Integer cost = null;
    public final Coordinate coos;

    public Node(int i, int j) {
        coos = new Coordinate(i, j);
    }

    public String toString() {
        if (isCorrupted) {
            return "#";
        } else if (origin == null) {
            return ".";
        } else {
            for (int i = 0; i<4; i++) {
                int[] dir = Map2D.directions[i];
                if (origin.coos.equals(coos.i+dir[0], coos.j+dir[1])) {
                    return ""+Map2D.symbols[i];
                }
            }
        }
        throw new IllegalArgumentException("Couldn't find origin Node, though it was not set to null.");
    }
}
