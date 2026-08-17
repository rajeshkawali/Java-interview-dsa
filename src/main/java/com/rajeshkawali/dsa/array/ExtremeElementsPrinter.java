package com.rajeshkawali.dsa.array;

import java.util.*;

// Print the extreme elements of an array in alternate order — meaning you print the first element, 
// then the last, then the second, then the second last, and so on. 
public class ExtremeElementsPrinter {

	// 1. Two-Pointer Approach
	public static void printExtremeElementsTwoPointer(int[] arr) {
		int left = 0;
		int right = arr.length - 1;
		System.out.print("Two-Pointer: ");
		while (left <= right) {
			System.out.print(arr[left] + " ");
			left++;
			if (left <= right) {
				System.out.print(arr[right] + " ");
				right--;
			}
		}
		System.out.println();
	}

	// 2. Using a New Result Array
	public static void printExtremeElementsNewArray(int[] arr) {
		int n = arr.length;
		int[] result = new int[n];
		int left = 0, right = n - 1, idx = 0;

		while (left <= right) {
			result[idx++] = arr[left++];
			if (left <= right)
				result[idx++] = arr[right--];
		}

		System.out.print("New Array: ");
		for (int num : result)
			System.out.print(num + " ");
		System.out.println();
	}

	// 3. Recursive Approach
	public static void printExtremeElementsRecursive(int[] arr, int left, int right) {
		if (left > right)
			return;
		System.out.print(arr[left] + " ");
		if (left != right)
			System.out.print(arr[right] + " ");
		printExtremeElementsRecursive(arr, left + 1, right - 1);
	}

	// 4. Queue + Stack Approach
	public static void printExtremeElementsQueueStack(int[] arr) {
		Queue<Integer> q = new LinkedList<>(); // queue to hold elements in order (front to back)
		Stack<Integer> s = new Stack<>(); // stack to hold elements in reverse order (back to front)

		for (int num : arr) {
			q.add(num); // enqueue each element
			s.push(num); // push each element onto stack
		}

		System.out.print("Queue+Stack: ");
		while (!q.isEmpty() && !s.isEmpty()) {
			System.out.print(q.poll() + " "); // print from front (queue)
			if (!s.isEmpty())
				System.out.print(s.pop() + " "); // print from back (stack)
		}
		System.out.println();
	}

	// 5. Index Trick Approach
	public static void printExtremeElementsIndexTrick(int[] arr) {
		int n = arr.length;
		System.out.print("Index Trick: ");
		for (int i = 0; i < n; i++) {
			if (i % 2 == 0) {
				System.out.print(arr[i / 2] + " ");
			} else {
				System.out.print(arr[n - 1 - i / 2] + " ");
			}
		}
		System.out.println();
	}

	// 6. Two-Pointers Approach
	public static void printExtremeElementsByUsingTwoPointer(int[] arr) {
		int n = arr.length;
		int i = 0;
		int j = n - 1;
		System.out.print("Two-Pointer: ");
		while (i <= j) {
			if(i==j) {
				System.out.print(arr[i] + " ");
				return;
			} else {
				System.out.print(arr[i] + " ");
				i++;
				System.out.print(arr[j] + " ");
				j--;
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = { 10, 20, 30, 40, 50, 60 };

		// Method 1: Two-Pointer
		printExtremeElementsTwoPointer(arr);

		// Method 2: New Result Array
		printExtremeElementsNewArray(arr);

		// Method 3: Recursive
		System.out.print("Recursive: ");
		printExtremeElementsRecursive(arr, 0, arr.length - 1);
		System.out.println();

		// Method 4: Queue + Stack
		printExtremeElementsQueueStack(arr);

		// Method 5: Index Trick
		printExtremeElementsIndexTrick(arr);
		
		// Method 6: Two pointer approach
		printExtremeElementsByUsingTwoPointer(arr);
	}
}


/*
 * Logic: (printExtremeElementsByUsingTwoPointer)
 * 1. Use two pointers: 
 *    - 'i' starts at the beginning, 'j' starts at the end.
 * 2. Print arr[i] (left side), then arr[j] (right side).
 * 3. Move 'i' forward and 'j' backward after each print.
 * 4. Continue until both pointers meet or cross.
 * 5. Special case: if i == j, print the middle element once.
 *
 * Complexity:
 * Time: O(n) — each element is printed once
 * Space: O(1) — no extra data structures used
 */
