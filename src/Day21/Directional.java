package Day21;

import java.util.Map;
import utils.Coordinate;

public class Directional extends Keypad {
    public Directional(Keypad keypadToControl, String sequence) {
        super(2, 0, keypadToControl, sequence);

        layout.put('^', new Coordinate(0, 1));
        layout.put('A', new Coordinate(0, 2));
        layout.put('<', new Coordinate(1, 0));
        layout.put('v', new Coordinate(1, 1));
        layout.put('>', new Coordinate(1, 2));
    }

    public Directional(Keypad keypadToControl) {
        this(keypadToControl, "");
    }
}
