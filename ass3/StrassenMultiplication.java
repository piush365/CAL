package ass3;

public class StrassenMultiplication {
    static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }

    static int[][] sub(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }

    static int[][] strassen(int[][] A, int[][] B) {
        int n = A.length;
        if (n == 1) {
            int[][] C = new int[1][1];
            C[0][0] = A[0][0] * B[0][0];
            return C;
        }

        int half = n / 2;
        int[][] A11 = new int[half][half], A12 = new int[half][half];
        int[][] A21 = new int[half][half], A22 = new int[half][half];
        int[][] B11 = new int[half][half], B12 = new int[half][half];
        int[][] B21 = new int[half][half], B22 = new int[half][half];

        for (int i = 0; i < half; i++) {
            for (int j = 0; j < half; j++) {
                A11[i][j] = A[i][j];
                A12[i][j] = A[i][j + half];
                A21[i][j] = A[i + half][j];
                A22[i][j] = A[i + half][j + half];
                B11[i][j] = B[i][j];
                B12[i][j] = B[i][j + half];
                B21[i][j] = B[i + half][j];
                B22[i][j] = B[i + half][j + half];
            }
        }

        int[][] M1 = strassen(add(A11, A22), add(B11, B22));
        int[][] M2 = strassen(add(A21, A22), B11);
        int[][] M3 = strassen(A11, sub(B12, B22));
        int[][] M4 = strassen(A22, sub(B21, B11));
        int[][] M5 = strassen(add(A11, A12), B22);
        int[][] M6 = strassen(sub(A21, A11), add(B11, B12));
        int[][] M7 = strassen(sub(A12, A22), add(B21, B22));

        int[][] C11 = add(sub(add(M1, M4), M5), M7);
        int[][] C12 = add(M3, M5);
        int[][] C21 = add(M2, M4);
        int[][] C22 = add(sub(add(M1, M3), M2), M6);

        int[][] C = new int[n][n];
        for (int i = 0; i < half; i++) {
            for (int j = 0; j < half; j++) {
                C[i][j] = C11[i][j];
                C[i][j + half] = C12[i][j];
                C[i + half][j] = C21[i][j];
                C[i + half][j + half] = C22[i][j];
            }
        }
        return C;
    }

    static void printMatrix(int[][] M) {
        for (int[] row : M) {
            StringBuilder sb = new StringBuilder();
            for (int val : row) sb.append(val).append(" ");
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) {
        int[][][][] tests = {
            { {{1,2},{3,4}}, {{5,6},{7,8}} },
            { {{1,0},{0,1}}, {{2,3},{4,5}} },
            { {{1,1,1,1},{2,2,2,2},{3,3,3,3},{4,4,4,4}}, {{1,0,0,0},{0,1,0,0},{0,0,1,0},{0,0,0,1}} },
            { {{0,0},{0,0}}, {{1,2},{3,4}} },
            { {{2,0},{0,2}}, {{3,4},{5,6}} }
        };

        for (int t = 0; t < tests.length; t++) {
            int[][] result = strassen(tests[t][0], tests[t][1]);
            System.out.println("Test " + (t + 1) + " Output:");
            printMatrix(result);
            System.out.println();
        }
    }
}

