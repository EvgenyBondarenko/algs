import java.util.TreeSet;

public class LimitedTreeSet<T extends Comparable<T>> {

    private final int k;
    private final TreeSet<T> treeSet;

    public LimitedTreeSet(int k) {
        this.k = k;
        treeSet = new TreeSet<T>();
    }

    public static void main(String[] args) {
        LimitedTreeSet<Integer> lt = new LimitedTreeSet<Integer>(2);
        lt.add(1);
        lt.add(3);
        lt.add(2);
        lt.add(4);
        lt.add(0);
        System.out.println(lt.getKthGreatestElement());
    }

    public void add(T element) {
        if (treeSet.size() < k) {
            treeSet.add(element);
        } else if (element.compareTo(treeSet.first()) > 0) {
            treeSet.pollFirst();
            treeSet.add(element);
        }
    }

    public T getKthGreatestElement() {
        if (treeSet.size() < k)
            throw new IllegalStateException(String.format("The LimitedTreeSet does not contain enough elements to return %d-th greatest element", k));
        return treeSet.first();
    }

}
