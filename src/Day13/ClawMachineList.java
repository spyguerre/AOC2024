package Day13;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a list of claw machines.
 */
public class ClawMachineList {
    /**
     * The List of Claw Machines.
     */
    public List<ClawMachine> clawMachines;

    /**
     * Constructor for a ClawMachineList.
     * @param clawMachines The list of Claw Machines to consider.
     */
    public ClawMachineList(List<ClawMachine> clawMachines) {
        this.clawMachines = new ArrayList<>(clawMachines);
    }

    /**
     * Appends a Claw Machine to the List.
     * @param clawMachine The Claw Machine to add.
     */
    public void add(ClawMachine clawMachine) {
        clawMachines.add(clawMachine);
    }

    /**
     * Computes the price to pay in order to get the item inside the claw machines. 0 if one can't get the item.
     * @return the price to pay in order to get the item inside the claw machines. 0 if one can't get the item.
     */
    public long getCheapPrice() {
        long price = 0;
        for (ClawMachine clawMachine : clawMachines) {
            price += clawMachine.getCheapPrice();
        }
        return price;
    }

    /**
     * Computes the price to pay in order to get the item inside the claw machines, considering the item's coordinates
     * are the ones given plus an additional (and ridiculous) 10000000000000.
     * @return the price to pay in order to get the item inside the claw machines, considering the item's coordinates
     * are the ones given plus an additional (and ridiculous) 10000000000000.
     */
    public long getExpensivePrice() {
        long price = 0;
        for (ClawMachine clawMachine : clawMachines) {
            price += clawMachine.getExpensivePrice();
        }
        return price;
    }
}
