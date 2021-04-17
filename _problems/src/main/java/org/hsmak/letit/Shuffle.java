package org.hsmak.letit;

public class Shuffle {
    public int[] shuffle(int[] nums, int n) {

        int[] out = new int[nums.length];

        for (int k = 0, i = 0; i < n; i++) {
            out[k++] = nums[i];
            out[k++] = nums[i + n];
        }
        return out;
    }
}
