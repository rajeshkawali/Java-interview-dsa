package com.rajeshkawali.dsa.array;

import java.util.*;


//Find the first repeating element in an array
public class FirstRepeatingElement {

    /*
     * Approach 1: Brute Force
     * - Compare each element with all others.
     * - Return the first element that repeats.
     * Time: O(n^2), Space: O(1)
     */
    public static int findFirstRepeatingBruteForce(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (arr[i] == arr[j]) {
                    return arr[i];
                }
            }
        }
        return -1; // no repeating element
    }

    /*
     * Approach 2: HashSet
     * - Traverse array, keep track of seen elements.
     * - If element already exists in set, return it immediately.
     * Time: O(n), Space: O(n)
     */
    public static int findFirstRepeatingHashSet(int[] arr) {
        Set<Integer> seen = new LinkedHashSet<>();
        for (int num : arr) {
            if (seen.contains(num)) {
                return num;
            }
            seen.add(num);
        }
        return -1;
    }

    /*
     * Approach 3: HashMap (track index of first occurrence)
     * - Store element → index mapping.
     * - If element repeats, return the one with smallest index.
     * Time: O(n), Space: O(n)
     */
    public static int findFirstRepeatingHashMap(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int minIndex = Integer.MAX_VALUE;
        int repeatingElement = -1;

        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                if (map.get(arr[i]) < minIndex) {
                    minIndex = map.get(arr[i]);
                    repeatingElement = arr[i];
                }
            } else {
                map.put(arr[i], i);
            }
        }
        return repeatingElement;
    }

    public static void main(String[] args) {
        int[] arr = {10, 5, 3, 4, 3, 5, 6};
        System.out.println("================================================");
        System.out.println("Brute Force → " + findFirstRepeatingBruteForce(arr));
        System.out.println("================================================");
        System.out.println("HashSet → " + findFirstRepeatingHashSet(arr));
        System.out.println("================================================");
        System.out.println("HashMap → " + findFirstRepeatingHashMap(arr));
        System.out.println("================================================");
    }
}
