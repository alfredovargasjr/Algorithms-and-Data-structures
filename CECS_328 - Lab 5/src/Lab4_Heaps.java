import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lab4_Heaps {

    public static void main(String[] args){
        System.out.println("Enter n: ");
        int loops = 100;
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] array = new int[n];

        //  estimate running time of heap_sort
        long startTime = System.nanoTime();
        long startTimeNewArray;
        long TimeNewArray = 0;
        for(int i = 0; i < loops; i++) {
            startTimeNewArray = System.nanoTime();
            randomArray_Int(array);
            TimeNewArray = TimeNewArray + (System.nanoTime() - startTimeNewArray);
            array = build_MaxHeap(array, array.length / 2, array.length);
            heap_sort(array);
        }
        long endTime = System.nanoTime();
        long runTime = endTime - startTime;
        double nanoSeconds = Math.pow(10, 9);
        System.out.println("Heap_Sort Average Running Time (n = " + n + ", loops = " + loops + "): " + ((runTime - TimeNewArray) / nanoSeconds /loops) + " seconds");
        
        //  find average running time of insertion sort
        startTime = System.nanoTime();
        TimeNewArray = 0;
        for(int i = 0; i < loops; i++) {
            startTimeNewArray = System.nanoTime();
            randomArray_Int(array);
            TimeNewArray = TimeNewArray + (System.nanoTime() - startTimeNewArray);
            insertion_sort(array);
        }
        endTime = System.nanoTime();
        runTime = endTime - startTime;
        System.out.println("Insertion_Sort Average Running Time (n = " + n + ", loops = " + loops + "): " + ((runTime - TimeNewArray) / nanoSeconds /loops) + " seconds");

        randomArray_Int(array);
        insertion_sort(array);
        System.out.println(Arrays.toString(array));

        //find average running time of quick sort
        startTime = System.nanoTime();
        TimeNewArray = 0;
        for(int i = 0; i < loops; i++) {
            startTimeNewArray = System.nanoTime();
            randomArray_Int(array);
            quick_sort(array, 0, array.length - 1);
        }
        quick_sort(array, 0, array.length - 1);
        endTime = System.nanoTime();
        runTime = endTime - startTime;
        System.out.println("Quick_Sort Average Running Time (n = " + n + ", loops = " + loops + "): " + ((runTime - TimeNewArray) / nanoSeconds /loops) + " seconds");


    }

    /**
     * Create random integers within the array
     * @param array
     */
    public static void randomArray_Int(int[] array){
        for (int j = 0; j < array.length; j++) {
            array[j] = randInt(-10000, 10000);
        }
    }

    /**
     * Sort the array, in ascending order, using heap sort
     *
     * Gets a max heap and swaps the root with last leaf node,
     *      ignore the swapped root, (deleting the old root from the tree)
     *      array no longer a max heap so we will build a new max heap.
     *      Max heapify will be called until the array is a max heap.
     *      repeats this process until there are no more nodes in the heap (right is 0)
     *
     * @param array
     */
    public static void heap_sort(int[] array){
        int right = array.length;
        while(right > 0){
            swap(array, 0, right - 1);
            right--;
            build_MaxHeap(array, right, right);
        }
    }

    public static void changeOrder(int[] array){
        int left = 0;
        int right = array.length - 1;
        while(left < right){
            swap(array, left, right);
            left++;
            right--;
        }
    }

    /**
     *
     * @param array
     * @param parent    is current node at which max_heapify is being done
     * @param right     the last leaf node, ignore the swapped roots
     * @return          max_heap of array
     */
    public static int[] build_MaxHeap(int[] array, int parent, int  right){
        for(int i = parent; i > 0; i--){
            max_Heapify(array, i, right);
        }
        return array;
    }

    /**
     *  run the max heapify algorithm, the left and right child must be a max heap.
     *  run recursivly to check if both children are max heaps
     *
     * @param array
     * @param parent    the parent node, current root of subtree
     */
    public static void max_Heapify(int[] array, int parent,  int right){
        int max = parent;
        int leftChild = (2 * parent);
        int rightChild = leftChild + 1;
            if (leftChild <= right && array[leftChild - 1] > array[max - 1])
                max = leftChild;
            if (rightChild <= right && array[rightChild - 1] > array[max - 1])
                max = rightChild;
            if (parent != max) {
                swap(array, max - 1, parent - 1);
                max_Heapify(array, max, right);
            }
    }

    /**
     * insertion sort algorithm
     * @param array
     */
    public static void insertion_sort(int[] array){
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
     * quick sort algorithm
     * @param array
     * @param left
     * @param right
     */
    public static void quick_sort(int[] array, int left, int right){
        if(left >= right)
            return;
        int pivot = med(array[left], array[(left + right) / 2], array[right]);
        int indexOfPivot = partition(array, left, right, pivot);
        quick_sort(array, left, indexOfPivot - 1);
        quick_sort(array, indexOfPivot, right);
        
    }

    public static int partition(int[] array, int left, int right, int pivot) {
        while(left <= right){
            while(array[left] < pivot) {
                left++;
            }
            while(array[right] > pivot) {
                right--;
            }
            if(left <= right) {
                swap(array, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    public static int med(int a, int b, int c) {
        return Math.max(Math.min(a,b), Math.min(Math.max(a,b),c));
    }

    /**
     * swap value of index a with index b in array
     * @param array
     * @param a
     * @param b
     */
    public static void swap(int[] array, int a, int b){
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    /**
     *
     * @param min
     * @param max
     * @return a random int between min - max
     */
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
