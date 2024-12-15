package Day15;

import utils.AOCFileReader;

public class Main {
    public static void main(String[] args) {
        RobotWareHouse rwh1 = AOCFileReader.getInstance().readRobotWareHouse(15, false);

        rwh1.moveSequence();
        int part1res = rwh1.getGPSSum();

        RobotWareHouse rwh2 = AOCFileReader.getInstance().readRobotWareHouse(15, true);
        rwh2.moveSequence();
        int part2res = rwh2.getGPSSum();

        System.out.println("Part 1: " + part1res);
        System.out.println("Part 2: " + part2res);
    }
}
