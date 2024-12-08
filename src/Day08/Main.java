package Day08;

import utils.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<List<Character>> input = AOCFileReader.getInstance().readCharMatrix(8);

        AntennaMap map = new AntennaMap(input);
        int part1res = map.countAntinodes();

        AntennaMap mapWithResonance = new AntennaMap(input, true);
        int part2res = mapWithResonance.countAntinodes();

        System.out.println("Part 1: " + part1res);
        System.out.println("Part 2: " + part2res);
    }
}
