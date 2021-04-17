package org.hsmak.letit;

public class ReplaceElements {

    ////// V1 ////////

    public int[] replaceElements(int[] arr) {
        int j = arr.length - 1;
        int max = arr[j];
        arr[j] = -1;
        j--;
        while(j >= 0) {
            int temp = arr[j];
            arr[j] = max;
            max = Math.max(max, temp);
            j--;
        }
        return arr;
    }

    ///// V2 /////

    public int[] replaceElementsV2(int[] arr) {
        for(int i = 0; i < arr.length; i++){
            arr[i] = findGreatestFromIndex(arr, i+1);
        }
        return arr;
    }

    int findGreatestFromIndex(int[] arr, int startIndex){
        if(startIndex == arr.length)
            return -1;

        int max = arr[startIndex];
        for(int i = startIndex; i < arr.length; i++){
            if(max < arr[i])
                max = arr[i];
        }
        return max;
    }

}
