package com.rajeshkawali.dsa.generic;


public class StringPrograms {

    public static void main(String[] args) {

        // =========================================================
        // 1. REVERSE A STRING
        // =========================================================

        String str1 = "Hello";

        System.out.println("1. Reverse String:");

        // Method 1: Using for loop
        reverseString1(str1);

        // Method 2: Using StringBuilder
        reverseString2(str1);


        // =========================================================
        // 2. PALINDROME STRING
        // =========================================================

        String str2 = "madam";

        System.out.println("\n2. Palindrome:");

        // Method 1: Reverse and compare
        palindrome1(str2);

        // Method 2: Compare characters from both ends
        palindrome2(str2);


        // =========================================================
        // 3. ANAGRAM
        // =========================================================

        String str3 = "listen";
        String str4 = "silent";

        System.out.println("\n3. Anagram:");

        // Method 1: Using sorting
        anagram1(str3, str4);

        // Method 2: Using frequency array
        anagram2(str3, str4);


        // =========================================================
        // 4. COUNT FREQUENCY OF EACH CHARACTER
        // =========================================================

        String str5 = "programming";

        System.out.println("\n4. Character Frequency:");

        // Method 1: Using frequency array
        frequency1(str5);

        // Method 2: Using nested loops
        frequency2(str5);


        // =========================================================
        // 5. FIRST NON-REPEATED CHARACTER
        // =========================================================

        String str6 = "swiss";

        System.out.println("\n5. First Non-Repeated Character:");

        // Method 1: Frequency array
        firstNonRepeated1(str6);

        // Method 2: Using indexOf() and lastIndexOf()
        firstNonRepeated2(str6);


        // =========================================================
        // 6. FIND DUPLICATE CHARACTERS
        // =========================================================

        String str7 = "programming";

        System.out.println("\n6. Duplicate Characters:");

        // Method 1: Frequency array
        duplicateCharacters1(str7);

        // Method 2: Nested loops
        duplicateCharacters2(str7);


        // =========================================================
        // 7. REMOVE DUPLICATE CHARACTERS
        // =========================================================

        String str8 = "programming";

        System.out.println("\n7. Remove Duplicate Characters:");

        // Method 1: Using boolean array
        removeDuplicates1(str8);

        // Method 2: Using indexOf()
        removeDuplicates2(str8);


        // =========================================================
        // 8. COUNT WORDS
        // =========================================================

        String str9 = "Java is very easy";

        System.out.println("\n8. Count Words:");

        // Method 1: Using split()
        countWords1(str9);

        // Method 2: Count spaces
        countWords2(str9);


        // =========================================================
        // 9. REVERSE EACH WORD
        // =========================================================

        String str10 = "Java is easy";

        System.out.println("\n9. Reverse Each Word:");

        // Method 1: Using split()
        reverseEachWord1(str10);

        // Method 2: Using StringBuilder
        reverseEachWord2(str10);


        // =========================================================
        // 10. REMOVE SPACES
        // =========================================================

        String str11 = "Java Programming Language";

        System.out.println("\n10. Remove Spaces:");

        // Method 1: Using replace()
        removeSpaces1(str11);

        // Method 2: Using loop
        removeSpaces2(str11);


        // =========================================================
        // 11. COUNT UPPERCASE AND LOWERCASE
        // =========================================================

        String str12 = "Java Programming";

        System.out.println("\n11. Uppercase and Lowercase:");

        // Method 1: Character methods
        countCase1(str12);

        // Method 2: ASCII values
        countCase2(str12);


        // =========================================================
        // 12. CHECK WHETHER STRING CONTAINS ONLY DIGITS
        // =========================================================

        String str13 = "123456";

        System.out.println("\n12. Only Digits:");

        // Method 1: Character.isDigit()
        onlyDigits1(str13);

        // Method 2: ASCII values
        onlyDigits2(str13);


        // =========================================================
        // 13. FIND LONGEST WORD
        // =========================================================

        String str14 = "Java programming is interesting";

        System.out.println("\n13. Longest Word:");

        // Method 1: Using split()
        longestWord1(str14);

        // Method 2: Without split()
        longestWord2(str14);


        // =========================================================
        // 14. STRING COMPRESSION
        // =========================================================

        String str15 = "aaabbcccc";

        System.out.println("\n14. String Compression:");

        // Method 1: Count consecutive characters
        compression1(str15);

        // Method 2: Using StringBuilder
        compression2(str15);


        // =========================================================
        // 15. CHECK STRING ROTATION
        // =========================================================

        String str16 = "ABCD";
        String str17 = "CDAB";

        System.out.println("\n15. String Rotation:");

        // Method 1: Concatenation + contains()
        rotation1(str16, str17);

        // Method 2: Manually check rotations
        rotation2(str16, str17);


        // =========================================================
        // 16. PRINT ALL PERMUTATIONS
        // =========================================================

        String str18 = "ABC";

        System.out.println("\n16. All Permutations:");

        // Recursion / Backtracking
        permutations(str18, "");
    }


