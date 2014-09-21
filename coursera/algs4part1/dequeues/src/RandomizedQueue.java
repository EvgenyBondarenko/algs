import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] a;

    public RandomizedQueue() {
        a = (Item[]) new Object[1];
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        if (size == a.length) resize();
        a[size++] = item;
    }

    private void resize() {
        Item[] b = (Item[]) new Object[a.length * 2];
        System.arraycopy(a, 0, b, 0, a.length);
        a = b;
    }

    public Item dequeue() {
        if (size == 0) throw new NoSuchElementException();
        int rnd = StdRandom.uniform(size);
        Item item = a[rnd];
        a[rnd] = a[--size];
        a[size] = null;
        if (a.length / (size + 1) >= 4) shrink();
        return item;
    }

    private void shrink() {
        Item[] b = (Item[]) new Object[a.length / 2];
        System.arraycopy(a, 0, b, 0, size);
        a = b;
    }

    /**
     * returns (but does not delete) a random item
     */
    public Item sample() {
        if (size == 0) throw new NoSuchElementException();
        return a[StdRandom.uniform(size)];
    }

    public Iterator<Item> iterator() {
        return new RandomizedIterator<>(a, size);
    }

    private static class RandomizedIterator<Item> implements Iterator<Item> {

        private Item[] shuffled;
        private int next;

        private RandomizedIterator(Item[] src, int size) {
            shuffled = (Item[]) new Object[size];
            System.arraycopy(src, 0, shuffled, 0, size);
            StdRandom.shuffle(shuffled);
        }

        @Override
        public boolean hasNext() {
            return next < shuffled.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return shuffled[next++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}