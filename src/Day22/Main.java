package Day22;

import utils.AOCFileReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> input = AOCFileReader.getInstance().readLines(22);

        Buyers buyers = new Buyers(input, 2000);
        long part1res = buyers.getSecretSum();
        // Part 2 is a bit slow to ensure the heuristic method is optimal, but still works within 50 seconds or so for me!
        long part2res = buyers.getBestPrice();

        System.out.println("Part 1: " + part1res);
        System.out.println("Part 2: " + part2res);
    }
}
