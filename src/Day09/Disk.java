package Day09;

import java.util.List;
import java.util.ArrayList;

/**
 * Class representing a Disk's memory.
 */
public class Disk {
    /**
     * Input of the puzzle divided into chars.
     */
    private char[] inputMemory;
    /**
     * Transformation of the this.inputmemory into the visual form os the memory.
     */
    private List<Integer> visualMemory;
    /**
     * Index used to find gaps from the start of the this.visualMemory.
     */
    private int startCursor;
    /**
     * Index used to find blocks of filled memory from the end of the this.visualMemory.
     */
    private int endCursor;

    /**
     * Constructor of a Disk. Initializes this.visualMemory depending on the wholeFiles parameter
     * (!wholeFiles -> Part 1 // wholeFiles -> Part 2).
     * @param inputMemory The string read from the input file.
     * @param wholeFiles Whether to move whole files or single blocks.
     */
    public Disk(String inputMemory, boolean wholeFiles) {
        this.inputMemory = inputMemory.toCharArray();

        this.initializeVisualMemory();

        this.startCursor = 0; this.endCursor = this.visualMemory.size() - 1;

        this.orderDisk(wholeFiles);
    }

    /**
     * Constructor of a Disk. Initializes this.visualMemory by moving single blocks.
     * @param inputMemory The string read form the input file.
     */
    public Disk(String inputMemory) {
        this(inputMemory, false);
    }

    /**
     * Initializes this.visualMemory from this.inputMemory.
     */
    private void initializeVisualMemory() {
        this.visualMemory = new ArrayList<>();
        boolean isFile = true;
        int currentId = 0;

        for (int i = 0; i < this.inputMemory.length; i++) {
            if (isFile) {
                for (int j = 0; j < (int)(this.inputMemory[i] - '0'); j++) {
                    this.visualMemory.add(currentId);
                }
                currentId += 1;
            } else {
                for (int j = 0; j < (int)(this.inputMemory[i] - '0'); j++) {
                    this.visualMemory.add(-1);
                }
            }
            isFile = !isFile;
        }
    }

    /**
     * Orders the whole this.visualMemory by moving all the single blocks from right to left, whenever there is space.
     */
    private void orderDisk() {
        boolean flag = true;
        this.moveToNextGap(false);

        while (flag = this.startCursor < this.endCursor) { // Useful to move the file, continue the algorithm
            this.moveBlock();

            this.moveToNextGap(false);
            this.moveToPrevFileBlock();
        }
    }

    /**
     * If wholeFiles: redirects to orderDisk().
     * Else: Orders the whole this.visualMemory by trying to move each whole files from right to left once.
     * @param wholeFiles
     */
    private void orderDisk(boolean wholeFiles) {
        if (!wholeFiles) {
            this.orderDisk();
            return;
        }

        this.moveToNextGap(true);
        int maxId = this.visualMemory.get(this.endCursor);

        for (int id = maxId; id >= 0; id--) {
            while (this.startCursor < this.endCursor) {
                if (this.getGapSize() >= this.getFileBlockSize()) {
                    moveFileBlock();
                    break;
                }
                this.moveToNextGap(true);
            }


            if (id > 0) {
                this.moveToPrevFileBlock();
            }
            this.startCursor = 0;
        }
    }

    /**
     * Inverts values from both indexes in this.visualMemory, considering its value at index localStartCursor is -1.
     * @param localStartCursor First index. this.visualMemory.get(localStartCursor) shall be -1.
     * @param localEndCursor Second index.
     */
    private void moveBlock(int localStartCursor, int localEndCursor) {
        int fileBlockValue = this.visualMemory.get(localEndCursor);
        if (this.visualMemory.get(localStartCursor) != -1) {
            System.out.println("this.visualMemory.get(localStartCursor) sohuld be -1, not " + this.visualMemory.get(localStartCursor));
            throw new java.lang.IllegalArgumentException();
        }
        this.visualMemory.set(localStartCursor, fileBlockValue);
        this.visualMemory.set(localEndCursor, -1);
    }

    /**
     * Inverts values from both indexes this.startCursor and this.endCursor in this.visualMemory,
     * considering its value at index localStartCursor is -1.
     * Equivalent to this.moveBlock(this.startCursor, this.endCursor).
     */
    private void moveBlock() {
        this.moveBlock(this.startCursor, this.endCursor);
    }

    /**
     * Tries to move all entire file block groups once, from right to left, to the left-most gap with sufficient space.
     */
    private void moveFileBlock() {
        int fileBlockValue = this.visualMemory.get(this.startCursor);
        int currentBlockValue = this.visualMemory.get(this.startCursor);

        int localStartCursor = this.startCursor;
        int localEndCursor = this.endCursor;

        int fileBlockSize = this.getFileBlockSize();
        for (int i = 0; i < fileBlockSize; i++) {
            this.moveBlock(localStartCursor, localEndCursor);
            localStartCursor++;
            localEndCursor--;
            currentBlockValue = this.visualMemory.get(localStartCursor);
        }
    }

    /**
     * Moves this.startCursor to the start of the next available gap in this.visualMemory.
     * @param wholeFiles Modifies this function's behavior by forcing it to skip at least one file block.
     */
    private void moveToNextGap(boolean wholeFiles) {
        int blockValue = this.visualMemory.get(this.startCursor);
        int startBlockValue = this.visualMemory.get(this.startCursor);
        boolean canStop = false;

        while (blockValue != -1 || (!canStop && wholeFiles)) {
            this.startCursor++;
            blockValue = this.visualMemory.get(this.startCursor);
            canStop = canStop || (blockValue == startBlockValue && startBlockValue != -1) || (blockValue != -1);
        }
    }

    /**
     * Moves this.endCursor to the previous file block in this.visualMemory.
     */
    private void moveToPrevFileBlock() {
        int blockValue = this.visualMemory.get(this.endCursor);
        int startBlockValue = this.visualMemory.get(this.endCursor);

        while (blockValue == -1 || (blockValue == startBlockValue && startBlockValue != -1)) {
            this.endCursor--;
            blockValue = this.visualMemory.get(this.endCursor);
        }
    }

    /**
     * Computes the size of a gap, assuming the this.startCursor is at the first block of one.
     * @return the size of the gap.
     */
    private int getGapSize() {
        int rightGapIndex = this.startCursor;

        while (this.visualMemory.get(rightGapIndex) == -1) {
            rightGapIndex++;
        }

        return rightGapIndex-this.startCursor;
    }

    /**
     * Computes the size of a whole file block, assuming the this.endCursor is at the last block of one.
     * @return the size of the whole file block.
     */
    private int getFileBlockSize() {
        int leftBlockIndex = this.endCursor;
        int blockValue = this.visualMemory.get(leftBlockIndex);

        while (this.visualMemory.get(leftBlockIndex) == blockValue) {
            leftBlockIndex--;
        }

        return this.endCursor-leftBlockIndex;
    }

    /**
     * Computes the checksum of the filesystem, as described in the subject.
     * @return
     */
    public long getCheckSum() {
        long sum = 0;

        for (int i = 0; i < this.visualMemory.size(); i++) {
            int blockValue = this.visualMemory.get(i);
            if (blockValue != -1) {
                sum += i * blockValue;
            }
        }

        return sum;
    }
}
