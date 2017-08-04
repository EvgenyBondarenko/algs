public class SuperReducedString {

  static String super_reduced_string(String s){
    String reduced = reduce(new StringBuilder(s), 0).toString();
    return reduced.isEmpty() ? "Empty String" : reduced;
  }

  private static StringBuilder reduce(StringBuilder sb, int from) {
    for (int i = from; i < sb.length() - 1; i++) {
      if (sb.charAt(i) == sb.charAt(i+1)) {
        from = i > 0 ? i-1 : 0;
        return reduce(sb.delete(i, i+2), from);
      }
    }
    return sb;
  }

  public static void main(String[] args) {
//    Scanner in = new Scanner(System.in);
//    String s = in.next();
    String result = super_reduced_string("aabcddceffh");
    System.out.println(result);
  }
}
