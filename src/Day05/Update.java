package Day05;

import java.util.List;
import java.util.ArrayList;

public class Update {
    public List<Integer> pages = new ArrayList<>();

    public Update() {}

    // If not rearrange: returns whether the update is valid
    // If rearrange: rearranges the update if necessary and returns whether the update was MODIFIED
    private boolean isOrderedAux(List<Constraint> constraints, boolean rearrange) {
        if (rearrange) {
            boolean ModifiedUpdate = false;

            for (Constraint constraint : constraints) {
                ModifiedUpdate = ModifiedUpdate || !constraint.checkValidity(this, rearrange);
            }

            return ModifiedUpdate;
        } else {
            boolean updateIsValid = true;

            for (Constraint constraint : constraints) {
                updateIsValid = updateIsValid && constraint.checkValidity(this, rearrange);
                if (!updateIsValid) {
                    break;
                }
            }

            return updateIsValid;
        }
    }

    // Returns whether the update was originally valid
    // If rearrange: also sorts the update to make it valid
    public boolean isOrdered(List<Constraint> constraints, boolean rearrange) {
        boolean updateOriginallyValid = isOrderedAux(constraints, false);

        if (!updateOriginallyValid) {
            boolean modifiedUpdate = true; // Set to true to enter the loop the first time
            while (modifiedUpdate && rearrange) { // Iterate until all constraints are respected and update is not modified
                modifiedUpdate = isOrderedAux(constraints, rearrange);
            }
        }

        return updateOriginallyValid;
    }

    public boolean irOrdered(List<Constraint> constraints) {
        return this.isOrdered(constraints, false);
    }
}