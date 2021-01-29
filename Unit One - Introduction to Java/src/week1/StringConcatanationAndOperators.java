package week1;

public class StringConcatanationAndOperators {
    public static void main(String[] args) {
        //exampleOne();
        //exampleTwo();
        exampleThree();
        exampleFour();
    }

    /*
    * Remainder using the modulus operator (it is a form of division).
    * Usually only used with integers.
    * Checks if a number is divisible by another number.
    */
    private static void exampleFour() {
        int x = 11;

        int y = x % 2;      // % is the sign for the modulus operator. It has NOTHING to do with percent. (Modulus obtains the remainder)
                            // y is the remainder (of 11/2, which should be 1 because 11 goes into 2 five times with 1 left over).
        
        System.out.println(y);

        boolean isEven = (x % 2) == 0;      // == checks for equality, = is assignment.
                                            // boolean variables store true or false (in this case based off of the answer to the expression it is told to evalutate).

        System.out.println(x + " is even: " + isEven);
    }

    /*
     * Review order of operations (BEDMAS) for string concatanation
     */
    private static void exampleThree() {
        int x = 4, y = 3;

        System.out.println(x + y);                              // Because both data types are ints, the + sign adds them.
        System.out.println(x + " + " + y + " = " + x + y);      // "4 + 3 = 4" + 3 yields "4 + 3 + 43". This is because unlike the example above, + has been used to concatanate strings before getting to x+y. As such, it just puts x beside y, giving 43, and does not add them to give 7.
        
        System.out.println(x + " + " + y + " = " + (x + y));    // BEDMAS still exists in programming. If we put brackets, it will read that first, and in this case do the addition we want before the string concatanation.

        System.out.println(x + y + " = " + x + " + " + y);      // Brackets are not needed here because also with BEDMAS, we go right to left. As such, it adds the ints first to get 7 before the string concatanation.
    }

    /*
     * String Concatanation Example Will display something like 4+3=7
     */
    private static void exampleTwo() {
        int x = 4, y = 3, sum = x + y;      // Declared 3 different variables on a single line and assigned them values.

        //The long and terrible way - This is NOT string concatanation
        System.out.print(x);
        System.out.print("+");
        System.out.print(y);
        System.out.print("=");
        System.out.println(sum);

        // Thke better way - This IS string concatanation
        System.out.println(x + " + " + y + " = " + sum);
    }

    /*
    * Topics Covered;
    * Constants using the modified final
    * Double and int division
    * Type casting
    */
    private static void exampleOne() {
        int markOne = 90;
        int markTwo = 94;
        int markThree = 88;

        final int NUM_TESTS = 3; // final ensures that the value stored cannot change (IT IS CONSTANT). Constant naming convention is all caps.
        // NUM_TESTS = 6;        // Causes an error because numTests is final (CONSTANT)

        double averageMark = (markOne + markTwo + markThree)/NUM_TESTS; // expected it to be 90.999997 but the program truncates the result to 90 because it is dividing integers.
                                                                // if you do int / int, the program will give you an int (called a truncated result).
                                                                // As long as one of the elements of the equation is a double, the result will be a double.
                                                                // i.e. int / double, double / int, or double / double will yield a double when performing division.
        System.out.println(averageMark);

        averageMark = (markOne + markTwo + markThree) /3.0;     // Don't like using 3.0 because it makes no sense, there are no decimal tests.
                                                                // Side Note: even though the marks are still integers, the int 3 (NUM_TESTS) is now a double here, 3.0, making the program output the full, non-truncated result.
        System.out.println(averageMark);

        averageMark = (double)(markOne + markTwo + markThree)/NUM_TESTS; // This is the best way to do it. It woks becuase we temporarily cast (markOne + markTwo + markThree) as doubles to complete this specific operation.
        
        System.out.println(averageMark);
    }
}