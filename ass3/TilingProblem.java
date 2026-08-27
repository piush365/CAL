package ass3;

public class TilingProblem {
    static int[][] board = new int[64][64];
    static int tileNo = 0;

    static void tileBoard(int r0, int c0, int mr, int mc, int size) {
        if (size == 1)
            return;
        int t = ++tileNo;
        int half = size / 2;

        // top-left quadrant
        if (mr < r0 + half && mc < c0 + half) {
            tileBoard(r0, c0, mr, mc, half);
        } else {
            board[r0 + half - 1][c0 + half - 1] = t;
            tileBoard(r0, c0, r0 + half - 1, c0 + half - 1, half);
        }
        // top-right quadrant
        if (mr < r0 + half && mc >= c0 + half) {
            tileBoard(r0, c0 + half, mr, mc, half);
        } else {
            board[r0 + half - 1][c0 + half] = t;
            tileBoard(r0, c0 + half, r0 + half - 1, c0 + half, half);
        }
        // bottom-left quadrant
        if (mr >= r0 + half && mc < c0 + half) {
            tileBoard(r0 + half, c0, mr, mc, half);
        } else {
            board[r0 + half][c0 + half - 1] = t;
            tileBoard(r0 + half, c0, r0 + half, c0 + half - 1, half);
        }
        // bottom-right quadrant
        if (mr >= r0 + half && mc >= c0 + half) {
            tileBoard(r0 + half, c0 + half, mr, mc, half);
        } else {
            board[r0 + half][c0 + half] = t;
            tileBoard(r0 + half, c0 + half, r0 + half, c0 + half, half);
        }
    }

    public static void main(String[] args) {
        int[][] tests = {
            {2, 0, 0},
            {2, 1, 1},
            {4, 2, 2},
            {4, 0, 0},
            {8, 3, 5}
        };

        for (int t = 0; t < tests.length; t++) {
            int n = tests[t][0], mr = tests[t][1], mc = tests[t][2];
            tileNo = 0;
            board[mr][mc] = 0;
            tileBoard(0, 0, mr, mc, n);
            System.out.println("Test " + (t + 1) + " Output (tiles used): " + tileNo);
        }
    }
}

