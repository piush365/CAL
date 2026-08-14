/**
 * Q8) Streaming Rank Tracker
 *
 * As a stream of integers is read in, support:
 *   track(x)          -> record that value x has just appeared
 *   getRankOfNumber(x) -> return the count of values seen SO FAR that are
 *                          less than or equal to x, NOT including x itself
 *                          (per the assignment's definition/example).
 *
 * EXAMPLE:
 *   Stream: 5, 1, 4, 4, 5, 9, 7, 13, 3
 *   getRankOfNumber(1) = 0
 *   getRankOfNumber(3) = 1
 *   getRankOfNumber(4) = 3
 *
 * NOTE ON THE DEFINITION: "not including x itself" means that if x has
 * been tracked before, exactly ONE occurrence of x is excluded from the
 * count (other duplicates of x still count). E.g. after tracking the
 * full stream above, values <= 4 are {1, 4, 4, 3} (count = 4); excluding
 * one occurrence of the queried value 4 gives 4 - 1 = 3, which matches
 * the expected output. So:
 *     rank(x) = (count of tracked values <= x) - (1 if x was tracked, else 0)
 *
 * APPROACH: Fenwick Tree / Binary Indexed Tree (BIT)
 * A naive approach (insert into a sorted structure and count on each
 * query by scanning) is too slow for frequent queries on a live stream.
 *
 * Instead we use a Binary Indexed Tree, which supports:
 *   - track(x):            O(log MAX_VALUE)  — increment frequency at x
 *   - prefixCount(x):      O(log MAX_VALUE)  — count of all tracked
 *                                                values <= x
 *
 * The BIT is indexed by VALUE (1-indexed, offset by +1 to support 0 and
 * negative-safe indexing), and each cell stores how many times that
 * value has been tracked. getRankOfNumber(x) combines two prefix
 * queries (for <=x and <x) to isolate whether x itself was tracked, and
 * subtracts one occurrence of x accordingly.
 *
 * NOTE: This implementation assumes values fit within a bounded range
 * [0, MAX_VALUE]. For truly unbounded streams, use coordinate
 * compression or a self-balancing BST (e.g., an order-statistics /
 * augmented AVL tree) with subtree-size counters instead — same
 * O(log n) complexity, but no fixed range requirement.
 *
 * TIME COMPLEXITY:  O(log MAX_VALUE) per track() and per getRankOfNumber()
 * SPACE COMPLEXITY: O(MAX_VALUE)
 */
public class Q8_StreamRank {

    private final int[] bit;      // Binary Indexed Tree array
    private final int maxValue;   // largest value this tracker supports

    public Q8_StreamRank(int maxValue) {
        this.maxValue = maxValue;
        this.bit = new int[maxValue + 2]; // 1-indexed, +1 buffer
    }

    /** Records that value x has appeared in the stream. */
    public void track(int x) {
        int i = x + 1; // shift by 1 so BIT index is always >= 1
        while (i <= maxValue + 1) {
            bit[i]++;
            i += (i & (-i)); // move to next responsible node
        }
    }

    /** Helper: returns count of all tracked values <= x. */
    private int prefixCount(int x) {
        int i = x + 1; // shift by 1, matching track()'s indexing
        int sum = 0;
        while (i > 0) {
            sum += bit[i];
            i -= (i & (-i)); // move to parent node
        }
        return sum;
    }

    /**
     * Returns the rank of x: the count of tracked values <= x, excluding
     * one occurrence of x itself if x has been tracked (see class-level
     * doc comment for why, based on the assignment's example).
     */
    public int getRankOfNumber(int x) {
        int countLessEqual = prefixCount(x);
        int countLess = (x > 0) ? prefixCount(x - 1) : 0;
        boolean xWasTracked = (countLessEqual - countLess) > 0;
        return xWasTracked ? countLessEqual - 1 : countLessEqual;
    }

    public static void main(String[] args) {
        // Values in the example range from 1 to 13, use a safe upper bound
        Q8_StreamRank tracker = new Q8_StreamRank(100);

        int[] stream = {5, 1, 4, 4, 5, 9, 7, 13, 3};
        for (int val : stream) {
            tracker.track(val);
        }

        System.out.println("getRankOfNumber(1) = " + tracker.getRankOfNumber(1)); // 0
        System.out.println("getRankOfNumber(3) = " + tracker.getRankOfNumber(3)); // 1
        System.out.println("getRankOfNumber(4) = " + tracker.getRankOfNumber(4)); // 3
    }
}
