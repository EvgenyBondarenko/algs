import java.util.Comparator;

public class Point implements Comparable<Point> {
    public final Comparator<Point> SLOPE_ORDER;

    private final int x;
    private final int y;

    // create the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        SLOPE_ORDER = new SlopeComparator();
    }

    public static void main(String[] args) {
    }

    // plot this point to standard drawing
    public void draw() {
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if ((y == that.y) && (x == that.x)) return Double.NEGATIVE_INFINITY;
        if (y == that.y) return 0.0;
        if (x == that.x) return Double.POSITIVE_INFINITY;
        return (double) (that.y - y) / (that.x - x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (y > that.y) return 1;
        else if (that.y > y) return -1;
        else if (x > that.x) return 1;
        else if (that.x > x) return -1;
        else return 0;
    }

    // return string representation of this point
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    private class SlopeComparator implements Comparator<Point> {

        @Override
        public int compare(Point p1, Point p2) {
            double d = Point.this.slopeTo(p1) - Point.this.slopeTo(p2);
            return d > 0 ? 1 : d < 0 ? -1 : 0;
        }
    }
}