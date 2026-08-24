package com.rajeshkawali.dsa.recursion;

public class FactorialOfNumber {

	public static void main(String[] args) {
		System.out.println(factorial(4));
	}

	public static int factorial(int n) {
		if (n == 0) { // Base case
			return 1;
		}
		return n * factorial(n - 1); // Processing(n* factorial()) + Recursive call(factorial(n -1))
	}
}
