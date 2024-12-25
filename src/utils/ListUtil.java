package utils;

import java.util.List;

public abstract class ListUtil {
    public static int workingGetIndex(List<Object> list, Object object) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).equals(object)) {
                return i;
            }
        }
        return -1;
    }
}
