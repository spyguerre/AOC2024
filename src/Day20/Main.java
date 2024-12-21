package Day20;

import utils.AOCFileReader;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<List<Character>> charMatrix = AOCFileReader.getInstance().readCharMatrix(20);

        // Fill the matrix using Nodes, initialized properly.
        List<List<Node>> matrix = new ArrayList<>();
        for (int i = 0; i < charMatrix.size(); i++) {
            List<Node> row = new ArrayList<>();
            matrix.add(row);
            for (int j = 0; j < charMatrix.get(i).size(); j++) {
                row.add(new Node(i, j, charMatrix.get(i).get(j)));
            }
        }

        RaceTrack track = new RaceTrack(matrix);

        track.dijkstra();

        int part1res = track.getCheatPossibilities(2, 100);
        int part2res = track.getCheatPossibilities(20, 100);

        System.out.println("Part 1: " + part1res);
        System.out.println("Part 2: " + part2res);
    }
}
