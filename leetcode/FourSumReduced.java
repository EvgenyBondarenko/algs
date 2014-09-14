import java.util.*;

/**
 * Simplified solution. For incoming array {-2, 0, 0, 1, 1} prints {-2, 0, 1, 1}. However There are two such sets (0 appears twice)
 */
public class FourSumReduced {

    public static void main(String[] args) {
        Set<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>();
        ArrayList<Integer> l1 = new ArrayList<Integer>(1);
        l1.add(1);
        set.add(l1);
        ArrayList<Integer> l2 = new ArrayList<Integer>(1);
        l2.add(1);
        set.add(l2);
        System.out.println(set);
        int[] A = {-3, -2, -1, 0, 0, 1, 2, 3};
        System.out.println(new FourSumReduced().new Solution().fourSum(A, 0));
    }

    public class Solution {
        public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
            Set<ArrayList<Integer>> res = new HashSet<ArrayList<Integer>>();
            int len = num.length;
            Map<Integer, Set<Pair>> sumToPairMap = new HashMap<Integer, Set<Pair>>();
            for (int i = 2; i < len - 1; i++) {
                addSums(i, num, sumToPairMap);
                for (int j = i + 1; j < len; j++) {
                    int curSum = num[i] + num[j];
                    int neededSum = target - curSum;
                    Set<Pair> pairs = sumToPairMap.get(neededSum);
                    if (pairs != null) {
                        for (Pair pair : pairs) {
                            res.add(createSortedListFrom(num[i], num[j], pair.a, pair.b));
                        }
                    }
                }
            }
            return new ArrayList<ArrayList<Integer>>(res);
        }

        private void addSums(int fromPosition, int[] num, Map<Integer, Set<Pair>> sumToPairMap) {
            int curValue = num[fromPosition - 1];
            for (int i = fromPosition - 2; i >= 0; i--) {
                int sumToAdd = curValue + num[i];
                Set<Pair> pairs = sumToPairMap.get(sumToAdd);
                if (pairs == null) {
                    pairs = new HashSet<Pair>();
                }
                pairs.add(new Pair(curValue, num[i]));
                sumToPairMap.put(sumToAdd, pairs);
            }
        }

        private ArrayList<Integer> createSortedListFrom(int... array) {
            ArrayList<Integer> list = new ArrayList<Integer>(4);
            Arrays.sort(array);
            for (int i : array) {
                list.add(i);
            }
            return list;
        }

        private class Pair {
            int a, b;

            private Pair(int a, int b) {
                this.a = a;
                this.b = b;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Pair pair = (Pair) o;

                if ((a == pair.a && b == pair.b) || (a == pair.b && b == pair.a)) return true;

                return false;
            }

            @Override
            public int hashCode() {
                int result = a;
                result = 31 * result + b;
                return result;
            }
        }
    }

//    public class Solution {
//        public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
//            int len = num.length;
//            ArrayList<ArrayList<Integer>> resultList = new ArrayList<ArrayList<Integer>>();
//            for(int i = 0; i < len-2; i++){
//                for(int j = i; j < len-1; j++){
//                    Map<Integer, Integer> contributorCountMap = new HashMap<Integer, Integer>(len);
//                    for(int k = j; k < len; k++){
//                        int contributor = target - num[i] - num[j] - num[k];
//                        int contributorCount = getContributorCount(contributorCountMap, contributor);
//                        for (int each = 0; each < contributorCount; each++){
//                            resultList.add(createSortedListFrom(num[i], num[j], num[k], contributor));
//                        }
//                        addToContributorsMap(contributorCountMap, num[k]);
//                    }
//                }
//            }
//            return resultList;
//        }
//
//        private void addToContributorsMap(Map<Integer, Integer> contributorCountMap, int i) {
//            Integer currentCount = contributorCountMap.get(i);
//            int newCount = currentCount == null ? 1 : currentCount + 1;
//            contributorCountMap.put(i, newCount);
//        }
//
//        private ArrayList<Integer> createSortedListFrom(int... array) {
//            ArrayList<Integer> list = new ArrayList<Integer>(4);
//            Arrays.sort(array);
//            for (int i : array){
//                list.add(i);
//            }
//            return list;
//        }
//
//        private int getContributorCount(Map<Integer, Integer> contributorCountMap, int contributor) {
//            Integer currentCount = contributorCountMap.get(contributor);
//            return currentCount == null ? 0 : currentCount;
//        }
//    }

}
