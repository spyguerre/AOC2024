package Day14;

import java.util.List;
import java.util.ArrayList;

/**
 * A class representing a list of Robots.
 */
public class Robots {
    /**
     * The list of Robots.
     */
    public List<Robot> robots;
    /**
     * The size of the map in which the Robots are. n is for X, p for Y.
     */
    public int n, p;

    /**
     * Constructor for a list of Robots.
     * @param robots The list of Robots.
     * @param n The size of the map along the X axis.
     * @param p The size of the map along the Y axis.
     */
    public Robots(List<Robot> robots, int n, int p) {
        this.robots = new ArrayList<>(robots);
        this.n = n;
        this.p = p;
    }
    /**
     * Constructor for a list of Robots. The size of the map is (101, 103) by default.
     */
    public Robots(List<Robot> robots) {
        this(robots, 101, 103);
    }

    /**
     * Appends a Robot to the list of Robots.
     * @param robot The  Robot to add.
     */
    public void add(Robot robot) {
        robots.add(robot);
    }

    /**
     * Moves all the Robots at their respective speed for n seconds.
     * @param n The number of seconds to fast forward to.
     * @param print If set to true, prints the Robots' map at each iteration.
     */
    public void move(int n, boolean print) {
        for (int i = 0; i < n; i++) {
            for (Robot robot : robots) {
                robot.move(this.n, this.p);
            }
            if (print) {
                System.out.println("Iteration " + i+1);
                this.printRobots();
            }
        }
    }

    /**
     * Computes the safety factor, as described in the subject.
     * @return the safety factor, as described in the subject.
     */
    public int getSafetyFactor() {
        int[] quadrants = new int[4];
        for (Robot robot : robots) {
            if (robot.x < n/2 && robot.y < p/2) {
                quadrants[0]++;
            } else if (robot.x > n/2 && robot.y < p/2) {
                quadrants[1]++;
            } else if (robot.x < n/2 && robot.y > p/2) {
                quadrants[2]++;
            } else if (robot.x > n/2 && robot.y > p/2) {
                quadrants[3]++;
            }
        }

        return quadrants[0] * quadrants[1] * quadrants[2] * quadrants[3];
    }

    /**
     * Displays the current map of Robots.
     */
    public void printRobots() {
        // Initializes the String matrix used as the map
        List<List<String>> matrix = new ArrayList<>();
        for (int y = 0; y < p; y++) {
            List<String> row = new ArrayList<>();
            matrix.add(row);
            for (int x = 0; x < n; x++) {
                row.add(".");
            }
        }

        // Places the robots on the map
        for (Robot robot : robots) {
            String s = matrix.get(robot.y).get(robot.x);
            if (s.equals(".")) {
                matrix.get(robot.y).set(robot.x, "1");
            } else {
                matrix.get(robot.y).set(robot.x, String.valueOf((Integer.parseInt(s) + 1)));
            }
        }

        // Displays the map to the stdout.
        for (int y = 0; y < p; y++) {
            StringBuilder row = new StringBuilder(matrix.get(y).getFirst());
            for (int x = 0; x < n; x++) {
                row.append(matrix.get(y).get(x));
            }
            System.out.println(row);
        }
        System.out.println("\n\n");
    }
}
