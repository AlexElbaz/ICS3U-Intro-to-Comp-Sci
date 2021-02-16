package week3;
import java.util.Scanner;

public class CrossCountryAssignment {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);    
            // ^^ Assings the variable "in" to one of the built in scanners in java/vscode.
            //  This Scanner is created in main so that it can be passed into functions that require it later on in the program 
            //   (to avoid creating multiple Scanners which tends to crash the program).

        addRun(in); 
            // ^^ Calls the function addRun() which executes the vast majority of the program.
            //  This function only exists so that I can run the program the 3 required times without extensive copy pasting.
        addRun(in);
        addRun(in);

        in.close();
    }



    /**
     * This is the aforementioned addRun() function which runs the vast majority of the program.
     * It acts as the control centre for my program, declaring all the variables, calling all the functions, and printing the end run summary.
     * @param in is the Scanner created in the main function. As I mentioned earlier, it is passed from the main function to this function
     *  to all the individual functions that use it to avoid creating multiple Scanners which often crashes the program.
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
     * This function prompts the user to input their full name and then returns their input.
     * @param in is the same Scanner declared in the main function. It is finally put to use in this function,
     *  taking in user input from the keyboard.
     * @return This function returns the runner's full name in the form (First Last) (assuming that the user inputs their name correctly).
     */
    private static String getName(Scanner in) {
        System.out.print("Please input your full name (First Last): ");
        String name = in.nextLine();
        return name;
    }


    /**
     * This function prompts the user to input their time to a one of the three markers (depending on what is stored in the marker variable)
     *  and returns their input.
     * @param in is once again the same Scanner from the main function. It is used again to detect and store keyboard input from the user.
     * @param name is the name of the user, gathered from the getName() function. This function uses it in the prompts to make the program
     *  less impersonal.
     * @param marker is a String that allows this function to prompt the user for all three times gathered on their run rather than just one.
     *  In this program it holds one of the following phrases: "time to first mile", "time to second mile", "full run time", depending on which
     *   one is inputted when the function is called, allowing the function to ask for a specific time at a specific instance of the function,
     *   rather than only just one time at all instance of the function.
     * @return This fucntion returns the input from the user, which will either be their time to the first mile, second mile, or end of the run.
     */
    private static String getTime(Scanner in, String name, String marker) {
        System.out.print(name.substring(0, name.indexOf(" ")) + ", please input your " + marker + " (mm:ss.sss): ");
        String timeToMarker = in.nextLine();
        return timeToMarker;
    }


    /**
     * This function is used to determine the user's splits for their inputted run.
     * @param startMarker is a variable representing the time from the beginning of the run to the point which marks the start of a given split (the closer of the two markers to the beginning of the run).
     * @param endMarker is a variable representing the time from the beginning of the run to the point which marks the end of a given split (the further of the two markers from the beginning of the run).
     * @return This function returns a split time (for either the second or third split (which are the only ones requiring calculations)
     *  depending on which split the function was called for) formatted so that it will always be mm:ss.sss.
     */
    private static String getSplit(String startMarker, String endMarker) {
        int startMarkerMinutes = Integer.parseInt(startMarker.substring(0, startMarker.indexOf(":")));
            // ^^ This line takes startMarker, a time to a specific marker inputted by the user, cuts off everything from the colon onwards,
            //  and parses it as an integer, effectively getting the amount of minutes from the beginning of the run to that marker.
        int endMarkerMinutes = Integer.parseInt(endMarker.substring(0, endMarker.indexOf(":")));
        int splitMinutes = endMarkerMinutes - startMarkerMinutes;

        double startMarkerSeconds = Double.parseDouble(startMarker.substring(startMarker.indexOf(":") + 1));
            // ^^ This does a similar set of actions to the previous comment (line 93/94), but instead of getting the minutes,
            //  it gets the seconds, cutting off everything from the beginning to the colon (including the colon), and parsing it as a double.
        double endMarkerSeconds = Double.parseDouble(endMarker.substring(endMarker.indexOf(":") + 1));
        
        if (startMarkerSeconds > endMarkerSeconds) {
            splitMinutes--;
            endMarkerSeconds += 60;
        }
            // ^^ This if statement is here so that if the subtration of seconds would result in a negative, it doesn't.

        double splitSeconds = endMarkerSeconds - startMarkerSeconds;
        
        return String.format("%d:%06.3f", splitMinutes, splitSeconds);
            // ^^ This line uses a special function to format the split minutes and seconds so that it will always be in the form mm:ss.sss.
    }
}