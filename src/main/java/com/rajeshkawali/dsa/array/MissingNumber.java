package com.rajeshkawali.dsa.array;
/*
 * Problem: Find the missing number in an array of size n containing numbers from 1 to n+1.
 *
 * Approaches:
 * 1. Sum Formula:
 *    - Expected sum = n*(n+1)/2
 *    - Actual sum = sum of array elements
 *    - Missing = Expected - Actual
 *
 * 2. XOR Method:
 *    - XOR all numbers from 1 to n+1
 *    - XOR with all array elements
 *    - Result = missing number (because duplicates cancel out)
 *
 * 3. Sorting + Linear Scan:
 *    - Sort array
 *    - Traverse sequentially, check where index+1 != element
 *    - That mismatch is the missing number
 *
 * 4. HashSet / Boolean Array:
 *    - Mark presence of each number
 *    - The number not marked is missing
 *
 * Complexity:
 * - Sum Formula: Time O(n), Space O(1)
 * - XOR Method: Time O(n), Space O(1)
 * - Sorting: Time O(n log n), Space O(1)
 * - HashSet: Time O(n), Space O(n)
 */

import java.util.Arrays;
import java.util.HashSet;

public class MissingNumber {

	// Method 1: Sum Formula
	public static int findBySum(int[] arr, int n) {
		int expectedSum = n * (n + 1) / 2; // expectedSum --> 5 * (5 + 1) / 2 = 15
		int actualSum = 0;
		for (int num : arr) {
			actualSum += num; // actualSum --> 12
		}
		return expectedSum - actualSum; // 15 - 12 = 3
	}

	// Method 2: XOR Method
	public static int findByXOR(int[] arr, int n) {
		int xorAll = 0;
		for (int i = 1; i <= n; i++) {
			xorAll ^= i;
		}
		int xorArr = 0;
		for (int num : arr) {
			xorArr ^= num;
		}
		return xorAll ^ xorArr;
	}

	// Method 3: Sorting + Linear Scan
	public static int findBySorting(int[] arr, int n) {
		Arrays.sort(arr);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != i + 1)
				return i + 1;
		}
		return n; // if last number missing
	}

	// Method 4: HashSet
	public static int findByHashSet(int[] arr, int n) {
		HashSet<Integer> set = new HashSet<>();
		for (int num : arr) {
			set.add(num);
		}
		for (int i = 1; i <= n; i++) {
			if (!set.contains(i)) {
				return i;
			}
		}
		return -1; // should not happen
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 4, 5 }; // Missing number is 3
		int n = 5; // numbers from 1 to 5
		System.out.println("==================================================================");
		System.out.println("Missing number(Sum Formula): " + findBySum(arr, n));
		System.out.println("==================================================================");
		System.out.println("Missing number(XOR Method): " + findByXOR(arr, n));
		System.out.println("==================================================================");
		System.out.println("Missing number(Sorting): " + findBySorting(arr.clone(), n));
		System.out.println("==================================================================");
		System.out.println("Missing number(HashSet): " + findByHashSet(arr, n));
		System.out.println("==================================================================");
	}
}
