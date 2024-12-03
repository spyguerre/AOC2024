package Day03;

public class Operation {
    public String op;
    public int number1;
    public int number2;

    public Operation(String op, int number1, int number2) {
        this.op = op;
        this.number1 = number1;
        this.number2 = number2;
    }

    // Returns the result of operation then the fact that operation changes enableness. values are null if operation doesn't change said value.
    public Integer[] compute() throws java.lang.IllegalArgumentException {
        Integer[] res = new Integer[2];

        if (op.equals("mul")) {
            res[0] = number1*number2; res[1] = null;
            return res;
        } else if (op.equals("do")) {
            res[0] = null; res[1] = 1;
            return res;
        } else if (op.equals("don't")) {
            res[0] = null; res[1] = 0;
            return res;
        }
        throw new java.lang.IllegalArgumentException();
    }
}