    // =============================================================
    // 1. REVERSE A STRING
    // =============================================================

    /*
        LOGIC:
        Start from the last character and move towards
        the first character.

        Example:
        Hello

        o -> l -> l -> e -> H

        Result = olleH
    */

    // Method 1: Using for loop
    static void reverseString1(String str) {

        String reverse = "";

        for (int i = str.length() - 1; i >= 0; i--) {

            reverse = reverse + str.charAt(i);
        }

        System.out.println("Method 1: " + reverse);
    }


    /*
        StringBuilder has a built-in reverse() method.
    */

    // Method 2: Using StringBuilder
    static void reverseString2(String str) {

        StringBuilder sb = new StringBuilder(str);

        System.out.println("Method 2: " + sb.reverse());
    }


    // =============================================================
    // 2. PALINDROME STRING
    // =============================================================

    /*
        LOGIC:
        A palindrome is a String that reads the same
        from both directions.

        Example:
        madam

        Forward = madam
        Reverse = madam

        Therefore it is a palindrome.
    */

    // Method 1: Reverse and compare
    static void palindrome1(String str) {

        String reverse = "";

        for (int i = str.length() - 1; i >= 0; i--) {

            reverse = reverse + str.charAt(i);
        }

        if (str.equals(reverse)) {

            System.out.println("Method 1: Palindrome");

        } else {

            System.out.println("Method 1: Not Palindrome");
        }
    }


    /*
        Compare first character with last character,
        second with second-last, and so on.
    */

    // Method 2: Two-pointer approach
    static void palindrome2(String str) {

        int start = 0;
        int end = str.length() - 1;

        boolean palindrome = true;

        while (start < end) {

            if (str.charAt(start) != str.charAt(end)) {

                palindrome = false;
                break;
            }

            start++;
            end--;
        }

        if (palindrome) {

            System.out.println("Method 2: Palindrome");

        } else {

            System.out.println("Method 2: Not Palindrome");
        }
    }


    // =============================================================
    // 3. ANAGRAM
    // =============================================================

    /*
        LOGIC:
        Two Strings are anagrams if they contain the same
        characters with the same frequency.

        Example:

        listen
        silent

        Both contain:
        l, i, s, t, e, n
    */

    // Method 1: Sort both Strings
    static void anagram1(String str1, String str2) {

        if (str1.length() != str2.length()) {

            System.out.println("Method 1: Not Anagram");
            return;
        }

        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();

        java.util.Arrays.sort(arr1);
        java.util.Arrays.sort(arr2);

        if (java.util.Arrays.equals(arr1, arr2)) {

            System.out.println("Method 1: Anagram");

        } else {

            System.out.println("Method 1: Not Anagram");
        }
    }


    /*
        Count every character in the first String.
        Decrease the count for every character in the second String.

        If all counts become zero, they are anagrams.
    */

    // Method 2: Frequency array
    static void anagram2(String str1, String str2) {

        if (str1.length() != str2.length()) {

            System.out.println("Method 2: Not Anagram");
            return;
        }

        int[] count = new int[256];

        for (int i = 0; i < str1.length(); i++) {

            count[str1.charAt(i)]++;
            count[str2.charAt(i)]--;
        }

        for (int i = 0; i < count.length; i++) {

            if (count[i] != 0) {

                System.out.println("Method 2: Not Anagram");
                return;
            }
        }

        System.out.println("Method 2: Anagram");
    }


    // =============================================================
    // 4. COUNT FREQUENCY OF EACH CHARACTER
    // =============================================================

    /*
        LOGIC:
        Count how many times each character appears.

        Example:

        hello

        h = 1
        e = 1
        l = 2
        o = 1
    */

    // Method 1: Frequency array
    static void frequency1(String str) {

        int[] count = new int[256];

        for (int i = 0; i < str.length(); i++) {

            count[str.charAt(i)]++;
        }

        System.out.println("Method 1:");

        for (int i = 0; i < count.length; i++) {

            if (count[i] > 0) {

                System.out.println(
                        (char) i + " = " + count[i]
                );
            }
        }
    }


