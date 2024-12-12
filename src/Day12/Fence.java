package Day12;

import utils.*;
import java.util.List;

/**
 * A class representing a fence.
 */
public class Fence extends Coordinate {
    /**
     * The direction of the fence. Always faces away from the region it's attached to.
     */
    public int dir;

    /**
     * Constructor for a Fence.
     * @param i The first coordinate of the Fence.
     * @param j The second coordinate of the Fence.
     * @param direction The direction of the Fence. Always faces away from the region it's attached to.
     */
    public Fence(int i, int j, int direction) {
        super(i, j);
        this.dir = direction;
    }

    /**
     * Computes whether this Fence has the same i, j and dir as the other Fence passed as an argument.
     * @param f The Fence to compare the object with.
     * @return whether this Fence has the same i, j and dir as the other Fence passed as an argument.
     */
    public boolean equals(Fence f) {
        return super.equals(f) && this.dir == f.dir;
    }

    /**
     * Computes whether the fence or a fence with similar attributes is in the fenceList.
     * If remove parameter is set to true and a similar fence is found, remove it from the list.
     * @param fenceList The list of fences.
     * @param remove Whether to remove the fence from the list if one is found.
     * @return whether the fence was found in the list.
     */
    public boolean isInList(List<Fence> fenceList, boolean remove) {
        for (Fence fence : fenceList) {
            if (this.equals(fence)) {
                if (remove) {
                    fenceList.remove(fence);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Method used to print the Fence.
     * @return a string describing the Fence.
     */
    public String toString() {
        return "(" + this.i + ", " + this.j + ", " + dir + ")";
    }
}
