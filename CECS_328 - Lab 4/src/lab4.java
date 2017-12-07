import java.math.MathContext;
import java.util.Arrays;
import java.util.Random;

public class lab4 {
    public static void main(String[] args){
        int n = 5;
        int[] array = new int[n];
        for(int i = 0; i < n; i++) {
            array[i] = (randInt(-100, 100));
        }
        System.out.println(Arrays.toString(array));
        System.out.println("MSS nlogn: " + MSS_nlogn(array,0,array.length - 1));
        System.out.println("MSS n: " + MSS_n(array));
    }

    public static int MSS_n(int[] a){
        int mss = a[0];
        int temp = a[0];
        for(int i = 1; i < a.length; i++){
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

    public static int MSS_nlogn(int[] a, int left, int right){
        if(left >= right){
            return a[left];
        }
        int left_tree = MSS_nlogn(a, left, (left + right) / 2);
        int right_tree = MSS_nlogn(a, ((left + right) / 2) + 1, right);
        return Math.max(left_tree, Math.max(right_tree, left_tree + right_tree));
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
