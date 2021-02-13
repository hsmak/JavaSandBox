package org.hsmak.letit;

public class MaximumSwap {
    public static void main(String[] args) {
        System.out.println(new MaximumSwap().maximumSwap(1993));
        System.out.println(new MaximumSwap().maximumSwap(98368));
    }

    public int maximumSwap(int num) {
        char[] nums = String.valueOf(num).toCharArray();
        int n = nums.length;
        int j = n - 1, min = j, max = j;

        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[j]) {
                min = i;
                max = j;
            } else if (nums[i] > nums[j])
                j = i;
        }
        swap(nums, min, max);
        return Integer.parseInt(String.valueOf(nums));
    }

    void swap(char[] nums, int i, int j) {
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
