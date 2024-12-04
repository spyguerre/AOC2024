package Day04;

import java.util.List;

public class XmasFinder {
    private List<String> lines;
    private Integer[] coos = new Integer[4]; // Contains i, j for iteration and max_i, max_j
    private String word;
    private Integer xmasFound = 0;
    private String mode;

    public XmasFinder(List<String> lines, String word, String mode) {
        this.lines = lines;
        this.coos[0] = 0; this.coos[1] = -1; this.coos[2] = lines.size(); this.coos[3] = lines.get(0).length(); // j starts at -1 in case (0,0) is an X
        this.word = word;
        this.mode = mode;
    }

    // Reads char at i, j
    public char readChar(int i, int j) {
        return lines.get(i).charAt(j);
    }

    // Reads char at this.coos[0], this.coos[1]
    public char readChar() {
        return this.readChar(this.coos[0], this.coos[1]);
    }

    // Returns whether it can advance to the next character (no end of file), in which case it does.
    public boolean nextChar() {
        if (coos[1] < coos[3] - 1) { // Advance in the line
            coos[1]++;
            return true;
        } else if (coos[0] < coos[2] - 1) { // Go to next line
            coos[1] = 0; coos[0]++;
            return true;
        } else { // Reached the end of the file
            return false;
        }
    }

    // Goes to next X character or the end of file and returns whether it has found the X (true) or the EOF (false)
    public boolean goToNextX() {
        if (this.mode.equals("straight")) {
            while (this.nextChar() && readChar() != this.word.charAt(0)) {}
        } else if (this.mode.equals("cross")) {
            while (this.nextChar() && readChar() != this.word.charAt(this.word.length()/2)) {}
        }
        if (coos[0] < coos[2] - 1 || coos[1] < coos[3] - 1) {
            return true;
        } else {
            return false;
        }
    }

    // Adds to this.xmasFound how many XMAS are found for the current X
    public void checkForXMAS() {
        Integer[][] directions = {
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1},
            {0, -1},
            {-1, -1},
            {-1, 0},
            {-1, 1}
        };

        for (Integer[] dir : directions) {
            boolean valid = true;
            for (int k = 0; k < this.word.length(); k++) {
                Integer[] coosToCheck = {this.coos[0]+k*dir[0], this.coos[1]+k*dir[1]};
                if (coosToCheck[0] < 0 || coosToCheck[0] >= this.coos[2] || coosToCheck[1] < 0 || coosToCheck[1] >= this.coos[3]) {
                    valid = false;
                } else if (this.readChar(coosToCheck[0], coosToCheck[1]) != this.word.charAt(k)) {
                    valid = false;
                }
            }
            if (valid) {
                this.xmasFound++;
            }
        }
    }

    public void checkForMASInX() {
        Integer[][][] directions = {
                {
                        {1, 1},
                        {0, 0},
                        {-1, -1}
                },
                {
                        {1, -1},
                        {0, 0},
                        {-1, 1}
                },
                {
                        {-1, -1},
                        {0, 0},
                        {1, 1}
                },
                {
                        {-1, 1},
                        {0, 0},
                        {1, -1}
                }

        };

        int patternsMatched = 0;
        for (Integer[][] dir : directions) {

            boolean valid = true;
            for (int k = 0; k < this.word.length(); k++) {
                Integer[] coosToCheck = {this.coos[0] + dir[k][0], this.coos[1] + dir[k][1]};
                if (coosToCheck[0] < 0 || coosToCheck[0] >= this.coos[2] || coosToCheck[1] < 0 || coosToCheck[1] >= this.coos[3]) {
                    valid = false;
                } else if (this.readChar(coosToCheck[0], coosToCheck[1]) != this.word.charAt(k)) {
                    valid = false;
                }
            }
            if (valid) {
                patternsMatched++;
            }
        }
        if (patternsMatched == 2) {
            this.xmasFound++;
        }
    }

    // Find all Xmas in the text
    public Integer findAllXmas() {
        while (this.goToNextX()) {
            if (this.mode.equals("straight")) {
                this.checkForXMAS();
            } else if (this.mode.equals("cross")) {
                this.checkForMASInX();
            }
        }

        if (this.mode.equals("straight") && this.readChar() == this.word.charAt(0)) { // Just in case last char of file is X
            this.checkForXMAS();
        } else if (this.mode.equals("cross") && this.readChar() == this.word.charAt(this.word.length()/2)) {
            this.checkForMASInX();
        }

        return this.xmasFound;
    }
}


