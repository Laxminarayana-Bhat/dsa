package sort;

import java.util.Arrays;

public class Main {
    public static void main(String... vals){
//        int[] arr=new int[]{3,5,7,9,2,4,6};
        int[] arr = {9, 7, 5, 3, 1};
//        Sort.bubbleSort(arr);
//        Sort.selectionSort(arr);
        Sort.quickSort(arr);
    }
}
