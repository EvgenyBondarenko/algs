import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CourseSchedule {

    public static void main(String[] args) {
        int [][] a = {{1, 0}, {0, 1}};
        new Solution().canFinish(2, a);
    }

    static class Solution {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            Set<Integer> visited = new HashSet<>();
            Set<Integer> completable = new HashSet<>();
            Map<Integer, Set<Integer>> prereqMap = new HashMap<>();

            for (int i = 0; i < prerequisites.length; i++) {
                Set<Integer> prereqs = prereqMap.get(prerequisites[i][0]);
                if (prereqs == null) {
                    prereqs = new HashSet<>();
                    prereqMap.put(prerequisites[i][0], prereqs);
                }
                prereqs.add(prerequisites[i][1]);
            }

            for (int i = 0; i < numCourses; i++) {
                if (isLoop(i, prereqMap, visited, completable)) {
                    return false;
                }
            }

            return true;
        }

        private boolean isLoop(
            int course,
            Map<Integer, Set<Integer>> prereqMap,
            Set<Integer> visited, Set<Integer> completable) {
            if (completable.contains(course)) return false;
            if (visited.contains(course)) return true;

            Set<Integer> prereqs = prereqMap.get(course);
            if (prereqs == null) {
                completable.add(course);
                return false;
            }
            visited.add(course);

            for (Integer prereq : prereqs) {
                if (isLoop(prereq, prereqMap, visited, completable)) {
                    return true;
                }
            }
            visited.remove(course);
            completable.add(course);
            return false;
        }
    }


}
