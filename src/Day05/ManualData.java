package Day05;

import java.util.List;
import java.util.ArrayList;

public class ManualData {
    public List<Constraint> constraints = new ArrayList<>();
    public List<Update> updates = new ArrayList<>();

    public ManualData() {}

    // If not rearrange: returns only the sum of the middle element of valid updates
    // If rearrange: corrects order of all updates and returns only the sum of the middle element of the ORIGINALLY NOT VALID updates
    public Integer sumMiddlePagesFromValidUpdates(boolean rearrange) {
        Integer sum = 0;
        int count = 0;
        for (Update update : this.updates) {
            boolean updateIsValid = update.isOrdered(this.constraints, rearrange);
            if (updateIsValid && !rearrange) { // Processing every valid update
                sum += update.pages.get(update.pages.size()/2);
            } else if (!updateIsValid && rearrange) { // Counting every invalid and corrected update
                sum += update.pages.get(update.pages.size()/2);
                count++;
            }
        }

        return sum;
    }

    public Integer sumMiddlePagesFromValidUpdates() {
        return sumMiddlePagesFromValidUpdates(false);
    }
}