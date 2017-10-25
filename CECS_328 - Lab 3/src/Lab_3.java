import java.util.Arrays;
import java.util.Random;

public class Lab_3 {
    public static void main(String[] args){
        System.out.print("Enter K: ");
        int k = 3;
        int n = 5;
//        int[] a = new int[n];
        int[] a = {96, 38, 87, -94, -22};
//        for(int i = 0; i < n; i++) {
//            a[i] = (randInt(-100, 100));
//        }
        System.out.println(Arrays.toString(a));
        System.out.println(quick_sort(a, 0, a.length - 1, k - 1, k - 1));
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));

    }

    public static boolean quick_sort(int[] array, int left, int right, int k, int K){
        if(right - left < 1) {
            if (k == 0) {
                System.out.println("K: " + array[right]);
                System.out.println("K L: " + array[left]);
                return true;
            } else
                return false;
        }
        int pivot = med(array[left], array[(left + right / 2)], array[right]);
        System.out.println("Pivot: " + pivot);
        int indexOfPartition = partition(array, left, right, pivot);
        System.out.println("Index: " + indexOfPartition);
        System.out.println("Current K: " + k);
        if(k == indexOfPartition){
            System.out.println("K: " + indexOfPartition + "  " + array[k]);
            return true;
        }
        if(k < indexOfPartition)
            quick_sort(array, left, indexOfPartition - 1, k, K);
        if(k > indexOfPartition)
            quick_sort(array, indexOfPartition + 1, right, k - (indexOfPartition + 1), K);
        return false;
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