    /*
        For every character, count how many times it occurs.
        Use a visited array so we don't print the same character twice.
    */

    // Method 2: Nested loops
    static void frequency2(String str) {

        boolean[] visited = new boolean[str.length()];

        System.out.println("Method 2:");

        for (int i = 0; i < str.length(); i++) {

            if (visited[i]) {
                continue;
            }

            int count = 1;

            for (int j = i + 1; j < str.length(); j++) {

                if (str.charAt(i) == str.charAt(j)) {

                    count++;
                    visited[j] = true;
                }
            }

            System.out.println(
                    str.charAt(i) + " = " + count
            );
        }
    }


    // =============================================================
    // 5. FIND FIRST NON-REPEATED CHARACTER
    // =============================================================

    /*
        LOGIC:
        A non-repeated character occurs only once.

        Example:

        swiss

        s = 3
        w = 1
        i = 1

        Answer = w
    */

    // Method 1: Frequency array
    static void firstNonRepeated1(String str) {

        int[] count = new int[256];

        for (int i = 0; i < str.length(); i++) {

            count[str.charAt(i)]++;
        }

        for (int i = 0; i < str.length(); i++) {

            if (count[str.charAt(i)] == 1) {

                System.out.println(
                        "Method 1: " + str.charAt(i)
                );

                return;
            }
        }

        System.out.println("Method 1: No character found");
    }


    /*
        indexOf() gives the first position.
        lastIndexOf() gives the last position.

        If both positions are the same,
        the character occurs only once.
    */

    // Method 2: indexOf() and lastIndexOf()
    static void firstNonRepeated2(String str) {

        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);

