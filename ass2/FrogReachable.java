package ass2;

public class FrogReachable {
    static int countReachable(int X, int Y, int s, int T) {
        int count = 0;
        for (int x = X; x <= X + s; x++) {
            for (int y = Y; y <= Y + s; y++) {
                if (x >= 0 && y >= 0 && (x + y) <= T)
                    count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] X = {0, 0, 1, 0, 2};
        int[] Y = {0, 0, 1, 0, 3};
        int[] s = {1, 2, 1, 0, 2};
        int[] T = {1, 2, 1, 5, 10};

        for (int i = 0; i < 5; i++) {
            System.out.println("Test " + (i + 1) + " Output: " + countReachable(X[i], Y[i], s[i], T[i]));
        }
    }
}

