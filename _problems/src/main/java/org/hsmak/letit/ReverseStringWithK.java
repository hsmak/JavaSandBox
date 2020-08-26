package org.hsmak.letit;

public class ReverseStringWithK {
    public String reverseFirstKEvery2K(String s, int k) {

        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i += 2 * k) {
            int j = i, end = Math.min(i + k - 1, chars.length - 1);
            while (j < end) {
                char temp = chars[j];
                chars[j++] = chars[end];
                chars[end--] = temp;
            }
        }
        return String.valueOf(chars);
        /*char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i += 2 * k ) {//Every 2*K -> multiple of Ks

            for (int j = i; j < k * (i + 1) / 2; j++) {

                if (j >= chars.length - 1)
                    break;

                char front = chars[j];
                chars[j] = chars[j + k - 1];
                chars[j + k - 1] = front;
            }
        }

        return String.valueOf(chars);*/

    }
}
