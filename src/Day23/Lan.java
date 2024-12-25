package Day23;

import java.util.*;

public class Lan {
    public Map<String, Computer> computers;

    public Lan(List<String> input) {
        computers = new HashMap<>();

        for (String line : input) {
            String[] parts = line.split("-");

            Computer c1; Computer c2;
            if (!computers.containsKey(parts[0])) {
                c1 = new Computer(parts[0]);
                computers.put(parts[0], c1);
            } else {
                c1 = computers.get(parts[0]);
            }

            if (!computers.containsKey(parts[1])) {
                c2 = new Computer(parts[1]);
                computers.put(parts[1], c2);
            } else {
                c2 = computers.get(parts[1]);
            }

            Connection.addConnection(c1, c2);
        }
    }

    public List<List<Computer>> getGroupsOf3() { // Bad code to not bother too much for part 1.
        List<List<Computer>> res = new ArrayList<>();

        for (Computer c : computers.values()) {
            for (Computer c2 : c.connections) {
                for (Computer c3 : c2.connections) {
                    if (c3.connections.contains(c)) {
                        if ('t' == c.name.charAt(0) || 't' == c2.name.charAt(0) || 't' == c3.name.charAt(0)) {
                            List<Computer> group = new ArrayList<>();
                            group.add(c);
                            group.add(c2);
                            group.add(c3);
                            group.sort(Computer::compare);

                            if (!res.contains(group)) {
                                res.add(group);
                            }
                        }
                    }
                }
            }
        }

        return res;
    }

    public String getLargestGroup() {
        List<Computer> res = new ArrayList<>();

        // Initializes the list of groups to explore.
        List<Computer> group = new ArrayList<>();
        for (Computer c : computers.values()) {
            group.add(c);
            for (Computer c2 : c.connections) {
                boolean canAdd = true;
                for (Computer c3 : group) {
                    if (!c3.connections.contains(c2)) {
                        canAdd = false;
                        break;
                    }
                }
                if (canAdd) {
                    group.add(c);
                }
            }

            
        }

        res.sort(Computer::compare);

        StringBuilder largest = new StringBuilder();
        for (Computer c : res) {
            if (!largest.isEmpty()) largest.append(",").append(c.name); else largest.append(c.name);
        }

        return largest.toString();
    }

    public void print() {
        for (Computer c : computers.values()) {
            System.out.println(c + " " + c.connections);
        }
    }
}
