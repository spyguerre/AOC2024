package Day09;

import utils.*;

public class Main {
    public static void main(String[] args) {
        String input = AOCFileReader.getInstance().read(9);

        Disk disk1 = new Disk(input);
        long part1res = disk1.getCheckSum();

        Disk disk2 = new Disk(input, true);
        long part2res = disk2.getCheckSum();

        System.out.println("Part 1: " + part1res);
        System.out.println("Part 2: " + part2res);
    }
}
