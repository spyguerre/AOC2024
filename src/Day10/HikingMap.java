package Day10;

import java.util.List;
import java.util.ArrayList;
import utils.*;

/**
 * A class that represents a topographic map using a matrix of integers.
 */
public class HikingMap {
    /**
     * The topographic map.
     */
    private List<List<Integer>> matrix;
    /**
     * The coordinates that are used to search the graph.
     */
    private Coordinate coos;
    /**
     * The directions in which a hiker can travel.
     */
    private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    /**
     * Constructor for a HikingMap.
     * @param matrix A topologic map, which is deepcopied.
     */
    public HikingMap(List<List<Integer>> matrix) {
        this.matrix = new ArrayList<>();
        for (List<Integer> row : matrix) {
            this.matrix.add(new ArrayList<>(row));
        }

        this.coos = new Coordinate(0, -1); // j is -1 in case there is a 0 in coordinates (0, 0).
    }

    /**
     * Goes to the next 0 value in this.matrix using this.coos, in reading order.
     * @return
     */
    private boolean goToNext0() {
        boolean reachedTheEnd = false;

        coos.next(matrix.size(), matrix.get(0).size()); // Leave the current 0

        while (!reachedTheEnd && matrix.get(coos.i).get(coos.j) != 0) {
            reachedTheEnd = !coos.next(matrix.size(), matrix.get(0).size());
        }

        return reachedTheEnd;
    }

    /**
     * Counts how many h+1's are accessible from the position (i, j) by hiking towards any of this.directions in this.matrix.
     * @param i The current position's first coordinate.
     * @param j The current position's second coordinate.
     * @param h The current position's height.
     * @param coosList The list that is to be updated during execution of this method; contains a list of Coordinates of the 9's found by hiking from i, j at height h.
     */
    private void trail9CountRec(int i, int j, int h, List<Coordinate> coosList) {
        if (h == 8) {
            for (int[] direction : directions) {
                boolean inMap = (new Coordinate(i+direction[0], j+direction[1])).isInMap(matrix.size(), matrix.get(0).size());
                if (inMap) {
                    if (matrix.get(i+direction[0]).get(j+direction[1]) == 9) {
                        Coordinate newCoos = new Coordinate(i+direction[0], j+direction[1]);
                        boolean alreadyInList = false;
                        for (Coordinate coos : coosList) {
                            if (newCoos.equals(coos)) {
                                alreadyInList = true;
                                break;
                            }
                        }
                        if (!alreadyInList) {
                            coosList.add(newCoos);
                        }
                    }
                }
            }
        } else {
            for (int[] direction : directions) {
                boolean inMap = (new Coordinate(i+direction[0], j+direction[1])).isInMap(matrix.size(), matrix.get(0).size());
                if (inMap) {
                    if (matrix.get(i+direction[0]).get(j+direction[1]) == h+1) {
                        trail9CountRec(i+direction[0], j+direction[1], h+1, coosList);
                    }
                }
            }
        }
    }

    /**
     * Counts how many 9's are accessible from the position (this.coos.i, this.coos.j) by hiking towards any of this.directions in this.matrix.
     * @return The number of different 9's found by exploring.
     */
    private int trail9Count() {
        if (matrix.get(coos.i).get(coos.j) != 0) {
            return 0;
        }
        List<Coordinate> coosList = new ArrayList<>();
        trail9CountRec(this.coos.i, this.coos.j, 0, coosList); // Updates coosList
        return coosList.size();
    }

    /**
     * Counts the different hiking trails available from height h at coordinates (i, j) to height 9.
     * @param i The current position's first coordinate.
     * @param j The current position's second coordinate.
     * @param h The current position's height.
     * @return The number of different hiking trails found.
     */
    private int trailCountRec(int i, int j, int h) {
        int count = 0;

        if (h == 8) {
            for (int[] direction : directions) {
                boolean inMap = (new Coordinate(i+direction[0], j+direction[1])).isInMap(matrix.size(), matrix.get(0).size());
                if (inMap) {
                    count += matrix.get(i+direction[0]).get(j+direction[1]) == 9 ? 1 : 0;
                }
            }
        } else {
            for (int[] direction : directions) {
                boolean inMap = (new Coordinate(i+direction[0], j+direction[1])).isInMap(matrix.size(), matrix.get(0).size());
                if (inMap) {
                    if (matrix.get(i+direction[0]).get(j+direction[1]) == h+1) {
                        count += trailCountRec(i+direction[0], j+direction[1], h+1);
                    }
                }
            }
        }

        return count;
    }

    /**
     * Counts the different hiking trails available from height 0 at coordinates (this.coos.i, this.coos.j) to height 9.
     * @return the number of different hiking trails found.
     */
    private int trailCount() {
        if (matrix.get(coos.i).get(coos.j) != 0) {
            return 0;
        }
        return trailCountRec(this.coos.i, this.coos.j, 0);
    }

    /**
     * Computes either the sum of scores of all trailheads, or the sum of the rating of all trailheads, depending on parameter distinct.
     * @param distinct If distinct: computes sum of scores; Else: computes sum of ratings.
     * @return the sum of the scores or ratings of all trailheads.
     */
    public int totalTrailCount(boolean distinct) {
        boolean reachedTheEnd = false;
        int count = 0;
        this.goToNext0();

        while (!reachedTheEnd) {
            if (distinct) {
                count += trailCount();
            } else {
                count += trail9Count();
            }
            reachedTheEnd = this.goToNext0();
        }

        if (this.matrix.get(coos.i).get(coos.j) == 0) {
            if (distinct) {
                count += trailCount();
            } else {
                count += trail9Count();
            }
        }

        // Reset the attributes
        this.coos.i = 0; this.coos.j = 0;

        return count;
    }
}
