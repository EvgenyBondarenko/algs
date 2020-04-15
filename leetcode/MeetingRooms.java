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
        System.out.println(new Solution2().minMeetingRooms(intervals));
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

    /**
     * Sort the start times, sort the end times. Then kind of merge the two arrays. It'll look something like:
     * [s1, s2, f2, s3, f1, f2] (where s1 - start of event 1, f1 - finish of event 1);
     * Then traverse the array and increment the number of rooms when you see 's' and decrement it when you see 'f'.
     * Return the maximum number of rooms observed.
     */
    static class Solution2 {
        public int minMeetingRooms(Interval[] intervals) {
            if (intervals == null || intervals.length == 0) return 0;
            int[] startTimes = new int[intervals.length];
            int[] endTimes = new int[intervals.length];
            for (int i = 0; i < intervals.length; i++) {
                startTimes[i] = intervals[i].start;
                endTimes[i] = intervals[i].end;
            }
           Arrays.sort(startTimes);
           Arrays.sort(endTimes);
           int curRoomsCount = 0;
           int maxRoomsCount = 0;
           int i = 0;
           int j = 0;
           while (i < startTimes.length) {
               if (startTimes[i] < endTimes[j]) {
                   curRoomsCount++;
                   maxRoomsCount = Math.max(maxRoomsCount, curRoomsCount);
                   i++;
               } else {
                   curRoomsCount--;
                   j++;
               }
           }
           return maxRoomsCount;
        }
    }
}
