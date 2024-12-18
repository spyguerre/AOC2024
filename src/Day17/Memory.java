package Day17;

public class Memory {
    public long a;
    public long b;
    public long c;
    public int instructionPointer;

    public Memory(long ra, long rb, long rc, int instructionPointer) {
        this.a = ra;
        this.b = rb;
        this.c = rc;
        this.instructionPointer = instructionPointer;
    }

    public boolean equals(Memory r) {
        return Memory.compare(this, r) == 0;
    }

    public static int compare(Memory r1, Memory r2) {
        if (r1.a == r2.a) {
            if (r1.b == r2.b) {
                if (r1.c == r2.c) {
                    return 0;
                } else {
                    return Long.compare(r1.c, r2.c);
                }
            } else {
                return Long.compare(r1.b, r2.b);
            }
        } else {
            return Long.compare(r1.a, r2.a);
        }
    }

    public Memory clone() {
        return new Memory(a, b, c, instructionPointer);
    }

    public void reset(long a) {
        this.a = a;
        this.b = 0;
        this.c = 0;
        this.instructionPointer = 0;
    }
}
