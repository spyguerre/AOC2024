package Day18;

import utils.AOCFileReader;
import utils.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> input = AOCFileReader.getInstance().readLines(18);
        List<List<Node>> matrix = new ArrayList<>();

        // Hyperparameters of the problem
        int n = 71, p = 71;
        int instantBytes = 1024;

        // Initializes the matrix with Nodes.
        for (int i = 0; i < n; i++) {
            List<Node> row = new ArrayList<>();
            matrix.add(row);
            for (int j = 0; j < p; j++) {
                row.add(new Node(i, j));
            }
        }

        // Mark the first 1024 Nodes of the list as corrupted
        for (int k = 0; k < instantBytes; k++) {
            String line = input.get(k);
            int j = Integer.parseInt(line.split(",")[0]);
            int i = Integer.parseInt(line.split(",")[1]);
            matrix.get(i).get(j).isCorrupted = true;
        }

        Memory memory = new Memory(matrix);

        memory.dijkstra();

        int part1res = memory.getPathLength();

        // Adds one more corrupted byte from the list and tries to find a path, at every iteration.
        Coordinate part2res = new Coordinate(-1, -1);
        for (int k = instantBytes; k < input.size(); k++) {
            memory.resetDijkstra();

            String line = input.get(k);
            int j = Integer.parseInt(line.split(",")[0]);
            int i = Integer.parseInt(line.split(",")[1]);
            matrix.get(i).get(j).isCorrupted = true;
            part2res.i = i; part2res.j = j;

            memory.dijkstra();

            Integer check = memory.getPathLength();

            if (check == null) {
                break;
            }
        }

        System.out.println("Part 1: " + part1res);
        System.out.println("Part 2: " + part2res.j+","+part2res.i);
        // Gave this many wrong answers because i and j are inverted ://
    }
}
