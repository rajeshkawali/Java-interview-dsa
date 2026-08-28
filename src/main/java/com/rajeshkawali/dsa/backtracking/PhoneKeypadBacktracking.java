package com.rajeshkawali.dsa.backtracking;

public class PhoneKeypadBacktracking {

	// Mapping digits to letters
	static String[] mapping = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

	// Time Complexity: O(3^n * 4^m) (n digits with 3 letters, m digits with 4 letters)
	// Space Complexity: O(n) recursion depth
	public static void backtrack(String digits, int index, String current) {
		if (index == digits.length()) {
			System.out.println(current); // base case
			return;
		}

		String letters = mapping[digits.charAt(index) - '0'];
		for (char c : letters.toCharArray()) {
			backtrack(digits, index + 1, current + c); // explore choice
		}
	}

	public static void main(String[] args) {
		String digits = "23";
		System.out.println("Combinations for " + digits + ":");
		backtrack(digits, 0, "");
	}
}

/*
Each digit maps to a set of letters.
At each step, choose one letter from the current digit.
Recursively move to the next digit.
If all digits are processed, add the combination to results.
If stuck, backtrack and try another letter.

Short Note:
Backtracking explores all possible letter choices for each digit.
If one path is complete, it prints the combination.
This is widely used in problems like word generation, T9 predictive text, and combinatorial search.
*/