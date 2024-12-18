package Day17;

public class OpCode extends Instruction {
    private final AssemblyProgram computer;

    public OpCode(int opcode, AssemblyProgram computer) {
        super(opcode);
        this.computer = computer;
    }

    public void execute() {
        if (code == 0) { // adv
            long numerator = computer.r.a;
            long denominator = AssemblyProgram.longPower(2, computer.readOperand().getComboValue());
            computer.r.a = numerator / denominator;
        } else if (code == 1) { // bxl
            computer.r.b ^= computer.readOperand().getLiteralValue();
        } else if (code == 2) { // bst
            computer.r.b = computer.readOperand().getComboValue() % 8;
        } else if (code == 3) { // jnz
            if (computer.r.a != 0) {
                computer.r.instructionPointer = computer.readOperand().getLiteralValue();
            } else computer.read();
        } else if (code == 4) { // bxc
            computer.read();
            computer.r.b ^= computer.r.c;
        } else if (code == 5) { // out
            computer.output(computer.readOperand().getComboValue() % 8);
        } else if (code == 6) { // bdv
            long numerator = computer.r.a;
            long denominator = AssemblyProgram.longPower(2, computer.readOperand().getComboValue());
            computer.r.b = numerator / denominator;
        } else if (code == 7) { // cdv
            long numerator = computer.r.a;
            long denominator = AssemblyProgram.longPower(2, computer.readOperand().getComboValue());
            computer.r.c = numerator / denominator;
        }
    }
}
