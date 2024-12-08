package Day08;

import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable;
import java.util.Dictionary;

/**
 * Class representing a map of antennas.
 */
public class AntennaMap {
    /**
     * Representation of the map in a matrix of Character. Contains '.' and chars in this.frequencies.
     */
    public List<List<Character>> matrix;
    /**
     * Dictionary in which any frequency is associated with its list of coordinates of antennas.
     */
    public Dictionary<Character, List<Coordinate>> antennas;
    /**
     * Dictionary in which any frequency is associated with its list of coordinates of antinodes.
     */
    public Dictionary<Character, List<Coordinate>> antinodes;
    /**
     * All frequencies available.
     */
    private char[] frequencies = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    /**
     * Constructor for an AntennaMap.
     * @param matrix Matrix of char containing '.', letters, LETTERS, and numbers.
     * @param resonance Whether antinodes should be computed by using resonance or not. false by default.
     */
    public AntennaMap(List<List<Character>> matrix, boolean resonance) {
        this.matrix = new ArrayList<>();
        for (List<Character> row : matrix) {
            this.matrix.add(new ArrayList<>(row));
        }

        this.antennas = new Hashtable<>();
        for (Character c : this.frequencies) {
            this.antennas.put(c, new ArrayList<>());
        }
        this.initAntennas();

        this.antinodes = new Hashtable<>();
        for (Character c : frequencies) {
            this.antinodes.put(c, new ArrayList<>());
        }
        this.initAntinodes(resonance);
    }

    public AntennaMap(List<List<Character>> matrix) {
        this(matrix, false);
    }

    /**
     * Initializes the this.antenna Dictionary.
     */
    private void initAntennas() {
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                Character c = matrix.get(i).get(j);
                if (c != '.') {
                    this.antennas.get(c).add(new Coordinate(i, j));
                }
            }
        }
    }

    /**
     * Initializes the this.antinodes Dictionary.
     * If not resonance: Only add antinodes that are 1 distance away from the closest antenna of the pair.
     * If resonance: Add antinodes for every distance (starting at 0) from the closest antenna of the pair.
     */
    public void initAntinodes(boolean resonance) {
        int n = this.matrix.size(); int p = this.matrix.get(0).size();
        for (Character f : frequencies) {
            for (Coordinate coos1 : this.antennas.get(f)) {
                for (Coordinate coos2 : this.antennas.get(f)) {
                    if (!coos1.equals(coos2)) {
                        Coordinate distance = new Coordinate(coos2.i-coos1.i, coos2.j-coos1.j);

                        boolean inMap = true;
                        int k = resonance ? 0 : 1;
                        Coordinate antinode1;
                        Coordinate antinode2;

                        while (inMap && (resonance || k == 1)) {
                            antinode1 = new Coordinate(coos1.i-distance.i*k, coos1.j-distance.j*k);
                            antinode2 = new Coordinate(coos2.i+distance.i*k, coos2.j+distance.j*k);
                            inMap = false;

                            if (antinode1.isInMap(n, p)) {
                                this.antinodes.get(f).add(antinode1);
                                inMap = true;
                            }
                            if (antinode2.isInMap(n, p)) {
                                this.antinodes.get(f).add(antinode2);
                                inMap = true;
                            }

                            k++;
                        }
                    }
                }
            }
        }
    }

    /**
     * Counts how many antinodes are on this AntennaMap. Multiple antinodes on a single spot only counts as one.
     */
    public Integer countAntinodes() {
        int count = 0;
        List<List<Character>> antinodesMap = new ArrayList<>(); // Contains . by default, set to # if antinode in that place.
        for (int i = 0; i < this.matrix.size(); i++) {
            List<Character> row = new ArrayList<>();
            antinodesMap.add(row);
            for (int j = 0; j < this.matrix.get(i).size(); j++) {
                row.add('.');
            }
        }

        for (Character f : frequencies) {
            for (Coordinate antinode : this.antinodes.get(f)) {
                if (antinodesMap.get(antinode.i).get(antinode.j) == '.') {
                    count++;
                    antinodesMap.get(antinode.i).set(antinode.j, '#');
                }
            }
        }

        return count;
    }
}
