package utils;

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

    /**
     * Checks whether this Coordinate is whithin the bounds of the map.
     * @param n Vertical size of the map. (i)
     * @param p Horizontal size of the map. (j)
     * @return whether this Coordinate is whithin the bounds of the map.
     */
    public boolean isInMap(int n, int p) {
        return this.i < n && this.j < p && this.i >= 0 && this.j >= 0;
    }

    /**
     * Goes to the next available coordinate, in reading order.
     * @param n Vertical size of the map. (i)
     * @param p Horizontal size of the map. (j)
     * @return if it was able to do so, ie if the coordinates were not already the end of the map before calling this method.
     */
    public boolean next(int n, int p) {
        if (this.j < p-1) {
            this.j++;
        } else if (this.i < n-1) {
            this.i++;
            this.j = 0;
        } else {
            return false;
        }
        return true;
    }

    public String toString() {
        return "(" + i + ", " + j + ")";
    }
}
