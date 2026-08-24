package com.rajeshkawali.dsa.recursion;

public class RecursionExamples {

    /*
     * Recursion:
     * ----------
     * A function calling itself until a base condition is met.
     * Used in problems like factorial, Fibonacci, array/string processing, etc.
     *
     * Key Points:
     * - Always define a base case (to stop recursion).
     * - Each recursive call reduces the problem size.
     * - Time complexity depends on number of calls.
     * - Space complexity depends on recursion stack depth.
     */

    // 1. Factorial (Classic Recursion)
    // Time: O(n), Space: O(n) (stack depth)
    public static int factorial(int n) {
        // Base case
        if (n == 0 || n == 1) return 1;
        // Recursive case
        return n * factorial(n - 1);
    }

    // 2. Fibonacci (Naive Recursion)
    // Time: O(2^n), Space: O(n)
    public static int fibonacci(int n) {
        // Base case
        if (n == 0) return 0;
        if (n == 1) return 1;
        // Recursive case
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // 3. Sum of Array (Recursion)
    // Time: O(n), Space: O(n)
    public static int sumArray(int[] arr, int index) {
        // Base case
        if (index == arr.length) return 0;
        // Recursive case
        return arr[index] + sumArray(arr, index + 1);
    }

    // 4. Reverse String (Recursion)
    // Time: O(n), Space: O(n)
    public static String reverseString(String str) {
        // Base case
        if (str.isEmpty()) return "";
        // Recursive case
        return reverseString(str.substring(1)) + str.charAt(0);
    }

    // 5. Print Numbers (Tail Recursion)
    // Time: O(n), Space: O(n)
    public static void printNumbers(int n) {
        // Base case
        if (n == 0) return;
        // Recursive case
        System.out.print(n + " ");
        printNumbers(n - 1);
    }

    // 6. Binary Search (Recursion)
    // Time: O(log n), Space: O(log n)
    public static int binarySearch(int[] arr, int low, int high, int target) {
        if (low > high) return -1; // Base case: not found
        int mid = low + (high - low) / 2;
        if (arr[mid] == target) return mid;
        else if (arr[mid] > target) return binarySearch(arr, low, mid - 1, target);
        else return binarySearch(arr, mid + 1, high, target);
    }

    public static void main(String[] args) {
    	System.out.println("============================================");
        System.out.println("Factorial(5): " + factorial(5)); // 120
        System.out.println("============================================");
        System.out.println("Fibonacci(6): " + fibonacci(6)); // 8
        System.out.println("============================================");
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Sum of Array: " + sumArray(arr, 0)); // 15
        System.out.println("============================================");
        System.out.println("Reverse of 'rajesh': " + reverseString("rajesh")); // hsejar
        System.out.println("============================================");
        System.out.print("Print Numbers (5 to 1): ");
        printNumbers(5); // 5 4 3 2 1
        System.out.println();
        System.out.println("============================================");
        int[] sortedArr = {1, 3, 5, 7, 9, 11};
        System.out.println("Binary Search for 7: " + binarySearch(sortedArr, 0, sortedArr.length - 1, 7)); // index 3
        System.out.println("============================================");
    }
}
