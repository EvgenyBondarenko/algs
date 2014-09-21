import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first, last;
    private int size;

    public Deque() {

    }

    public static void main(String[] args) {
        Deque<Integer> d = new Deque<>();
        d.addLast(1);
        d.removeFirst();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        Node<Item> oldFirst = first;
        first = new Node<>(item);
        first.next = oldFirst;
        if (isEmpty()) last = first;
        else oldFirst.prev = first;
        size++;
    }

    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();
        Node<Item> oldLast = last;
        last = new Node<>(item);
        last.prev = oldLast;
        if (isEmpty()) first = last;
        else oldLast.next = last;
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.value;
        first = first.next;
        if (first == null) last = null;
        else first.prev = null;
        size--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.value;
        last = last.prev;
        if (last == null) first = null;
        else last.next = null;
        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            private Node<Item> next = first;

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public Item next() {
                if (next == null) throw new NoSuchElementException();
                Item item = next.value;
                next = next.next;
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private static class Node<Item> {
        private Item value;
        private Node<Item> prev;
        private Node<Item> next;

        private Node(Item item) {
            this.value = item;
        }
    }
}