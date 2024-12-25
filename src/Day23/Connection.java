package Day23;

public abstract class Connection {
    public static void addConnection(Computer c1, Computer c2) {
        c1.connections.add(c2);
        c2.connections.add(c1);
    }
}
