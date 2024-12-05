package Day05;

import utils.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ManualData manual = AOCFileReader.getInstance().readManual(5);

        Integer part1res = manual.sumMiddlePagesFromValidUpdates();
        Integer part2res = manual.sumMiddlePagesFromValidUpdates(true); // No need to create another ManualData since it wasn't modified for the first part

        System.out.println("Part 1: " + part1res);
        System.out.println("Part 2: " + part2res);
    }
}