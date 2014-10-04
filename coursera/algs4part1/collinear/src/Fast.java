import java.util.*;

public class Fast {
    public static void main(String[] args) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            Point p = new Point(in.readInt(), in.readInt());
            p.draw();
            points[i] = p;
        }
        printLines(points);
    }

    private static void printLines(Point[] orig) {
        Map<Double, Set<Point>> discovered = new HashMap<>();
        Arrays.sort(orig);
        for (int i = 0; i < orig.length; i++) {
            Point point = orig[i];
            Point[] points = new Point[orig.length - i - 1];
            System.arraycopy(orig, i + 1, points, 0, orig.length - i - 1);
            Arrays.sort(points, point.SLOPE_ORDER);
            int start = 0;
            int end = 1;
            while (end < points.length) {
                if (point.slopeTo(points[start]) == point.slopeTo(points[end])) {
                    end++;
                } else {
                    if (end - start >= 3) {
                        printLine(point, points, start, end - 1, discovered);
                    }
                    start = end++;
                }
            }
            if (end - start >= 3) {
                printLine(point, points, start, end - 1, discovered);
            }
        }
    }

    private static void putToDiscovered(Map<Double, Set<Point>> discovered, double slope, Point last) {
        if (!discovered.containsKey(slope)) discovered.put(slope, new HashSet<Point>());
        discovered.get(slope).add(last);
    }

    private static void printLine(Point point, Point[] points, int from, int to, Map<Double, Set<Point>> discovered) {
        if (!alreadyDiscovered(discovered, point.slopeTo(points[to]), points[to])) {
            StdOut.print(point);
            for (int i = from; i <= to; i++) {
                StdOut.print(" -> " + points[i]);
            }
            StdOut.println();
            point.drawTo(points[to]);
            putToDiscovered(discovered, point.slopeTo(points[from]), points[to]);
        }
    }

    private static boolean alreadyDiscovered(Map<Double, Set<Point>> discovered, double slope, Point point) {
        return discovered.containsKey(slope) && discovered.get(slope).contains(point);
    }
}
