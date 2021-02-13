package week3;
import java.util.Scanner;

public class ObsoleteCrossCountryProject {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);    // assings the variable "in" to one of the built in scanners in java/vscode. I create the one scanner here and then call it when it is required (rather then creating multiple) because creating multiple scanners in quick succession crashes the program.

        addRun(in); // Calls the function addRun() which executes the vast majority of the program. This function is only created so that I can run the program the 3 required times without extensive copy pasting.
        addRun(in);
        addRun(in);

        in.close();
    }

    /**
     * This is the aforementioned addRun() function which runs the vast majority of the program.
     * It acts as the control centre for my program, declaring all the variables, calling all the functions, and printing the end run summary.
     * @param in is the Scanner the function takes in from the main function. The function passes "in" into all the other functions that require it so that they can all access the same scanner.
     */
    private static void addRun(Scanner in) {
        String name = getName(in);
        String firstSplit = getTime(in, name, "time to the first mile");
        String timeToSecondMile = getTime(in, name, "time to the second mile");
        String totalTime = getTime(in, name, "full run time");
        String secondSplit = getSplit(firstSplit, timeToSecondMile);
        String thirdSplit = getSplit(timeToSecondMile, totalTime);

        System.out.println("");
        System.out.println("--------------------------");
        System.out.println(name + "'s Run Summary");
        System.out.println("--------------------------");
        System.out.println("Total run time: " + totalTime);
        System.out.println("First split: " + firstSplit);
        System.out.println("Second split: " + secondSplit);
        System.out.println("Third split: " + thirdSplit);
        System.out.println("--------------------------");
        System.out.println("");
    }

    /**
     * This function provokes the user to input their full name and then returns their input.
     * @param in is the Scanner, passed in from addRun(), which is finally put to use detecting and storing keyboard input.
     * @return This function returns the runner's full name in the form (First Last) (assuming that the user inputs their name correctly).
     */
    private static String getName(Scanner in) {
        System.out.print("Please input your full name (First Last): ");
        String name = in.nextLine();
        return name;
    }

    /**
     * This function provokes the user to input their time to a certain marker on their run depending on what marker (a string variable) is input (either "time to first mile", "... second mile", "... full run") and returns their input.
     * @param in is once again the Scanner passed in from addRun(). It is once again used to detect and store keyboard input
     * @param name is the name of the user, gathered from the previous getName() function. This function takes in the name of to user so that it can use that name to ask for the required data, making it seem less impersonal.
     * @param marker is a String which in this case holds a phrase that determines what the user will input (either "time to first mile", "... second mile", "... full run"). This is done so that I can use the same function to gather all three times from the user rather than having three seperate functions.
     * @return This fucntion returns the input from the user, which will either be their time to the first mile, second mile, or the end of the run.
     */
    private static String getTime(Scanner in, String name, String marker) {
        System.out.print(name.substring(0, name.indexOf(" ")) + ", please input your " + marker + " (mm:ss.sss): ");
        String timeToMarker = in.nextLine();
        return timeToMarker;
    }

    /**
     * 
     * @param marker1
     * @param marker2
     * @return
     */
    private static String getSplit(String marker1, String marker2) {
        int marker1MinutesInSeconds = (Integer.parseInt(marker1.substring(0, marker1.indexOf(":")))) * 60; // This line takes marker1, a time given by the user, and cuts off everything from the colon onwards, and parsing it as an integer, effectively getting the minutes for the time to that marker.
        double marker1Seconds = Double.parseDouble(marker1.substring(marker1.indexOf(":") + 1)); // This does a similar thing to the previous comment, but instead of getting the minutes it gets the seconds, cutting off everything from the beginning to the colon (including the colon), and parsing it as a double.
        double marker1TotalTimeInSeconds = marker1MinutesInSeconds + marker1Seconds;
        
        int marker2MinutesInSeconds = (Integer.parseInt(marker2.substring(0, marker2.indexOf(":")))) * 60;
        double marker2Seconds = Double.parseDouble(marker2.substring(marker2.indexOf(":") + 1));
        double marker2TotalTimeInSeconds = marker2MinutesInSeconds + marker2Seconds;
        
        double splitTotalTimeInSeconds = marker2TotalTimeInSeconds - marker1TotalTimeInSeconds;

        int splitMinutes = (int) splitTotalTimeInSeconds / 60;
        double splitSeconds = splitTotalTimeInSeconds % 60;
        
        return String.format("%d:%06.3f", splitMinutes, splitSeconds);
    }
}
