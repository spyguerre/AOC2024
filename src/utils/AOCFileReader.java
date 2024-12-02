package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Day01.TwoLists;

public class AOCFileReader {
    // Static instance of the class to ensure only one instance
    private static AOCFileReader instance = null;

    // Private constructor to prevent external instantiation
    private AOCFileReader() {}

    // Public method to provide access to the instance
    public static AOCFileReader getInstance() {
        if (instance == null) {
            instance = new AOCFileReader();
        }
        return instance;
    }

    // Read today's input content
    public List<String> read(int day) {
        // Format the string to a decimal (d) number and pad it with leading zeros (0) up to the specified width (2).
        String zfilledDay = String.format("%02d", day);
        List<String> lines = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader("./src/Day"+zfilledDay+"/input.txt"))) {
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    // Read day 1 inputs
    public TwoLists readTwoLists(int day) {
        List<String> lines = this.read(day);

        TwoLists lists = new TwoLists();
        for (String line : lines) {
            String[] nums = line.split("   ");
            lists.list1.add(Integer.parseInt(nums[0]));
            lists.list2.add(Integer.parseInt(nums[1]));
        }

        return lists;
    }
}



