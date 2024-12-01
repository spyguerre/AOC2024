package Day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        TwoLists lists = new TwoLists();
        try (BufferedReader br = new BufferedReader(new FileReader("./src/Day01/input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] nums = line.split("   ");
                lists.list1.add(Integer.parseInt(nums[0]));
                lists.list2.add(Integer.parseInt(nums[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        lists.sortLists();

        System.out.println("Part 1: " + lists.addDistances());
        System.out.println("Part 2: " + lists.similarityScore());
    }

}
