package Day20;

import utils.Coordinate;
import utils.Map2D;

import java.util.*;

/**
 * Class representing a Race Track.
 */
public class RaceTrack extends Map2D<Node> {
    /**
     * Start and End Coordinates of the track.
     */
    public Coordinate s, e;

    /**
     * Constructor for a Race Track.
     * @param matrix The matrix of Characters describing the Track.
     */
    public RaceTrack(List<List<Node>> matrix) {
        super(matrix);
        s = initCoos('S');
        e = initCoos('E');
    }

    /**
     * Helps initialize a Coordinate by finding the first occurence of a character in the this.matrix.
     * @param x The character to look for in the this.matrix.
     * @return a Coordinate object with the coordinates where x was found.
     */
    private Coordinate initCoos(char x) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                if (this.get(i, j).c == x) {return new Coordinate(i, j);}
            }
        }
        throw new RuntimeException("Couldn't find " + x + " in this.matrix.");
    }

    /**
     * Dijkstra algorithm. Explores the whole graph and saves the shortest path thanks to each Node.origin.
     */
    public void dijkstra() {
        List<Node> toVisit = new ArrayList<>();
        Node start = this.get(e.i, e.j);
        start.cost = 0;
        toVisit.add(start);

        while (!toVisit.isEmpty()) {
            Node current = toVisit.removeFirst();

            for (int[] dir : directions) {
                Coordinate foundCoos = new Coordinate(current.coos.i + dir[0], current.coos.j + dir[1]);
                if (foundCoos.isInMap(n, p)) {
                    Node found = this.get(foundCoos);

                    if (found.c != '#' && (found.cost == null || current.cost+1 < found.cost)) {
                        found.origin = current;
                        found.cost = current.cost + 1;
                        toVisit.add(found);
                    }
                }
            }
        }
    }

    /**
     * Explores all the tiles that are less than maxCheatLength tiles away from tile (i, j) using Manhattan distance,
     * to compute whether there is a cheat from (i, j) to each of these tiles, that saves at least minimumSavedTime.
     */
    private Integer countNodeCheats(int i, int j, int maxCheatLength, int minimumSavedTime) {
        Coordinate start = new Coordinate(i, j);
        Coordinate end = new Coordinate(i-maxCheatLength, j-1);

        int count = 0;
        while (end.nextTaxicab(start, maxCheatLength)) { // Iterate around (i, j) with maximal manhattan radius in parameter.
            if (end.isInMap(n, p) && this.get(end).cost != null
                    && this.get(start).cost - this.get(end).cost
                    - Math.abs(start.i-end.i) - Math.abs(start.j-end.j) >= minimumSavedTime) {
                count++;
            }
        }

        return count;
    }

    /**
     * Counts the possibilities of cheating in this Race Track. Cheats must save at least minimumSavedTime picoseconds.
     * @param maxCheatLength The maximum invicibility time of the program.
     * @param minimumSavedTime Only take cheats that save above this value picoseconds into account.
     * @return the possibilities of cheating in this Race Track, that save at least the specified value.
     */
    public int getCheatPossibilities(int maxCheatLength, int minimumSavedTime) {
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                if (this.get(i, j).c != '#') {
                    count += countNodeCheats(i, j, maxCheatLength, minimumSavedTime);
                }
            }
        }

        return count;
    }
}
