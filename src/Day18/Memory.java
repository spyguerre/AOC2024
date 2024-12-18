package Day18;

import utils.Coordinate;
import utils.Map2D;

import java.util.ArrayList;
import java.util.List;

public class Memory extends Map2D<Node> {
    public Memory(List<List<Node>> matrix) {
        super(matrix);
    }

    public void dijkstra() {
        List<Node> toVisit = new ArrayList<>();
        Node start = this.get(0, 0);
        start.cost = 0;
        toVisit.add(start);

        while (!toVisit.isEmpty()) {
            Node current = toVisit.removeFirst();

            for (int[] dir : directions) {
                Coordinate foundCoos = new Coordinate(current.coos.i + dir[0], current.coos.j + dir[1]);
                if (foundCoos.isInMap(n, p)) {
                    Node found = this.get(foundCoos);
                    if (!found.isCorrupted && (found.cost == null || current.cost+1 < found.cost)) {
                        found.origin = current;
                        found.cost = current.cost + 1;
                        toVisit.add(found);
                    }
                }
            }
        }
    }

    public Integer getPathLength() {
        Node current = this.get(n-1, p-1);
        Integer length = null;

        while (!current.coos.equals(0, 0)) {
            current = current.origin;
            if (current == null) {break;}
            if (length == null) {length = 0;}
            length++;
        }

        return length;
    }

    public void resetDijkstra() {
        for (List<Node> row : matrix) {
            for (Node node : row) {
                node.cost = null;
                node.origin = null;
            }
        }
    }
}
