package Day10;

import java.util.List;
import utils.*;

public class Main {
    public static void main(String[] args) {
        List<List<Integer>> input = AOCFileReader.getInstance().readIntMatrix(10);

        HikingMap map = new HikingMap(input);
        int part1res = map.totalTrailCount(false);

        // Casually copied my code here because I misread part 1 and did part 2 first...
        int part2res = map.totalTrailCount(true);

        System.out.println("Part 1: " + part1res);
        System.out.println("Part 2: " + part2res);
    }
}
