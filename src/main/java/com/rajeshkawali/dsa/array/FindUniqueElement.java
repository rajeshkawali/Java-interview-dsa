package com.rajeshkawali.dsa.array;
/*
 * Problem: Find the unique element in a given array where all other elements appear twice.
 *
 * Approaches:
 * 1. XOR Method:
 *    - XOR of two same numbers = 0
 *    - XOR of any number with 0 = number itself
 *    - So XOR of all elements leaves the unique element.
 *
 * 2. HashMap / Frequency Count:
 *    - Count occurrences of each element.
 *    - The one with frequency == 1 is unique.
 *
 * 3. Sorting + Linear Scan:
 *    - Sort array.
 *    - Compare adjacent elements.
 *    - The one without a pair is unique.
 *
 * 4. Set Method:
 *    - Add elements to a set.
 *    - If element repeats, remove it.
 *    - The remaining element in set is unique.
 *
 * Complexity:
 * - XOR: Time O(n), Space O(1) → Best
 * - HashMap: Time O(n), Space O(n)
 * - Sorting: Time O(n log n), Space O(1)
 * - Set: Time O(n), Space O(n)
 */

import java.util.*;

public class FindUniqueElement {

    // Method 1: XOR Method
	public static int findByXOR(int[] arr) {
	    int result = 0;              // Step 1: Start with 0
	    for (int num : arr) {        // Step 2: Loop through each element
	        result = result ^ num;   // Step 3: XOR current element with result
	    }
	    return result;               // Step 4: Return the final value
	}

    // Method 2: HashMap Frequency
    public static int findByHashMap(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }
        return -1;
    }

    // Method 3: Sorting + Linear Scan
    public static int findBySorting(int[] arr) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i += 2) {
            if (arr[i] != arr[i + 1]) return arr[i];
        }
        return arr[arr.length - 1]; // last element unique
    }

    // Method 4: Set Method
    public static int findBySet(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : arr) {
            if (set.contains(num)) set.remove(num);
            else set.add(num);
        }
        return set.iterator().next();
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 4, 5, 3, 2}; // Unique element = 4
        System.out.println("==================================================================");
        System.out.println("Unique Element(XOR): " + findByXOR(arr));
        System.out.println("==================================================================");
        System.out.println("Unique Element(HashMap): " + findByHashMap(arr));
        System.out.println("==================================================================");
        System.out.println("Unique Element(Sorting): " + findBySorting(arr.clone()));
        System.out.println("==================================================================");
        System.out.println("Unique Element(Set): " + findBySet(arr));
        System.out.println("==================================================================");
    }
}
/*
XOR basics:
𝑎 ⊕ 𝑎 = 0  → Same numbers cancel each other.
𝑎 ⊕ 0 = 𝑎  → XOR with zero keeps the number.
XOR is commutative and associative → order doesn’t matter.
*/