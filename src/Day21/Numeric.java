package Day21;

import utils.Coordinate;

public class Numeric extends Keypad {
    public Numeric(Keypad keypadToControl, String sequence) {
        super(2, 3, keypadToControl, sequence);

        layout.put('7', new Coordinate(0, 0));
        layout.put('8', new Coordinate(0, 1));
        layout.put('9', new Coordinate(0, 2));
        layout.put('4', new Coordinate(1, 0));
        layout.put('5', new Coordinate(1, 1));
        layout.put('6', new Coordinate(1, 2));
        layout.put('1', new Coordinate(2, 0));
        layout.put('2', new Coordinate(2, 1));
        layout.put('3', new Coordinate(2, 2));
        layout.put('0', new Coordinate(3, 1));
        layout.put('A', new Coordinate(3, 2));
    }

    public Numeric(Keypad keypadToControl) {
        this(keypadToControl, "");
    }
}
