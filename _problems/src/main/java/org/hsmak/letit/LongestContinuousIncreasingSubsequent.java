package org.hsmak.letit;

public class LongestContinuousIncreasingSubsequent {
    public int findLengthOfLCIS(int[] nums) {
        if(nums.length == 0)
            return 0;

        int max = 1;
        int c = 1;

        for (int i = 1; i < nums.length; i++){
            if(nums[i] > nums[i-1])
                c++;
            else
                c = 1;

            if(c > max)
                max = c;
        }

        return max;
    }

    public static void main(String[] args) {

    }
}
