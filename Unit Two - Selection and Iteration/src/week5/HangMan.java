package week5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HangMan {

    private static final int ALLOWED_BAD_GUESSES = 6;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {

            String sentence = getMessage();
            String usedLetters = "";
            int incorrectGuesses = 0;
            boolean isWinner = false;
            boolean isLoser = false;

            while (!isWinner && !isLoser) {
                String hiddenMessage = encryptMessage(sentence, usedLetters);
                System.out.println(hiddenMessage);

                String guess = getLetter(usedLetters, in);
                usedLetters += guess;

                if (sentence.indexOf(guess) < 0) {
                    incorrectGuesses++;
                    drawHangMan(incorrectGuesses);
                }

                hiddenMessage = encryptMessage(sentence, usedLetters);

                if (hiddenMessage.indexOf("_") < 0) {
                    isWinner = true;
                    System.out.println(hiddenMessage);
                    System.out.println("You win!");
                } else if (incorrectGuesses == ALLOWED_BAD_GUESSES) {
                    isLoser = true;
                    System.out.println("You Lost!");
                }
            }
            playAgain = playAnother(in);
        }

        in.close();
    }

    private static void drawHangMan(int incorrectGuesses) {
        if (incorrectGuesses == 0) {
            System.out.println("   -------");
            System.out.println("   |     |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("-------");
        } else if (incorrectGuesses == 1) {
            System.out.println("   -------");
            System.out.println("   |     |");
            System.out.println("   |     0");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("-------");
        } else if (incorrectGuesses == 2) {
            System.out.println("   -------");
            System.out.println("   |     |");
            System.out.println("   |     0");
            System.out.println("   |     |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("-------");
        } else if (incorrectGuesses == 3) {
            System.out.println("   -------");
            System.out.println("   |     |");
            System.out.println("   |     0");
            System.out.println("   |   --|");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("-------");
        } else if (incorrectGuesses == 4) {
            System.out.println("   -------");
            System.out.println("   |     |");
            System.out.println("   |     0");
            System.out.println("   |   --|--");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("-------");
        } else if (incorrectGuesses == 5) {
            System.out.println("   -------");
            System.out.println("   |     |");
            System.out.println("   |     0");
            System.out.println("   |   --|--");
            System.out.println("   |    /");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("-------"); 
        } else if (incorrectGuesses == 6) {
            System.out.println("   -------");
            System.out.println("   |     |");
            System.out.println("   |     0");
            System.out.println("   |   --|--");
            System.out.println("   |    / \\");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("-------");
        }
    }



    private static String getMessage() {
        try {
            Scanner in = new Scanner(new File("src\\week5\\Clues.dat"));
            int numberOfClues = Integer.parseInt(in.nextLine());
            int clueNumber = (int) (Math.random() * numberOfClues + 1);
            for (int i = 0; i < clueNumber; i++) {
                in.nextLine();
            }
            return in.nextLine();
        } catch (FileNotFoundException e) {
            System.out.println("Incorrect file name.");
            System.exit(0);
        }
        return null;
    }

    private static boolean playAnother(Scanner in) {
        String temp = "";
        while (!(temp.equalsIgnoreCase("Y") || temp.equalsIgnoreCase("YES") || temp.equalsIgnoreCase("N") || temp.equalsIgnoreCase("NO"))) {
            System.out.print("Do you want to play again? (Y/N)");
            temp = in.nextLine().toLowerCase();
        }
        return temp.indexOf("y") >= 0;
    }

    private static String getLetter(String usedLetters, Scanner in) {
        String letter = "";
        boolean validInput = false;
        String validCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        while (!validInput) {
            System.out.print("Used Letters : " + displayUsedLetters(usedLetters));
            System.out.println();
            System.out.print("Please enter a letter: ");
            letter = in.nextLine();
            if (letter.length() == 1 && validCharacters.indexOf(letter) >= 0 && usedLetters.indexOf(letter) < 0) {
                validInput = true;
            } else {
                System.out.println("Invalid guess. You can only input ONE LETTER at a time which you have not already guessed previously.");
            }
        }
        return letter.toUpperCase();
    }

    private static String displayUsedLetters(String usedLetters) {
        String letters = "";
        for (int i = 0; i < usedLetters.length(); i++) {
            letters += usedLetters.substring(i, i+1) + " ";
        }
        return letters;
    }

    /**
     * "BASEBALL IS THE BEST SPORT OUT THERE"
     * _ A _ _ _ A _ _ / _ _ / _ _ _ / _ _ _ _ / _ _ _ _ _ / _ _ _ / _ _ _ _ _
     * 
     * @param sentence
     * @return
     */
    private static String encryptMessage(String sentence, String usedLetters) {
        String hiddenMessage = "";
        for (int i = 0; i < sentence.length(); i++) {
            String temp = sentence.substring(i, i + 1);
            if (temp.equals(" ")) {
                hiddenMessage += "/ ";
            } else if (usedLetters.indexOf(temp) < 0) {
                hiddenMessage += "_ ";
            } else if (usedLetters.indexOf(temp) >= 0) {
                hiddenMessage += temp + " ";
            }
        }
        return hiddenMessage;
    }
}
