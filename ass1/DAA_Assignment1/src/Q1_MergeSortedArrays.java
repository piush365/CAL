/**
 * Q1) Merge sorted array B into sorted array A.    
 *
 * A has a large enough buffer at the end (extra empty slots, represented
 * here as any placeholder value beyond A's "real" length m) to hold all
 * of B's elements. After merging, A should contain all elements of A and
 * B in sorted order.
 *
 * APPROACH:
 * A naive approach would insert each element of B into A and shift
 * elements — that is O((n+m)^2) in the worst case because every insertion
 * shifts a chunk of the array.
 *
 * The efficient approach fills A from the BACK instead of the front.
 * Since the last (n) slots of A are empty buffer space, we compare the
 * largest remaining elements of A and B and place the larger one at the
 * end of A, moving inward. This avoids any shifting and merges in a
 * single backward pass.
 *
 * TIME COMPLEXITY:  O(n + m)   where m = size of A's real data, n = size of B
 * SPACE COMPLEXITY: O(1) extra space (merge happens in-place inside A)
 */
public class Q1_MergeSortedArrays {

    /**
     * Merges array B into array A in-place.
     *
     * @param A     array with real data in indices [0, m-1] and empty
     *              buffer space in indices [m, m+n-1]
     * @param m     number of actual (valid) elements in A
     * @param B     second sorted array to merge into A
     * @param n     number of elements in B
     */
    public static void merge(int[] A, int m, int[] B, int n) {
        int indexA = m - 1;      // last real element in A
        int indexB = n - 1;      // last element in B
        int indexMerged = m + n - 1; // last slot of the merged array (end of buffer)

        // Walk backwards, placing the larger of the two current elements
        // into the last available slot of A.
        while (indexB >= 0) {
            if (indexA >= 0 && A[indexA] > B[indexB]) {
                A[indexMerged] = A[indexA];
                indexA--;
            } else {
                A[indexMerged] = B[indexB];
                indexB--;
            }
            indexMerged--;
        }
        // If any elements remain in A, they are already in place
        // (since A was sorted and we only moved elements we compared).
    }

    public static void main(String[] args) {
        // A has real data {1, 3, 5, 7} followed by empty buffer slots (0 used as placeholder)
        int[] A = {1, 3, 5, 7, 0, 0, 0, 0};
        int m = 4;
        int[] B = {2, 4, 6, 8};
        int n = 4;

        merge(A, m, B, n);

        System.out.print("Merged array: ");
        for (int val : A) {
            System.out.print(val + " ");
        }
        System.out.println();
        // Expected output: 1 2 3 4 5 6 7 8
    }
}
