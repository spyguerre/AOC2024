package Day17;

import utils.AOCFileReader;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> input = AOCFileReader.getInstance().readLines(17);

        long initRa = 3304073773L;

        AssemblyProgram computer = new AssemblyProgram(input);
        initRa = AssemblyProgram.toLong("101110001001101011100000010111000101110101000");
        System.out.println("aaa" + initRa);
        long part2res = AssemblyProgram.toLong(computer.findA());

        System.out.println("Part 1: " + 0);
        System.out.println("Part 2: " + part2res);
    }
}
