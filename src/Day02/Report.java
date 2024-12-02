package Day02;

import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

public class Report {
    public List<Integer> intList;

    public Report() {
        this.intList = new ArrayList<>();
    }

    public Report(List<Integer> intList) {
        this.intList = intList;
    }

    // Returns whether the report is safe or not
    public boolean checkDirection(boolean dampener) {
        // res = direction ; -1 for decreasing, 1 for increasing, 0 for invalid or not yet known.
        int res = 0;
        int lastInt = this.intList.get(0);

        List<Integer> dampenedList1 = new ArrayList<>(this.intList); // Copy without element i
        List<Integer> dampenedList2 = new ArrayList<>(this.intList); // Copy without element i-1
        List<Integer> dampenedList3 = new ArrayList<>(this.intList); // Copy without element 0
        // dList3 only necessary for this ONE SPECIFIC COUNTEREXAMPLE AAAAAAAAA: 48 46 47 49 51 54 56 (not my phone number btw)

        // Save whether the problem dampener joker has already been used
        boolean jokerUsed = false;

        int i = 1;
        int maxi = this.intList.size();
        while (i < maxi && !jokerUsed) {
            int currentInt = this.intList.get(i);

            if (lastInt < currentInt && currentInt < lastInt + 4 && (res == 0 || res == 1)) {  // Check increasing
                res = 1;
                lastInt = currentInt;
            } else if (lastInt - 4 < currentInt && currentInt < lastInt && (res == 0 || res == -1)) {  // Check decreasing
                res = -1;
                lastInt = currentInt;
            } else {
                if (!dampener) {
                    return false;
                } else {  // Break out of the while loop and continue iteration on both new lists instead
                    jokerUsed = true;
                    dampenedList1.remove(i);
                    dampenedList2.remove(i-1);
                    dampenedList3.remove(0);
                }
            }

            i++;
        }

        if (!jokerUsed || i == maxi) {
            return true;
        } else {
            return (new Report(dampenedList1)).checkDirection()
                    || (new Report(dampenedList2)).checkDirection()
                    || (new Report(dampenedList3)).checkDirection();
        }
    }

    public boolean checkDirection() {
        return this.checkDirection(false);
    }

    public void add(int num) {
        this.intList.add(num);
    }
}
