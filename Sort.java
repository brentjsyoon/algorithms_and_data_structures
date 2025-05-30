package pkg1;

import java.util.Arrays;

public class Sort {

    // Bubble sort: place the largest value at the end of the array every iteration
    public static int[] bubble(int[] arr) {
        int i = 0;
        boolean sorted = false;

        // If sorted before iterations complete end
        while (!sorted) {
            sorted = true;
            // Placing largest at the end of the array
            for (int j=0; j < arr.length-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    sorted = false;
                }
            }
            i++;
        }
        return arr;
    }

    // Selection sort: select the smallest value from unsorted part
    // and place in sorted part
    public static int[] selection(int[] arr) {
        // Moving delimiter separating sorted/unsorted sections
        for (int delim=0; delim < arr.length-1; delim++) {
            int min = delim;
            // Getting smallest value from unsorted part
            for (int i=delim+1; i < arr.length; i++) {
                if (arr[min] > arr[i]) {
                    min = i;
                }
            }
            // Swap if the smallest value not in the correct spot
            if (min != delim) {
                int temp = arr[delim];
                arr[delim] = arr[min];
                arr[min] = temp;
            }
        }
        return arr;
    }

    // Insertion sort: insert each element into its correct position
    // among the previously sorted elements
    public static int[] insertion(int[] arr) {
        for (int i=0; i < arr.length; i++) {
            int element = arr[i];
            int j = i;
            // Iterate down the sorted section until correct position found
            while (j > 0 && element < arr[j-1]) {
                arr[j] = arr[j-1];
                j--;
            }
            arr[j] = element;
        }
        return arr;
    }

    // Merge sort: divide and conquer strategy break array recursively into smaller 
    // arrays until one element arrays order while merging subarrays together
    public static int[] mergesort(int[] arr) {
        // Base case: one element array
        if (arr.length == 1) {
            return arr;
        }
        // Mid point to divide array
        int mid = arr.length / 2;
        int[] list1 = Arrays.copyOfRange(arr, 0, mid);
        int[] list2 = Arrays.copyOfRange(arr, mid, arr.length);
        // Run mergesort() on recursive subarray
        list1 = mergesort(list1);
        list2 = mergesort(list2);
        // Merge the two subarrays while ordering
        arr = merge(list1,list2);
        return arr;
    }

    // Merge function to accompany mergesort()
    private static int[] merge(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;
        int[] merged = new int[n1 + n2];
        int i = 0, j = 0, k = 0;
        // This block runs while elements are still in both subarrays
        // comparing which element is smaller then adding that element
        while (i < n1 && j < n2) {
            if (arr1[i] < arr2[j]) {
                merged[k++] = arr1[i++];
            }
            else merged[k++] = arr2[j++];
        }
        // The follow are to add the rest of one subarray if the other is empty
        while (i < n1) {
            merged[k++] = arr1[i++];
        }
        while (j < n2) {
            merged[k++] = arr2[j++];
        }
        return merged;
    }

    // Quick sort: choose a pivot (last element here) and reorder the list so
    // smaller elements to the left and larger elements to the left
    public static int[] quickSort(int[] arr, int leftIdx, int rightIdx) {
        // Base case: subarray has one element
        if (leftIdx >= rightIdx) {
            return arr;
        }
        // Choose a pivot i and modify list to sort smaller and 
        // larger elements to the left and right
        int i = placeAndDivide(arr, leftIdx, rightIdx);
        // Recursive subarray calls to quicksort() for left and right subarray
        quickSort(arr, leftIdx, i-1);
        quickSort(arr, i+1, rightIdx);
        return arr;
    }

    // Place the pivot and sort smaller to the left larger to the right
    private static int placeAndDivide(int[] arr, int leftIdx, int rightIdx) {
        // Pivot value is the last index of the array
        int pivot = arr[rightIdx];
        // This is pivot location we start from -1 index
        int wall = leftIdx - 1;
        // If element at index i is smaller move to left of wall
        for (int i=leftIdx; i < rightIdx; i++) {
            if (arr[i] < pivot) {
                wall++;
                int temp = arr[wall];
                arr[wall] = arr[i];
                arr[i] = temp;
            }
        }
        // Swap wall element and pivot element
        int temp = arr[wall+1];
        arr[wall+1] = arr[rightIdx];
        arr[rightIdx] = temp;
        // Return the index of the pivot
        return wall+1;
    }
}
