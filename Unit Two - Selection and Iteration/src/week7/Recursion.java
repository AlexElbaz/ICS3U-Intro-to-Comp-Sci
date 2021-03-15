package week7;

public class Recursion {
    public static void main(String[] args) {
        int x = factorial(7);
        System.out.println(x);

        x = factorial2(7);
        System.out.println(x);

        int test = fibonacci(7);
        System.out.println(test);
    }

    /**
     * Obtains the nth number of the fibonacci sequence
     * @param n the number in the sequence we want to obtain
     * @return the nth number of the fibonacci sequence (i.e. fibonacci(7) = 13)
     */
    private static int fibonacci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return fibonacci(n-1) + fibonacci(n-2);
        }
    }


    private static int factorial2(int n) {
        int result = 1;

        for (int i = 1; i <= n; i++) {      // Starts i at 1 (so that you don't multiply by zero, as per factorials) and goes until i = n (7). Basically makes it so that you multiply 1 by 1 then that result by 2 and so on to get n!
            result *= i;
        }

        return result;
    }


    /**
     * Obtains the factorial of the given an integer
     * 
     * @param n an int representing the integer we would like to obtain the factorial of
     * @return the answe to n!
     */
    private static int factorial(int n) {
        if (n == 1 || n == 0) {             // Apparently 0! is 1, idk how
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
}