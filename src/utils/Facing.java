package utils;

/**
 * Extension of a Coordinate which saves an additional direction.
 */
public class Facing extends Coordinate {
    /**
     * The direction of the Facing object.
     */
    public int f;

    /**
     * Constructor for a Facing object.
     * @param i The first coordinate.
     * @param j The second coordinate.
     * @param f The direction faced.
     */
    public Facing(int i, int j, int f) {
        super(i, j);
        this.f = f;
    }

    /**
     * Constructor for a Facing object.
     * @param coos The Coordinates.
     * @param f The direction faced.
     */
    public Facing(Coordinate coos, int f) {
        super(coos.i, coos.j);
        this.f = f;
    }

    /**
     * Turns right by 90°, ie adds 1 to f mod 4.
     */
    public void turnRight() {
        f = (f+1) % 4;
    }

    /**
     * Turns left by 90°, ie substracts 1 to f mod 4.
     */
    public void turnLeft() {
        f = (f+3) % 4;
    }

    /**
     * Moves forward by 1 tile, in the direction faced.
     */
    public void moveForward() {
        if (f == 0) {j += 1;}
        else if (f == 1) {i += 1;}
        else if (f == 2) {j -= 1;}
        else if (f == 3) {i -= 1;}
    }

    /**
     * @return a copy of this Facing object.
     */
    public Facing clone() {
        return new Facing(i, j, f);
    }

    /**
     * @return a String representation of this object.
     */
    public String toString() {
        return "(" + i + ", " + j + ", " + new char[]{'>', 'v', '<', '^'}[f] + ")";
    }
}
