package week4;

public class TestPrep {
    public static void main(String[] args) {
        double v = 2;
        int x = 2;
        int y = 4;
        System.out.println(v);
        System.out.println(v/y);
        System.out.println("I like trains " + y/v);

        double result = (double) (x / y);
        double result1 = (double) x / y;
        double result2 = (double) (x) / y;
        int result3 = (int) (v / y);

        System.out.println(result);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);

        String hi = "hello";

        changeHi(hi);

        System.out.println(hi);
    }

    private static String changeHi(String hi) {
        return hi + "h";
    }
}
