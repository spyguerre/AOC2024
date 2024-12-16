package Day16;

/**
 * A class representing a Node of an OlympicMap.
 */
public class Node {
    /**
     * The character given in the subject, and the one displayed on tha map.
     */
    public char c;
    /**
     * The cost that it takes to reach that Node. null if not yet reached.
     */
    public Integer cost;

    /**
     * Constructor for a Node.
     * @param c The character given in the subject, and the one to display on the map.
     * @param cost The cost it takes to each that Node. null of not yet reached.
     */
    public Node(char c, Integer cost) {
        this.c = c;
        this.cost = cost;
    }

    /**
     * A String representation of the object; only c here, to have the map displayed correctly.
     */
    public String toString() {
        return ""+c;
    }
}
