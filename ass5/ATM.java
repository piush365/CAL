package ass5;

import java.util.*;

public class ATM {
    static void minimumNotes(int[] denominations, int amount) {
        Arrays.sort(denominations);

        int count = 0;
        List<Integer> notes = new ArrayList<>();

        for (int i = denominations.length - 1; i >= 0; i--) {
            while (amount >= denominations[i]) {
                amount -= denominations[i];
                notes.add(denominations[i]);
                count++;
            }
        }

        if (amount != 0)
            System.out.println("Exact amount cannot be formed.");
        else {
            System.out.println("Selected notes = " + notes);
            System.out.println("Minimum notes using greedy = " + count);
        }
    }

    public static void main(String[] args) {
        int[] denominations = {1, 2, 5, 10, 20, 50, 100, 200, 500};
        minimumNotes(denominations, 1287);
    }
}
