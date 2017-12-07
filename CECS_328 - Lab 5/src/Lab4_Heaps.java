import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lab4_Heaps {

    public static void main(String[] args){
        System.out.println("Enter n: ");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = randInt(-10000,10000);
        }
        array = build_MaxHeap(array, array.length);
        System.out.println(Arrays.toString(array));
        heap_sort(array);
        System.out.println(Arrays.toString(array));

    }

    public static void heap_sort(int[] array){
        int right = array.length;
        while(right > 0){
            System.out.println(right);
            swap(array, 0, right - 1);
            build_MaxHeap(array, right--);
        }
    }

    public static int[] build_MaxHeap(int[] array, int  right){
        for(int i = right; i > 0; i--){
            max_Heapify(array,i);
        }
        return array;
    }

    public static void max_Heapify(int[] array, int root){
        int max = root;
        int left = (2 * root);
        int right = left + 1;
        if(left <= array.length - 1 && array[left - 1] > array[max - 1])
            max = left;
        if(right <= array.length - 1 && array[right - 1] > array[max - 1])
            max = right;
        if(root != max) {
            swap(array, max - 1, root - 1);
            max_Heapify(array, max);
        }
    }

    public static void swap(int[] array, int a, int b){
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
