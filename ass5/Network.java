package ass5;

import java.util.*;

public class Network {
    static class Edge {
        String to;
        int weight;
        Edge(String to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Node {
        String name;
        int distance;
        Node(String name, int distance) {
            this.name = name;
            this.distance = distance;
        }
    }

    static void dijkstra(Map<String, List<Edge>> graph, String source) {
        Map<String, Integer> dist = new LinkedHashMap<>();
        Map<String, String> parent = new HashMap<>();

        for (String v : graph.keySet()) {
            dist.put(v, Integer.MAX_VALUE);
            parent.put(v, null);
        }
        dist.put(source, 0);

        PriorityQueue<Node> pq =
            new PriorityQueue<>(Comparator.comparingInt(n -> n.distance));
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            String u = current.name;

            if (current.distance != dist.get(u))
                continue;

            for (Edge e : graph.getOrDefault(u, new ArrayList<>())) {
                int newDist = dist.get(u) + e.weight;

                if (newDist < dist.get(e.to)) {
                    dist.put(e.to, newDist);
                    parent.put(e.to, u);
                    pq.add(new Node(e.to, newDist));
                }
            }
        }

        System.out.println("Shortest distances from " + source + ":");
        for (String v : dist.keySet())
            System.out.println(v + " = " + dist.get(v));

        String target = "H";
        if (dist.containsKey(target) && dist.get(target) != Integer.MAX_VALUE) {
            List<String> path = new ArrayList<>();

            for (String at = target; at != null; at = parent.get(at))
                path.add(at);
            Collections.reverse(path);
            System.out.println("Shortest path to H = " + path);
            System.out.println("Delay to H = " + dist.get(target));
        }
    }

    public static void main(String[] args) {
        Map<String, List<Edge>> g = new LinkedHashMap<>();

        add(g, "S", "A", 4);  add(g, "S", "B", 8);
        add(g, "A", "B", 2);  add(g, "A", "C", 5);
        add(g, "B", "C", 1);  add(g, "B", "D", 7);
        add(g, "C", "D", 3);  add(g, "C", "E", 9);
        add(g, "D", "E", 2);  add(g, "D", "F", 6);
        add(g, "E", "F", 1);  add(g, "E", "G", 8);
        add(g, "F", "G", 3);  add(g, "F", "H", 5);
        add(g, "G", "H", 2);

        dijkstra(g, "S");
    }

    static void add(Map<String, List<Edge>> g, String u, String v, int w) {
        g.putIfAbsent(u, new ArrayList<>());
        g.putIfAbsent(v, new ArrayList<>());
        g.get(u).add(new Edge(v, w));
    }
}
