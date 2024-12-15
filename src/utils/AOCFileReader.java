package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Day01.TwoLists;
import Day02.Report;
import Day05.*;
import Day07.*;
import Day13.ClawMachine;
import Day13.ClawMachineList;
import Day14.Robot;
import Day14.Robots;
import Day15.RobotWareHouse;

/**
 * Class used to read inputs from the Advent of Code puzzles.
 */
public class AOCFileReader {
    /**
     * Static instance of the class to ensure only one instance.
     */
    private static AOCFileReader instance = null;

    /**
     * Private constructor to prevent external instantiation.
     */
    private AOCFileReader() {}

    /**
     * Public static method to provide access to the instance.
     */
    public static AOCFileReader getInstance() {
        if (instance == null) {
            instance = new AOCFileReader();
        }
        return instance;
    }

    /**
     * Reads today's input content.
     * @return a list of lines found in the input's content.
     */
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

    /**
     * Reads inputs of two vertical lists separated by 3 spaces.
     */
    public TwoLists readTwoLists(int day) {
        List<String> lines = this.readLines(day);

        TwoLists lists = new TwoLists();
        for (String line : lines) {
            String[] nums = line.split(" {3}");
            lists.list1.add(Integer.parseInt(nums[0]));
            lists.list2.add(Integer.parseInt(nums[1]));
        }

        return lists;
    }

    /**
     * Reads a list of Reports, which are a list of integers separated by a single space.
     */
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

    /**
     * Returns a simple String containing today's inputs contents.
     */
    public String read(int day) {
        return String.join("\n", this.readLines(day));
    }

    /**
     * Reads the content of pages ordering and updates and returns a ManualData object.
     */
    public ManualData readManual(int day) {
        List<String> lines = this.readLines(day);

        ManualData manual = new ManualData();

        int lineNum = 0;
        int lineMax = lines.size();

        while (!lines.get(lineNum).isEmpty()) {
            String[] constr = lines.get(lineNum).split("\\|");
            manual.constraints.add(new Constraint(Integer.parseInt(constr[0]), Integer.parseInt(constr[1])));
            lineNum++;
        }

        lineNum++;

        while (lineNum < lineMax) {
            Update update = new Update();
            manual.updates.add(update);
            for (String strPage : lines.get(lineNum).split(",")) {
                update.pages.add(Integer.parseInt(strPage));
            }
            lineNum++;
        }

        return manual;
    }

    /**
     * Reads a matrix of characters.
     */
    public List<List<Character>> readCharMatrix(int day) {
        List<String> lines = this.readLines(day);
        List<List<Character>> res = new ArrayList<>();

        for (String line : lines) {
            List<Character> row = new ArrayList<>();
            res.add(row);
            for (int i = 0; i < line.length(); i++) {
                row.add(line.charAt(i));
            }
        }

        return res;
    }

    /**
     * Reads a list of Equations.
     */
    public List<Equation> readEquations(int day) {
        List<String> lines = this.readLines(day);
        List<Equation> res = new ArrayList<>();

        for (String line : lines) {
            long eqRes = Long.parseLong(line.split(":")[0]);
            String[] eqNumbersStr = line.split(":")[1].split(" ");
            List<Long> eqNumbers = new ArrayList<>();
            for (int i = 1; i < eqNumbersStr.length; i++) { // Starts at 1 to avoid first "" string
                eqNumbers.add(Long.parseLong(eqNumbersStr[i]));
            }

            res.add(new Equation(eqRes, eqNumbers));
        }

        return res;
    }

    /**
     * Reads a matrix of Integers.
     */
    public List<List<Integer>> readIntMatrix(int day) {
        List<List<Character>> charMatrix = this.readCharMatrix(day);

        List<List<Integer>> res = new ArrayList<>();
        for (List<Character> charRow : charMatrix) {
            List<Integer> intRow = new ArrayList<>();
            res.add(intRow);
            for (Character c : charRow) {
                intRow.add(c - '0');
            }
        }

        return res;
    }

    /**
     * Reads a list of longs separated by a single space in the first line.
     */
    public List<Long> readLongList(int day) {
        List<String> lines = this.readLines(day);
        String[] stringArray = lines.getFirst().split(" ");
        List<Long> longList = new ArrayList<>();
        for (String str : stringArray) {
            longList.add(Long.parseLong(str));
        }
        return longList;
    }

    /**
     * Creates a ClawMachineList representing today's input.
     */
    public ClawMachineList readClawMachineList(int day) {
        int lineNum = 0;
        List<String> lines = this.readLines(day);
        ClawMachineList res = new ClawMachineList(new ArrayList<>());
        long ax=0, ay=0, bx=0, by=0, x, y;
        for (String line : lines) {
            if (lineNum == 0) {
                String[] nums = line.split("Button A: X+")[1].split(", Y+");
                ax = Long.parseLong(nums[0]);
                ay = Long.parseLong(nums[1]);
            } else if (lineNum == 1) {
                String[] nums = line.split("Button B: X+")[1].split(", Y+");
                bx = Long.parseLong(nums[0]);
                by = Long.parseLong(nums[1]);
            } else if (lineNum == 2) {
                String[] nums = line.split("Prize: X=")[1].split(", Y=");
                x = Long.parseLong(nums[0]);
                y = Long.parseLong(nums[1]);
                res.add(new ClawMachine(ax, ay, bx, by, x, y));
            }

            lineNum = (lineNum+1)%4;
        }

        return res;
    }

    /**
     * Reads a list of robots' data from today's input and constructs a Robot list (Robots) from it.
     */
    public Robots readRobots(int day) {
        List<String> lines = this.readLines(day);
        Robots res = new Robots(new ArrayList<>());
        for (String line : lines) {
            String[] data = line.split("p=")[1].split(" v=");
            String[] pos = data[0].split(",");
            String[] speed = data[1].split(",");
            res.add(new Robot(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]),
                    Integer.parseInt(speed[0]), Integer.parseInt(speed[1])));
        }

        return res;
    }

    /**
     * Reads a lanternfish's mad Robot Warehouse.
     * @param wide Whether the input should be widened as described in the subject.
     */
    public RobotWareHouse readRobotWareHouse(int day, boolean wide) {
        List<String> lines = this.readLines(day);
        boolean seenEmptyLine = false;

        List<List<Character>> resMap = new ArrayList<>();
        List<Character> moves = new ArrayList<>();
        for (String line : lines) {
            if (line.isEmpty()) {seenEmptyLine = true;}
            else if (!seenEmptyLine) { // Read the map.
                List<Character> row = new ArrayList<>();
                resMap.add(row);
                for (int i = 0; i < line.length(); i++) {
                    if (!wide) {
                        row.add(line.charAt(i));
                    } else {
                        char c = line.charAt(i);
                        if (c == '#' || c == '.') {
                            row.add(line.charAt(i)); row.add(line.charAt(i));
                        } else if (c == 'O') {
                            row.add('['); row.add(']');
                        } else if (c == '@') {
                            row.add('@'); row.add('.');
                        }
                    }
                }
            } else { // Read the robot moves.
                for (Character c: line.toCharArray()) {moves.add(c);}
            }
        }

        return new RobotWareHouse(resMap, moves);
    }
}
