package week1;

public class UsingBuiltInMathFunctions {
    public static void main(String[] args) {
        exampleOne();
        exampleTwo();

        // Pass in two ints and find a random number between those two numbers inclusive
        // First number is smaller than second number
        displayRandomNumber(6, 12);
    }

    /*
    * General method for obtaining a random number between lower and upper inclusive
    * @param lower - Lower bound - smallest number to return
    * @param upper - Upper bound - largest number to return
    */
    private static void displayRandomNumber(int lower, int upper) {
        int randomNumber = (int)(Math.random() * (upper-lower+1)) + lower;
        System.out.println(randomNumber);
    }

    /*
     * Generating random numbers
     */
    private static void exampleTwo() {
        double randomNumber = Math.random();   // The Math.random function will result in a double (decimal) between 0.0 and 1.0, including 0.0 and exluding 1.0.
        System.out.println(randomNumber);     // ex: 0.7919971175599081 or 0.570026695644729

        // Goal is to get a random integer between 1 and 10.
        randomNumber = Math.random() * 10;  // multiplies every possible number by 10
        System.out.println(randomNumber);   // smallest is 0.0 * 10 = 0.0
                                            // largest is 0.99999999999 * 10 = 9.9999999999
        
        randomNumber = (int)(Math.random() * 10) + 1;    // Multiplies every possible number by 10 and then casts it as an integer, resulting in numbers from 0-9. Then we add 1 to get numbers from 1-10.
        System.out.println(randomNumber);               // As such, with this program, smallest = 1, largest = 10.



    }

    private static void exampleOne() {
        double x = 2.0;

        double y = Math.sqrt(x);
        System.out.println(y);

        double z = Math.pow(x, 2);      // Returns x to the power of 2 (x squared). Bascially, Math.pow returns the first arguement to the power of the second arguement.
        System.out.println(z);
    }
}
