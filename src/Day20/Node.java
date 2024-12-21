package Day20;

import utils.Coordinate;
import utils.Map2D;

import java.util.Set;

/**
 * Class for a Node representing a tile from the race track.
 */
public class Node {
    /**
     * The char representing the tile on the input.
     */
    public char c;
    /**
     * The Node to go to in order to find the shortest path to the end. Is null if not yet found.
     */
    public Node origin = null;
    /**
     * The number of Nodes to travel from the start of the track in order to find this Node.
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
    public Node(int i, int j, char c) {
        coos = new Coordinate(i, j);
        this.c = c;
    }

    /**
     * Returns a String representing the Node.
     * @return a String of length 1 containing '#' if Node is a wall,
     * 'S' or 'E' if it is the start or end,
     * else the direction of the this.origin.
     */
    public String toString() {
        if (cost == null || Set.of('S', 'E').contains(c)) {
            return "" + c;
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
