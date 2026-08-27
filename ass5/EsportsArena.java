package ass5;

import java.util.*;

public class EsportsArena {
    static class Match {
        int start, end;
        Match(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public String toString() {
            return "(" + start + "," + end + ")";
        }
    }

    static List<Match> activitySelection(Match[] matches) {
        Arrays.sort(matches, Comparator.comparingInt(m -> m.end));

        List<Match> selected = new ArrayList<>();
        int lastEnd = -1;

        for (Match m : matches) {
            if (m.start >= lastEnd) {
                selected.add(m);
                lastEnd = m.end;
            }
        }
        return selected;
    }

    public static void main(String[] args) {
        Match[] matches = {
            new Match(1,3), new Match(2,5), new Match(0,6),
            new Match(5,8), new Match(4,9), new Match(6,10),
            new Match(8,11), new Match(9,13), new Match(11,14),
            new Match(2,15), new Match(13,17), new Match(12,16),
            new Match(15,18), new Match(17,19)
        };

        List<Match> result = activitySelection(matches);
        System.out.println("Selected matches: " + result);
        System.out.println("Maximum number of matches = " + result.size());
    }
}

