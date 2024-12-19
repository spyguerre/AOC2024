package Day18;

import utils.Coordinate;
import utils.Map2D;

/**
 * Class for a Node representing a byte from the memory matrix.
 */
public class Node {
    /**
     * Whether the byte is corrupted.
     */
    public boolean isCorrupted = false;
    /**
     * The Node Node to go to in order to find the shortest path to the entrance. Is null if not yet found.
     */
    public Node origin = null;
    /**
     * The number of Nodes to travel from the top-left of the matrix in order to find this Node.
     */
    public Integer cost = null;
    /**
     * Coordinates of the Node.
     */
    public final Coordinate coos;

    /**
     * Constructor of a Node.
     * @param i The first coordinate of the Node.
     * @param j The second coordinate of the Node.
     */
    public Node(int i, int j) {
        coos = new Coordinate(i, j);
    }

    /**
     * Returns a String representing the Node.
     * @return a String of length 1 containing '#' if Node is corrupted, '.' if Node has no origin,
     * else the direction of the this.origin.
     */
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
