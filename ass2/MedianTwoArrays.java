package ass2;

public class MedianTwoArrays {
    static double findMedian(int[] A, int[] B) {
        int[] C = new int[A.length + B.length];
        int i = 0, j = 0, k = 0;
        while (i < A.length && j < B.length) {
            if (A[i] <= B[j])
                C[k++] = A[i++];
            else
                C[k++] = B[j++];
        }
        while (i < A.length) C[k++] = A[i++];
        while (j < B.length) C[k++] = B[j++];

        int n = C.length;
        if (n % 2 == 1)
            return C[n / 2];
        else
            return (C[n / 2 - 1] + C[n / 2]) / 2.0;
    }

    public static void main(String[] args) {
        int[][] A = {{1, 3}, {1, 2}, {}, {2, 4, 6}, {5}};
        int[][] B = {{2}, {3, 4}, {1}, {1, 3, 5}, {}};

        for (int i = 0; i < 5; i++) {
            System.out.println("Test " + (i + 1) + " Output: " + findMedian(A[i], B[i]));
        }
    }
}

