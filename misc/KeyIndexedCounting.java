import java.util.Arrays;
import java.util.Collections;

public class KeyIndexedCounting {
    public static void main(String[] args) {
        MyObject[] a = new MyObject[6];
        a[0] = new MyObject(3, "qwe");
        a[1] = new MyObject(1, "asd");
        a[2] = new MyObject(2, "zxc");
        a[3] = new MyObject(3, "rt");
        a[4] = new MyObject(4, "fg");
        a[5] = new MyObject(2, "io");
        sort(a);
        System.out.println(Arrays.asList(a));
    }

    private static void sort(MyObject[] a) {
        int[] count = new int[maxKey(a) + 2];
        for (int i = 0; i < a.length; i++) {
            count[a[i].num + 1]++;
        }

        
        return;
    }

    private static int maxKey(MyObject[] a) {
        return Collections.max(Arrays.asList(a)).num;
    }

    private static class MyObject implements Comparable<MyObject> {
        private int num;
        private String str;

        public MyObject(int num, String str) {
            this.num = num;
            this.str = str;
        }

        @Override
        public int compareTo(MyObject o) {
            return this.num > o.num ? 1 :
                    this.num == o.num ? 0 : -1;
        }

        @Override
        public String toString() {
            return "{" +
                    "num=" + num +
                    ", str='" + str + '\'' +
                    '}';
        }
    }
}
