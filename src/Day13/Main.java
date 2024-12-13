package Day13;

import utils.AOCFileReader;

public class Main {
    public static void main(String[] args) {
        ClawMachineList cml = AOCFileReader.getInstance().readClawMachineList(13);

        long part1res = cml.getCheapPrice();
        long part2res = cml.getExpensivePrice(); // When you call O(n) bruteforce...

        System.out.println("Part 1: " + part1res);
        System.out.println("Part 2: " + part2res);
    }
}
