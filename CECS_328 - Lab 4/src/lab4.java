import java.math.MathContext;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class lab4 {

    /**
     * compare the results of the Max Subsequence Summation of an array, using a O(n) and O(nlogn) algorithm
     * @param args
     */
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter n: ");
        int n = in.nextInt();
        int[] array = new int[n];
        for(int i = 0; i < n; i++) {
            array[i] = randInt(-100, 100);
        }
        System.out.println(Arrays.toString(array));
        System.out.println("MSS nlogn: " + MSS_nlogn(array,0,array.length - 1));
        System.out.println("MSS n: " + MSS_n(array, 0, array.length));
    }

    /**
     * find the max subsequence summation, O(n) running time
     * @param a
     * @param left
     * @param right
     * @return  mss
     */
    public static int MSS_n(int[] a, int left, int right){
        int mss = a[left];
        int temp = a[left];
        for(int i = left + 1; i < right; i++){
            temp = temp + a[i];
            if(temp > mss){
                mss = temp;
            }
            else if(temp < mss){
                temp = 0;
            }
            if(a[i] > mss){
                mss = a[i];
                temp = a[i];
            }
        }
        return mss;
    }

    /**
     * find the max subsequence summation, O(nlog) running time
     * get the largest max sequence of the left tree, right tree, and center tree
     * @param a
     * @param left
     * @param right
     * @return  mss
     */
    public static int MSS_nlogn(int[] a, int left, int right){
        if(left >= right){
            return a[left];
        }
        int parent = (left + right) / 2;
        int left_tree = MSS_nlogn(a, left, parent - 1);
        int right_tree = MSS_nlogn(a, parent + 1, right);
        int center_tree = MSS_n(a, left, (right - left) + 1);
        return Math.max(center_tree, Math.max(left_tree, right_tree));
    }

    /**
     * generate a random integer between min - max
     * @param min
     * @param max
     * @return  random integer
     */
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
