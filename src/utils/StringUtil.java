package utils;

public abstract class StringUtil {
    /**
     * Finds the longest suffix between s1 and s2.
     * @param s1 The first string.
     * @param s2 The second string.
     * @return the longest suffix between s1 and s2.
     */
    public static String longestSuffix(String s1, String s2) {
        int s1l = s1.length(); int s2l = s2.length();
        int minLength = Math.min(s1l, s2l);

        for (int i = 0; i < minLength; i++) {
            if (s1.charAt(s1l-1-i) != s2.charAt(s2l-1-i)) {
                return s1.substring(s1l-i);
            }
        }

        return s1;
    }
}
