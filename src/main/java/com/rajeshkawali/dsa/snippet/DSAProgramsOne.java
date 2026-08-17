package com.rajeshkawali.dsa.snippet;

public class DSAProgramsOne {

    // 1. Reverse an integer array manually
    public static void reverseArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    // 2. Reverse a string manually
    public static String reverseString(String str) {
        char[] chars = str.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }

    // 3. Check palindrome string
    public static boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    // 4. Find factorial (iterative)
    public static int factorial(int n) {
        int fact = 1;
        for (int i = 1; i <= n; i++) fact *= i;
        return fact;
    }

    // 5. Fibonacci series
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

    // 6. Prime number check
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // 7. Armstrong number check
    public static boolean isArmstrong(int n) {
        int sum = 0, temp = n;
        while (temp > 0) {
            int digit = temp % 10;
            sum += digit * digit * digit;
            temp /= 10;
        }
        return sum == n;
    }

    // 8. Linear search
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    // 9. Binary search (array must be sorted)
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

    // 10. Bubble sort
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // 11. Selection sort
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) minIndex = j;
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    // 12. Insertion sort
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // 13. Find maximum element
    public static int findMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) if (num > max) max = num;
        return max;
    }

    // 14. Find minimum element
    public static int findMin(int[] arr) {
        int min = arr[0];
        for (int num : arr) if (num < min) min = num;
        return min;
    }

    // 15. Count even numbers
    public static int countEven(int[] arr) {
        int count = 0;
        for (int num : arr) if (num % 2 == 0) count++;
        return count;
    }

    // 16. Count odd numbers
    public static int countOdd(int[] arr) {
        int count = 0;
        for (int num : arr) if (num % 2 != 0) count++;
        return count;
    }

    // 17. Reverse integer
    public static int reverseInteger(int n) {
        int rev = 0;
        while (n != 0) {
            rev = rev * 10 + n % 10;
            n /= 10;
        }
        return rev;
    }

    // 18. Sum of digits
    public static int sumOfDigits(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    // 19. Find missing number in array (1 to n)
    public static int findMissingNumber(int[] arr, int n) {
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        for (int num : arr) actualSum += num;
        return expectedSum - actualSum;
    }

    // 20. Find duplicate element in array
    public static int findDuplicate(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) return arr[i];
            }
        }
        return -1; // no duplicate
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 2, 5};

        reverseArray(arr);
        System.out.println("Reversed Array:");
        for (int num : arr) System.out.print(num + " ");
        System.out.println();

        System.out.println("Reverse String: " + reverseString("hello"));
        System.out.println("Palindrome? " + isPalindrome("madam"));
        System.out.println("Factorial(5): " + factorial(5));
        fibonacci(7);
        System.out.println("Prime(29)? " + isPrime(29));
        System.out.println("Armstrong(153)? " + isArmstrong(153));
        System.out.println("Linear Search (4): " + linearSearch(arr, 4));
        bubbleSort(arr);
        System.out.println("Bubble Sorted Array:");
        for (int num : arr) System.out.print(num + " ");
        System.out.println();
    }
}
