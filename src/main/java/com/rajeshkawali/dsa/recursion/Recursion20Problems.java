package com.rajeshkawali.dsa.recursion;

public class Recursion20Problems {

    /*
     * Recursion:
     * ----------
     * A function calling itself until a base condition is met.
     * Each method below demonstrates a different recursion problem.
     */

    // 1. Factorial
    // Time: O(n), Space: O(n)
    public static int factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    // 2. Fibonacci
    // Time: O(2^n), Space: O(n)
    public static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // 3. Sum of Array
    // Time: O(n), Space: O(n)
    public static int sumArray(int[] arr, int i) {
        if (i == arr.length) return 0;
        return arr[i] + sumArray(arr, i + 1);
    }

    // 4. Reverse String
    // Time: O(n), Space: O(n)
    public static String reverseString(String str) {
        if (str.isEmpty()) return "";
        return reverseString(str.substring(1)) + str.charAt(0);
    }

    // 5. Palindrome Check
    // Time: O(n), Space: O(n)
    public static boolean isPalindrome(String str, int l, int r) {
        if (l >= r) return true;
        return str.charAt(l) == str.charAt(r) && isPalindrome(str, l + 1, r - 1);
    }

    // 6. Print Numbers 1 to N
    // Time: O(n), Space: O(n)
    public static void print1ToN(int n) {
        if (n == 0) return;
        print1ToN(n - 1);
        System.out.print(n + " ");
    }

    // 7. Print Numbers N to 1
    // Time: O(n), Space: O(n)
    public static void printNTo1(int n) {
        if (n == 0) return;
        System.out.print(n + " ");
        printNTo1(n - 1);
    }

    // 8. Binary Search
    // Time: O(log n), Space: O(log n)
    public static int binarySearch(int[] arr, int l, int r, int target) {
        if (l > r) return -1;
        int mid = l + (r - l) / 2;
        if (arr[mid] == target) return mid;
        if (arr[mid] > target) return binarySearch(arr, l, mid - 1, target);
        return binarySearch(arr, mid + 1, r, target);
    }

    // 9. GCD (Euclidean Algorithm)
    // Time: O(log(min(a,b))), Space: O(log(min(a,b)))
    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // 10. Power (x^n)
    // Time: O(n), Space: O(n)
    public static int power(int x, int n) {
        if (n == 0) return 1;
        return x * power(x, n - 1);
    }

    // 11. Power Optimized (Divide & Conquer)
    // Time: O(log n), Space: O(log n)
    public static int powerOptimized(int x, int n) {
        if (n == 0) return 1;
        int half = powerOptimized(x, n / 2);
        if (n % 2 == 0) return half * half;
        return x * half * half;
    }

    // 12. Sum of Digits
    // Time: O(log n), Space: O(log n)
    public static int sumDigits(int n) {
        if (n == 0) return 0;
        return (n % 10) + sumDigits(n / 10);
    }

    // 13. Count Digits
    // Time: O(log n), Space: O(log n)
    public static int countDigits(int n) {
        if (n == 0) return 0;
        return 1 + countDigits(n / 10);
    }

    // 14. Print Array in Reverse
    // Time: O(n), Space: O(n)
    public static void printArrayReverse(int[] arr, int i) {
        if (i < 0) return;
        System.out.print(arr[i] + " ");
        printArrayReverse(arr, i - 1);
    }

    // 15. Print Array Forward
    // Time: O(n), Space: O(n)
    public static void printArrayForward(int[] arr, int i) {
        if (i == arr.length) return;
        System.out.print(arr[i] + " ");
        printArrayForward(arr, i + 1);
    }

    // 16. Find Max in Array
    // Time: O(n), Space: O(n)
    public static int findMax(int[] arr, int i) {
        if (i == arr.length - 1) return arr[i];
        return Math.max(arr[i], findMax(arr, i + 1));
    }

    // 17. Find Min in Array
    // Time: O(n), Space: O(n)
    public static int findMin(int[] arr, int i) {
        if (i == arr.length - 1) return arr[i];
        return Math.min(arr[i], findMin(arr, i + 1));
    }

    // 18. Tower of Hanoi
    // Time: O(2^n), Space: O(n)
    public static void towerOfHanoi(int n, char from, char to, char aux) {
        if (n == 0) return;
        towerOfHanoi(n - 1, from, aux, to);
        System.out.println("Move disk " + n + " from " + from + " to " + to);
        towerOfHanoi(n - 1, aux, to, from);
    }

    // 19. Generate Subsets (Power Set)
    // Time: O(2^n), Space: O(n)
    public static void generateSubsets(String str, String curr, int i) {
        if (i == str.length()) {
            System.out.println(curr);
            return;
        }
        generateSubsets(str, curr + str.charAt(i), i + 1); // include
        generateSubsets(str, curr, i + 1); // exclude
    }

    // 20. Permutations of String
    // Time: O(n!), Space: O(n)
    public static void permutations(String str, String curr) {
        if (str.isEmpty()) {
            System.out.println(curr);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            permutations(str.substring(0, i) + str.substring(i + 1), curr + str.charAt(i));
        }
    }

    public static void main(String[] args) {
    		System.out.println("============================================");
        System.out.println("Factorial(5): " + factorial(5));
        System.out.println("============================================");
        System.out.println("Fibonacci(6): " + fibonacci(6));
        System.out.println("============================================");
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Sum of Array: " + sumArray(arr, 0));
        System.out.println("============================================");
        System.out.println("Reverse of 'rajesh': " + reverseString("rajesh"));
        System.out.println("============================================");
        System.out.println("Palindrome 'madam': " + isPalindrome("madam", 0, 4));
        System.out.println("============================================");
        print1ToN(5); System.out.println();
        System.out.println("============================================");
        printNTo1(5); System.out.println();
        System.out.println("============================================");
        int[] sortedArr = {1, 3, 5, 7, 9};
        System.out.println("Binary Search for 7: " + binarySearch(sortedArr, 0, sortedArr.length - 1, 7));
        System.out.println("============================================");
        System.out.println("GCD(48,18): " + gcd(48, 18));
        System.out.println("============================================");
        System.out.println("Power(2,5): " + power(2, 5));
        System.out.println("============================================");
        System.out.println("PowerOptimized(2,10): " + powerOptimized(2, 10));
        System.out.println("============================================");
        System.out.println("SumDigits(1234): " + sumDigits(1234));
        System.out.println("============================================");
        System.out.println("CountDigits(1234): " + countDigits(1234));
        System.out.println("============================================");
        printArrayReverse(arr, arr.length - 1); System.out.println();
        System.out.println("============================================");
        printArrayForward(arr, 0); System.out.println();
        System.out.println("============================================");
        System.out.println("Max in Array: " + findMax(arr, 0));
        System.out.println("============================================");
        System.out.println("Min in Array: " + findMin(arr, 0));
        System.out.println("============================================");
        towerOfHanoi(3, 'A', 'C', 'B');
        System.out.println("============================================");
        System.out.println("Subsets of 'abc':");
        generateSubsets("abc", "", 0);
        System.out.println("============================================");
        System.out.println("Permutations of 'abc':");
        permutations("abc", "");
        System.out.println("============================================");
    }
}