package Day16;

import utils.AOCFileReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<List<Character>> matrix = AOCFileReader.getInstance().readCharMatrix(16);
        List<List<Node>> nodeMatrix = OlympicsMap.makeNodeMatrix(matrix);

        OlympicsMap map = new OlympicsMap(nodeMatrix);

        int part1res = map.optimizeScores();

        int part2res = map.countGoodSeats();

        System.out.println("Part 1: " + part1res);
        System.out.println("Part 2: " + part2res);
    }


}
