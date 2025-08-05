package sort;

import java.util.Arrays;

public class Sort {
    public static void KindOfSelectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void bubbleSortBest(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swaps = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps = true;
                }
            }
            if (!swaps) return;
        }
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
            }
        }
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= 0 && temp < arr[j]) {
                arr[j + 1] = arr[j];
                arr[j] = temp;
                j--;
            }
        }
    }

    public static void insertionSortActual(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= 0 && temp < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    /*
🔵 Bubble Sort Algorithm Steps:
    Start from the first element of the array.

    Compare the current element with the next element.

    If the current element is greater than the next, swap them.

    Move to the next pair and repeat Step 2–3 until the end of the array.

    After each full pass, the largest element bubbles to the end.

    Repeat the process for the remaining unsorted part of the array.

    Stop when no swaps are needed (array is sorted).

    📝 Time Complexity: O(n²) in worst and average cases

🟡 Insertion Sort Algorithm Steps:
    Start from the second element (assume the first element is sorted).

    Compare the current element with the elements in the sorted part (left side).

    Shift all larger elements to the right to make space.

    Insert the current element at the correct position.

    Move to the next element and repeat steps 2–4.

    Continue until the entire array is sorted.

    📝 Time Complexity: O(n²) in worst case; O(n) in best case (already sorted)

🟢 Selection Sort Algorithm Steps:
    Start from the first element.

    Find the smallest element in the unsorted part of the array.

    Swap it with the first unsorted element.

    Move the boundary of the sorted and unsorted parts one element forward.

    Repeat steps 2–4 until the array is sorted.

    📝 Time Complexity: Always O(n²), regardless of initial order

     */

    public static int[] mergeSort(int[] arr) {
        if (arr.length == 1) return arr;
        int mid = arr.length / 2;
        int[] left = mergeSort(Arrays.copyOfRange(arr, 0, mid));
        int[] right = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));
        return merge(left, right);
    }

    private static int[] merge(int[] arr1, int[] arr2) {
        int[] combined = new int[arr1.length + arr2.length];
        int index = 0;
        int i = 0;
        int j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                combined[index++] = arr1[i++];
            } else {
                combined[index++] = arr2[j++];
            }

        }
        while (i < arr1.length) {
            combined[index++] = arr1[i++];
        }
        while (j < arr2.length) {
            combined[index++] = arr2[j++];
        }
        return combined;
    }

    //Quick Sort
    /*
    start with a pivot in the beginning
    check next element grater than the pivot indexed element
    if yes increase swap index
    else do nothing
    then we will get one pivot index
    with that use divide and conquer method
    go to both left and right of the pivot index until left<right become false
    ex:
    //{9, 7, 5, 3, 1}
    //[1, 7, 5, 3, 9]
    after 1st step
     */
    private static void swap(int[] arr1, int index1, int index2) {
        int temp = arr1[index1];
        arr1[index1] = arr1[index2];
        arr1[index2] = temp;
    }

    public static int pivot(int[] arr, int pivotIndex, int endIndex) {
        int swapIndex = pivotIndex;
        for (int i = pivotIndex + 1; i <= endIndex; i++) {
            if (arr[i] < arr[pivotIndex]) {
                swapIndex++;
                swap(arr, swapIndex, i);
            }
        }
        swap(arr, swapIndex, pivotIndex);
        return swapIndex;
    }

    private static void quickSortHelper(int[] arr, int left, int right) {
        if (left < right) {
            System.out.println(Arrays.toString(arr));
            int pivot = pivot(arr, left, right);
            quickSortHelper(arr, left, pivot - 1);
            quickSortHelper(arr, pivot + 1, right);
        }
    }

    public static void quickSort(int[] arr) {
        quickSortHelper(arr, 0, arr.length - 1);
    }
    public static class MergeSort {

    // Main mergeSort method that divides the array recursively
    public static void mergeSort(int[] array, int left, int right) {
        // Base condition: If the array has more than one element
        if (left < right) {
            // Find the middle point of the array
            int mid = (left + right) / 2;

            // Recursively sort the first half
            mergeSort(array, left, mid);

            // Recursively sort the second half
            mergeSort(array, mid + 1, right);

            // Merge the two sorted halves
            merge(array, left, mid, right);
        }
    }

    // Method to merge two sorted subarrays into one
    public static void merge(int[] array, int left, int mid, int right) {
        // Sizes of the two subarrays
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temporary arrays to hold the two halves
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data into the temporary left array
        for (int i = 0; i < n1; i++)
            L[i] = array[left + i];

        // Copy data into the temporary right array
        for (int j = 0; j < n2; j++)
            R[j] = array[mid + 1 + j];

        // Initial indexes of the two subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray
        int k = left;

        // Merge the temp arrays back into the main array
        while (i < n1 && j < n2) {
            // Compare and place the smaller element
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy any remaining elements from L[] (if any)
        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        // Copy any remaining elements from R[] (if any)
        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }

    // Utility method to print the array
    public static void printArray(int[] array) {
        for (int num : array)
            System.out.print(num + " ");
        System.out.println();
    }

    // Main method to test the merge sort
    public static void main(String[] args) {
        // Sample input array
        int[] arr = {38, 27, 43, 3, 9, 82, 10};

        // Print original array
        System.out.println("Original Array:");
        printArray(arr);

        // Sort the array
        mergeSort(arr, 0, arr.length - 1);

        // Print sorted array
        System.out.println("Sorted Array:");
        printArray(arr);
    }
}


}
