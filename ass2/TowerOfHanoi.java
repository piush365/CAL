package ass2;

public class TowerOfHanoi {
    static int moveCount = 0;

    static void towerOfHanoi(int n, char from, char aux, char to) {
        if (n == 0)
            return;
        towerOfHanoi(n - 1, from, to, aux);
        System.out.println("Move disk " + n + " from " + from + " to " + to);
        moveCount++;
        towerOfHanoi(n - 1, aux, from, to);
    }

    public static void main(String[] args) {
        for (int n = 1; n <= 5; n++) {
            moveCount = 0;
            System.out.println("----- n = " + n + " -----");
            towerOfHanoi(n, 'A', 'B', 'C');
            System.out.println("Total moves: " + moveCount + "\n");
        }
    }
}

