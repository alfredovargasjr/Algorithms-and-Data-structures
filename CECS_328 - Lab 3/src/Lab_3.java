import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lab_3 {
    public static void main(String[] args){
        System.out.print("Enter n: ");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = 3;
        int[] a = new int[n];
//        int[] a = {96, 38, 87, -94, -22};
        for(int i = 0; i < n; i++) {
            a[i] = (randInt(-100, 100));
        }
        quick_search_k(a, 0, a.length - 1, k - 1);
        quick_sort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));

    }

    public static void quick_search_k(int[] array, int left, int right, int k){
//        System.out.println("IN");
        if(left >= right){
            if(k == 0) {
                return;
            }
            else
                return;
        }
        int pivot = med(array[left], array[(left + right) / 2], array[right]);
        int partition_index = partition(array, left, right, pivot);
        if(k == partition_index){
            System.out.println(array[partition_index]);
            return;
        }
        if(k < partition_index){
            quick_search_k(array, left, partition_index - 1, k);
        }
        if(k > partition_index){
            quick_search_k(array, partition_index + 1, right, k - (partition_index + 1));
        }
        return ;
     }

    public static void quick_sort(int[] array, int left, int right){
        if(left >= right)
            return;
        int pivot =  med(array[left],array[(left + right) / 2],array[right]);
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
             while (array[left] < pivot) {
                 left++;
             }
             while (array[right] > pivot) {
                 right--;
             }
             if (left <= right) {
                 int temp = array[left];
                 array[left] = array[right];
                 array[right] = temp;
                 left++;
                 right--;
             }
         }
         return left;
     }

     public static int med(int a, int b, int c){
         return Math.max(Math.min(a,b), Math.min(Math.max(a,b),c));
     }

    /**
     * Generate a random integer
     * @param min smallest value in range
     * @param max largest value in range
     * @return
     */
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}