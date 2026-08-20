package com.rajeshkawali.dsa.string;
/*
 * Logic:
 * 1. Convert the input string into a char array for easy manipulation.
 * 2. Use two pointers:
 *    - 'left' starts at the beginning, 'right' starts at the end.
 * 3. If left points to '1' and right points to '0', swap them.
 * 4. Move pointers inward until they meet.
 * 5. Result: all zeros shifted to the left, all ones shifted to the right.
 *
 * Complexity:
 * Time: O(n) — single traversal with swaps
 * Space: O(1) — in-place operation
 */

public class MoveZerosAndOnes {
	
	/*
     * Problem:
     * --------
     * Given a binary array/string, move all 0s to the left
     * and all 1s to the right.
     *
     * Example:
     * Input:  [1,0,1,0,1,1,0,1]
     * Output: [0,0,0,1,1,1,1,1]
     *
     * Variants:
     * 1. Counting Method
     * 2. Two-Pointer Swap
     * 3. Partition Method (like QuickSort partition)
     * 4. String-based approach
     */

    // 1. Counting Method
    // Time: O(n), Space: O(1)
    public static int[] moveByCounting(int[] arr) {
        int countZero = 0;
        for (int num : arr) {
            if (num == 0) countZero++;
        }
        for (int i = 0; i < arr.length; i++) {
            if (i < countZero) arr[i] = 0;
            else arr[i] = 1;
        }
        return arr;
    }

    // 2. Two-Pointer Swap
    // Time: O(n), Space: O(1)
    public static int[] moveByTwoPointer(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            if (arr[left] == 1 && arr[right] == 0) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            } else {
                if (arr[left] == 0) left++;
                if (arr[right] == 1) right--;
            }
        }
        return arr;
    }

    // 3. Partition Method (QuickSort style)
    // Time: O(n), Space: O(1)
    public static int[] moveByPartition(int[] arr) {
        int index = 0; // position for next zero
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                int temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
                index++;
            }
        }
        return arr;
    }

    // 4. String-based approach
    // Time: O(n), Space: O(n)
    public static String moveByString(String str) {
        int countZero = 0;
        for (char c : str.toCharArray()) {
            if (c == '0') countZero++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < countZero; i++) sb.append('0');
        for (int i = countZero; i < str.length(); i++) sb.append('1');
        return sb.toString();
    }

    // Utility method to print array
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    public static String moveZerosLeftOnesRight(String str) {
        char[] arr = str.toCharArray();
        int left = 0, right = arr.length - 1;

        while (left < right) {
            if (arr[left] == '1' && arr[right] == '0') {
                // Swap
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            } else {
                if (arr[left] == '0') left++;
                if (arr[right] == '1') right--;
            }
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        String input = "10101101";
        int[] inputArr = {1,0,1,0,1,1,0,1};
        System.out.println("=================================================================");
        System.out.println("Original: " + input);
        System.out.println("=================================================================");
        String result = moveZerosLeftOnesRight(input);
        System.out.println("Modified: " + result);
        System.out.println("=================================================================");
        System.out.println("Using Counting Method:");
        printArray(moveByCounting(inputArr.clone()));
        System.out.println("=================================================================");
        System.out.println("Using Two-Pointer Method:");
        printArray(moveByTwoPointer(inputArr.clone()));
        System.out.println("=================================================================");
        System.out.println("Using Partition Method:");
        printArray(moveByPartition(inputArr.clone()));
        System.out.println("=================================================================");
        System.out.println("Using String-based Method: " + moveByString(input));
        System.out.println("=================================================================");
    }
}

/*

Zeros Left, Ones Right:
1. Counting → O(n), rebuild array.
2. Two-Pointer → O(n), in-place swaps.
3. Partition → O(n), elegant QuickSort style.
4. String-based → O(n), rebuild string.

*/