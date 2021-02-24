package week5;

import java.util.Scanner;

public class ForLoops {
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

        drawRectangle(4, 6, "*");

        int M = 3;
        int N = 4;
        int P = 1;
        int Q = 2;
        ecoo2010(M, N, P, Q);
   }



    private static void ecoo2010(int m, int n, int p, int q) {
        // top of frame
        for (int j = 0; j < q; j++) {
            for (int i = 0; i < (n+(2*p)+(2*q)); i++) {
                System.out.print("#");
            }
            System.out.println();
        }

        // top of matting
        for (int i = 0; i < p; i++) {
            // left side of matting
            for (int k = 0; k < q; k++) {
                System.out.print("#");
            }
            // matting
            for (int k = 0; k < 2 * p + n; k++) {
                System.out.print("+");
            }  
            // right side of matting
            for (int k = 0; k < q; k++) {
                System.out.print("#");
            }
            System.out.println();
        }
  
        // picture
        for (int j = 0; j < m; j++) {
            for (int k = 0; k < q; k++) {
                System.out.print("#");
            }
            // matting
            for (int k = 0; k < p; k++) {
                System.out.print("+");
            }
  
            // pic
            for (int k = 0; k < n; k++) {
                System.out.print(".");
            }
  
            // matting
            for (int k = 0; k < p; k++) {
                System.out.print("+");
            }
            // picture
            for (int k = 0; k < q; k++) {
                System.out.print("#");
            }
            System.out.println();
        }
  
        // bottom of matting
        for (int i = 0; i < p; i++) {
            // left side of matting
            for (int k = 0; k < q; k++) {
                System.out.print("#");
            }
            // matting
            for (int k = 0; k < 2 * p + n; k++) {
                System.out.print("+");
            }
            // right side of matting
            for (int k = 0; k < q; k++) {
                System.out.print("#");
            }
            System.out.println();
        } 

        // bottom of frame
        for (int j = 0; j < q; j++) {
            for (int i = 0; i < (n+(2*p)+(2*q)); i++) {
                 System.out.print("#");
            }
            System.out.println();
        }
    }


    /**
     * 4, 6, "*" should return:
     *
     * **** **** **** **** **** ****
     * 
     * @param width
     * @param height
     * @param symbol
     */
   private static void drawRectangle(int width, int height, String symbol) {
    for (int row = 0; row < height; row++) {
        for (int col = 0; col < width; col++) {
            System.out.print("symbol");
        }
        System.out.println();
    }
   }


    private static int countVowelsV2(String text) {
        int numVowels = 0;

        for (int index = 0; index < text.length(); index++) {
            String letter = text.substring(index, index+1);

            // checks if letter is in the string "AEIOUaeiou" (basically checking if the letter you grabbed is a vowel)
            if ("AEIOUaeiou".indexOf(letter) >= 0) {
                numVowels++;
            }
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

        for (int index = 0; index < text.length(); index++) {
            String letter = text.substring(index, index+1);
            if (letter.equalsIgnoreCase("a") || letter.equalsIgnoreCase("e") || letter.equalsIgnoreCase("i") || letter.equalsIgnoreCase("o") || letter.equalsIgnoreCase("u")) {
                numVowels++;
            }
        }
        return numVowels;
   }


   private static void loopExampleTwo(Scanner in) {
        String mysteryColour = "red";
        String colour = null;

        for (; !mysteryColour.equalsIgnoreCase(colour); ) {     // We would never realistically use a for loop for something like this, but that doesn't mean we can't.
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
       int result = 0;

       // for loop has 3 prts (initialize the counter, condition, how we are counting).
       for (int i = start; i <= end; i++) {
          result += i;
       }
       return result;
   }
}