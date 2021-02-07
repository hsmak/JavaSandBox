package org.hsmak.letit;

public class GoodTriplets {
    public static void main(String[] args) {
        System.out.println(new GoodTriplets().countGoodTriplets(new int[]{3, 0, 1, 1, 9, 7}, 7, 2, 3));
        System.out.println(new GoodTriplets().countGoodTripletsImp(new int[]{3, 0, 1, 1, 9, 7}, 7, 2, 3));
    }

    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int count = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    int aa = Math.abs(arr[i] - arr[j]);
                    int bb = Math.abs(arr[j] - arr[k]);
                    int cc = Math.abs(arr[i] - arr[k]);

                    if (aa <= a && bb <= b && cc <= c)
                        count++;
                }
            }
        }
        return count;
    }

    public int countGoodTripletsImp(int[] arr, int a, int b, int c) {
        int count = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                int aa = Math.abs(arr[i] - arr[j]);
                if (aa <= a) // if this
                    for (int k = j + 1; k < arr.length; k++) {
                        int bb = Math.abs(arr[j] - arr[k]);
                        int cc = Math.abs(arr[i] - arr[k]);
                        if (bb <= b && cc <= c)
                            count++;
                    }
            }
        }
        return count;
    }
}
