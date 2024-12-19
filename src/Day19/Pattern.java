package Day19;

import java.util.List;

/**
 * Class representing a Pattern.
 */
public class Pattern extends Towel {
    /**
     * Constructor for a Pattern.
     * @param t The String representing the Towel.
     */
    public Pattern(String t) {
        super(t);
    }

    /**
     * Constructor for a Pattern.
     * @param t The list representing the Towel. Can be a list of Characters or a List of Patterns.
     */
    public Pattern(List<?> t) {
        super(t);
    }
}
