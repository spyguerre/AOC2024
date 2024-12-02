package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Day01.TwoLists;
import Day02.Report;

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

    // Reads today's input content
    public List<String> readLines(int day) {
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

    // Reads inputs of two vertical lists separated by 3 spaces
    public TwoLists readTwoLists(int day) {
        List<String> lines = this.readLines(day);

        TwoLists lists = new TwoLists();
        for (String line : lines) {
            String[] nums = line.split("   ");
            lists.list1.add(Integer.parseInt(nums[0]));
            lists.list2.add(Integer.parseInt(nums[1]));
        }

        return lists;
    }

    // Reads a list of integer lists, which are separated by a single space
    public List<Report> readReports(int day) {
        // Get today's inputs as a list of Strings
        List<String> lines = this.readLines(day);

        List<Report> res = new ArrayList<>();

        // Iterate over each line
        for (String line : lines) {
            // Create integer list and add its reference in res
            Report ints = new Report();
            res.add(ints);
            // Add each number to the list after converting it to int
            String[] nums = line.split(" ");
            for (String num : nums) {
                ints.add(Integer.parseInt(num));
            }
        }

        return res;
    }

    // Returns a simple String containing today's inputs contents
    public String read(int day) {
        return String.join("\n", this.readLines(day));
    }
}
