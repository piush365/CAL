package ass1;

import java.util.*;
public class GroupAnagrams {
    public static void sortAnagrams(String[] arr) {
        Arrays.sort(arr, new Comparator<String>() {
            public int compare(String s1, String s2) {
                char[] c1 = s1.toCharArray();
                char[] c2 = s2.toCharArray();
                Arrays.sort(c1);
                Arrays.sort(c2);
                return new String(c1).compareTo(new String(c2));
            }
        });
    }
    public static void main(String[] args) {
        String[] words = {"cat", "dog", "tac", "god", "act", "odg"};
        System.out.println("Before Sorting: " + Arrays.toString(words));
        sortAnagrams(words);
        System.out.println("After Sorting:  " + Arrays.toString(words));
    }
}

