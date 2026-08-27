package ass1;

import java.util.Arrays;
public class SearchRotatedArray {
    public static int search(int[] arr, int low, int high, int target) {
        if (low > high)
            return -1;
        int mid = (low + high) / 2;
        if (arr[mid] == target)
            return mid;
        if (arr[low] <= arr[mid]) {
            if (target >= arr[low] && target < arr[mid])
                return search(arr, low, mid - 1, target);
            else
                return search(arr, mid + 1, high, target);
        }
        if (target > arr[mid] && target <= arr[high])
            return search(arr, mid + 1, high, target);
        return search(arr, low, mid - 1, target);
    }
    public static void main(String[] args) {
        int[] arr = {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
        int target = 5;
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Index of " + target + ": " + search(arr, 0, arr.length - 1, target));
    }
}
