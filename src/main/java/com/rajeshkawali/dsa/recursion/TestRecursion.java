package com.rajeshkawali.dsa.recursion;

public class TestRecursion {

	public static void main(String[] args) {

		int arr[] = { 1, 3, 5, 6, 7, 8, 9 };
		printArrayByRecursion(arr, 0);
		System.out.println("============================================");
		System.out.println(sumOfDigitsByRecursion(1234));
		System.out.println("============================================");
		countAscByRecursion(1);
		System.out.println();
		System.out.println("============================================");
		countDescByRecursion(5);
		System.out.println();
		System.out.println("============================================");
		System.out.println(reverseStringByRecursion("rajesh"));
		System.out.println("============================================");
		System.out.println(searchByRecursion(arr.clone(), 0, 8));
		System.out.println("============================================");
		System.out.println(countElements(arr, 0));
		System.out.println("============================================");
		
		System.out.println("============================================");
		
		System.out.println("============================================");
		
		System.out.println("============================================");
	}

	public static int printArrayByRecursion(int arr[], int index) {
		if (arr.length == index) {
			return 1;
		}
		System.out.println(arr[index]);
		return printArrayByRecursion(arr, index + 1);
	}

	public static int sumOfDigitsByRecursion(int num) {
		int sum = 0;
		if (num == 0) {
			return 0;
		}
		int digit = num % 10;
		sum = sum + digit;
		return sum + sumOfDigitsByRecursion(num / 10);
	}

	public static int countAscByRecursion(int n) {
		if (n > 5) {
			return 0;
		}
		System.out.print(n + " ");
		return countAscByRecursion(++n);
	}

	public static int countDescByRecursion(int n) {
		if (n == 0) {
			return 0;
		}
		System.out.print(n + " ");
		return countDescByRecursion(--n);
	}

	public static String reverseStringByRecursion(String str) {
		if (str.isEmpty()) {
			return "";
		}
		return reverseStringByRecursion(str.substring(1)) + str.charAt(0);
	}

	public static int searchByRecursion(int arr[], int index, int target) {
		if (index > arr.length) {
			return 0;
		}
		if (arr[index] == target) {
			return index;
		}
		return searchByRecursion(arr, index + 1, target);
	}
	
	public static int countElements(int[] arr, int index) {
        if (index >= arr.length) {
            return 0;
        }
        return 1 + countElements(arr, index + 1);
    }

}
