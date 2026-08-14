/**
 * Q6) Given an M*N matrix in which each row and each column is sorted in
 * ascending order, find whether a given element exists in the matrix
 * (and report its position).
 *
 * APPROACH (Staircase / Top-Right Corner Search):
 * Start at the TOP-RIGHT corner of the matrix. From this position, exactly
 * one useful comparison is possible each step:
 *
 *   - If current element == target        -> found it.
 *   - If current element  > target        -> the entire current COLUMN
 *       below is even larger (column sorted ascending), so it's useless;
 *       move one step LEFT (decrease column).
 *   - If current element  < target        -> the entire current ROW to
 *       the left is even smaller (row sorted ascending), so it's useless;
 *       move one step DOWN (increase row).
 *
 * Each comparison eliminates either a full row or a full column, so we
 * take at most (M + N) steps total before either finding the target or
 * falling off the matrix.
 *
 * (Starting from the top-left or bottom-right corner does NOT work,
 * because both directions from those corners increase/decrease together,
 * giving no way to decide which way to move.)
 *
 * TIME COMPLEXITY:  O(M + N)
 * SPACE COMPLEXITY: O(1)
 */
public class Q6_SearchSortedMatrix {

    /**
     * Searches for target in the matrix.
     * @return an int[] {row, col} if found, or null if not found.
     */
    public static int[] search(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return null;
        }

        int row = 0;
        int col = matrix[0].length - 1; // start at top-right corner

        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return new int[]{row, col};
            } else if (matrix[row][col] > target) {
                col--; // eliminate this column
            } else {
                row++; // eliminate this row
            }
        }

        return null; // not found
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,  4,  7,  11, 15},
                {2,  5,  8,  12, 19},
                {3,  6,  9,  16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        int target = 5;
        int[] result = search(matrix, target);

        if (result != null) {
            System.out.println("Found " + target + " at row " + result[0] + ", col " + result[1]);
        } else {
            System.out.println(target + " not found in matrix");
        }
        // Expected output: Found 5 at row 1, col 1
    }
}
