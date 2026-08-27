package ass3;

public class BitonicMax {
    static int findMaxBitonic(int[] arr) {
        int low = 0, high = arr.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] < arr[mid + 1])
                low = mid + 1;
            else
                high = mid;
        }
        return arr[low];
    }

    public static void main(String[] args) {
        int[][] tests = {
            {1, 3, 8, 12, 4, 2},
            {1, 2, 3, 4, 5},
            {5, 4, 3, 2, 1},
            {1, 3, 5, 4, 2},
            {1, 2, 3, 1}
        };

        for (int i = 0; i < tests.length; i++) {
            System.out.println("Test " + (i + 1) + " Output: " + findMaxBitonic(tests[i]));
        }
    }
}
