import java.util.*;

/**
 * Q2) Sort an array of strings so that all the anagrams are next to each other.
 *
 * APPROACH:
 * Two strings are anagrams of each other if and only if their characters,
 * when sorted, produce the identical string. So we can use this "sorted
 * character signature" as a grouping key:
 *
 *   1. For every string, compute its sorted-character key
 *      (e.g. "eat" -> "aet", "tea" -> "aet", "tan" -> "ant").
 *   2. Group all strings sharing the same key using a HashMap<String, List<String>>.
 *   3. Concatenate all the groups back into a single array/list.
 *
 * This guarantees anagrams end up adjacent without needing a full
 * string-to-string comparison sort.
 *
 * TIME COMPLEXITY:  O(n * k log k)
 *      n = number of strings, k = average length of a string
 *      (k log k) comes from sorting the characters of each string
 * SPACE COMPLEXITY: O(n * k) for the hashmap and grouped output
 */
public class Q2_GroupAnagrams {

    /**
     * Returns a helper "signature" for a string: its characters sorted
     * alphabetically. Anagrams always share the same signature.
     */
    private static String getSignature(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static void sortByAnagram(String[] arr) {
        // Map from sorted-character signature -> list of original strings sharing it
        Map<String, List<String>> groups = new HashMap<>();

        for (String s : arr) {
            String key = getSignature(s);
            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }

        // Flatten the grouped map back into the original array (anagrams adjacent)
        int index = 0;
        for (List<String> group : groups.values()) {
            for (String s : group) {
                arr[index++] = s;
            }
        }
    }

    public static void main(String[] args) {
        String[] words = {"eat", "tea", "tan", "ate", "nat", "bat"};

        sortByAnagram(words);

        System.out.print("Grouped anagrams: ");
        System.out.println(Arrays.toString(words));
        // Example expected grouping (order of groups may vary since HashMap
        // does not guarantee insertion order):
        // [eat, tea, ate, tan, nat, bat]
    }
}
