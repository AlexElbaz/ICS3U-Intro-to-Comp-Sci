package week3;

import java.util.Scanner;

public class ReadingDataFromKeyboard {
    public static void main(String[] args) {
        exampleOne();
        exampleTwo();
        exampleThree();
    }

    private static void exampleThree() {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter a five digit integer: ");
        String number = in.nextLine();
        int sum = MoreFunctions.getSumVersion2(number);
        System.out.println(sum);
        in.close();
    }

    private static void exampleTwo() {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter a five digit integer: ");
        int number = Integer.parseInt(in.nextLine());
        int sum = MoreFunctions.getSum(number);     // We are accessing the function getSum we made from the class MoreFunctions. This is possible because we made the function getSum public in the class MoreFunctions.
        System.out.println(sum);
        in.close();
    }

    private static void exampleOne() {
        Scanner in = new Scanner(System.in);    // System.in is the default input device (keyboard)
        System.out.print("Please enter your favourite colour: ");
        String favouriteColour = in.nextLine();
        System.out.println(favouriteColour);
        in.close();
    }
}
