package Day23;

import utils.AOCFileReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> input = AOCFileReader.getInstance().readLines(23);

        Lan lan = new Lan(input);

        int part1res = lan.getGroupsOf3().size();
        // String part2res = lan.getLargestGroup();

        System.out.println("Part 1: " + part1res);
        // System.out.println("Part 2: " + part2res);
    }
}
