package com.rajeshkawali.dsa.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class UnionOfTwoArrays {

	/*
	 * Logic: 1. The union of two arrays means all unique elements from both arrays
	 * combined. 2. Use a HashSet to store elements (since it automatically avoids
	 * duplicates). 3. Add all elements from the first array, then from the second
	 * array. 4. Print the set as the union result.
	 * 
	 * Complexity: Time: O(n + m) — where n and m are lengths of the two arrays
	 * Space: O(n + m) — HashSet stores unique elements
	 */

	public static void main(String[] args) {
		int[] arr1 = { 10, 20, 30, 40, 50 };
		int[] arr2 = { 30, 40, 50, 60, 70 };

		unionUsingHashSet(arr1, arr2);
		System.out.println("===============================================");
		unionUsingLinkedHashSet(arr1, arr2);
		System.out.println("===============================================");
		unionUsingSorting(arr1, arr2);
		System.out.println("===============================================");
		unionManualCheck(arr1, arr2);
		System.out.println("===============================================");
	}

	// 1. Using HashSet (fastest, but order not guaranteed)
	public static void unionUsingHashSet(int[] arr1, int[] arr2) {
		HashSet<Integer> unionSet = new HashSet<>(); // store unique elements

		// Add elements from first array
		for (int num : arr1) {
			unionSet.add(num);
		}

		// Add elements from second array
		for (int num : arr2) {
			unionSet.add(num);
		}

		// Print union
		System.out.print("Union using HashSet: ");
		for (int num : unionSet) {
			System.out.print(num + " ");
		}
		System.out.println();
	}

	// 2. Using LinkedHashSet (preserves insertion order)
	public static void unionUsingLinkedHashSet(int[] arr1, int[] arr2) {
		Set<Integer> set = new LinkedHashSet<>();
		for (int num : arr1)
			set.add(num);
		for (int num : arr2)
			set.add(num);

		System.out.print("Union using LinkedHashSet: ");
		for (int num : set)
			System.out.print(num + " ");
		System.out.println();
	}

	// 3. Using Sorting + Merge (DSA style)
	public static void unionUsingSorting(int[] arr1, int[] arr2) {
		Arrays.sort(arr1);
		Arrays.sort(arr2);

		int i = 0, j = 0;
		System.out.print("Union using Sorting: ");
		while (i < arr1.length && j < arr2.length) {
			if (arr1[i] < arr2[j]) {
				System.out.print(arr1[i++] + " ");
			} else if (arr2[j] < arr1[i]) {
				System.out.print(arr2[j++] + " ");
			} else {
				System.out.print(arr1[i] + " "); // equal
				i++;
				j++;
			}
		}
		while (i < arr1.length)
			System.out.print(arr1[i++] + " ");
		while (j < arr2.length)
			System.out.print(arr2[j++] + " ");
		System.out.println();
	}

	// 4. Manual check (without collections)
	public static void unionManualCheck(int[] arr1, int[] arr2) {
		List<Integer> unionList = new ArrayList<>();

		for (int num : arr1) {
			if (!unionList.contains(num))
				unionList.add(num);
		}
		for (int num : arr2) {
			if (!unionList.contains(num))
				unionList.add(num);
		}

		System.out.print("Union using Manual Check: ");
		for (int num : unionList)
			System.out.print(num + " ");
		System.out.println();
	}
}
