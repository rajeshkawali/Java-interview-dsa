package com.rajeshkawali.dsa.array;

/**
 * Two strings are anagrams if they contain the same characters with the same
 * frequency, but possibly in a different order. Example: "listen" and "silent"
 * are anagrams.
 * 
 * Anagram logic = same length + same characters + same frequency. The most
 * reliable way is to count characters or sort and compare.
 */
public class AnagramPrograms {

	// 1. Frequency Count Method
	public static boolean isAnagramByCount(String s1, String s2) {
		if (s1.length() != s2.length())
			return false;

		int[] freq = new int[256]; // ASCII size
		for (int i = 0; i < s1.length(); i++) {
			freq[s1.charAt(i)]++;
			freq[s2.charAt(i)]--;
		}

		for (int i = 0; i < 256; i++) {
			if (freq[i] != 0)
				return false;
		}
		return true;
	}

	// 2. Manual Sorting (Bubble Sort)
	public static boolean isAnagramBySorting(String s1, String s2) {
		if (s1.length() != s2.length())
			return false;

		char[] a = s1.toCharArray();
		char[] b = s2.toCharArray();

		// Bubble sort both arrays
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = 0; j < a.length - 1 - i; j++) {
				if (a[j] > a[j + 1]) {
					char temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
				if (b[j] > b[j + 1]) {
					char temp = b[j];
					b[j] = b[j + 1];
					b[j + 1] = temp;
				}
			}
		}

		// Compare sorted arrays
		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i])
				return false;
		}
		return true;
	}

	// 3. ASCII Sum Method (not fully reliable)
	public static boolean isAnagramByAsciiSum(String s1, String s2) {
		if (s1.length() != s2.length())
			return false;

		int sum1 = 0, sum2 = 0;
		for (int i = 0; i < s1.length(); i++) {
			sum1 += s1.charAt(i);
			sum2 += s2.charAt(i);
		}
		return sum1 == sum2;
	}

	// 4. Prime Multiplication Trick
	public static boolean isAnagramByPrime(String s1, String s2) {
		if (s1.length() != s2.length())
			return false;

		int[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,
				101 }; // mapping a-z

		long product1 = 1, product2 = 1;
		for (int i = 0; i < s1.length(); i++) {
			product1 *= primes[s1.charAt(i) - 'a'];
			product2 *= primes[s2.charAt(i) - 'a'];
		}
		return product1 == product2;
	}

	// 5. Manual Removal Method
	public static boolean isAnagramByRemoval(String s1, String s2) {
		if (s1.length() != s2.length())
			return false;

		char[] b = s2.toCharArray();
		for (int i = 0; i < s1.length(); i++) {
			boolean found = false;
			for (int j = 0; j < b.length; j++) {
				if (s1.charAt(i) == b[j]) {
					b[j] = '*'; // mark as used
					found = true;
					break;
				}
			}
			if (!found)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println("By Count: " + isAnagramByCount("listen", "silent")); // true
		System.out.println("By Sorting: " + isAnagramBySorting("triangle", "integral")); // true
		System.out.println("By ASCII Sum: " + isAnagramByAsciiSum("ab", "ba")); // true (but risky)
		System.out.println("By Prime: " + isAnagramByPrime("rat", "tar")); // true
		System.out.println("By Removal: " + isAnagramByRemoval("evil", "vile")); // true
		System.out.println("=================================================");
		System.out.println("listen & silent: " + isAnagramBySorting("listen", "silent")); // true
		System.out.println("triangle & integral: " + isAnagramBySorting("triangle", "integral")); // true
		System.out.println("apple & paple: " + isAnagramBySorting("apple", "paple")); // false
	}
}
