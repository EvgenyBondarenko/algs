import java.util.ArrayList;
import java.util.List;

public class BoundedPriorityQueue {

    private int[] a;
    private int N = 0;

    public BoundedPriorityQueue(int n) {
        a = new int[n];
    }

    public static void main(String[] args) {
        BoundedPriorityQueue pq = new BoundedPriorityQueue(10);
        pq.add(1);
        System.out.println(pq);
        pq.add(2);
        System.out.println(pq);
        pq.add(3);
        System.out.println(pq);
        pq.add(4);
        System.out.println(pq);
        pq.add(5);
        System.out.println(pq);
        pq.add(6);
        System.out.println(pq);
        pq.add(7);
        System.out.println(pq);
        System.out.println(pq.delMax());
        System.out.println(pq);
        System.out.println(pq.delMax());
        System.out.println(pq);
        System.out.println(pq.delMax());
        System.out.println(pq);
        System.out.println(pq.delMax());
        System.out.println(pq);
        System.out.println(pq.delMax());
        System.out.println(pq);
        System.out.println(pq.delMax());
        System.out.println(pq);
        System.out.println(pq.delMax());
        System.out.println(pq);
        System.out.println(pq.delMax());
        System.out.println(pq);
    }

    public void add(int e) {
        a[++N] = e;
        swim();
    }

    public int delMax() {
        int max = a[1];
        a[1] = a[N];
        a[N--] = 0;
        sink();
        return max;
    }

    private void sink() {
        int i = 1;
        while (i < N && (a[i] < a[2 * i] || a[i] < a[2 * i + 1])) {
            exch(i,
                    a[2 * i] > a[2 * i + 1] ?
                            2 * i :
                            2 * i + 1);
        }
    }

    private void swim() {
        int i = N;
        while (i > 1 && a[i] > a[i / 2]) {
            exch(i, i / 2);
            i = i / 2;
        }
    }

    private void exch(int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int height = N == 0 ?
                0 :
                Double.valueOf(Math.log(N) / Math.log(2)).intValue() + 1;
        for (int i = 1; i <= height; i++) {
            printLevel(indicesForLevel(i), sb);
        }
        return sb.toString();
    }

    private void printLevel(List<Integer> indices, StringBuilder sb) {
        for (Integer index : indices) {
            sb.append(" ").append(a[index]);
        }
        sb.append("\n");
    }

    private List<Integer> indicesForLevel(int level) {
        List<Integer> indices = new ArrayList<>();
        for (int i = Double.valueOf(Math.pow(2, level - 1)).intValue(); i < Double.valueOf(Math.pow(2, level)).intValue(); i++) {
            indices.add(i);
        }
        return indices;
    }
}
