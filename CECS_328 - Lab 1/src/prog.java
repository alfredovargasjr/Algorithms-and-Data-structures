import java.sql.Time;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class prog {
    public static void main(String[] args){
        //System.out.print("Enter an n: ");
        //Scanner in = new Scanner(System.in);
        int n = 100000;
        System.out.println("n = 100,0000");
        int[] a = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = (randInt(-1000, 1000));

        }
        Arrays.sort(a);
        int key = a[randInt(0, a.length - 1)];

        //*****PART A*****
        System.out.println("\n- PART A -");
        //start timer for n search
        long start = System.nanoTime();
        //TimeUnit seconds = new TimeUnit();
        for(int i = 0; i < 500; i++){
            linearSearch(a,a[randInt(0, a.length - 1)]);
        }
        long end = System.nanoTime();
        double nanoSec = Math.pow(10,9);
        System.out.println("T(n) - Average Running Time: " + (((end - start) / 500) / nanoSec) + " seconds");

        //start timer for logn search
        start = System.nanoTime();
        for(int i = 0; i < 500; i++){
            binarySearch(0, a.length - 1, a[randInt(0, a.length - 1)], a);
        }
        end = System.nanoTime();
        System.out.println("T(logn) - Average Running Time: " + (((end - start) / 500) / nanoSec) + " seconds");

        //*****PART B*****
        System.out.println("\n- PART B -");
        key = 5000;
        //start timer for n search
        start = System.nanoTime();
        for (int i = 0; i < 1; i++) {
            linearSearch(a, key);
        }
        end = System.nanoTime();
        double f1SingleStep = ((end - start) / nanoSec / n);
        System.out.println("T(n) - Worst Case Running Time (Key = 5000): " + ((end - start) / nanoSec) + " seconds"
                + "\n\t One Step: " + f1SingleStep + " seconds");
        //start timer for logn search
        start = System.nanoTime();
        for (int i = 0; i < 1; i++) {
            binarySearch(0, a.length - 1, key, a);
        }
        end = System.nanoTime();
        double f2SingleStep = ((end - start) / nanoSec / (Math.log(Math.pow(10, 7)) / Math.log(2)));
        System.out.println("T(logn) - Worst Case Running Time (Key = 5000): " + ((end - start) / nanoSec) + " seconds"
                + "\n\t One Step: " + f2SingleStep + " seconds");

        //estimate running time when n = 10^7
        System.out.println("\nEstimated Running Time When n = 10^7"
                + "\n\tT(n): " + (f1SingleStep * n + " seconds")
                + "\n\tT(logn): " + (f2SingleStep * (Math.log(Math.pow(10, 7)) / Math.log(2))) + " seconds");

        //run with n = 10^7, actual
        n = 10000000;
        System.out.println("\nn = 10,000,000");
        a = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = (randInt(-1000, 1000));

        }
        Arrays.sort(a);
        start = System.nanoTime();
        for (int i = 0; i < 1; i++) {
            linearSearch(a, key);
        }
        end = System.nanoTime();
        System.out.println("T(n) - Running Time (Key = 5000): " + ((end - start) / nanoSec) + " seconds");
        //start timer for logn search
        start = System.nanoTime();
        for (int i = 0; i < 1; i++) {
            binarySearch(0, a.length - 1, key, a);
        }
        end = System.nanoTime();
        System.out.println("T(logn) - Running Time (Key = 5000): " + ((end - start) / nanoSec) + " seconds");


    }

    public static boolean linearSearch(int[] arry, int key){
        int[] a = arry;
        for(int i = 0; i < a.length; i++){
            if(a[i] == key)
                return true;
        }
        return false;
    }

    public static boolean binarySearch(int start, int end, int key, int[] a){
        int mid = (end - start) / 2 + start;
        if (end - start > 0) {
            if (a[mid] == key)
                return true;
            if (a[mid] < key)
                return binarySearch(mid + 1, a.length - 1, key, a);
            if (a[mid] > key)
                return binarySearch( start, mid - 1, key, a);
        }
        return a[mid] == key;

    }

    public static int randInt(int min, int max){
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
