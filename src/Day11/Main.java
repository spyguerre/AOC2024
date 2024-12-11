package Day11;

import java.util.List;
import utils.*;

public class Main {
    public static void main(String[] args) {
        List<Long> input = AOCFileReader.getInstance().readLongList(11);

        StoneList stones = new StoneList(input);

        stones.multiBlinkAllStones(25);

        long part1res = stones.getStoneCount();

        stones.multiBlinkAllStones(50);

        long part2res = stones.getStoneCount();

        System.out.println("Part 1: " + part1res);
        System.out.println("Part 2: " + part2res);
    }
}
