package Day13;

public class ClawMachine {
    /**
     * Coordinates added on an "A" button press.
     */
    public long ax, ay;
    /**
     * Coordinates added on an "B" button press.
     */
    public long bx, by;
    /**
     * Coordinates of the item.
     */
    public long x1, y1;
    /**
     * Coordinates of the item plus an additional (and ridiculous) 10000000000000.
     */
    public long x2, y2;
    /**
     * Number of times to press each button to get the item. 0 if item is unreachable.
     */
    public long asol1, bsol1;
    /**
     * Number of times to press each button to get the item, considering the addition by 10000000000000.
     * 0 if item is unreachable.
     */
    public long asol2, bsol2;

    /**
     * Constructor for a single Claw Machine.
     * @param ax Number of units to move the claw in the X axis on an "A" button press.
     * @param ay Number of units to move the claw in the Y axis on an "A" button press.
     * @param bx Number of units to move the claw in the X axis on an "B" button press.
     * @param by Number of units to move the claw in the Y axis on an "B" button press.
     * @param x Number of units to travel in the X axis before reaching the item.
     * @param y Number of units to travel in the Y axis before reaching the item.
     */
    public ClawMachine(long ax, long ay, long bx, long by, long x, long y) {
        this.ax = ax;
        this.ay = ay;
        this.bx = bx;
        this.by = by;
        this.x1 = x;
        this.y1 = y;
        this.x2 = x+10000000000000L;
        this.y2 = y+10000000000000L;

        this.findSolution();
    }

    /**
     * Computes the number of button presses required to get the item for part 1 and part 2,
     * respectively in this.asol1/bsol1 and in this.asol2/bsol2.
     */
    private void findSolution() {
        // Algorithm for part 1; part 2 algorithm works as well but "bruteforce" in O(n) is fine imo
        for (long a = 0; a <= 100; a++) {
            double xdiv = (double)(x1-a*ax)/(double)bx;
            double ydiv = (double)(y1-a*ay)/(double)by;
            if (xdiv == Math.floor(xdiv) && ydiv == Math.floor(ydiv) && xdiv == ydiv) {
                asol1 = a; bsol1 = (int)xdiv;
                break;
            }
        }

        // Algorithm for part 2 in O(1) thanks to MATHS
        double adiv = (double)(y2*bx-x2*by)/(double)(ay*bx-ax*by);
        double bdiv = (double)(y2*ax-x2*ay)/(double)(by*ax-bx*ay);
        if (adiv == Math.floor(adiv) && bdiv == Math.floor(bdiv)) {
            asol2 = (long)adiv; bsol2 = (long)bdiv;
        } else {
            asol2 = 0; bsol2 = 0;
        }
    }

    /**
     * Computes the price to pay in order to get the item inside the claw machine. 0 if one can't get the item.
     * @return the price to pay in order to get the item inside the claw machine. 0 if one can't get the item.
     */
    public long getCheapPrice() {
        return asol1*3+bsol1;
    }

    /**
     * Computes the price to pay in order to get the item inside the claw machine, considering the item's coordinates
     * are the ones given plus an additional (and ridiculous) 10000000000000.
     * @return the price to pay in order to get the item inside the claw machine, considering the item's coordinates
     * are the ones given plus an additional (and ridiculous) 10000000000000.
     */
    public long getExpensivePrice() {
        return asol2*3+bsol2;
    }
}
