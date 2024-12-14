package Day14;

/**
 * Class representing a robot by its position (x, y) and its speed (vx, vy).
 */
public class Robot {
    /**
     * The robot's current position.
     */
    public int x, y;
    /**
     * The robot's speed.
     */
    public int vx, vy;

    /**
     * Constructor for a Robot.
     * @param x The robot's position along the X axis.
     * @param y The robot's position along the Y axis.
     * @param vx The robot's speed along the X axis.
     * @param vy The robot's speed along the Y axis.
     */
    public Robot(int x, int y, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    /**
     * Moves the Robot to the position it'll be next second.
     * @param maxx The size of the map along the X axis.
     * @param maxy The size of the map along the Y axis.
     */
    public void move(int maxx, int maxy) {
        this.x += vx;
        while (this.x < 0) {this.x += maxx;} // In case this.x gets negative.
        this.x %= maxx;

        this.y += vy;
        while (this.y < 0) {this.y += maxy;} // In case this.y gets negative.
        this.y %= maxy;
    }
}
