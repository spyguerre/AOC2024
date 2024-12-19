package Day19;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing an Onsen.
 */
public class Onsen {
    /**
     * The list of Designs wanted by the Onsen.
     */
    public List<Design> designs;
    /**
     * The list of Patterns available at the Onsen.
     */
    public List<Pattern> patterns;

    /**
     * Constructor for an Onsen.
     * @param input The input of the puzzle.
     */
    public Onsen(List<String> input) {
        designs = new ArrayList<>();
        patterns = new ArrayList<>();

        for (String patternStr : input.getFirst().split(", ")) {
            patterns.add(new Pattern(patternStr));
        }

        for (int i = 2; i < input.size(); i++) {
            designs.add(new Design(input.get(i), patterns));
        }
    }

    /**
     * Computes how many Designs can be created using this set of Patterns.
     * @param countWays If true, counts the total number of ways that every Design can be created instead.
     * @return how many Designs can be created using this set of Patterns.
     */
    public Long countMatchable(boolean countWays) {
        long count = 0L;

        for (Design design : designs) {
            Long curRes = design.waysCount();
            if (countWays) {
                count += curRes;
            } else {
                count += curRes > 0 ? 1 : 0;
            }
        }

        return count;
    }

    /**
     * Computes how many Designs can be created using this set of Patterns.
     * @return how many Designs can be created using this set of Patterns.
     */
    public long countMatchable() {
        return countMatchable(false);
    }
}
