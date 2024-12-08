package Day08;

/**
 * Simple class representing a pair of integers.
 */
public class Coordinate {
    /**
     * First coordinate.
     */
    public int i;
    /**
     * Second coordinate.
     */
    public int j;

    /**
     * Constructor for a Coordinate.
     * @param i First coordinate.
     * @param j Second coordinate.
     */
    public Coordinate(int i, int j) {
        this.i = i;
        this.j = j;
    }

    /**
     * Checks if this equals another Coordinate.
     * @param c another Coordinate.
     * @return whether this equals the other coordinate.
     */
    public boolean equals(Coordinate c) {
        return c.i == this.i && c.j == this.j;
    }

    public boolean isInMap(int n, int p) {
        return this.i < n && this.j < p && this.i >= 0 && this.j >= 0;
    }
}