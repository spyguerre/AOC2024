package Day23;

import java.util.ArrayList;
import java.util.List;

public class Computer {
    public String name;
    public List<Computer> connections;

    public Computer(String name) {
        this.name = name;
        connections = new ArrayList<>();
    }

    public boolean equals(Computer c) {
        return name.equals(c.name);
    }

    public boolean equals(String name) {
        return this.name.equals(name);
    }

    public String toString() {
        return name;
    }

    public static int compare(Computer c1, Computer c2) {
        return c1.name.compareTo(c2.name);
    }
}
