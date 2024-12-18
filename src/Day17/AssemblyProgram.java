package Day17;

import utils.StringUtil;

import java.util.List;
import java.util.ArrayList;

public class AssemblyProgram {
    public Memory r;
    public String output;
    public String instructionsStr;
    public List<Instruction> instructions;

    public AssemblyProgram(List<String> input) {
        long a = Long.parseLong(input.get(0).split("Register A: ")[1]);
        long b = Long.parseLong(input.get(1).split("Register B: ")[1]);
        long c = Long.parseLong(input.get(2).split("Register C: ")[1]);
        this.r = new Memory(a, b, c, 0);

        output = "";
        instructionsStr = input.get(4).split("Program: ")[1];

        instructions = new ArrayList<>();
        int i = 0;
        for (String strIntruction: instructionsStr.split(",")) {
            int instruction = Integer.parseInt(strIntruction);

            if (i % 2 == 0) {
                instructions.add(new OpCode(instruction, this));
            } else {
                instructions.add(new Operand(instruction, this));
            }

            i++;
        }
    }

    public Instruction read() {
        if (r.instructionPointer >= instructions.size()) {
            throw new IndexOutOfBoundsException("Instruction index out of bounds of the program.");
        }

        Instruction instruction = instructions.get(r.instructionPointer);
        r.instructionPointer++;
        return instruction;
    }

    public OpCode readOpCode() {
        return (OpCode)read();
    }

    public Operand readOperand() {
        return (Operand)read();
    }

    public void output(long value) {
        if (!this.output.isEmpty()) {output += ",";}
        this.output += Long.toString(value);
    }

    public void computeOutput() {
        while (r.instructionPointer < instructions.size()) {
            OpCode opCode = readOpCode();
            opCode.execute();
        }
    }

    public static String toBinary(long a) {
        StringBuilder str = new StringBuilder();
        while (a != 0) {
            str.insert(0, ((a % 2 == 1) ? "1" : "0"));
            a = a / 2;
        }
        return str.toString();
    }

    public static long toLong(String a) {
        long result = 0;
        for (int i = 0; i < a.length(); i++) {
            char c = a.charAt(i);
            if (c == '1') {
                result += longPower(2, a.length()-1 - i);
            }
        }
        return result;
    }

    public static long longPower(long number, long power) {
        if(power == 0) return 1;
        long result = number;

        while(power > 1) {
            result*=number;
            power--;
        }

        return result;
    }

    private void buildSList(int bitsRemaining, List<String> sList) {
        int initialSize = sList.size();
        for (int i = 0; i < initialSize; i++) {
            String s = sList.removeFirst();
            sList.add(s + "0"); sList.add(s + "1");
        }

        bitsRemaining--;

        if (bitsRemaining > 0) {
            buildSList(bitsRemaining, sList);
        }
    }

    public String findA() {
        List<String> sList = new ArrayList<>();
        int currentOutputLength = 0;
        List<String> bestsList = new ArrayList<>(); bestsList.add("");
        String s;

        while (true) {
            boolean noValue = false;
            String lowestS = null;

            if (!bestsList.isEmpty()) {
                lowestS = bestsList.stream().min(AssemblyProgram::compareBits).orElseThrow();
            } else noValue = true;
            System.out.println(bestsList);

            sList.clear();
            bestsList.clear();

            if (!noValue) {
                sList.add(lowestS);
                buildSList(3, sList);
            } else {
                sList.add(lowestS);
                buildSList(6, sList);
            }
            System.out.println("Building sList " + sList.getFirst());


            while (!sList.isEmpty()) {
                s = sList.removeFirst();
                r.reset(toLong(s));
                output = "";

                computeOutput();

                output = StringUtil.longestSuffix(output, instructionsStr);
                if (output.length() > 1) {
                    output = output.substring(1);
                }
                System.out.println("Output " + output);

                if (output.length() > currentOutputLength) {
                    System.out.println("OUAIS ON AVANCE");
                    //System.out.println(s + " '" + output + "' " + currentOutputLength + " " + sList.size());
                    bestsList.add(s);
                    currentOutputLength = output.length();
                }

                if (output.equals(instructionsStr)) {
                    return s;
                }
            }
        }
    }

    public static int compareBits(String a, String b) {
        int minLength = Math.min(a.length(), b.length());

        for (int i = 0; i < minLength; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return Character.compare(a.charAt(i), b.charAt(i));
            }
        }

        return Integer.compare(a.length(), b.length());
    }
}
