import java.util.Arrays;
import java.util.TreeSet;

public class MeetingRooms {
    public static void main(String[] args) {
        Interval[] intervals = new Interval[5];
        intervals[0] = new Interval(18, 22);
        intervals[1] = new Interval(1, 5);
        intervals[2] = new Interval(21, 25);
        intervals[3] = new Interval(0, 20);
        intervals[4] = new Interval(7, 10);
        System.out.println(new Solution().minMeetingRooms(intervals));
    }

    static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static class Solution {
        public int minMeetingRooms(Interval[] intervals) {
            if (intervals == null || intervals.length == 0) return 0;
            TreeSet<Integer> set = new TreeSet<>();
            for (Interval interval : intervals) {
                set.add(interval.end);
            }
            Arrays.sort(intervals, (a, b) -> a.start - b.start);
            for (Interval interval : intervals) {
                Integer lower = set.lower(interval.start);
                if (lower != null) {
                    set.remove(lower);
                }
                set.add(interval.end);
            }
            return set.size();
        }
    }
}
