package Day14;

import utils.AOCFileReader;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Robots robots = AOCFileReader.getInstance().readRobots(14);

        robots.move(100, false);

        int part1res = robots.getSafetyFactor();

        System.out.println("Part 1: " + part1res);
        System.out.println("Part 2: For part 2, use " +
                "`make run DAY=Day14 > ./src/Day14/output.txt`, then you can look for the Easter Egg " +
                "using ctrl+f 111111111 or similar :')\nOh yeah and er, the end file size is over 2GB lmao.");

        Thread.sleep(5000);
        robots.move(100000, true);
    }
}
