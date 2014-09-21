public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        if (k > 0) {
            RandomizedQueue<String> q = new RandomizedQueue<>();
            while (!StdIn.isEmpty()) {
                q.enqueue(StdIn.readString());
            }
            for (int i = 0; i < k; i++) {
                System.out.println(q.dequeue());
            }
        }
    }
}