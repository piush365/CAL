/**
 * Q5) Given a sorted array of strings interspersed with empty strings,
 * find the location of a given string.
 *
 * EXAMPLE:
 *   Input: find "ball" in
 *     {"at", "", "", "ball", "", "", "car", "", "", "dad", "", ""}
 *   Output: 4
 *
 * APPROACH (Modified Binary Search):
 * The array is still sorted overall, just with empty strings "" sprinkled
 * in between the real values. A straight binary search breaks the moment
 * arr[mid] is "", because we can't compare "" against the target.
 *
 * Fix: whenever arr[mid] is empty, look outward (left and right) from mid
 * for the closest non-empty string, and treat THAT as the new mid. If we
 * reach low without finding a non-empty string, the target isn't in the
 * left region we were about to search — fall back to searching the right
 * half instead (and vice versa).
 *
 * TIME COMPLEXITY:
 *   Average case: O(log n), same as standard binary search.
 *   Worst case: O(n) if the array is mostly empty strings (e.g.,
 *   {"a", "", "", "", "", "", "", "", ""}), since we may need to scan
 *   past many blanks to find a comparable midpoint.
 * SPACE COMPLEXITY: O(1) iterative, O(log n) if implemented recursively
 *   (due to call stack).
 */
public class Q5_SparseSearch {

    public static int search(String[] arr, String target, int low, int high) {
        if (low > high) {
            return -1;
        }

        int mid = low + (high - low) / 2;

        // If arr[mid] is empty, find the nearest non-empty string
        if (arr[mid].isEmpty()) {
            int left = mid - 1;
            int right = mid + 1;

            while (true) {
                if (left < low && right > high) {
                    return -1; // no non-empty strings in this range at all
                } else if (right <= high && !arr[right].isEmpty()) {
                    mid = right;
                    break;
                } else if (left >= low && !arr[left].isEmpty()) {
                    mid = left;
                    break;
                }
                right++;
                left--;
            }
        }

        int cmp = arr[mid].compareTo(target);
        if (cmp == 0) {
            return mid; // found it
        } else if (cmp < 0) {
            return search(arr, target, mid + 1, high); // search right half
        } else {
            return search(arr, target, low, mid - 1);  // search left half
        }
    }

    public static void main(String[] args) {
        String[] arr = {"at", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        String target = "ball";

        int result = search(arr, target, 0, arr.length - 1);
        System.out.println("Index of \"" + target + "\" is: " + result);
        // NOTE: for the array exactly as given in the assignment, "ball" is
        // truly at index 3 (0-indexed). The assignment statement lists the
        // expected output as 4, but that would only be correct if there
        // were one more "" before "ball" in the input array. The algorithm
        // above is verified correct against the array as written.
    }
}
