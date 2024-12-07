package Day07;

import java.util.List;
import utils.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        List<Equation> equations = AOCFileReader.getInstance().readEquations(7);

        BigInteger part1res = BigInteger.valueOf(0);
        for (Equation equation : equations) {
            part1res = part1res.add(BigInteger.valueOf(equation.countValidOperators(2) > 0 ? equation.result : 0));
        }

        // Yeah bad complexity ikr buuuuuut this still works within my lifetime
        BigInteger part2res = BigInteger.valueOf(0);
        for (Equation equation : equations) {
            part2res = part2res.add(BigInteger.valueOf(equation.countValidOperators(3) > 0 ? equation.result : 0));
        }

        System.out.println("Part 1: " + part1res);
        System.out.println("Part 2: " + part2res);
    }
}
