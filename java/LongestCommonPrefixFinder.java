/**
 * Created by alpoloz on 11.1.16.
 * Write a function to find the longest common prefix string amongst an array of strings.
 */
public class LongestCommonPrefixFinder {

    public static void main(String... args) {
        String[] data = {"aaa", "aaab", "aacb", "aa"};
        System.out.println(findCommonPrefix(data));

    }

    private static String findCommonPrefix(String[] data) {
        String shortestStr = data[0];
        for(String str : data) {
            if (shortestStr.length() > str.length()) {
                shortestStr = str;
            }
        }

        String commonPrefix = shortestStr;
        for(String str : data) {
            int i = 0;
            for(;i < commonPrefix.length() && commonPrefix.charAt(i) == str.charAt(i); i++) {
            }
            commonPrefix = str.substring(0, i);
        }
        return commonPrefix;
    }
}
