package ass5;

import java.util.*;

public class Marketing {
    static void setCover(Map<String, Set<String>> channels,
                         Set<String> universe) {

        Set<String> covered = new HashSet<>();
        Set<String> remaining = new HashSet<>(channels.keySet());
        List<String> selected = new ArrayList<>();

        while (!covered.containsAll(universe)) {
            String best = null;
            Set<String> bestNew = new HashSet<>();

            for (String ch : remaining) {
                Set<String> newlyCovered = new HashSet<>(channels.get(ch));
                newlyCovered.removeAll(covered);

                if (newlyCovered.size() > bestNew.size()) {
                    bestNew = newlyCovered;
                    best = ch;
                }
            }

            if (best == null)
                break;

            selected.add(best);
            covered.addAll(channels.get(best));
            remaining.remove(best);
        }

        System.out.println("Selected channels = " + selected);
        System.out.println("Covered segments = " + covered);
        System.out.println("Number of channels = " + selected.size());
    }

    public static void main(String[] args) {
        Map<String, Set<String>> c = new LinkedHashMap<>();

        c.put("Channel1", new HashSet<>(Arrays.asList("D1","D2","D5","D9")));
        c.put("Channel2", new HashSet<>(Arrays.asList("D2","D3","D4")));
        c.put("Channel3", new HashSet<>(Arrays.asList("D4","D5","D6","D7")));
        c.put("Channel4", new HashSet<>(Arrays.asList("D8","D9","D10","D11")));
        c.put("Channel5", new HashSet<>(Arrays.asList("D10","D12","D13","D14","D15")));
        c.put("Channel6", new HashSet<>(Arrays.asList("D1","D6","D7","D8")));
        c.put("Channel7", new HashSet<>(Arrays.asList("D3","D11","D12")));
        c.put("Channel8", new HashSet<>(Arrays.asList("D13","D14","D15")));

        Set<String> universe = new LinkedHashSet<>();
        for (int i = 1; i <= 15; i++)
            universe.add("D" + i);

        setCover(c, universe);
    }
}
