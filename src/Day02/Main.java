package Day02;

import utils.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Report> reports = AOCFileReader.getInstance().readReports(2);

        int part1res = 0;
        int part2res = 0;
        for (Report report : reports) {
            part1res += (report.checkDirection()) ? 1 : 0;
            part2res += (report.checkDirection(true)) ? 1 : 0;
            System.out.println(report.checkDirection(true) ? 1 : 0);
        }

        System.out.println("Part 1: " + part1res);
        System.out.println("Part 2: " + part2res);
    }
}
