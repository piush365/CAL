import java.util.*;

/**
 * Q7) Circus Tower Problem
 *
 * Each person in the tower must be strictly SHORTER and LIGHTER than the
 * person standing below them. Given each person's (height, weight), find
 * the largest possible number of people that can be stacked this way,
 * and print one valid ordering.
 *
 * EXAMPLE:
 *   Input: (65,100) (70,150) (56,90) (75,190) (60,95) (68,110)
 *   Output: length 6, top to bottom:
 *     (56,90) (60,95) (65,100) (68,110) (70,150) (75,190)
 *
 * APPROACH:
 * This is the classic "Longest Increasing Subsequence" (LIS) problem
 * extended to 2 dimensions (a "Longest Chain / Box Stacking" pattern):
 *
 *   1. Sort all people by height ascending. If two people share the same
 *      height, sort those ties by weight DESCENDING. (This tie-break
 *      trick prevents two people of equal height from being picked
 *      together into the same increasing chain, since the problem
 *      requires STRICTLY shorter/lighter.)
 *   2. After sorting by height, the height dimension is now handled.
 *      All that's left is to find the Longest Increasing Subsequence
 *      of WEIGHT within this height-sorted order (using the standard
 *      O(n log n) patience-sorting / binary-search LIS algorithm).
 *   3. Track parent pointers while computing the LIS so we can
 *      reconstruct and print the actual tower, not just its length.
 *
 * TIME COMPLEXITY:  O(n log n)   — O(n log n) to sort + O(n log n) for LIS
 * SPACE COMPLEXITY: O(n) for auxiliary arrays
 */
public class Q7_CircusTower {

    static class Person {
        int height, weight;
        Person(int h, int w) { height = h; weight = w; }
        public String toString() { return "(" + height + ", " + weight + ")"; }
    }

    public static List<Person> longestTower(Person[] people) {
        int n = people.length;

        // Step 1: sort by height ascending; ties broken by weight descending
        Arrays.sort(people, (a, b) ->
                a.height != b.height ? a.height - b.height : b.weight - a.weight);

        // Step 2: Longest Increasing Subsequence on weight, O(n log n)
        // tails[i]      = index (into people[]) of the smallest possible
        //                 tail-weight for an increasing subsequence of length i+1
        // tailsLen      = current length of the LIS found so far
        // predecessor[] = for reconstructing the actual sequence
        int[] tails = new int[n];
        int[] predecessor = new int[n];
        int tailsLen = 0;

        for (int i = 0; i < n; i++) {
            int weight = people[i].weight;

            // Binary search for the first tail >= weight (lower bound)
            int lo = 0, hi = tailsLen;
            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if (people[tails[mid]].weight < weight) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }

            predecessor[i] = (lo > 0) ? tails[lo - 1] : -1;
            tails[lo] = i;
            if (lo == tailsLen) {
                tailsLen++;
            }
        }

        // Step 3: reconstruct the tower by following predecessor pointers
        // backward from the end of the longest chain, then reverse it.
        LinkedList<Person> tower = new LinkedList<>();
        int k = tails[tailsLen - 1];
        while (k >= 0) {
            tower.addFirst(people[k]);
            k = predecessor[k];
        }

        return tower;
    }

    public static void main(String[] args) {
        Person[] people = {
                new Person(65, 100),
                new Person(70, 150),
                new Person(56, 90),
                new Person(75, 190),
                new Person(60, 95),
                new Person(68, 110)
        };

        List<Person> tower = longestTower(people);

        System.out.println("The longest tower is length " + tower.size() + " and includes from top to bottom:");
        for (Person p : tower) {
            System.out.print(p + " ");
        }
        System.out.println();
        // Expected output:
        // The longest tower is length 6 and includes from top to bottom:
        // (56, 90) (60, 95) (65, 100) (68, 110) (70, 150) (75, 190)
    }
}
