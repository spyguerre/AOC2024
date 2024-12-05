package Day05;

public class Constraint {
    public int page1;
    public int page2;

    public Constraint(int page1, int page2) {
        this.page1 = page1;
        this.page2 = page2;
    }

    // If not rearrange: returns whether the update respects this constraint
    // If rearrange: swaps the two pages if necessary then returns whether the pages were swapped
    public boolean checkValidity(Update update, boolean rearrange) {
        int i1 = update.pages.indexOf(this.page1); int i2 = update.pages.indexOf(this.page2);
        if (!rearrange) {
            return i1 == -1 || i2 == -1 || i1 < i2;
        } else {
            if (i1 != -1 && i2 != -1 && i1 >= i2) { // Swaps both pages in the Update if necessary
                update.pages.set(i1, this.page2);
                update.pages.set(i2, this.page1);
                return false;
            } else {
                return true;
            }
        }
    }
}