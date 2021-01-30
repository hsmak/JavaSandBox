package org.hsmak.hackerrank;

import java.util.*;

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

class EqualizeArray {

    public static void main(String[] args) {
        int[] nums = Arrays.stream("69 86 100 69 55 83 15 69 86 69 79 16 86 24 24 55 16 69 100 79 16 83 83 79 15 15 86 16 55 18 100 100 86 16 83 79 86 83 100 83 55 15 86 86 55 100 55 18 55 100 86 69 83 24 16 55 100 16 100 24 16 55 15 79 16 18 16 16 83 83 69 18 100 79 16 24 79 16 69 86 83 79 83 18 15 100 24 83"
                .split(" "))
                .mapToInt(Integer::valueOf).toArray();

        System.out.println(equalizeArray(nums));
    }
    // Complete the equalizeArray function below.
    static int equalizeArray(int[] arr) {
        Map<Integer, Integer> m = new HashMap<>();
        for (Integer i : arr) {
            if (!m.containsKey(i))
                m.put(i, 1);
            else
                m.put(i, (m.get(i)) + 1);
        }
        return arr.length - m.values().stream().mapToInt(i -> i).max().getAsInt();
    }
}