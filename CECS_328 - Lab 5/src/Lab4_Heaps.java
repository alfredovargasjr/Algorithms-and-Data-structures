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
        array = build_MaxHeap(array, array.length, array.length);
        heap_sort(array);
        System.out.println(Arrays.toString(array));

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
            System.out.println(Arrays.toString(array));
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
