package Day11;

public class Stone {
    public long mark;
    public long count;

    public Stone(long mark, long count) {
        this.mark = mark;
        this.count = count;
    }

    public Stone blink() {
        if (this.mark == 0) {
            this.mark += 1;
        } else if ((""+this.mark).length() % 2 == 0) {
            String stoneMarkStr = ""+this.mark;
            int length = stoneMarkStr.length();
            long firstMark = Integer.parseInt(stoneMarkStr.substring(0, length/2));
            long secondMark = Integer.parseInt(stoneMarkStr.substring(length/2));

            this.mark = firstMark;
            return new Stone(secondMark, 1);
        } else {
            this.mark *= 2024;
        }

        return null;
    }

    public boolean equals(Stone stone) {
        return this.mark == stone.mark;
    }
}
