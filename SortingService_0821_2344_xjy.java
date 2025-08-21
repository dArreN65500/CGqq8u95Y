// 代码生成时间: 2025-08-21 23:44:48
package com.yourcompany.sorting;

import org.springframework.stereotype.Service;

@Service
public class SortingService {

    /**
     * Sorts an array of integers using the Bubble Sort algorithm.
     * 
     * @param arr The array to be sorted.
     * @return The sorted array.
     */
    public int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }
        
        boolean swapped;
        for (int i = 0; i < arr.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j + 1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break; // If no two elements were swapped, the array is sorted.
            }
        }
        return arr;
    }

    /**
     * Sorts an array of integers using the Quick Sort algorithm.
     * 
     * @param arr The array to be sorted.
     * @return The sorted array.
     */
    public int[] quickSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }
        
        return quickSort(arr, 0, arr.length - 1);
    }

    private int[] quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            int[] left = quickSort(arr, low, pi - 1);
            int[] right = quickSort(arr, pi + 1, high);
            
            // Combine the results
            return combine(left, right);
        } else {
            return new int[]{arr[low]};
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    private int[] combine(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }
        return result;
    }
}
