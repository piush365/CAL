package ass1;

public class SparseSearch {
    public static int search(String[] arr, String target) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            // Move to the nearest non-empty string
            if (arr[mid].equals("")) {
                int left = mid - 1;
                int right = mid + 1;
                while (true) {
                    if (left < low && right > high)
                        return -1;
                    else if (right <= high && !arr[right].equals("")) {
                        mid = right;
                        break;
                    } else if (left >= low && !arr[left].equals("")) {
                        mid = left;
                        break;
                    }
                    right++;
                    left--;
                }
            }
            if (arr[mid].equals(target))
                return mid;
            else if (arr[mid].compareTo(target) < 0)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }
    public static void main(String[] args) {
        String[] arr = {"at", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        String target = "ball";
        int index = search(arr, target);
        System.out.println("Index of \"" + target + "\" is: " + index);
    }
}

