package utils;

import java.util.List;

/**
 * Represents a 2D map, useful to represent some puzzle inputs.
 * @param <E> The type of data stored in the map.
 */
public abstract class StructMap<E> {
    /**
     * The matrix in which the map data is stored.
     */
    public List<List<E>> matrix;
    /**
     * The first coordinate of the size of the matrix.
     */
    public int n;
    /**
     * The second coordinate of the size of the matrix.
     */
    public int p;

    /**
     * The constructor for a StructMap.
     * @param matrix The matrix that the StructMap should represent.
     */
    public StructMap(List<List<E>> matrix) {
        this.matrix = matrix;
        this.n = matrix.size(); this.p = matrix.getFirst().size();
    }

    /**
     * Getter for an item in the this.matrix.
     * @param i The first coordinate of the item to get.
     * @param j The second coordinate of the item to get.
     * @return the item at position (i, j) in this.matrix.
     */
    public E get(int i, int j) {
        return matrix.get(i).get(j);
    }
}
