package Day07;

import java.util.List;
import java.util.ArrayList;
import java.math.BigInteger;

// Represents an equation with its result and without its operators.
public class Equation {
    Long result;
    List<Long> numbers;

    public Equation(long result, List<Long> numbers) {
        this.result = result;
        this.numbers = numbers;
    }

    // Computes whether the operators passed as parameters help compute the right this.result.
    public boolean testValidity(List<Character> operators) {
        List<BigInteger> numbersCopy = new ArrayList<>();
        for (Long number : numbers) {
            numbersCopy.add(BigInteger.valueOf(number));
        }

        BigInteger res = numbersCopy.get(0);
        numbersCopy.remove(0);
        for (Character operator : operators) {
            if (operator == '+') {
                res = res.add(numbersCopy.get(0));
                numbersCopy.remove(0);
            } else if (operator == '*') {
                res = res.multiply(numbersCopy.get(0));
                numbersCopy.remove(0);
            } else if (operator == '|') {
                res = new BigInteger(res.toString() + numbersCopy.get(0).toString());
                numbersCopy.remove(0);
            }

            if (res.compareTo(BigInteger.valueOf(this.result)) == 1) {
                break;
            }
        }

        return res.compareTo(BigInteger.valueOf(this.result)) == 0;
    }

    // Constructs the list of all possible lists of operations for this equation.
    public List<List<Character>> buildOperatorsList(int symbols) {
        List<List<Character>> operatorsList = new ArrayList<>();
        operatorsList.add(new ArrayList<Character>());

        for (int i = 0; i < numbers.size() - 1; i++) {
            List<List<Character>> operators2s = new ArrayList<>();
            List<List<Character>> operators3s = new ArrayList<>();
            for (List<Character> operators : operatorsList) {
                List<Character> operators2 = new ArrayList<>(operators);
                List<Character> operators3 = new ArrayList<>(operators);
                operators2s.add(operators2);
                operators3s.add(operators3);
                operators.add('+');
                operators2.add('*');
                operators3.add('|');
            }
            for (List<Character> operators2 : operators2s) {
                operatorsList.add(operators2);
            }
            if (symbols == 3) {
                for (List<Character> operators3 : operators3s) {
                    operatorsList.add(operators3);
                }
            }
        }

        return operatorsList;
    }

    // Computes how many of the possible lists of operators compute the right this.result.
    public Integer countValidOperators(int symbols) {
        Integer count = 0;

        List<List<Character>> operatorsList = this.buildOperatorsList(symbols);
        for (List<Character> operators : operatorsList) {
            if (this.testValidity(operators)) {
                count++;
            }
        }

        return count;
    }
}
