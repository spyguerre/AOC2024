package Day01;

import utils.AOCFileReader;

public class Main {
    public static void main(String[] args) {

        TwoLists lists = AOCFileReader.getInstance().readTwoLists(1);

        lists.sortLists();

        System.out.println("Part 1: " + lists.addDistances());
        System.out.println("Part 2: " + lists.similarityScore());
    }
}
