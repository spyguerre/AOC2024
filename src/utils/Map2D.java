package utils;

import java.util.List;

/**
 * Represents a 2D map, useful to represent some puzzle inputs.
 * @param <E> The type of data stored in the map.
 */
public abstract class Map2D<E> {
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
     * The 4 directions (di, dj) around any coordinate.
     */
    public static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    /**
     * An Array to visually display directions on a map.
     */
    public static char[] symbols = {'>', 'v', '<', '^'};

    /**
     * The constructor for a StructMap.
     * @param matrix The matrix that the StructMap should represent.
     */
    public Map2D(List<List<E>> matrix) {
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

    /**
     * Getter for an item in the this.matrix.
     * @param coos The coordinates of the item to get.
     * @return the item at position (i, j) in this.matrix.
     */
    public E get(Coordinate coos) {
        return matrix.get(coos.i).get(coos.j);
    }

    /**
     * Setter for an item in the this.matrix.
     * @param i The first coordinate of the item to set.
     * @param j The second coordinate of the item to set.
     * @param e The item to set.
     */
    public void set(int i, int j, E e) {
        matrix.get(i).set(j, e);
    }


    /**
     * Prints the map to the stdout.
     */
    public void print() {
        for (List<E> row : matrix) {
            StringBuilder str = new StringBuilder();
            for (E e : row) {
                str.append(e);
            }
            System.out.println(str);
        }

        System.out.println("\n\n");
    }
}
