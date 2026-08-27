package ass2;

public class LostPackageTracker {
    static int firstMissingTimestamp(int[] timestamps) {
        for (int i = 1; i < timestamps.length; i++) {
            if (timestamps[i] - timestamps[i - 1] > 1) {
                return timestamps[i - 1] + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] tests = {
            {1001, 1002, 1004, 1005},
            {10, 11, 12, 13},
            {5, 7},
            {100, 100, 101, 103},
            {1, 2, 2, 3, 5}
        };

        for (int i = 0; i < tests.length; i++) {
            System.out.println("Test " + (i + 1) + " Output: " + firstMissingTimestamp(tests[i]));
        }
    }
}

