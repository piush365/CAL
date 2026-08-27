package ass3;

import java.util.*;

public class ClosestPair {
    static class Point {
        double x, y;
        Point(double x, double y) { this.x = x; this.y = y; }
    }

    static double dist(Point a, Point b) {
        return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }

    static double bruteForce(List<Point> pts, int left, int right) {
        double minDist = Double.MAX_VALUE;
        for (int i = left; i <= right; i++)
            for (int j = i + 1; j <= right; j++)
                minDist = Math.min(minDist, dist(pts.get(i), pts.get(j)));
        return minDist;
    }

    static double stripClosest(List<Point> strip, double d) {
        strip.sort((a, b) -> Double.compare(a.y, b.y));
        double minDist = d;
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < minDist; j++) {
                minDist = Math.min(minDist, dist(strip.get(i), strip.get(j)));
            }
        }
        return minDist;
    }

    static double closestUtil(List<Point> pts, int left, int right) {
        if (right - left <= 2)
            return bruteForce(pts, left, right);

        int mid = (left + right) / 2;
        double midX = pts.get(mid).x;

        double dl = closestUtil(pts, left, mid);
        double dr = closestUtil(pts, mid + 1, right);
        double d = Math.min(dl, dr);

        List<Point> strip = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (Math.abs(pts.get(i).x - midX) < d)
                strip.add(pts.get(i));
        }
        return Math.min(d, stripClosest(strip, d));
    }

    static double closestPair(List<Point> pts) {
        List<Point> sorted = new ArrayList<>(pts);
        sorted.sort((a, b) -> Double.compare(a.x, b.x));
        return closestUtil(sorted, 0, sorted.size() - 1);
    }

    public static void main(String[] args) {
        List<List<Point>> tests = new ArrayList<>();

        tests.add(Arrays.asList(
            new Point(1,2), new Point(2.5,0.8), new Point(3,5), new Point(4.1,5.2),
            new Point(5,6.8), new Point(6,1), new Point(7,4), new Point(8.2,5.5)
        ));
        tests.add(Arrays.asList(new Point(0,0), new Point(1,1), new Point(2,2), new Point(3,3)));
        tests.add(Arrays.asList(new Point(0,0), new Point(5,5)));
        tests.add(Arrays.asList(new Point(1,1), new Point(1,1), new Point(5,5)));
        tests.add(Arrays.asList(new Point(0,0), new Point(3,4), new Point(6,8), new Point(1,1)));

        for (int t = 0; t < tests.size(); t++) {
            System.out.println("Test " + (t + 1) + " Output: " + closestPair(tests.get(t)));
        }
    }
}

