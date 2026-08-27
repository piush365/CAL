package ass1;
import java.util.Arrays;
public class MergeSortedArrays {
    public static void merge(int[] A, int m, int[] B, int n) {
        int i = m - 1;      // Last element in A
        int j = n - 1;      // Last element in B
        int k = m + n - 1;  // Last position in A
        while (i >= 0 && j >= 0) {
            if (A[i] > B[j]) {
                A[k] = A[i];
                i--;
            } else {
                A[k] = B[j];
                j--;
            }
            k--;
        }
        while (j >= 0) {
            A[k] = B[j];
            j--;
            k--;
        }
    }
    public static void main(String[] args) {
        int[] A = {1, 3, 5, 7, 0, 0, 0};
        int[] B = {2, 4, 6};
        int m = 4; // Number of valid elements in A
        int n = 3; // Number of elements in 
        System.out.println("Array A before merge: " + Arrays.toString(A));
        System.out.println("Array B: " + Arrays.toString(B));

        merge(A, m, B, n);
        System.out.println("Merged Array: " + Arrays.toString(A));
    }
}
