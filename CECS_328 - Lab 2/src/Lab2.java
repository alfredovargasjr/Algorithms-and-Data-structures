import java.sql.Time;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Alfredo Vargas
 * CECS 328
 * Lab 2
 */

public class Lab2 {
     public static void main(String[] args){
//         System.out.print("Enter an n: ");
//         Scanner in = new Scanner(System.in);
         int n = 10000;
         int[] a = new int[n];
         for(int i = 0; i < n; i++) {
             a[i] = (randInt(-7000, 7000));
         }
         n = 10000;
         a = new int[n];
         int loops = 100;
         double nanoSec = Math.pow(10,9);
         long start = System.nanoTime();
         long start_newArray = 0;
         double newArray_time = 0;
         for(int j = 0; j < loops; j++) {
             start_newArray = System.nanoTime();
             for (int i = 0; i < a.length; i++) {
                 a[i] = (randInt(-7000, 7000));
             }
             newArray_time = newArray_time + ((System.nanoTime() - start_newArray) / nanoSec);
             quick_sort(a, 0, a.length - 1);
         }
         long end = System.nanoTime();
         double quicksort_AverageRT = ((end - start) / nanoSec / loops - (newArray_time / loops));
         System.out.println("Quicksort - Average Running Time: " + quicksort_AverageRT + " seconds");
         newArray_time = 0;
         start = System.nanoTime();
         for(int j = 0; j < loops; j++) {
             start_newArray = System.nanoTime();
             for (int i = 0; i < a.length; i++) {
                 a[i] = (randInt(-7000, 7000));
             }
             newArray_time = newArray_time + ((System.nanoTime() - start_newArray) / nanoSec);
             insertion_sort(a);
         }
         end = System.nanoTime();
         double insertionsort_AverageRT = ((end - start) / nanoSec / loops - (newArray_time / loops));
         System.out.println("Insertion Sort - Average Running Time: " + insertionsort_AverageRT + " seconds");
         System.out.println("\n" + (1 / (insertionsort_AverageRT / (n * n))) + " Instructions/second");


     }

    /**
     * Quick sort algorithm, recursive call
     * @param array
     * @param left
     * @param right
     */
     public static void quick_sort(int[] array, int left, int right){
         if(left >= right)
             return;
         int pivot =  array[(left + right) / 2];
         int indexOfPartition = partition(array, left, right, pivot);
         quick_sort(array, left, indexOfPartition - 1);
         quick_sort(array, indexOfPartition, right);
     }

    /**
     * find the final index of the pivot
     * @param array
     * @param left
     * @param right
     * @param pivot
     * @return  the pivot index so the subarrays can be created
     */
     public static int partition(int[] array, int left, int right, int pivot){
         while(left <= right) {
             while(array[left] < pivot) {
                 left++;
             }
             while(array[right] > pivot){
                 right--;
             }
             if(left <= right){
                 int temp = array[left];
                 array[left] = array[right];
                 array[right] = temp;
                 left++;
                 right--;
             }
         }
         return left;
     }

    /**
     * instertion sort algorithm
     * @param array
     */
     public static void insertion_sort(int[] array){
         if(array.length == 1){
             return;
         }
         for(int i = 1; i < array.length; i++) {
             int current = array[i];
             int prev = i - 1;
             while (prev >= 0 && array[prev] > current) {
                 array[prev + 1] = array[prev];
                 prev--;
             }
             array[prev + 1] = current;
         }
     }

    /**
     * Generate a random integer
     * @param min smallest value in range
     * @param max largest value in range
     * @return
     */
    public static int randInt(int min, int max){
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
