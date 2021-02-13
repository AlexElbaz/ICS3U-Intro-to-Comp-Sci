package week3;
import java.util.Scanner;

public class CrossCountryAssignment {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);    // assings the variable "in" to one of the built in scanners in java/vscode. This Scanner is created in the main function so that it can be passed into the various functions that require it later on in the program (to avoid creating multiple Scanners which tends to crash the program).

        addRun(in); // Calls the function addRun() which executes the vast majority of the program. This function only exists so that I can run the program the 3 required times without extensive copy pasting.
        addRun(in);
        addRun(in);

        in.close();
    }



    /**
     * This is the aforementioned addRun() function which runs the vast majority of the program.
     * It acts as the control centre for my program, declaring all the variables, calling all the functions, and printing the end run summary.
     * @param in is the Scanner created in the main function. As I mentioned earlier, it is passed from the main function to this function and then into all the individual functions that use it to avoid creating multiple Scanners which I have found crashes the program.
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
     * @param in is the same Scanner declared in the main function. It is finally put to use in this function, taking in user input from the keyboard.
     * @return This function returns the runner's full name in the form (First Last) (assuming that the user inputs their name correctly).
     */
    private static String getName(Scanner in) {
        System.out.print("Please input your full name (First Last): ");
        String name = in.nextLine();
        return name;
    }


    /**
     * This function prompts the user to input their time to a one of the three markers on their run (depending on what is stored in the marker variable) and returns their input.
     * @param in is once again the same Scanner from the main function. It is used again to detect and store keyboard input from the user.
     * @param name is the name of the user, gathered from the previous getName() function. This function takes in the name of to user and uses part of it (the first name) in the prompts, making the program less impersonal.
     * @param marker is a String created so that this function can be used to prompt the user for all three times gathered on their run rather than just one. In this program it holds one of the following phrases: "time to first mile", "time to second mile", "full run time", depending on which one is inputted when the function is called, allowing the function to ask for a specific time at a specific instance of the function, rather than only just one time at all instance of the function.
     * @return This fucntion returns the input from the user, which will either be their time to the first mile, second mile, or end of the run.
     */
    private static String getTime(Scanner in, String name, String marker) {
        System.out.print(name.substring(0, name.indexOf(" ")) + ", please input your " + marker + " (mm:ss.sss): ");
        String timeToMarker = in.nextLine();
        return timeToMarker;
    }


    /**
     * This function is used to determine the user's splits for their inputted run.
     * @param marker1 is the closer of the two markers inputted into the function to the beginning of the run.
     * @param marker2 is the further of the two markers inputted into the function from the beginning of the run.
     * Note: Unless it wasn't clear, in this program a marker represents one of three specific point in the run where the runners note their times (first mile, second mile, end of run).
     * @return This function returns a split time (for either the second or third split (which are the only ones requiring calculations) depending on which split the function was called for) formatted so that it will always be mm:ss.sss.
     */
    private static String getSplit(String marker1, String marker2) {
        int marker1Minutes = Integer.parseInt(marker1.substring(0, marker1.indexOf(":"))); // This line takes marker1, a time to a specific marker inputted by the user, cuts off everything from the colon onwards, and parses it as an integer, effectively getting the amount of minutes from the beginning of the run to that marker.
        int marker2Minutes = Integer.parseInt(marker2.substring(0, marker2.indexOf(":")));
        int splitMinutes = marker2Minutes - marker1Minutes;

        double marker1Seconds = Double.parseDouble(marker1.substring(marker1.indexOf(":") + 1)); // This does a similar set of actions to the previous comment (line 77), but instead of getting the minutes, it gets the seconds, cutting off everything from the beginning to the colon (including the colon), and parsing it as a double.
        double marker2Seconds = Double.parseDouble(marker2.substring(marker2.indexOf(":") + 1));
        if (marker1Seconds > marker2Seconds) { // This if statement is here so that if the subtration of seconds would result in a negative, it doesn't. (If it would result in a negative, this will subtract a minute from splitMinutes and add 60 to marker2 seconds, getting rid of any possibility for negatives as long as the inputs from the user are correct).
            splitMinutes--;
            marker2Seconds += 60;
        }
        double splitSeconds = marker2Seconds - marker1Seconds; 
        
        return String.format("%d:%06.3f", splitMinutes, splitSeconds);  //This line uses a special function to format the split minutes and seconds so that it will always be in the form mm:ss.sss.
    }
}