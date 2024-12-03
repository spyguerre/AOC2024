package Day03;

import utils.*;

public class Main {
    public static void main(String[] args) {
        String input = AOCFileReader.getInstance().read(3);

        Parser parser = new Parser(input);

        int part1res = parser.computeMemory();

        int part2res = parser.computeMemory(true);

        System.out.println("Part 1: " + part1res);
        System.out.println("Part 2: " + part2res);
    }
}