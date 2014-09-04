import java.util.*;

/**
 * Created by jbon on 4/26/14.
 */
public class FourSum {

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 1, 3};
        System.out.println(new SingleNumber().new Solution().singleNumber(A));
    }

    public class Solution {
        public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
            ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
            int len = num.length;
            Map<Integer, List<Pair>> sumToPairMap = new HashMap<Integer, List<Pair>>();
            for (int i = 2; i < len - 1; i++) {
                addSums(i, num, sumToPairMap);
                for (int j = i + 1; j < len; j++) {
                    int curSum = num[i] + num[j];
                    int neededSum = target - curSum;
                    List<Pair> pairs = sumToPairMap.get(neededSum);
                    if (pairs != null) {
                        for (Pair pair : pairs) {
                            res.add(createSortedListFrom(num[i], num[j], pair.a, pair.b));
                        }
                    }
                }
            }
            return res;
        }

        private void addSums(int fromPosition, int[] num, Map<Integer, List<Pair>> sumToPairMap) {
            int curValue = num[fromPosition - 1];
            for (int i = fromPosition - 2; i >= 0; i--) {
                int sumToAdd = curValue + num[i];
                List<Pair> pairs = sumToPairMap.get(sumToAdd);
                if (pairs == null) {
                    pairs = new ArrayList<Pair>();
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
