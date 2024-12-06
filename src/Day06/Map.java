package Day06;

import java.util.List;
import java.util.ArrayList;

public class Map {
    public List<List<Character>> initialMatrix;
    public List<List<Character>> matrix;
    public int[] initialGuard;
    public int[] guard;
    public int facing; // 0 for right, 3 for up
    public boolean guardInLab;

    public Map(List<List<Character>> matrix) {
        // Deepcopy matrix into this.initialMatrix
        this.initialMatrix = new ArrayList<>();
        for (List<Character> subList : matrix) {
            this.initialMatrix.add(new ArrayList<>(subList));
        }

        // Deepcopy matrix into this.matrix
        this.matrix = new ArrayList<>();
        for (List<Character> subList : matrix) {
            this.matrix.add(new ArrayList<>(subList));
        }

        this.initGuard();
    }

    private void initGuard() {
        this.facing = 3;
        this.guardInLab = true;
        for (int i = 0; i < this.matrix.size(); i++) {
            for (int j = 0; j < this.matrix.get(i).size(); j++) {
                char c = this.matrix.get(i).get(j);
                if (c != '#' && c != '.') {
                    this.initialGuard = new int[]{i, j};
                    this.guard = new int[]{i, j};
                }
            }
        }
    }

    private boolean markTile() {
        boolean alreadyMarked = this.matrix.get(this.guard[0]).get(this.guard[1]) == 'X';
        this.matrix.get(this.guard[0]).set(this.guard[1], 'X');

        return alreadyMarked;
    }

    private void turnRight() {
        if (this.facing == 3) {
            this.facing = 0;
        } else {
            this.facing++;
        }
    }

    private boolean edgeFound() {
        boolean found = this.guard[0] == 0 && this.facing == 3;
        found = found || this.guard[1] == 0 && this.facing == 2;
        found = found || this.guard[0] == this.matrix.size() - 1 && this.facing == 1;
        found = found || this.guard[1] == this.matrix.get(0).size() - 1 && this.facing == 0;
        return found;
    }

    private boolean guardStep() {
        int[] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}[this.facing];
        int[] newForwardPos = {this.guard[0] + direction[0], this.guard[1] + direction[1]};

        if (this.edgeFound()) { // Guard leaves the lab
            this.guardInLab = false;
        } else if (this.matrix.get(newForwardPos[0]).get(newForwardPos[1]) != '#') { // Free to move forward
            this.guard = newForwardPos;
        } else { // Obstacle forward
            this.turnRight();
            return this.guardStep();
        }
        return this.markTile(); // Returns whether tile was already marked before or not
    }

    public void print() {
        for (int i = 0; i < this.matrix.size(); i++) {
            String line = "";
            for (int j = 0; j < this.matrix.get(i).size(); j++) {
                line += this.matrix.get(i).get(j);
            }
            System.out.println(line);
        }
        System.out.println("\n");
    }

    public boolean multiGuardStep(boolean checkLoop) {
        int stepsSinceLastMark = 0;
        boolean condition = true;

        while (condition) {
            boolean alreadyMarked = guardStep();

            // Update stepsSinceLastMark
            if (!alreadyMarked) {
                stepsSinceLastMark = 0;
            } else {
                stepsSinceLastMark++;
            }

            // Update loop condition
            if (checkLoop) {
                condition = stepsSinceLastMark < this.matrix.size() && this.guardInLab;
            } else {
                condition = this.guardInLab;
            }
        }

        if (checkLoop && this.guardInLab) { // Loop found
            return true;
        } else { // guard is out
            return false;
        }
    }

    public void multiGuardStep() {
        this.multiGuardStep(false);
    }

    public Integer countTotalPositions() {
        int count = 0;
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                count += matrix.get(i).get(j) == 'X' ? 1 : 0;
            }
        }
        return count;
    }

    public void resetMap() {
        this.matrix = new ArrayList<>();
        for (List<Character> subList : this.initialMatrix) {
            this.matrix.add(new ArrayList<>(subList));
        }

        this.facing = 3;
        this.guardInLab = true;

        this.guard = new int[]{this.initialGuard[0], this.initialGuard[1]};
    }

    public Integer countTotalLoops() {
        int count = 0;
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                this.resetMap(); // Resets matrix to initialMatrix and resets guard info
                if (i != this.guard[0] || j != this.guard[1]) {
                    this.matrix.get(i).set(j, '#');
                    count += this.multiGuardStep(true) ? 1 : 0;
                }
            }
        }
        return count;
    }
}