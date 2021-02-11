package week3;
import java.util.Scanner;

public class CrossCountryAssignment {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        addRun(in);
        addRun(in);
        addRun(in);

        in.close();
    }

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

    private static String getName(Scanner in) {
        System.out.print("Please input your full name (First Last): ");
        String name = in.nextLine();
        return name;
    }

    private static String getTime(Scanner in, String name, String marker) {
        System.out.print(name.substring(0, name.indexOf(" ")) + ", please input your " + marker + " (mm:ss.sss): ");
        String timeToMarker = in.nextLine();
        return timeToMarker;
    }

    private static String getSplit(String marker1, String marker2) {
        int marker1Minutes = Integer.parseInt(marker1.substring(0, marker1.indexOf(":")));
        int marker2Minutes = Integer.parseInt(marker2.substring(0, marker2.indexOf(":")));
        int splitMinutes = marker2Minutes - marker1Minutes;

        double marker1Seconds = Double.parseDouble(marker1.substring(marker1.indexOf(":") + 1));
        double marker2Seconds = Double.parseDouble(marker2.substring(marker2.indexOf(":") + 1));
        if (marker1Seconds > marker2Seconds) {
            splitMinutes--;
            marker2Seconds += 60;
        }
        double splitSeconds = marker2Seconds - marker1Seconds; 
        
        return String.format("%d:%06.3f", splitMinutes, splitSeconds);
    }
}