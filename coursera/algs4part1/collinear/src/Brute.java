import java.util.Arrays;

public class Brute {
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

    private static void printLines(Point[] points) {
        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int k = j + 1; k < points.length - 1; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        if (points[i].slopeTo(points[j]) == points[j].slopeTo(points[k])
                                && points[i].slopeTo(points[j]) == points[k].slopeTo(points[l])) {
                            Point[] line = new Point[4];
                            line[0] = points[i];
                            line[1] = points[j];
                            line[2] = points[k];
                            line[3] = points[l];
                            printLine(line);
                        }
                    }
                }
            }
        }
    }

    private static void printLine(Point[] line) {
        Arrays.sort(line);
        StdOut.print(line[0]);
        for (int i = 1; i < line.length; i++) {
            StdOut.print(" -> " + line[i]);
        }
        StdOut.println();
        line[0].drawTo(line[line.length - 1]);
    }
}