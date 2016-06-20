import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SortStack {
    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>(Arrays.asList(1, 3, 2, 4));
        Deque<Integer> aux = new ArrayDeque<>();
        sort(stack, aux);
        System.out.println(stack);
    }

    public static void sort(Deque<Integer> stack, Deque<Integer> aux) {
        while (!stack.isEmpty()) {
            Integer element = stack.pop();

            while (!aux.isEmpty() && element > aux.peek()) {
                stack.push(aux.pop());
            }
            aux.push(element);
        }
        while (!aux.isEmpty()) {
            stack.push(aux.pop());
        }
    }
}

