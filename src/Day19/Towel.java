package Day19;


import java.util.ArrayList;
import java.util.List;

public class Towel {
    public List<Character> t;

    public Towel(String t) {
        this.t = new ArrayList<>();
        for (char c : t.toCharArray()) {
            this.t.add(c);
        }
    }

    public Towel(List<?> t) {
        this.t = new ArrayList<>();

        for (Object o : t) {
            if (o instanceof Pattern) {
                this.t.addAll(((Pattern) o).t);
            } else if (o instanceof Character) {
                this.t.add((Character) o);
            } else {
                throw new IllegalArgumentException("Not a valid type of List to construct a Towel.");
            }
        }
    }

    public int size() {
        return t.size();
    }

    public Character get(int k) {
        return t.get(k);
    }

    public String toString() {
        return t.toString();
    }
}
