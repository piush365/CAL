/**
 * Q3) Given a sorted array of n integers that has been rotated an unknown
 * number of times, find a target element's index.
 *
 * EXAMPLE:
 *   Input: find 5 in {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14}
 *   Output: 8
 *
 * APPROACH (Modified Binary Search):
 * In a rotated sorted array, when we look at the midpoint, AT LEAST one
 * of the two halves (left half or right half) is guaranteed to still be
 * in normal sorted order. We can use that fact to decide which half to
 * recurse/iterate into, exactly like standard binary search:
 *
 *   1. Find mid = (low + high) / 2.
 *   2. If arr[mid] == target, done.
 *   3. Check if the LEFT half (low..mid) is sorted:
 *        - If target lies within that sorted range, search left half.
 *        - Otherwise search right half.
 *   4. Else the RIGHT half (mid..high) must be sorted:
 *        - If target lies within that sorted range, search right half.
 *        - Otherwise search left half.
 *
 * This still discards half the search space each iteration, so it
 * retains binary search's logarithmic performance despite the rotation.
 *
 * TIME COMPLEXITY:  O(log n)
 * SPACE COMPLEXITY: O(1) (iterative version)
 */
public class Q3_SearchRotatedArray {

    public static int search(int[] arr, int target) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                return mid;
            }

            // Left half [low..mid] is sorted normally
            if (arr[low] <= arr[mid]) {
                if (target >= arr[low] && target < arr[mid]) {
                    high = mid - 1; // target is in the sorted left half
                } else {
                    low = mid + 1;  // target must be in the right half
                }
            }
            // Otherwise, right half [mid..high] is sorted normally
            else {
                if (target > arr[mid] && target <= arr[high]) {
                    low = mid + 1;  // target is in the sorted right half
                } else {
                    high = mid - 1; // target must be in the left half
                }
            }
        }

        return -1; // not found
    }

    public static void main(String[] args) {
        int[] arr = {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
        int target = 5;

        int result = search(arr, target);
        System.out.println("Index of " + target + " is: " + result);
        // Expected output: 8
    }
}
