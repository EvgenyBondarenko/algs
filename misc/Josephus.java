import java.util.LinkedList;
import java.util.Queue;

public class Josephus {
    public static void main(String[] args) {
        System.out.println(josephus(10, 2));
    }

    private static int josephus(int N, int M) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }
        System.out.println(queue);
        while (queue.size() > 1) {
            for (int i = 0; i < M - 1; i++) {
                queue.add(queue.poll());
            }
            queue.poll();
            System.out.println(queue);
        }
        return queue.element();
    }
}
