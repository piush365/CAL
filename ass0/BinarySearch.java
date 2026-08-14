package ass0;

public class BinarySearch {
  public static int binarySearch(int[] arr, int target, int l, int r) {
    if(l <= r) {
      int mid = l + (r - l) / 2;
      if(mid == target) {
        return mid;
      } else if(mid < target) {
        return binarySearch(arr, target, mid+1, r);
      } else {
        return binarySearch(arr, target, l, mid-1);
      }
    }
    return -1;
  }
  public static void main(String[] args) {
    int[] arr = {1,2,3,4,5,6,7,8,9,10};
    int target = 5;
    int l = 0;
    int r = arr.length-1;

    System.out.println(binarySearch(arr, target, l, r));
  }
 }
