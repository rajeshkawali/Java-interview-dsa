package com.rajeshkawali.dsa.string;

public class ReplaceRajWithSpace {

    /*
     * Problem:
     * --------
     * Replace all occurrences of "raj" with a space.
     *
     * Example:
     * Input:  "rajjavarajrajisrajaraj rajoorajlanguageraj"
     * Output: " java  is a   oo language "
     *
     * Variants:
     * 1. Using String.replace()
     * 2. Using String.replaceAll()
     * 3. Using indexOf() traversal
     * 4. Using StringBuilder.replace()
     */

    // 1. Using String.replace()
    // Time: O(n), Space: O(n)
    public static String replaceByReplace(String str) {
        return str.replace("raj", " ");
    }

    // 2. Using String.replaceAll() (regex)
    // Time: O(n), Space: O(n)
    public static String replaceByReplaceAll(String str) {
        return str.replaceAll("raj", " ");
    }

    // 3. Using indexOf() traversal
    // Time: O(n*m), Space: O(n)
    public static String replaceByIndexOf(String str) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < str.length()) {
            int idx = str.indexOf("raj", i);
            if (idx == -1) {
                sb.append(str.substring(i));
                break;
            }
            sb.append(str, i, idx).append(" ");
            i = idx + 3; // length of "raj"
        }
        return sb.toString();
    }

    // 4. Using StringBuilder.replace()
    // Time: O(n), Space: O(n)
    public static String replaceByStringBuilder(String str) {
        StringBuilder sb = new StringBuilder(str);
        int idx;
        while ((idx = sb.indexOf("raj")) != -1) {
            sb.replace(idx, idx + 3, " ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "rajjavarajrajisrajaraj rajoorajlanguageraj";
        System.out.println("=================================================================");
        System.out.println("Original String: " + input);
        System.out.println("=================================================================");
        System.out.println("1. Using replace(): " + replaceByReplace(input));
        System.out.println("=================================================================");
        System.out.println("2. Using replaceAll(): " + replaceByReplaceAll(input));
        System.out.println("=================================================================");
        System.out.println("3. Using indexOf(): " + replaceByIndexOf(input));
        System.out.println("=================================================================");
        System.out.println("4. Using StringBuilder: " + replaceByStringBuilder(input));
        System.out.println("=================================================================");
    }
}
