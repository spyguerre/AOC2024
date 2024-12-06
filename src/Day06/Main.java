package Day06;

import utils.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<List<Character>> matrix = AOCFileReader.getInstance().readCharMatrix(6);

        Map map = new Map(matrix);

        map.multiGuardStep();

        int part1res = map.countTotalPositions();
        Map map2 = new Map(matrix);

        int part2res = map2.countTotalLoops();

        System.out.println("Part 1: " + part1res);
        System.out.println("Part 2: " + part2res);
    }
}
