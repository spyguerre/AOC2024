package utils;

import java.util.Map;

/**
 * A few useful methods for java.util.Map's.
 */
public abstract class MapUtil {
    /**
     * Increments an element of the map by value.
     * @param map The dictionary.
     * @param key The key in which to add something to.
     * @param value The value to add.
     */
    public static void incr(Map<Object, Long> map, Object key, Long value) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key)+value);
        } else {
            map.put(key, value);
        }
    }

    /**
     * Increments an element of the map by 1.
     * @param map The dictionary.
     * @param key The key in which to add 1 to.
     */
    public static void incr(Map<Object, Long> map, Object key) {
        incr(map, key, 1L);
    }

}
