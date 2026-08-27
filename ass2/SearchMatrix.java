package ass2;

public class SearchMatrix {
    static boolean searchMatrix(int[][] matrix, int target) {
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target)
                return true;
            else if (matrix[row][col] > target)
                col--;
            else
                row++;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][][] matrices = {
            {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}},
            {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}},
            {{1}},
            {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
            {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}
        };
        int[] targets = {5, 10, 1, 4, 0};

        for (int i = 0; i < 5; i++) {
            boolean result = searchMatrix(matrices[i], targets[i]);
            System.out.println("Test " + (i + 1) + " Output: " + result);
        }
    }
}

