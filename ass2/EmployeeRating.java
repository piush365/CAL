package ass2;

public class EmployeeRating {
    static int employeeRating(int[] workload) {
        int count = 0, maxCount = 0;
        for (int i = 0; i < workload.length; i++) {
            if (workload[i] > 6)
                count++;
            else
                count = 0;
            if (count > maxCount)
                maxCount = count;
        }
        return maxCount;
    }

    public static void main(String[] args) {
        int[][] tests = {
            {7, 8, 5, 9, 10, 3},
            {7, 7, 7, 7},
            {1, 2, 3, 4},
            {7, 2, 8, 9, 10, 11, 1},
            {6, 6, 6}
        };

        for (int i = 0; i < tests.length; i++) {
            System.out.println("Test " + (i + 1) + " Output: " + employeeRating(tests[i]));
        }
    }
}

