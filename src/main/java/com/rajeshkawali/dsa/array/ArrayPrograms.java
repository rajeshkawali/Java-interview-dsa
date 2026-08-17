package com.rajeshkawali.dsa.array;

import java.util.Arrays;

public class ArrayPrograms {

    // 1. Find Largest Element
    public static int findLargest(int[] arr) {
        int largest = arr[0];
        for (int num : arr) {
            if (num > largest) largest = num;
        }
        return largest;
    }

    // 2. Find Smallest Element
    public static int findSmallest(int[] arr) {
        int smallest = arr[0];
        for (int num : arr) {
            if (num < smallest) smallest = num;
        }
        return smallest;
    }

    // 3. Reverse String
    public static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    // 4. Palindrome Check (String)
    public static boolean isPalindrome(String str) {
        return str.equals(new StringBuilder(str).reverse().toString());
    }

    // 5. Factorial (Recursion)
    public static int factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }

    // 6. Fibonacci Series
    public static void fibonacci(int n) {
        int a = 0, b = 1;
        System.out.print("Fibonacci: ");
        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            int sum = a + b;
            a = b;
            b = sum;
        }
        System.out.println();
    }

    // 7. Prime Number Check
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // 8. Armstrong Number Check
    public static boolean isArmstrong(int n) {
        int sum = 0, temp = n;
        while (temp > 0) {
            int digit = temp % 10;
            sum += digit * digit * digit;
            temp /= 10;
        }
        return sum == n;
    }

    // 9. Anagram Check
    public static boolean isAnagram(String s1, String s2) {
        char[] a = s1.toCharArray();
        char[] b = s2.toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);
        return Arrays.equals(a, b);
    }

    // 10. Remove Duplicates from Array
    public static int[] removeDuplicates(int[] arr) {
        return Arrays.stream(arr).distinct().toArray();
    }

    // 11. Sort Array Ascending
    public static int[] sortArray(int[] arr) {
        Arrays.sort(arr);
        return arr;
    }

    // 12. Binary Search
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    // 13. Count Vowels in String
    public static int countVowels(String str) {
        int count = 0;
        for (char c : str.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) != -1) count++;
        }
        return count;
    }

    // 14. Reverse Words in Sentence
    public static String reverseWords(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]).append(" ");
        }
        return sb.toString().trim();
    }

    // 15. Find Missing Number in Array (1 to n)
    public static int findMissingNumber(int[] arr, int n) {
        int expectedSum = n * (n + 1) / 2;
        int actualSum = Arrays.stream(arr).sum();
        return expectedSum - actualSum;
    }

    // 16. Find Duplicate Characters in String
    public static void findDuplicateChars(String str) {
        int[] freq = new int[256];
        for (char c : str.toCharArray()) {
            freq[c]++;
        }
        System.out.print("Duplicate chars: ");
        for (int i = 0; i < 256; i++) {
            if (freq[i] > 1) System.out.print((char) i + " ");
        }
        System.out.println();
    }

    // 17. Check if Array is Sorted
    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) return false;
        }
        return true;
    }

    // 18. Find Intersection of Two Arrays
    public static int[] intersection(int[] arr1, int[] arr2) {
        return Arrays.stream(arr1).filter(x -> Arrays.stream(arr2).anyMatch(y -> y == x)).distinct().toArray();
    }

    // 19. Find Second Smallest Element
    public static int findSecondSmallest(int[] arr) {
        Arrays.sort(arr);
        return arr[1];
    }

    // 20. Reverse Integer
    public static int reverseInteger(int n) {
        int rev = 0;
        while (n != 0) {
            rev = rev * 10 + n % 10;
            n /= 10;
        }
        return rev;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 2, 5};

        System.out.println("Largest: " + findLargest(arr));
        System.out.println("Smallest: " + findSmallest(arr));
        System.out.println("Reverse String: " + reverseString("hello"));
        System.out.println("Palindrome? " + isPalindrome("madam"));
        System.out.println("Factorial(5): " + factorial(5));
        fibonacci(7);
        System.out.println("Prime(29)? " + isPrime(29));
        System.out.println("Armstrong(153)? " + isArmstrong(153));
        System.out.println("Anagram? " + isAnagram("listen", "silent"));
        System.out.println("Remove Duplicates: " + Arrays.toString(removeDuplicates(new int[]{1,2,2,3,4,4})));
        System.out.println("Sorted Array: " + Arrays.toString(sortArray(arr)));
        System.out.println("Binary Search (4): " + binarySearch(sortArray(arr), 4));
        System.out.println("Vowels in 'Interview': " + countVowels("Interview"));
        System.out.println("Reverse Words: " + reverseWords("Java is powerful"));
        System.out.println("Missing Number: " + findMissingNumber(new int[]{1,2,4,5}, 5));
        findDuplicateChars("programming");
        System.out.println("Is Sorted? " + isSorted(arr));
        System.out.println("Intersection: " + Arrays.toString(intersection(new int[]{1,2,3}, new int[]{2,3,4})));
        System.out.println("Second Smallest: " + findSecondSmallest(arr));
        System.out.println("Reverse Integer(1234): " + reverseInteger(1234));
    }
}
