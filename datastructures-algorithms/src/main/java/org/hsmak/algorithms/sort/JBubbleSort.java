package org.hsmak.algorithms.sort;

import java.util.Arrays;

/**
 * works by repeatedly swapping the adjacent elements if they are in wrong order.
 */
public class JBubbleSort {


    public static <E extends Comparable<E>> E[] bubbleSort(E[] array) {

        E[] data = Arrays.copyOf(array, array.length);

        for (int i = 0; i < data.length - 1; i++) {// n passes

            for (int j = 0; j < data.length - i - 1; j++) {// k swapping per pass

                if (data[j].compareTo(data[j + 1]) > 0) {
                    E tmp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = tmp;
                }

            }

        }

        return data;
    }

    public static <E extends Comparable<E>> E[] bubbleSortOptimized(E[] array) {

        E[] data = Arrays.copyOf(array, array.length);

        for (int i = 0; i < data.length - 1; i++) {// n passes

            boolean swapped = false;

            for (int j = 0; j < data.length - i - 1; j++) {// k swapping per pass

                if (data[j].compareTo(data[j + 1]) > 0) {
                    E tmp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = tmp;

                    swapped = true;
                }

            }

            // If no two elements were swapped by inner loop, then break
            if(swapped == false)
                break;

        }

        return data;
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(JBubbleSort.<String>bubbleSort(new String[]{"B","A", "C"})));
        System.out.println(Arrays.toString(JBubbleSort.<String>bubbleSortOptimized(new String[]{"B","A", "C"})));
    }
}
