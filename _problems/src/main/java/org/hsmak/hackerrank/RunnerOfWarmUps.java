package org.hsmak.hackerrank;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RunnerOfWarmUps {

}

class SockMerchant {
    public static void main(String[] args) {
        System.out.println(sockMerchant(new int[]{10, 20, 20, 10, 10, 30, 50, 10, 20}));
    }

    public static int sockMerchant(int[] ar) {
        Map<Integer, Integer> countSocks = new HashMap<>();

        for (int i : ar) {
            if (countSocks.containsKey(i))
                countSocks.put(i, countSocks.get(i) + 1);
            else
                countSocks.put(i, 1);
        }

        int c = 0;
        for (int i : countSocks.values()) {
            for (; i > 0; i--)
                if (i % 2 == 0)
                    c++;
        }

        return c;
    }

    public static int sockMerchant2(int[] c){
        Set<Integer> colors = new HashSet<>();
        int pairs = 0;

        for (int i = 0; i < c.length; i++) {
            if (!colors.contains(c[i])) {
                colors.add(c[i]);
            } else {
                pairs++;
                colors.remove(c[i]);
            }
        }
        return pairs;
    }
}

class CountingValleys {

    public static void main(String[] args) {
        System.out.println(countingValleys2("UDDDUDUU"));
    }

    public static int countingValleys(String path) {
        // Write your code here
        String[] stepsArr = path.split("");
        int valleyCount = 0;
        int stepCount = 0;
        boolean seaLevelChange = true;
        for (String step : stepsArr) {
            if (step.equals("D"))
                stepCount--;
            if (step.equals("U"))
                stepCount++;

            if (stepCount < 0 && seaLevelChange) {
                valleyCount++;
                seaLevelChange = false;
            }
            if (stepCount == 0)
                seaLevelChange = true;
        }
        return valleyCount;
    }

    public static int countingValleys2(String path) {
        int v = 0;     // # of valleys
        int lvl = 0;   // current level
        for(char c : path.toCharArray()){
            if(c == 'U') ++lvl;
            if(c == 'D') --lvl;

            // if we just came UP to sea level
            if(lvl == 0 && c == 'U')
                ++v;
        }
        return v;
    }
}

class JumpingOnClouds {
    public static void main(String[] args) {
        System.out.println(jumpingOnClouds(new int[]{0, 0, 0, 0, 1, 0}));
        System.out.println(jumpingOnClouds(new int[]{0, 0, 1, 0, 0, 1, 0}));
        System.out.println(jumpingOnClouds(new int[]{0, 0, 0, 1, 0, 0}));
    }

    static int jumpingOnClouds(int[] c) {
        int count = 0;
        for (int i = 0; i < c.length - 1; i++) {
            if (c[i] == 0)
                i++;
            count++;
        }
        return count;
    }
}