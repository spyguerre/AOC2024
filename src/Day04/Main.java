package Day04;

import utils.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> lines = AOCFileReader.getInstance().readLines(4);

        XmasFinder straightFinder = new XmasFinder(lines, "XMAS", "straight");

        int part1res = straightFinder.findAllXmas();

        XmasFinder crossFinder = new XmasFinder(lines, "MAS", "cross");

        int part2res = crossFinder.findAllXmas();

        System.out.println("Part 1: " + part1res);
        System.out.println("Part 2: " + part2res);
    }
}
