package Day21;

import utils.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Keypad {
    public Coordinate coos;
    public List<String> sequences;
    public Keypad keypadToControl;
    public Map<Character, Coordinate> layout;


    public Keypad(int i, int j, Keypad keypadToControl, String sequence) {
        this.coos = new Coordinate(i, j);
        this.sequences = new ArrayList<>();
        if (!sequence.isEmpty()) sequences.add(sequence);
        this.keypadToControl = keypadToControl;
        this.layout = new HashMap<>();
    }

    public Keypad(int i, int j, Keypad keypadToControl) {
        this(i, j, keypadToControl, "");
    }


    public List<String> tapKey(char c, List<String> sequences, String currentSequence, Coordinate currentCoos) {
        Coordinate keyCoos = keypadToControl.layout.get(c);
        Coordinate currentCoos = keypadToControl.coos;

        String currentSequence = sequences.remove(i);

        if (currentCoos.equals(keyCoos)) {
            sequences.add(currentSequence + 'A');
            return sequences;
        }

        if (currentCoos.j < keyCoos.j) {
            sequences.add(currentSequence + '>');
            currentCoos.j++;
            tapKey(c, sequences, sequences.size() - 1);
        }

        if (currentCoos.i < keyCoos.i) {
            sequences.add(currentSequence + 'v');
            currentCoos.i++;
            tapKey(c, sequences, sequences.size() - 1);
        }

        if (currentCoos.j > keyCoos.j) {
            sequences.add(currentSequence + '<');
            currentCoos.j--;
            tapKey(c, sequences, sequences.size() - 1);
        }

        if (currentCoos.i > keyCoos.i) {
            sequences.add(currentSequence + '^');
            currentCoos.i--;
            tapKey(c, sequences, sequences.size() - 1);
        }

        return sequences;
    }

    public List<String> tapSequence() {
        if (keypadToControl == null) {
            return sequences;
        } else {
            List<String> newSequences = new ArrayList<>();
            for (String sequence : keypadToControl.sequences) {
                for (char c : sequence.toCharArray()) {
                    tapKey(c, newSequences, 0);
                }
            }
            this.sequences = newSequences;
        }
        return sequences;
    }

    public void tapOneKeySequence() {

    }

    public Long getComplexity(int code) {
        List<String> sequences = tapSequence();

        long shortest = Long.MAX_VALUE;
        for (String sequence : sequences) {
            if (sequence.length() < shortest) {
                shortest = sequence.length();
            }
        }
        return shortest * (long) code;
    }
}
