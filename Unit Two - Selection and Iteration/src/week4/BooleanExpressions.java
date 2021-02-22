package week4;

import java.util.Scanner;

public class BooleanExpressions {
    public static void main(String[] args) {
        primitiveBooleans();
        compoundBooleanExpressions();
    }

    private static void compoundBooleanExpressions() {
    // && => and
    // || => or
    //! => not

    Scanner in = new Scanner(System.in);
    System.out.print("PLease enter a colour for the shoes: ");
    String colour = in.nextLine();//.toLowerCase();

    System.out.print("How many shoes? ");
    int numShoes = Integer.parseInt(in.nextLine());

    // are there at least 4 pairs of red shoes?
    System.out.println(colour.equalsIgnoreCase("red") && (numShoes >= 4));

    // are there at least 4 pairs of shoes OR are the shoes blue?
    System.out.println(colour.equalsIgnoreCase("blue") || (numShoes >= 4));

    /** short circuit expressions
     * 
     * colour.equalsIgnoreCase("red") && (numShoes >= 4)
     *  If the colour is not red, there is no way both expressions can be true, and as a result Java does not look at the numShoes expression.
     * 
     * colour.equalsIgnoreCase("blue") || (numShoes >= 4)
     *  If the colour is blue, then Java doesn't need to look at numShoes expression BECAUSE only 1 of the expression has to be true to give a true result (due to OR).
     */

    boolean isResult = false;
    System.out.println(isResult);   // prints false

    System.out.println(!isResult);  // prints true because !false => NOT false => true. Same works for !true

    }

    private static void primitiveBooleans() {
        boolean isYellow = true;        // boolean primitives are either true or false
        boolean hasDog = false;         // naming convention: we normally use is or has to prefix the variable name for booleans


        int ten = 10;           // just made so there are no errors in below line
        boolean isTrue = (7 + 3) == ten; // == is the equality operator and is used to check if two operands are equal
                                        // left operand => (7 + 3)
                                        // operator => ==
                                        // right operand => 10
                                        // This example evaluates to true
        int seven = 7;          // just made so there are no errors in below line
        System.out.println(7 != seven);     // != is used for not equal.    7 != 7 will return false because 7 is equal to 7.

        System.out.println(7 > 7);      // 7 > 7 is also false because 7 is equal to 7

        System.out.println(7 <= 7);      // 7 <= 7 is true because though 7 is not less than 7, 7 is equal to 7.    }
    }
}
