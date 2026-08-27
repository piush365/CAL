package ass2;

public class SignalDropDetector {
    static int countSignalDrops(int[] signal) {
        int drops = 0;
        int runLen = 1;
        for (int i = 1; i < signal.length; i++) {
            if (signal[i] < signal[i - 1]) {
                runLen++;
            } else {
                if (runLen >= 3)
                    drops++;
                runLen = 1;
            }
        }
        if (runLen >= 3)
            drops++;
        return drops;
    }

    public static void main(String[] args) {
        int[][] tests = {
            {5, 4, 3, 6, 7, 4, 3, 2},
            {1, 2, 3, 4},
            {9, 8, 7, 6, 5},
            {10, 9, 8, 10, 9, 8, 7},
            {3, 2, 1, 1, 1}
        };

        for (int i = 0; i < tests.length; i++) {
            System.out.println("Test " + (i + 1) + " Output: " + countSignalDrops(tests[i]));
        }
    }
}

