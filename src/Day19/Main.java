package Day19;

import utils.AOCFileReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> input = AOCFileReader.getInstance().readLines(19);

        Onsen onsen = new Onsen(input);

        long part1res = onsen.countMatchable();
        long part2res = onsen.countMatchable(true);

        System.out.println("Part 1: " + part1res);
        System.out.println("Part 2: " + part2res);
    }
}
