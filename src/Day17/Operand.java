package Day17;

public class Operand extends Instruction {
    private final AssemblyProgram computer;

    public Operand(int operand, AssemblyProgram computer) {
        super(operand);
        this.computer = computer;
    }

    public Long getComboValue() {
        if (code <= 3) {
            return (long)code;
        } else if (code == 4) {
            return computer.r.a;
        } else if (code == 5) {
            return computer.r.b;
        } else if (code == 6) {
            return computer.r.b;
        } else {
            throw new ArithmeticException("Combo value cannot be 7.");
        }
    }

    public int getLiteralValue() {
        return code;
    }
}
