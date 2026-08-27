package ass4;

import java.util.*;

public class HuffmanCoding {
    static class Node implements Comparable<Node> {
        String name;
        int freq;
        Node left, right;

        Node(String name, int freq) {
            this.name = name;
            this.freq = freq;
        }

        Node(Node left, Node right) {
            this.name = "";
            this.freq = left.freq + right.freq;
            this.left = left;
            this.right = right;
        }

        public int compareTo(Node other) {
            return Integer.compare(this.freq, other.freq);
        }
    }

    static void generateCodes(Node root, String code,
                               Map<String, String> codes) {
        if (root.left == null && root.right == null) {
            codes.put(root.name, code.length() == 0 ? "0" : code);
            return;
        }

        generateCodes(root.left, code + "0", codes);
        generateCodes(root.right, code + "1", codes);
    }

    static Map<String, String> huffman(List<Node> nodes) {
        PriorityQueue<Node> pq = new PriorityQueue<>(nodes);

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            pq.add(new Node(left, right));
        }

        Map<String, String> codes = new LinkedHashMap<>();
        generateCodes(pq.poll(), "", codes);
        return codes;
    }

    public static void main(String[] args) {
        String[] names = {
            "Temperature", "Pressure", "Voltage", "Gyro", "Thruster",
            "AttitudeControl", "SolarPanel", "BatteryLevel",
            "Communication", "Radiation", "Magnetometer", "StarTracker",
            "ReactionWheel", "FuelLevel", "Antenna", "Camera", "GPS",
            "Accelerometer", "DataBus", "Timing"
        };

        int[] freq = {
            380, 560, 300, 150, 110,
            190, 240, 420,
            500, 70, 60, 200,
            170, 130, 90, 210, 85,
            65, 150, 100
        };

        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < names.length; i++)
            nodes.add(new Node(names[i], freq[i]));

        Map<String, String> codes = huffman(nodes);

        int totalFreq = 0, weightedLength = 0;

        for (int i = 0; i < names.length; i++) {
            String code = codes.get(names[i]);
            System.out.println(names[i] + " = " + code);

            totalFreq += freq[i];
            weightedLength += freq[i] * code.length();
        }

        double average = (double) weightedLength / totalFreq;
        System.out.printf("Average Code Length = %.2f bits%n", average);
    }
}

