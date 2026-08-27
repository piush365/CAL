package ass2;

public class CandyInBoxes {
    static int findBox(int N, int K) {
        int box = 1, step = 1;
        for (int i = 1; i <= K; i++) {
            if (i == K)
                return box;
            box += step;
            if (box == N || box == 1)
                step = -step;
        }
        return box;
    }

    public static void main(String[] args) {
        int[] N = {3, 3, 5, 4, 2};
        int[] K = {1, 5, 7, 10, 6};

        for (int i = 0; i < 5; i++) {
            System.out.println("Test " + (i + 1) + " Output: " + findBox(N[i], K[i]));
        }
    }
}
