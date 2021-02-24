package week5;

import java.util.Scanner;

public class WhileLoops {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int x = sum(1, 10);
        System.out.println(x);

        loopExampleTwo(in);

        System.out.print("Please enter a sentence: ");
        String sentence = in.nextLine();

        int numVowelsV1 = countVowelsV1(sentence);
        System.out.println("There are " + numVowelsV1 + " vowels.");

        int numVowelsV2 = countVowelsV2(sentence);
        System.out.println("There are " + numVowelsV2 + " vowels.");

    }

    

    private static int countVowelsV2(String text) {
        int numVowels = 0;
        int index = 0;      // We usually just use i to stand for index, but for clarity's sake, here we used index.

        while (index < text.length()) {
            String letter = text.substring(index, index+1);

            // checks if letter is in the string "AEIOUaeiou" (basically checking if the letter you grabbed is a vowel)
            if ("AEIOUaeiou".indexOf(letter) >= 0) {
                numVowels++;
            }

            index++;
        }

        return numVowels;
    }

    /**
     * Iterates through the text and checks for AEIOU
     * @param text
     * @return the number of vowels in the sentence that was inputted by the user.
     */
    private static int countVowelsV1(String text) {
        int numVowels = 0;
        int index = 0;      // We usually just use i to stand for index, but for clarity's sake, here we used index.

        while (index < text.length()) {
            String letter = text.substring(index, index+1);
            if (letter.equalsIgnoreCase("a") || letter.equalsIgnoreCase("e") || letter.equalsIgnoreCase("i") || letter.equalsIgnoreCase("o") || letter.equalsIgnoreCase("u")) {
                numVowels++;
            }

            index++;
        }

        return numVowels;
    }


    private static void loopExampleTwo(Scanner in) {
        String mysteryColour = "red";
        String colour = null;

        while(!mysteryColour.equalsIgnoreCase(colour)) {    // Unless we want to be inefficient, we MUST have this statement in the order mysteryColour.equals(colour). This is because if we put colour.equals(mysteryColour), when the loop tries to run the first time, since colour is null, it will throw an error.
            System.out.println("What is the Mystery Colour?");
            colour = in.nextLine();
        }

        System.out.println("Correct! I like the colour " + mysteryColour);
    }


    /**
     * Displays the sum of the numbers from start to end (inclusive) (i.e. sum of 1+2+3+4+5+6+7+8+9+10)
     * 
     * @param start
     * @param end
     * @return the sum of the numbers from start to end.
     */
    private static int sum(int start, int end) {
        /**
         * Loops allow us to repeat code (without having to rewrite it).
         * Other words for loop include: repetition and iteration (usually iteration is used when referring to an array or a collection).
         * With what we know so far, we can iterate through the characters of a String.
         */


        /**
         * while(boolean expression) {  // as long as the boolean expression evaluates to true the program will enter the body of the loop.
         *  [Stuff that you want to happen in the loop]
         * }
         * 
         * Need to ensure that there is a way to make the boolean expression false or else we will have an infinite loop
         */
        int result = 0;
        int i = start;

        while (i <= end) {
            result += i;
            i++;        // updates the counter (so that eventually i > end, making the boolean expression false and ending the loop).
        }    
        
        return result;
    }
}
