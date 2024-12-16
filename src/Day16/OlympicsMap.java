package Day16;

import utils.Coordinate;
import utils.Facing;
import utils.Map2D;

import java.util.*;

/**
 * A class representing a Olympic Maze.
 */
public class OlympicsMap extends Map2D<Node> {
    /**
     * The start Coordinates of the maze.
      */
    public Coordinate s;
    /**
     * The end Coordinates of the maze.
     */
    public Coordinate e;
    /**
     * An Array of symbols to display on map when a direction is taken.
     */
    private final char[] symbols = {'>', 'v', '<', '^'};

    /**
     * Constructor for an Olympic Map.
     * @param matrix The matrix of Nodes representing the map, with costs set to null.
     */
    public OlympicsMap(List<List<Node>> matrix) {
        super(matrix);
        s = initCoos('S');
        e = initCoos('E');
        this.get(s.i, s.j).cost = 0;
        this.get(e.i, e.j).cost = Integer.MAX_VALUE;
    }

    /**
     * Static method to convert a matrix of Characters to a matrix of Nodes,
     * each with c initialized to the char value in matrix, and cost initialized to null.
     * @param matrix The matrix to create a Node matrix with.
     * @return the new matrix of Nodes.
     */
    public static List<List<Node>> makeNodeMatrix(List<List<Character>> matrix) {
        List<List<Node>> newMatrix = new ArrayList<>();
        for (List<Character> row : matrix) {
            List<Node> intRow = new ArrayList<>();
            newMatrix.add(intRow);
            for (Character c : row) {
                intRow.add(new Node(c, null));
            }
        }
        return newMatrix;
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
     * Computes which paths whithin this.direction should be taken to continue the exploration.
     * Successful paths must be non-walls and have a cost of strictly more
     * than the currentScore (+ 1 or 1001, depending on the direction).
     * @param r The current Facing object of the iteration
     * @param currentScore The current score of the tile in r.
     * @return a list of direction indexes that should be taken to continue the exploration.
     */
    private List<Integer> listMoves(Facing r, Integer currentScore) {
        List<Integer> moves = new ArrayList<>();
        for (int dir = 0; dir < 4; dir++) {
            char c = get(r.i+directions[dir][0], r.j+directions[dir][1]).c;
            if (c != '#') {
                if (dir != (r.f+2)%4) { // Don't allow turning back
                    Integer currentScoreCopy = currentScore;
                    if (currentScoreCopy == null) {currentScoreCopy = 1;}
                    if ((dir-r.f)%2 != 0) {currentScoreCopy += 1000;}

                    Integer previousScore = this.get(r.i+directions[dir][0], r.j+directions[dir][1]).cost;
                    if (previousScore == null || currentScoreCopy < previousScore) { // Don't allow loops
                        moves.add(dir);
                    }
                }
            }
        }
        return moves;
    }

    /**
     * Getter for the end's cost.
     */
    private Integer getMinFound() {
        return this.get(e.i, e.j).cost;
    }

    /**
     * Optimizes the score to reach every tile/node in this.matrix.
     * @param debug Whether to print debugging information.
     * @return the lowest score taken to reach the end of the maze.
     */
    public int optimizeScores(boolean debug) {
        List<Facing> paths = new ArrayList<>();
        paths.add(new Facing(s, 0));

        while (!paths.isEmpty()) {
            Facing f = paths.getFirst();
            paths.remove(f); // Remove it here, add the next ones necessary later on.

            int currentScore = this.get(f.i, f.j).cost;
            if (!e.equals(f)) { // Not the end tile of the maze.
                List<Integer> moves = listMoves(f, currentScore);
                if (debug) {
                    System.out.println(f);
                    System.out.println(this.get(f.i, f.j).cost);
                }
                if (moves.isEmpty()) { // Found a dead-end.
                    this.get(f.i, f.j).c = 'X';
                } else { // Keep running and explore every direction.
                    for (Integer dir: moves) {
                        Facing newF = f.clone();
                        currentScore = this.get(f.i, f.j).cost;
                        if (dir == (newF.f+3)%4) {
                            newF.turnLeft();
                            currentScore += 1000;
                            newF.moveForward();
                            currentScore += 1;
                            if (debug) {System.out.println("Turning left " + newF);}
                        } else if (dir == newF.f) {
                            newF.moveForward();
                            currentScore += 1;
                            if (debug) {System.out.println("Moving forwrd " + newF);}
                        } else if (dir == (newF.f+1)%4) {
                            newF.turnRight();
                            currentScore += 1000;
                            newF.moveForward();
                            currentScore += 1;
                            if (debug) {System.out.println("Turning right " + newF);}
                        }

                        this.get(f.i, f.j).c = symbols[newF.f]; // Update symbols on map
                        this.get(newF.i, newF.j).cost = currentScore; // Update score cost
                        if (debug) {System.out.println("Adding newF");}
                        paths.addFirst(newF);
                    }
                }
            }

            if (debug) {
                System.out.println(paths);
                this.print();
                try {Thread.sleep(500);} catch (InterruptedException ignored) {}
            }
        }

        if (debug) {this.printCosts();}

        return getMinFound();
    }

    /**
     * Optimizes the score to reach every tile/node in this.matrix.
     * @return the lowest score taken to reach the end of the maze.
     */
    public int optimizeScores() {
        return optimizeScores(false);
    }

    /**
     * Turns every 'X', '>', 'v', '<', '^' and 'o' back to '.' in this.matrix.
     */
    private void resetMatrix() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                if (Set.of('X', '>', 'v', '<', '^', 'o').contains(this.get(i, j).c)) {
                    this.get(i, j).c = '.';
                }
            }
        }
    }

    /**
     * Prints the current cost of each tile.
     */
    private void printCosts() {
        for (List<Node> row : matrix) {
            StringBuilder rowStr = new StringBuilder();
            for (Node node : row) {
                if (node.cost != null) {
                    rowStr.append(" ").append(String.format("%05d", node.cost));
                } else {
                    rowStr.append(" ").append(".....");
                }
            }
            System.out.println(rowStr);
        }
        System.out.println("\n\n");
    }

    /**
     * Marks valid shortest paths, iterating from the end to the start of the maze.
     */
    private void visualizePath() {
        resetMatrix();

        List<Coordinate> coosList = new ArrayList<>();
        coosList.add(new Coordinate(e.i, e.j));
        this.get(e.i, e.j).c = 'o';
        boolean allow999;
        boolean allowNext999 = false;

        while (!coosList.isEmpty()) {
            Coordinate coos = coosList.removeFirst();
            int cost = this.get(coos.i, coos.j).cost;
            // Following variables are to keep track of the edge case where two paths should be found;
            // one of them being in a straight line, and the other cutting into it with a lower score
            // (that then becomes equal when turning)
            allow999 = allowNext999;
            allowNext999 = false;

            for (int[] dir: directions) {
                Node node = this.get(coos.i+dir[0], coos.j+dir[1]);
                Coordinate newCoos = coos.clone();

                if (node.cost != null && (
                        node.cost == cost - 1
                        || node.cost == cost - 1001
                        || (node.cost == cost + 999 && allow999)
                )) {
                    node.c = 'o';
                    newCoos.i += dir[0];
                    newCoos.j += dir[1];
                    coosList.addFirst(newCoos);
                    if (node.cost == cost - 1001) {allowNext999 = true;} // Treat edge case
                }
            }
        }
    }

    /**
     * Counts the number of tiles that are in one of the shortest paths.
     */
    public int countGoodSeats() {
        visualizePath();

        int sum = 0;
        for (List<Node> row : matrix) {
            for (Node node : row) {
                if (node.cost != null && node.c == 'o') {
                    sum++;
                }
            }
        }
        return sum;
    }
}
