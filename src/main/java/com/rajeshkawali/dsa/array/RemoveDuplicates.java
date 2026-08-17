package com.rajeshkawali.dsa.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;


/*
 * Remove Duplicates from Array:
 * 1. HashSet → Fast, but order not guaranteed. O(n) time, O(n) space.
 * 2. Sorting + Linear Scan → In-place, but changes order. O(n log n) time, O(1) space.
 * 3. LinkedHashSet → Preserves original order. O(n) time, O(n) space.
 * 4. Stream distinct() → Concise, modern Java. O(n) time, O(n) space.
 *
 * Key Point:
 * - Choose HashSet for speed.
 * - Choose LinkedHashSet if order matters.
 * - Choose Sorting if you want sorted unique values.
 * - Choose Stream for clean, modern code.
 */

public class RemoveDuplicates {
	
	public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 4, 4, 5};

        System.out.println("Using HashSet:");
        System.out.println(Arrays.toString(removeWithHashSet(arr)));
        System.out.println("================================================");
        System.out.println("Using Sorting + Linear Scan:");
        System.out.println(Arrays.toString(removeWithSorting(arr.clone())));
        System.out.println("================================================");
        System.out.println("Using LinkedHashSet (preserve order):");
        System.out.println(Arrays.toString(removeWithLinkedHashSet(arr)));
        System.out.println("================================================");
        System.out.println("Using Stream (Java 8+):");
        System.out.println(Arrays.toString(removeWithStream(arr)));
        System.out.println("================================================");
        System.out.println("Print Duplicates:");
        printDuplicate(arr);
        System.out.println("================================================");
    }

    /*
     * Approach 1: HashSet
     * - Add elements to a HashSet (unique only).
     * - Convert back to array.
     * Time: O(n), Space: O(n)
     */
    public static int[] removeWithHashSet(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr) set.add(num);
        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    /*
     * Approach 2: Sorting + Linear Scan
     * - Sort array.
     * - Traverse, skip duplicates.
     * Time: O(n log n), Space: O(1) (if in-place)
     */
    public static int[] removeWithSorting(int[] arr) {
        Arrays.sort(arr);
        int j = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[j]) {
                j++;
                arr[j] = arr[i];
            }
        }
        return Arrays.copyOfRange(arr, 0, j + 1);
    }

    /*
     * Approach 3: LinkedHashSet
     * - Preserves insertion order.
     * - Useful if you want to keep original sequence.
     * Time: O(n), Space: O(n)
     */
    public static int[] removeWithLinkedHashSet(int[] arr) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int num : arr) set.add(num);
        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    /*
     * Approach 4: Java 8 Stream
     * - Use distinct() to filter unique values.
     * - Very concise.
     * Time: O(n), Space: O(n)
     */
    public static int[] removeWithStream(int[] arr) {
        return Arrays.stream(arr).distinct().toArray();
    }
    
    // Time: O(n²), Space: O(1).
    private static void printDuplicate(int[] arr) {
		for(int i=0; i< arr.length; i++) {
			for(int j=i+1; j< arr.length; j++) {
				if(arr[i]==arr[j]) {
					System.out.println("Duplicate value: "+arr[i]);
				}
			}
		}
	}
}

	


