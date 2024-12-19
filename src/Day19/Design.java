package Day19;

import utils.MapUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class representing a Towel Design.
 */
public class Design extends Towel {
    /**
     * The map in which to store the possibilities explored.
     * Saves the number of ways (values) that a sub-towel of indexes [key, ..., end] can be made.
     */
    private final Map<Object, Long> possibilityMap = new HashMap<>();
    /**
     * The list of Patterns to choose from when trying to build this Design.
     */
    private final List<Pattern> patterns;

    /**
     * Constructor for a Design.
     * @param t The String representing the Towel.
     */
    public Design(String t, List<Pattern> patterns) {
        super(t);
        this.patterns = patterns;

        this.match();
    }

    /**
     * Constructor for a Design
     * @param t The list representing the Towel. Can be a list of Characters or a List of Patterns.
     */
    public Design(List<?> t, List<Pattern> patterns) {
        super(t);
        this.patterns = patterns;

        this.match();
    }

    /**
     * Checks whether the Pattern in argument can be used to build this Design, starting at element k.
     * @param pattern The Pattern to check.
     * @param k The idex at which to start checking.
     * @return 0 if matches until the end, 1 if the pattern is prefix, -1 otherwise.
     */
    public int compare(Pattern pattern, int k) {
        // The Pattern is strictly longer than the remaining size of the Design.
        if (pattern.size() > this.size() - k) {
            return -1;
        }

        // Ensures the following colors are right, or return otherwise.
        for (int i = 0; i < pattern.size(); i++) {
            if (this.get(i+k) != pattern.get(i)) {
                return -1;
            }
        }

        // 0 if iteration went right and remaining sizes are equal, else 1.
        return (pattern.size() == this.size() - k) ? 0 : 1;
    }

    /**
     * Recursive method to find the number of ways to build this Design.
     * @param patterns The list of Patterns to use.
     * @param currentMatch The current List of Patterns to start from.
     */
    private void matchRec(List<Pattern> patterns, List<Pattern> currentMatch) {
        int currentSize = new Towel(currentMatch).size();

        // Check for each Pattern
        for (Pattern pattern : patterns) {
            int cmp = this.compare(pattern, new Towel(currentMatch).size());

            if (cmp == 0) { // Found an exact match for the end of the Design. Update the this.possibilityMap consequently.
                MapUtil.incr(possibilityMap, currentSize);
            } else if (cmp == 1) { // The current Pattern matches the next colors of the Design, but not until the end.
                // If the following condition is true, the end has already been explored.
                if (possibilityMap.containsKey(currentSize + pattern.size())) {
                    // Only update the possibilityMap consequently, without doing further iterations for this case.
                    MapUtil.incr(possibilityMap, currentSize, possibilityMap.get(currentSize + pattern.size()));
                } else { // Not yet explored
                    // Continue iterations for this case, since the end has not yet been reached.
                    List<Pattern> newMatch = new ArrayList<>(currentMatch);
                    newMatch.add(pattern);
                    matchRec(patterns, newMatch);

                    // Don't forget to update the map after the exploration!
                    Long toPut = possibilityMap.get(currentSize + pattern.size());
                    MapUtil.incr(possibilityMap, currentSize, toPut == null ? 0L : toPut);
                }
            }
        }
    }

    /**
     * Recursive method to find the number of ways to build this Design.
     */
    public void match() {
        List<Pattern> currentMatch = new ArrayList<>();

        this.possibilityMap.clear();

        matchRec(patterns, currentMatch);
    }

    /**
     * Returns the number of ways to build this Design.
     * @return the number of ways to build this Design.
     */
    public Long waysCount() {
        return possibilityMap.get(0) == null ? 0 : possibilityMap.get(0);
    }
}
