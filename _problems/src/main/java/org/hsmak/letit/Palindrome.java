package org.hsmak.letit;

public class Palindrome {

    public boolean isPalindrome(int x) {
        char[] c = String.valueOf(x).toCharArray();
        for (int i = 0, j = c.length - 1; i < j; i++, j--)
            if (c[i] != c[j])
                return false;
        return true;
    }
}
