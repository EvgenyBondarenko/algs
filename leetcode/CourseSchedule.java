import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseSchedule {

    public static void main(String[] args) {
        int [][] a = {{0, 1}, {0, 2}, {1, 2}};
        new Solution().canFinish(2, a);
    }

    static class Solution {
            public boolean canFinish(int numCourses, int[][] prerequisites) {
                if (prerequisites.length == 0) return true;
                Map<Integer, List<Integer>> graph = new HashMap<>();
                for (int[] pair : prerequisites) {
                    graph.putIfAbsent(pair[0], new ArrayList<>());
                    List<Integer> dependencies = graph.get(pair[0]);
                    dependencies.add(pair[1]);
                }
                Set<Integer> checked = new HashSet<>();
                for (Integer course : graph.keySet()) {
                    Set<Integer> visited = new HashSet<>();
                    if (hasLoop(course, graph, checked, visited)) return false;
                }
                return true;
            }

            private static boolean hasLoop(int course, Map<Integer, List<Integer>> graph, Set<Integer> checked, Set<Integer> visited) {
                if (visited.contains(course)) return true;
                if (checked.contains(course)) return false;
                visited.add(course);
                List<Integer> deps = graph.getOrDefault(course, List.of());
                for (Integer dep : deps) {
                    if (hasLoop(dep, graph, checked, visited)) return true;
                }
                checked.add(course);
                visited.remove(course);
                return false;
            }
        }

}
