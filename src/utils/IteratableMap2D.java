package utils;

import java.util.List;

/**
 * Represents a 2D map, useful to represent some puzzle inputs, that is iterable thanks to a few methods.
 * @param <E> The type of data stored in the map.
 */
public abstract class IteratableMap2D<E> extends Map2D<E> {
    /**
     * The current coordinate of the map's iteration.
     */
    public Coordinate coos;

    /**
     * Constructor for an IterableMap.
     * @param matrix The matrix that the StructMap should represent.
     */
    public IteratableMap2D(List<List<E>> matrix) {
        super(matrix);
        this.coos = new Coordinate(0, -1);
    }

    /**
     * Getter for an item in the this.matrix.
     * @return the item currently at position represented by this.coos in this.matrix.
     */
    public E get() {
        return this.get(coos.i, coos.j);
    }

    /**
     * Travels to the next item of the list that is equal to element x.
     * @param x The next item to stop at.
     * @return whether it has found the element searched (true) or reached the end of the map (false). false if both.
     */
    public boolean goToNextX(E x) {
        while (this.coos.next(this.n, this.p) && this.get() != x) {}

        return !(coos.i == n-1 && coos.j == p-1);
    }
}
