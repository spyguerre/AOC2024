package Day21;

import utils.AOCFileReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> input = AOCFileReader.getInstance().readLines(21);

        for (String code : input) {
            Keypad directional = new Numeric(null, ""+code.charAt(0));
            Keypad num1 = new Directional(directional);
            Keypad num2 = new Directional(num1);
            Keypad num3 = new Directional(num2);

            List<String> sequence = directional.tapSequence();
            System.out.println(sequence);
            System.out.println(num1.tapSequence());
            System.out.println(num2.tapSequence());
            System.out.println(num3.tapSequence());

            long complexity = num3.getComplexity(Integer.parseInt(code.substring(0, code.length()-1)));

            System.out.println(complexity);
        }
    }
}
