package sort;

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
                arr[j+1] = arr[j];
                arr[j]=temp;
                j--;
            }
        }
    }

    public static void insertionSortActual(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= 0 && temp < arr[j]) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1]=temp;
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

}
