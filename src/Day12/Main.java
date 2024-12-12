package Day12;

import utils.AOCFileReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<List<Character>> input = AOCFileReader.getInstance().readCharMatrix(12);

        GardeningMap garden = new GardeningMap(input);

        int part1res = garden.getPrice(false);
        int part2res = garden.getPrice(true); // Had to try and code like 3 different approaches...

        System.out.println("Part 1: " + part1res);
        System.out.println("Part 2: " + part2res);
    }
}
