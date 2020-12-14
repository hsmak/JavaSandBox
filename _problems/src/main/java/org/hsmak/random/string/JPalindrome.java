package org.hsmak.random.string;

import java.util.stream.IntStream;

public class JPalindrome {

    public static boolean isPalindrome(String str) {

        int f = 0;
        int b = str.length() - 1;

        while (b > f) {
            if (str.charAt(f++) != str.charAt(b--))
                return false;
        }

        return true;
    }

    public static boolean isPalindromeWStreams(String str) {

        return IntStream.range(0, str.length() / 2)
                .noneMatch(i -> str.charAt(i) != str.charAt(str.length() - i - 1));

    }

    public static boolean isPalindromeByReversing(String str){
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        for(int i = chars.length-1; i>= 0; i--)
            sb.append(chars[i]);

        return sb.toString().equals(str);

    }


    public static void main(String[] args) {
        String str1 = "abccba";
        System.out.println(isPalindrome(str1));
        System.out.println(isPalindromeWStreams(str1));
        System.out.println(isPalindromeByReversing(str1));
    }
}
