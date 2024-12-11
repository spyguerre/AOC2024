package Day11;

import java.util.*;

/**
 * A class representing a stone path.
 */
public class StoneList {
    /**
     * A dictionary of the stones; key is a stone's mark and value is the number of stones that share this mark.
     */
    public Map<Long, Long> stones;

    /**
     * Constructor for a StoneList.
     * @param stones A list containing the stones' marks.
     */
    public StoneList(List<Long> stones) {
        this.stones = new Hashtable<>();
        for (Long stone : stones) {
            this.addStone(stone, 1L, this.stones);
        }
    }

    /**
     * Adds (a) stone(s) to a dictionary.
     * @param stoneMark The mark on the stone to add.
     * @param stoneCount The number of stone(s) to add.
     * @param newStones The dictionary in which to add the stone(s).
     */
    private void addStone(Long stoneMark, Long stoneCount, Map<Long, Long> newStones) {
        try {
            long oldCount = newStones.get(stoneMark);
            newStones.put(stoneMark, oldCount + stoneCount);
        } catch (NullPointerException e) {
            newStones.put(stoneMark, stoneCount);
        }
    }

    /**
     * Computes what happens to a specific stone within the blink of an eye, as described in the subject.
     * @param stoneMark The current mark on the stone.
     * @param newStones The dictionary of the next iteration in which to add the result stones.
     */
    private void blinkStone(Long stoneMark, Map<Long, Long> newStones) {
        Long stoneCount = this.stones.get(stoneMark);

        if (stoneMark == 0) {
            stoneMark = 1L;
        } else if ((""+stoneMark).length() % 2 == 0) {
            String stoneMarkStr = ""+stoneMark;
            int length = stoneMarkStr.length();
            long firstMark = Integer.parseInt(stoneMarkStr.substring(0, length/2));
            long secondMark = Integer.parseInt(stoneMarkStr.substring(length/2));

            stoneMark = firstMark;
            this.addStone(secondMark, stoneCount, newStones);
        } else {
            stoneMark *= 2024;
        }

        addStone(stoneMark, stoneCount, newStones);
    }

    /**
     * Computes what happens to all stones within the blink of an eye, as described on the subject.
     */
    private void blinkAllStones() {
        Map<Long, Long> dict = new Hashtable<>();

        for (Long stoneMark : this.stones.keySet()) {
            this.blinkStone(stoneMark, dict);
        }

        this.stones = dict;
    }

    /**
     * Computes what happens to all stones within multiple blinks of an eye, as described on the subject.
     * @param times The number of times to blink.
     */
    public void multiBlinkAllStones(int times) {
        for (int i = 0; i < times; i++) {
            this.blinkAllStones();
        }
    }

    /**
     * Counts how many stones there currently are in the stone path.
     * @return how many stones there currently are in the stone path.
     */
    public long getStoneCount() {
        long count = 0;
        for (Long stoneMark : this.stones.keySet()) {
            count += this.stones.get(stoneMark);
        }
        return count;
    }
}