            if (str.indexOf(ch) == str.lastIndexOf(ch)) {

                System.out.println(
                        "Method 2: " + ch
                );

                return;
            }
        }

        System.out.println("Method 2: No character found");
    }


    // =============================================================
    // 6. FIND DUPLICATE CHARACTERS
    // =============================================================

    /*
        LOGIC:
        A character is duplicate if it appears more than once.
    */

    // Method 1: Frequency array
    static void duplicateCharacters1(String str) {

        int[] count = new int[256];

        for (int i = 0; i < str.length(); i++) {

            count[str.charAt(i)]++;
        }

        System.out.println("Method 1:");

        for (int i = 0; i < count.length; i++) {

            if (count[i] > 1) {

                System.out.println(
                        (char) i + " = " + count[i]
                );
            }
        }
    }


    // Method 2: Nested loops
    static void duplicateCharacters2(String str) {

        System.out.println("Method 2:");

        for (int i = 0; i < str.length(); i++) {

            boolean alreadyPrinted = false;

            // Check whether this character appeared before
            for (int k = 0; k < i; k++) {

                if (str.charAt(i) == str.charAt(k)) {

                    alreadyPrinted = true;
                    break;
                }
            }

            if (alreadyPrinted) {
                continue;
            }

            for (int j = i + 1; j < str.length(); j++) {

                if (str.charAt(i) == str.charAt(j)) {

                    System.out.println(str.charAt(i));
                    break;
                }
            }
        }
    }


    // =============================================================
    // 7. REMOVE DUPLICATE CHARACTERS
    // =============================================================

    /*
        LOGIC:
        Keep only the first occurrence of every character.

        programming

        Result:

        progamin
    */

    // Method 1: boolean array
    static void removeDuplicates1(String str) {

        boolean[] visited = new boolean[256];

        String result = "";

        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);

            if (!visited[ch]) {

                result = result + ch;
                visited[ch] = true;
            }
        }

        System.out.println("Method 1: " + result);
    }


    /*
        indexOf(ch) tells us where the first occurrence is.

        If the current index is equal to the first occurrence,
        this is the first time we have seen the character.
    */

    // Method 2: indexOf()
    static void removeDuplicates2(String str) {

        String result = "";

        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);

            if (str.indexOf(ch) == i) {

                result = result + ch;
            }
        }

        System.out.println("Method 2: " + result);
    }


    // =============================================================
    // 8. COUNT WORDS
    // =============================================================

    /*
        LOGIC:
        Split the String based on spaces.

        "Java is easy"

        Java
        is
        easy

        Total = 3
    */

    // Method 1: split()
    static void countWords1(String str) {

        str = str.trim();

        if (str.isEmpty()) {

            System.out.println("Method 1: 0");
            return;
        }

        String[] words = str.split("\\s+");

        System.out.println(
                "Method 1: " + words.length
        );
    }


    /*
        Every group of characters after a space is a new word.

        We can count the number of transitions from
        space -> non-space.
    */

    // Method 2: Without split()
    static void countWords2(String str) {

        int count = 0;

        boolean insideWord = false;

        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);

            if (!Character.isWhitespace(ch)) {

                if (!insideWord) {

                    count++;
                    insideWord = true;
                }

            } else {

                insideWord = false;
            }
        }

        System.out.println(
                "Method 2: " + count
        );
    }


    // =============================================================
    // 9. REVERSE EACH WORD
    // =============================================================

    /*
        LOGIC:

        Java is easy

        Java -> avaJ
        is   -> si
        easy -> ysae

        Result:

        avaJ si ysae
    */

    // Method 1: split() + loop
    static void reverseEachWord1(String str) {

        String[] words = str.split("\\s+");

        String result = "";

        for (String word : words) {

            for (int i = word.length() - 1; i >= 0; i--) {

                result = result + word.charAt(i);
            }

            result = result + " ";
        }

        System.out.println(
                "Method 1: " + result.trim()
        );
    }


    // Method 2: StringBuilder
    static void reverseEachWord2(String str) {

        String[] words = str.split("\\s+");

        String result = "";

        for (String word : words) {

            StringBuilder sb = new StringBuilder(word);

            result = result + sb.reverse() + " ";
        }

        System.out.println(
                "Method 2: " + result.trim()
        );
    }


    // =============================================================
    // 10. REMOVE SPACES
    // =============================================================

    /*
        Example:

        Java Programming

        Result:

        JavaProgramming
    */

    // Method 1: replace()
    static void removeSpaces1(String str) {

        String result = str.replace(" ", "");

        System.out.println(
                "Method 1: " + result
        );
    }


    // Method 2: Loop
    static void removeSpaces2(String str) {

        String result = "";

        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);

            if (ch != ' ') {

                result = result + ch;
            }
        }

        System.out.println(
                "Method 2: " + result
        );
    }


    // =============================================================
    // 11. COUNT UPPERCASE AND LOWERCASE
    // =============================================================

    /*
        LOGIC:
        Check each character.

        Example:

        Java

        J = uppercase
        a = lowercase
        v = lowercase
        a = lowercase

        Uppercase = 1
        Lowercase = 3
    */

    // Method 1: Character methods
    static void countCase1(String str) {

        int uppercase = 0;
        int lowercase = 0;

        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);

            if (Character.isUpperCase(ch)) {

                uppercase++;

            } else if (Character.isLowerCase(ch)) {

                lowercase++;
            }
        }

        System.out.println(
                "Method 1: Uppercase = " + uppercase
                + ", Lowercase = " + lowercase
        );
    }


    /*
        ASCII:

        A-Z = 65-90
        a-z = 97-122
    */

    // Method 2: ASCII values
    static void countCase2(String str) {

        int uppercase = 0;
        int lowercase = 0;

        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);

            if (ch >= 'A' && ch <= 'Z') {

                uppercase++;

            } else if (ch >= 'a' && ch <= 'z') {

                lowercase++;
            }
        }

        System.out.println(
                "Method 2: Uppercase = " + uppercase
                + ", Lowercase = " + lowercase
        );
    }


    // =============================================================
    // 12. CHECK WHETHER STRING CONTAINS ONLY DIGITS
    // =============================================================

    /*
        Example:

        "12345" -> true
        "123a5" -> false
    */

    // Method 1: Character.isDigit()
    static void onlyDigits1(String str) {

        boolean result = true;

        if (str.isEmpty()) {

            result = false;
        }

        for (int i = 0; i < str.length(); i++) {

            if (!Character.isDigit(str.charAt(i))) {

                result = false;
                break;
            }
        }

        System.out.println(
                "Method 1: " + result
        );
    }


    /*
        ASCII values:

        0 = 48
        9 = 57

        Therefore, a digit is between '0' and '9'.
    */

    // Method 2: ASCII comparison
    static void onlyDigits2(String str) {

        boolean result = true;

        if (str.isEmpty()) {

            result = false;
        }

        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);

            if (ch < '0' || ch > '9') {

                result = false;
                break;
            }
        }

        System.out.println(
                "Method 2: " + result
        );
    }


    // =============================================================
    // 13. FIND LONGEST WORD
    // =============================================================

    /*
        LOGIC:
        Compare the length of every word.

        Keep the word with the largest length.
    */

    // Method 1: split()
    static void longestWord1(String str) {

        String[] words = str.split("\\s+");

        String longest = "";

        for (String word : words) {

            if (word.length() > longest.length()) {

                longest = word;
            }
        }

        System.out.println(
                "Method 1: " + longest
        );
    }


    /*
        Build each word manually.

        When a space is found, compare the current word
        with the longest word.
    */

    // Method 2: Without split()
    static void longestWord2(String str) {

        String currentWord = "";
        String longestWord = "";

        for (int i = 0; i <= str.length(); i++) {

            if (i < str.length()
                    && str.charAt(i) != ' ') {

                currentWord = currentWord
                        + str.charAt(i);

            } else {

                if (currentWord.length()
                        > longestWord.length()) {

                    longestWord = currentWord;
                }

                currentWord = "";
            }
        }

        System.out.println(
                "Method 2: " + longestWord
        );
    }


    // =============================================================
    // 14. STRING COMPRESSION
    // =============================================================

    /*
        LOGIC:

        aaabbcccc

        aaa -> a3
        bb  -> b2
        cccc -> c4

        Result:

        a3b2c4
    */

    // Method 1: String concatenation
    static void compression1(String str) {

        String result = "";

        int count = 1;

        for (int i = 0; i < str.length(); i++) {

            if (i + 1 < str.length()
                    && str.charAt(i)
                    == str.charAt(i + 1)) {

                count++;

            } else {

                result = result
                        + str.charAt(i)
                        + count;

                count = 1;
            }
        }

        System.out.println(
                "Method 1: " + result
        );
    }


    // Method 2: StringBuilder
    static void compression2(String str) {

        StringBuilder result = new StringBuilder();

        int count = 1;

        for (int i = 0; i < str.length(); i++) {

            if (i + 1 < str.length()
                    && str.charAt(i)
                    == str.charAt(i + 1)) {

                count++;

            } else {

                result.append(str.charAt(i));
                result.append(count);

                count = 1;
            }
        }

        System.out.println(
                "Method 2: " + result
        );
    }


    // =============================================================
    // 15. CHECK STRING ROTATION
    // =============================================================

    /*
        LOGIC:

        String 1 = ABCD
        String 2 = CDAB

        ABCD + ABCD = ABCDABCD

        CDAB is present inside ABCDABCD.

        Therefore, CDAB is a rotation of ABCD.
    */

    // Method 1: Concatenation + contains()
    static void rotation1(String str1, String str2) {

        if (str1.length() != str2.length()) {

            System.out.println(
                    "Method 1: Not Rotation"
            );

            return;
        }

        String combined = str1 + str1;

        if (combined.contains(str2)) {

            System.out.println(
                    "Method 1: Rotation"
            );

        } else {

            System.out.println(
                    "Method 1: Not Rotation"
            );
        }
    }


    /*
        Manually create every possible rotation.

        ABCD rotations:

        ABCD
        BCDA
        CDAB
        DABC

        If any rotation equals str2,
        then it is a rotation.
    */

    // Method 2: Manually check rotations
    static void rotation2(String str1, String str2) {

        if (str1.length() != str2.length()) {

            System.out.println(
                    "Method 2: Not Rotation"
            );

            return;
        }

        for (int i = 0; i < str1.length(); i++) {

            String rotation =
                    str1.substring(i)
                    + str1.substring(0, i);

            if (rotation.equals(str2)) {

                System.out.println(
                        "Method 2: Rotation"
                );

                return;
            }
        }

        System.out.println(
                "Method 2: Not Rotation"
        );
    }


    // =============================================================
    // 16. PRINT ALL PERMUTATIONS OF A STRING
    // =============================================================

    /*
        LOGIC:

        Permutation means every possible arrangement.

        For ABC:

        ABC
        ACB
        BAC
        BCA
        CAB
        CBA

        RECURSION:

        Choose one character.
        Remove it from the String.
        Add it to the answer.
        Repeat until no characters remain.
    */

    static void permutations(String str, String answer) {

        // BASE CASE
        // When no characters are left,
        // one complete permutation is ready.
        if (str.length() == 0) {

            System.out.println(answer);

            return;
        }

        // Try every character
        for (int i = 0; i < str.length(); i++) {

            // Choose one character
            char ch = str.charAt(i);

            // Remove the chosen character
            String remaining =
                    str.substring(0, i)
                    + str.substring(i + 1);

            // Recursive call
            permutations(
                    remaining,
                    answer + ch
            );
        }
    }
}