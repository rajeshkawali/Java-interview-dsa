package com.rajeshkawali.dsa.string;

public class ReplaceSubstring {

    /*
     * Problem:
     * --------
     * Replace all occurrences of a given substring in a string with a space.
     *
     * Example:
     * Input:  str = "java is a programming language", target = "programming"
     * Output: "java is a   language"
     *
     * Variants:
     * 1. Using String.replace()
     * 2. Using String.replaceAll() (regex)
     * 3. Manual Traversal with indexOf()
     * 4. Using StringBuilder
     */

    // 1. Using String.replace()
    // Time: O(n), Space: O(n)
    public static String replaceByReplace(String str, String target) {
        return str.replace(target, " ");
    }

    // 2. Using String.replaceAll() (regex)
    // Time: O(n), Space: O(n)
    public static String replaceByReplaceAll(String str, String target) {
        return str.replaceAll(target, " ");
    }

    // 3. Manual Traversal with indexOf()
    // Time: O(n * m) (n = length of string, m = length of target)
    // Space: O(n)
    public static String replaceByIndexOf(String str, String target) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < str.length()) {
            int idx = str.indexOf(target, i);
            if (idx == -1) {
                sb.append(str.substring(i));
                break;
            }
            sb.append(str, i, idx).append(" ");
            i = idx + target.length();
        }
        return sb.toString();
    }

    // 4. Using StringBuilder (replace method)
    // Time: O(n), Space: O(n)
    public static String replaceByStringBuilder(String str, String target) {
        StringBuilder sb = new StringBuilder(str);
        int idx;
        while ((idx = sb.indexOf(target)) != -1) {
            sb.replace(idx, idx + target.length(), " ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "java is a programming language";
        String target = "programming";
        System.out.println("=================================================================");
        System.out.println("Original String: " + input);
        System.out.println("Target to replace: " + target);
        System.out.println("=================================================================");
        System.out.println("1. Using replace(): " + replaceByReplace(input, target));
        System.out.println("=================================================================");
        System.out.println("2. Using replaceAll(): " + replaceByReplaceAll(input, target));
        System.out.println("=================================================================");
        System.out.println("3. Using indexOf(): " + replaceByIndexOf(input, target));
        System.out.println("=================================================================");
        System.out.println("4. Using StringBuilder: " + replaceByStringBuilder(input, target));
        System.out.println("=================================================================");
    }
}

/*

Replace Substring with Space:
- String.replace() → O(n), simplest.
- String.replaceAll() → O(n), regex-based.
- indexOf() traversal → O(n*m), manual control.
- StringBuilder.replace() → O(n), efficient in-place.

*/