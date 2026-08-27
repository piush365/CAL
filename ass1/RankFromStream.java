package ass1;

import java.util.ArrayList;
public class RankFromStream {
    static ArrayList<Integer> stream = new ArrayList<>();
    // Store each number in the stream
    public static void track(int x) {
        stream.add(x);
    }
    // Return count of numbers less than or equal to x (excluding x itself)
    public static int getRankOfNumber(int x) {
        int count = 0;
        for (int num : stream) {
            if (num < x) {
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        int[] input = {5, 1, 4, 4, 5, 9, 7, 13, 3};
        for (int num : input) {
            track(num);
        }
        System.out.println("Rank of 1 = " + getRankOfNumber(1));
        System.out.println("Rank of 3 = " + getRankOfNumber(3));
        System.out.println("Rank of 4 = " + getRankOfNumber(4));
    }
}

