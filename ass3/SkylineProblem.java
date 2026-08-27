package ass3;

import java.util.*;

public class SkylineProblem {
    static class Building {
        int left, height, right;
        Building(int l, int h, int r) { left = l; height = h; right = r; }
    }

    static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
    }

    static List<Point> mergeSkyline(List<Point> sky1, List<Point> sky2) {
        List<Point> result = new ArrayList<>();
        int i = 0, j = 0, h1 = 0, h2 = 0;
        while (i < sky1.size() && j < sky2.size()) {
            int x, h;
            if (sky1.get(i).x < sky2.get(j).x) {
                x = sky1.get(i).x; h1 = sky1.get(i).y; i++;
            } else if (sky2.get(j).x < sky1.get(i).x) {
                x = sky2.get(j).x; h2 = sky2.get(j).y; j++;
            } else {
                x = sky1.get(i).x; h1 = sky1.get(i).y; h2 = sky2.get(j).y; i++; j++;
            }
            h = Math.max(h1, h2);
            if (result.isEmpty() || result.get(result.size() - 1).y != h)
                result.add(new Point(x, h));
        }
        while (i < sky1.size()) result.add(sky1.get(i++));
        while (j < sky2.size()) result.add(sky2.get(j++));
        return result;
    }

    static List<Point> getSkyline(List<Building> b, int start, int end) {
        if (start == end) {
            List<Point> res = new ArrayList<>();
            res.add(new Point(b.get(start).left, b.get(start).height));
            res.add(new Point(b.get(start).right, 0));
            return res;
        }
        int mid = (start + end) / 2;
        List<Point> leftSky = getSkyline(b, start, mid);
        List<Point> rightSky = getSkyline(b, mid + 1, end);
        return mergeSkyline(leftSky, rightSky);
    }

    public static void main(String[] args) {
        List<List<Building>> tests = new ArrayList<>();

        tests.add(Arrays.asList(
            new Building(1,11,5), new Building(2,6,7), new Building(3,13,9),
            new Building(12,7,16), new Building(14,3,25), new Building(19,18,22),
            new Building(23,13,29), new Building(24,4,28)
        ));
        tests.add(Arrays.asList(new Building(1,5,4), new Building(2,8,6)));
        tests.add(Arrays.asList(new Building(0,3,2), new Building(2,5,4), new Building(4,2,6)));
        tests.add(Arrays.asList(new Building(1,10,3)));
        tests.add(Arrays.asList(new Building(1,4,3), new Building(2,4,5), new Building(5,4,7)));

        for (int t = 0; t < tests.size(); t++) {
            List<Building> buildings = tests.get(t);
            List<Point> result = getSkyline(buildings, 0, buildings.size() - 1);
            StringBuilder sb = new StringBuilder("Test " + (t + 1) + " Output: ");
            for (Point p : result)
                sb.append("(").append(p.x).append(",").append(p.y).append(") ");
            System.out.println(sb.toString());
        }
    }
}

