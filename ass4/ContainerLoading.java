package ass4;

import java.util.*;

public class ContainerLoading {
    static int loadBags(int[] weights, int capacity) {
        Arrays.sort(weights);
        int total = 0, count = 0;

        for (int weight : weights) {
            if (total + weight <= capacity) {
                total += weight;
                count++;
            } else {
                break;
            }
        }
        System.out.println("Total weight loaded: " + total + " kg");
        return count;
    }

    public static void main(String[] args) {
        int[][] tests = {
            {22, 18, 40, 15, 60},
            {33, 27, 50, 12, 45},
            {38, 29, 55, 19, 42},
            {31, 24, 48, 36, 20},
            {58, 16, 47, 25, 34, 41, 13, 52, 28, 37}
        };

        for (int i = 0; i < tests.length; i++) {
            int[] weights = tests[i];
            int count = loadBags(weights, 3000);
            System.out.println("Test " + (i + 1) +
                               " Output (bags loaded): " + count);
        }
    }
}
