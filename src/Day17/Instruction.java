package Day17;


public abstract class Instruction {
    Integer code;

    public Instruction(Integer code) {
        this.code = code;
    }

    public String toString() {
        return ""+code;
    }
}
