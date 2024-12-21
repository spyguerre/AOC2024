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
     * Constructor for a Coordinate.
     * @param c The Coordinate to copy.
     */
    public Coordinate(Coordinate c) {
        this.i = c.i;
        this.j = c.j;
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
     * Checks if this equals other coordinates.
     * @param i another coordinate.
     * @param j another coordinate.
     * @return whether this equals the other coordinate.
     */
    public boolean equals(int i, int j) {
        return this.i == i && this.j == j;
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

    /**
     * Goes to the next available coordinate, in reading order.
     * Only explores the tiles that are at most distance away (using Manhattan distance) from (iCenter, jCenter).
     * @param iCenter The first coordinate of the center of the shape to explore.
     * @param jCenter The second coordinate of the center of the shape to explore.
     * @param distance The maximum distance in tiles to explore from the center.
     *                 0 would only explore one tile (the center), 1 could explore 5, and so on.
     * @return if it was able to do so, ie if the coordinates were not already the end of the shape before calling this method.
     */
    public boolean nextTaxicab(int iCenter, int jCenter, int distance) {
        if (i == iCenter+distance) { // Reached the bottom of the exploration zone.
            return false;
        } else if (j >= jCenter && Math.abs(i - iCenter) + Math.abs(j - jCenter) == distance) {
            // Currently on the edge of the right side of the shape.
            this.i++;
            this.j = jCenter - distance + Math.abs(i - iCenter);
        } else {
            this.j++;
        }

        return true;
    }

    /**
     * Goes to the next available coordinate, in reading order.
     * Only explores the tiles that are at most distance away (using Manhattan distance) from (iCenter, jCenter).
     * @param center The Coordinates of the center of the shape to explore.
     * @param distance The maximum distance in tiles to explore from the center.
     *                 0 would only explore one tile (the center), 1 could explore 5, and so on.
     * @return if it was able to do so, ie if the coordinates were not already the end of the shape before calling this method.
     */
    public boolean nextTaxicab(Coordinate center, int distance) {
        return nextTaxicab(center.i, center.j, distance);
    }

    public String toString() {
        return "(" + i + ", " + j + ")";
    }

    /**
     * @return a copy of the Coordinate.
     */
    public Coordinate clone() {
        return new Coordinate(i, j);
    }

    /**
     * Compares this Coordinate to another Coordinate, using reading order.
     * @param c The other Coordinate object to compate this to.
     * @return -1 if this Coordinate is before c in reading order, 0 if they are equal, and 1 if it is after.
     */
    public int compare(Coordinate c) {
        if (this.i != c.i) {
            return this.i > c.i ? 1 : -1;
        } else if (this.j != c.j) {
            return this.j > c.j ? 1 : -1;
        } else {
            return 0;
        }
    }
}
