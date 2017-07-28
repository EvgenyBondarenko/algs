public class RichieRich {

    static String richieRich(String s, int n, int k){
        char[] l = left(s);
        char[] r = right(s);
        Character m = middle(s);

        int d = diff(l, r);

        if (k < d) return "-1";

        for (int i = 0; i < l.length; i++) {
            int j = r.length - 1 - i;
            char ll = l[i];
            char rr = r[j];

            if (ll == rr && ll != '9' && k-d >= 2) {
                l[i] = '9';
                r[j] = '9';
                k -= 2;
            } else if (ll != rr && k == d) {
                char max = ll > rr ? ll : rr;
                l[i] = max; r[j] = max;
                d--;
                k--;
            } else if (ll != rr && k-d >= 1) {
                l[i] = '9'; r[j] = '9';
                if (ll != '9') k--;
                if (rr != '9') k--;
                d--;
            }
        }

        if (m != null && k > 0) {
            m = '9';
        }

        return m == null ? String.valueOf(l) + String.valueOf(r) :
                String.valueOf(l) + String.valueOf(m) + String.valueOf(r);
    }

    private static int diff(char[] l, char[] r) {
        int diff = 0;
        for (int i = 0; i < l.length; i++) {
            int j = r.length - 1 - i;
            if (l[i] != r[j]) diff++;
        }
        return diff;
    }

    private static Character middle(String s) {
        return s.length() % 2 == 0 ? null : s.charAt(s.length() / 2);
    }

    private static char[] right(String s) {
        return s.length() % 2 == 0 ? s.substring(s.length() / 2, s.length()).toCharArray() :
                s.substring(s.length() / 2 + 1, s.length()).toCharArray();
    }

    private static char[] left(String s) {
        return s.substring(0, s.length() / 2).toCharArray();
    }

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int k = in.nextInt();
//        String s = in.next();
        String result = richieRich("12321", 5, 1);
        System.out.println(result);
    }
}
