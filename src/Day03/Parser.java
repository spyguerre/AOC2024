package Day03;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    public String str;
    public int index;
    public int state;  // Values 0..5 are for mul(x,x) ; values 10 and 11 are for do() and don't() ; 12 is for do() ; values 21..25 are for don't()
    public String op = "";
    public String number1 = "0";
    public String number2 = "0";

    private List<Character> validInts = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

    public Parser(String str) {
        this.str = str;
        this.index = 0;
    }
    
    // Reads a character in this.str and returns whether a pattern was fully recognized
    public boolean read() {
        char c = this.str.charAt(this.index);

        if (c == 'm') { // Regardless of state to recognize things like mumul(x,x)
            this.state = 1;
        } else if (this.state == 1 && c == 'u') {
            this.state++;
        } else if (this.state == 2 && c == 'l') {
            this.state++;
        } else if (this.state == 3 && c == '(') {
            this.state++;
        } else if (this.state == 4) {
            if (c == ',') {
                this.state++;
            } else if (this.validInts.contains(c)) {
                this.number1 = this.number1 + c;
            } else {
                this.state = 0;
                this.op = "";
                this.number1 = "0";
                this.number2 = "0";
            }
        } else if (this.state == 5) {
            if (c == ')') {
                this.state = 0;
                this.op = "mul";
                return true;
            } else if (this.validInts.contains(c)) {
                this.number2 = this.number2 + c;
            } else {
                this.state = 0;
                this.op = "";
                this.number1 = "0";
                this.number2 = "0";
            }
        } else if (c == 'd') { // Regardless of the state
            this.state = 10;
        } else if (this.state == 10 && c == 'o') {
            this.state++;
        } else if (this.state == 11 && c == '(') {
            this.state++;
        } else if (this.state == 12 && c == ')') {
            this.state = 0;
            this.op = "do";
            return true;
        } else if (this.state == 11 && c == 'n') {
            this.state = 22;
        } else if (this.state == 22 && c == '\'') {
            this.state++;
        } else if (this.state == 23 && c == 't') {
            this.state++;
        } else if (this.state == 24 && c == '(') {
            this.state++;
        } else if (this.state == 25 && c == ')') {
            this.state = 0;
            this.op = "don't";
            return true;
        } else { // Reset state because no pattern matches
            this.state = 0;
            this.op = "";
            this.number1 = "0";
            this.number2 = "0";
        }
        this.index++;

        return false;
    }

    // Reads characters one by one in this.str and returns a list of operations recognized
    public List<Operation> readAll() {
        List<Operation> mulList = new ArrayList<>();
        this.index = 0;
        int length = this.str.length();

        while (this.index < length) {
            if (this.read()) {
                mulList.add(new Operation(
                        this.op,
                        Integer.parseInt(this.number1),
                        Integer.parseInt(this.number2)
                ));
                this.number1 = "";
                this.number2 = "";
            }
        }

        return mulList;
    }

    public int computeMemory(boolean conditional) {
        int res = 0;
        boolean enabled = true;

        List<Operation> opList = this.readAll();
        for (Operation op : opList) {
            Integer[] opRes = op.compute();
            if (opRes[1] == null && enabled) {
                res += opRes[0];
            } else if (opRes[0] == null && conditional) {
                enabled = opRes[1] == 1;
            }
        }
        return res;
    }

    public int computeMemory() {
        return this.computeMemory(false);
    }
}
