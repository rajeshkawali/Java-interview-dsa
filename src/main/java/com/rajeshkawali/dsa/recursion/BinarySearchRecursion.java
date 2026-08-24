package com.rajeshkawali.dsa.recursion;

public class BinarySearchRecursion {

    /*
     * Problem:
     * --------
     * Implement Binary Search using recursion in multiple styles.
     *
     * Variants:
     * 1. Standard Recursive Binary Search
     * 2. Tail Recursion Style
     * 3. First Occurrence (Leftmost Index)
     * 4. Last Occurrence (Rightmost Index)
     * 5. Lower Bound (first index where arr[i] >= target)
     * 6. Upper Bound (first index where arr[i] > target)
     *
     * Time Complexity: O(log n) for all variants
     * Space Complexity: O(log n) recursion stack
     */

    // 1. Standard Recursive Binary Search
    public static int binarySearch(int[] arr, int low, int high, int target) {
        if (low > high) {
        		return -1; // base case
        }
        int mid = low + (high - low) / 2;
        if (arr[mid] == target) {
        		return mid;
        }else if (arr[mid] > target) {
        		return binarySearch(arr, low, mid - 1, target);
        }else {
        		return binarySearch(arr, mid + 1, high, target);
        }
    }

    // 2. Tail Recursion Style
    public static int binarySearchTail(int[] arr, int low, int high, int target) {
        if (low > high) return -1;
        int mid = low + (high - low) / 2;
        if (arr[mid] == target) return mid;
        return (arr[mid] > target)
                ? binarySearchTail(arr, low, mid - 1, target)
                : binarySearchTail(arr, mid + 1, high, target);
    }

    // 3. First Occurrence (Leftmost Index)
    public static int firstOccurrence(int[] arr, int low, int high, int target) {
        if (low > high) return -1;
        int mid = low + (high - low) / 2;
        if (arr[mid] == target) {
            int left = firstOccurrence(arr, low, mid - 1, target);
            return (left != -1) ? left : mid;
        } else if (arr[mid] > target) {
            return firstOccurrence(arr, low, mid - 1, target);
        } else {
            return firstOccurrence(arr, mid + 1, high, target);
        }
    }

    // 4. Last Occurrence (Rightmost Index)
    public static int lastOccurrence(int[] arr, int low, int high, int target) {
        if (low > high) return -1;
        int mid = low + (high - low) / 2;
        if (arr[mid] == target) {
            int right = lastOccurrence(arr, mid + 1, high, target);
            return (right != -1) ? right : mid;
        } else if (arr[mid] > target) {
            return lastOccurrence(arr, low, mid - 1, target);
        } else {
            return lastOccurrence(arr, mid + 1, high, target);
        }
    }

    // 5. Lower Bound (first index where arr[i] >= target)
    public static int lowerBound(int[] arr, int low, int high, int target) {
        if (low > high) return low; // insertion point
        int mid = low + (high - low) / 2;
        if (arr[mid] >= target) {
            return lowerBound(arr, low, mid - 1, target);
        } else {
            return lowerBound(arr, mid + 1, high, target);
        }
    }

    // 6. Upper Bound (first index where arr[i] > target)
    public static int upperBound(int[] arr, int low, int high, int target) {
        if (low > high) return low; // insertion point
        int mid = low + (high - low) / 2;
        if (arr[mid] > target) {
            return upperBound(arr, low, mid - 1, target);
        } else {
            return upperBound(arr, mid + 1, high, target);
        }
    }
    
	public static int binarySearchTwo(int[] arr, int low, int high, int target) {
		if (low > high) {
			return -1;
		}
		int mid = low + (high - low) / 2;
		if (arr[mid] == target) {
			return mid;
		} else if (arr[mid] > target) {
			high = mid - 1;
		} else if (arr[mid] < target) {
			low = mid + 1;
		}
		return binarySearchTwo(arr, low, high, target);
	}

    public static void main(String[] args) {
        int[] arr = {1, 3, 3, 5, 7, 7, 7, 9, 11};
        System.out.println("============================================");
        System.out.println("Standard Binary Search (target=7): " +
                binarySearch(arr, 0, arr.length - 1, 7));
        System.out.println("============================================");
        System.out.println("Tail Recursion (target=7): " +
                binarySearchTail(arr, 0, arr.length - 1, 7));
        System.out.println("============================================");
        System.out.println("First Occurrence of 7: " +
                firstOccurrence(arr, 0, arr.length - 1, 7));
        System.out.println("============================================");
        System.out.println("Last Occurrence of 7: " +
                lastOccurrence(arr, 0, arr.length - 1, 7));
        System.out.println("============================================");
        System.out.println("Lower Bound of 7: " +
                lowerBound(arr, 0, arr.length - 1, 7));
        System.out.println("============================================");
        System.out.println("Upper Bound of 7: " +
                upperBound(arr, 0, arr.length - 1, 7));
        System.out.println("============================================");
        System.out.println("Binary Search two 7: " +
        		binarySearchTwo(arr, 0, arr.length - 1, 7));
        System.out.println("============================================");
    }
}

/*
Short Note:
Standard recursion → clean, classic binary search that checks mid and recurses left or right.
Tail recursion → same logic, but written in a tail style form where the recursive call is the last operation.
First occurrence → modified binary search to find the leftmost index of the target in a sorted array with duplicates.
Last occurrence → modified binary search to find the rightmost index of the target in a sorted array with duplicates.
Lower bound → returns the first index where arr[i] ≥ target, useful for insertion positions.
Upper bound → returns the first index where arr[i] > target, useful for range queries and counting occurrences.

*/