package ass4;

import java.util.*;

public class OptimalMergePattern {
    static int minMergeCost(int[] files) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int size : files)
            pq.add(size);

        int totalCost = 0;

        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            int merged = a + b;

            totalCost += merged;
            pq.add(merged);
        }

        return totalCost;
    }

    public static void main(String[] args) {
        int[][] tests = {
            {35, 110, 180, 8},
            {25, 12, 240, 85, 55},
            {95, 65, 30, 50, 80, 40},
            {480, 290, 140, 20, 65},
            {35, 110, 180, 8, 25, 12, 240, 85, 55}
        };

        for (int i = 0; i < tests.length; i++) {
            System.out.println("Test " + (i + 1) +
                               " Output (minimum merge cost): " +
                               minMergeCost(tests[i]) + " MB");
        }
    }
}
