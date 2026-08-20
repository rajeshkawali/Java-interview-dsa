package com.rajeshkawali.dsa.generic;

public class DSAProgramsTwo {

    // 1. Find GCD (Greatest Common Divisor)
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // 2. Find LCM (Least Common Multiple)
    public static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    // 3. Check if number is perfect square
    public static boolean isPerfectSquare(int n) {
        for (int i = 1; i * i <= n; i++) {
            if (i * i == n) return true;
        }
        return false;
    }

    // 4. Check if number is perfect number (sum of divisors = number)
    public static boolean isPerfectNumber(int n) {
        int sum = 1;
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) sum += i;
        }
        return sum == n && n != 1;
    }

    // 5. Find HCF of array
    public static int hcfArray(int[] arr) {
        int result = arr[0];
        for (int i = 1; i < arr.length; i++) {
            result = gcd(result, arr[i]);
        }
        return result;
    }

    // 6. Find LCM of array
    public static int lcmArray(int[] arr) {
        int result = arr[0];
        for (int i = 1; i < arr.length; i++) {
            result = lcm(result, arr[i]);
        }
        return result;
    }

    // 7. Count digits in number
    public static int countDigits(int n) {
        int count = 0;
        while (n > 0) {
            count++;
            n /= 10;
        }
        return count;
    }

    // 8. Find largest digit in number
    public static int largestDigit(int n) {
        int max = 0;
        while (n > 0) {
            int d = n % 10;
            if (d > max) max = d;
            n /= 10;
        }
        return max;
    }

    // 9. Find smallest digit in number
    public static int smallestDigit(int n) {
        int min = 9;
        while (n > 0) {
            int d = n % 10;
            if (d < min) min = d;
            n /= 10;
        }
        return min;
    }

    // 10. Check if string has all unique characters
    public static boolean hasUniqueChars(String str) {
        boolean[] seen = new boolean[256];
        for (int i = 0; i < str.length(); i++) {
            if (seen[str.charAt(i)]) return false;
            seen[str.charAt(i)] = true;
        }
        return true;
    }

    // 11. Count words in sentence
    public static int countWords(String sentence) {
        int count = 0;
        boolean inWord = false;
        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.charAt(i) != ' ' && !inWord) {
                inWord = true;
                count++;
            } else if (sentence.charAt(i) == ' ') {
                inWord = false;
            }
        }
        return count;
    }

    // 12. Reverse words in sentence manually
    public static String reverseWords(String sentence) {
        String[] words = new String[50]; // assume max 50 words
        int wordCount = 0;
        String temp = "";
        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.charAt(i) != ' ') {
                temp += sentence.charAt(i);
            } else {
                words[wordCount++] = temp;
                temp = "";
            }
        }
        words[wordCount++] = temp; // last word
        String result = "";
        for (int i = wordCount - 1; i >= 0; i--) {
            result += words[i] + " ";
        }
        return result.trim();
    }

    // 13. Find frequency of characters in string
    public static void charFrequency(String str) {
        int[] freq = new int[256];
        for (int i = 0; i < str.length(); i++) {
            freq[str.charAt(i)]++;
        }
        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0) {
                System.out.println((char) i + " -> " + freq[i]);
            }
        }
    }

    // 14. Find longest word in sentence
    public static String longestWord(String sentence) {
        String longest = "";
        String temp = "";
        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.charAt(i) != ' ') {
                temp += sentence.charAt(i);
            } else {
                if (temp.length() > longest.length()) longest = temp;
                temp = "";
            }
        }
        if (temp.length() > longest.length()) longest = temp;
        return longest;
    }

    // 15. Reverse matrix (transpose)
    public static void transposeMatrix(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    // 16. Rotate array by k positions
    public static void rotateArray(int[] arr, int k) {
        int n = arr.length;
        k = k % n;
        reverse(arr, 0, n - 1);
        reverse(arr, 0, k - 1);
        reverse(arr, k, n - 1);
    }
    private static void reverse(int[] arr, int l, int r) {
        while (l < r) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
    }

    // 17. Find majority element (appears > n/2 times)
    public static int majorityElement(int[] arr) {
        int candidate = arr[0], count = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == candidate) count++;
            else count--;
            if (count == 0) {
                candidate = arr[i];
                count = 1;
            }
        }
        // verify
        count = 0;
        for (int num : arr) if (num == candidate) count++;
        return count > arr.length / 2 ? candidate : -1;
    }

    // 18. Find leaders in array (element greater than all to its right)
    public static void leadersInArray(int[] arr) {
        int n = arr.length;
        int maxRight = arr[n - 1];
        System.out.print("Leaders: " + maxRight + " ");
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > maxRight) {
                maxRight = arr[i];
                System.out.print(maxRight + " ");
            }
        }
        System.out.println();
    }

    // 19. Kadane’s Algorithm (max subarray sum)
    public static int maxSubarraySum(int[] arr) {
        int maxSoFar = arr[0], currMax = arr[0];
        for (int i = 1; i < arr.length; i++) {
            currMax = Math.max(arr[i], currMax + arr[i]);
            maxSoFar = Math.max(maxSoFar, currMax);
        }
        return maxSoFar;
    }

    // 20. Find equilibrium index (sum left = sum right)
    public static int equilibriumIndex(int[] arr) {
        int totalSum = 0;
        for (int num : arr) totalSum += num;
        int leftSum = 0;
        for (int i = 0; i < arr.length; i++) {
            totalSum -= arr[i];
            if (leftSum == totalSum) return i;
            leftSum += arr[i];
        }
        return -1;
    }
    
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 6, 8};

        System.out.println("GCD(12,18): " + gcd(12,18));
        System.out.println("LCM(12,18): " + lcm(12,18));
        System.out.println("Perfect Square(49)? " + isPerfectSquare(49));
        System.out.println("Perfect Number(28)? " + isPerfectNumber(28));
        System.out.println("HCF of array: " + hcfArray(arr));
        System.out.println("LCM of array: " + lcmArray(arr));
        System.out.println("Count digits(12345): " + countDigits(12345));
        System.out.println("Largest digit(583): " + largestDigit(583));
        System.out.println("Smallest digit(583): " + smallestDigit(583));
        System.out.println("Unique chars in 'hello'? " + hasUniqueChars("hello"));
        System.out.println("Word count: " + countWords("Java is powerful and fast"));
        System.out.println("Reverse words: " + reverseWords("Java is powerful"));
        System.out.println("Character frequency for 'programming':");
        charFrequency("programming");
        System.out.println("Longest word: " + longestWord("DSA practice improves logic"));
        
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        transposeMatrix(matrix);
        System.out.println("Transposed matrix:");
        for (int i=0;i<matrix.length;i++) {
            for (int j=0;j<matrix.length;j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        int[] rotateArr = {1,2,3,4,5};
        rotateArray(rotateArr, 2);
        System.out.print("Array rotated by 2: ");
        for (int num : rotateArr) System.out.print(num + " ");
        System.out.println();

        int[] majorityArr = {2,2,1,2,3,2,2};
        System.out.println("Majority element: " + majorityElement(majorityArr));

        int[] leadersArr = {16,17,4,3,5,2};
        leadersInArray(leadersArr);

        int[] kadaneArr = {-2,-3,4,-1,-2,1,5,-3};
        System.out.println("Max subarray sum: " + maxSubarraySum(kadaneArr));

        int[] eqArr = {1,3,5,2,2};
        System.out.println("Equilibrium index: " + equilibriumIndex(eqArr));
    }
}
    
    