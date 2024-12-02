package Day01;

import java.util.List;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import utils.*;

public class TwoLists {
    public List<Integer> list1;
    public List<Integer> list2;


    public TwoLists() {
        this.list1 = new ArrayList<>();
        this.list2 = new ArrayList<>();
    }

    public TwoLists(List<Integer> list1, List<Integer> list2) {
        this.list1 = list1;
        this.list2 = list2;
    }
    
    public void sortLists() {
        this.list1.sort(null);
        this.list2.sort(null);
    }
    
    public int addDistances() {
        int res = 0;
        
        for (int i = 0; i < this.list1.size(); i++) {
            res += Math.abs(this.list1.get(i) - this.list2.get(i));
        }
        
        return res;
    }

    public int similarityScore() {
        int res = 0;

        for (int i = 0; i < this.list1.size(); i++) {
            int value = this.list1.get(i);
            res += value * Collections.frequency(this.list2, value);
        }

        return res;
    }
}
