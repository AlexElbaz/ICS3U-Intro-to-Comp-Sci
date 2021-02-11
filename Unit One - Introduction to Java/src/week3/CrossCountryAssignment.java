package week3;

import java.util.Scanner;

public class CrossCountryAssignment {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);


        String name = getName(in);
        String firstSplit = getTimeToFirstMile(in, name);
        String timeToSecondMile = getTimeToSecondMile(in, name);
        String fullRunTime = getFullRunTime(in, name);
        String secondSplit = getSecondSplit(firstSplit, timeToSecondMile);
        String thirdSplit = getThirdSplit(timeToSecondMile, fullRunTime);

        System.out.println("");
        System.out.println("--------------------------");
        System.out.println(name + "'s Run Summary");
        System.out.println("--------------------------");
        System.out.println("Total run time: " + fullRunTime);
        System.out.println("First split: " + firstSplit);
        System.out.println("Second split: " + secondSplit);
        System.out.println("Third split: " + thirdSplit);

        in.close();
    }

    private static String getName(Scanner in) {
        System.out.print("Please input your full name (First Last): ");
        String name = in.nextLine();
        return name;
    }

    private static String getTimeToFirstMile(Scanner in, String name) {
        System.out.print(name.substring(0, name.indexOf(" ")) + ", please input your time to the first mile (mm:ss.sss): ");
        String timeToFirstMile = in.nextLine();
        return timeToFirstMile;
    }
    
    private static String getTimeToSecondMile(Scanner in, String name) {
        System.out.print(name.substring(0, name.indexOf(" ")) + ", please input your time to the second mile (mm:ss.sss): ");
        String timeToSecondMile = in.nextLine();
        return timeToSecondMile;
    }

    private static String getFullRunTime(Scanner in, String name) {
        System.out.print(name.substring(0, name.indexOf(" ")) + ", please input your full run time (mm:ss.sss): ");
        String fullRunTime = in.nextLine();
        return fullRunTime;
    }

    private static String getSecondSplit(String firstSplit, String timeToSecondMile) {
        int firstMileMinutes = Integer.parseInt(firstSplit.substring(0, firstSplit.indexOf(":")));
        int startToSecondMileMinutes = Integer.parseInt(timeToSecondMile.substring(0, timeToSecondMile.indexOf(":")));
        int secondSplitMinutes = startToSecondMileMinutes - firstMileMinutes;

        double firstMileSeconds = Double.parseDouble(firstSplit.substring(firstSplit.indexOf(":") + 1));
        double startToSecondMileSeconds = Double.parseDouble(timeToSecondMile.substring(timeToSecondMile.indexOf(":") + 1));
        if (firstMileSeconds > startToSecondMileSeconds) {
            secondSplitMinutes--;
            startToSecondMileSeconds += 60;
        }
        double secondSplitSeconds = startToSecondMileSeconds - firstMileSeconds; 
        
        if (secondSplitSeconds < 10) {
            return secondSplitMinutes + ":0" + Math.round(secondSplitSeconds * 1000) / 1000.0;
        } else {
            return secondSplitMinutes + ":" + Math.round(secondSplitSeconds * 1000) / 1000.0;
        }
    }

    private static String getThirdSplit(String timeToSecondMile, String fullRunTime) {
        int startToSecondMileMinutes = Integer.parseInt(timeToSecondMile.substring(0, timeToSecondMile.indexOf(":")));
        int fullRunMinutes = Integer.parseInt(fullRunTime.substring(0, fullRunTime.indexOf(":")));
        int thirdSplitMinutes = fullRunMinutes - startToSecondMileMinutes;

        double startToSecondMileSeconds = Double.parseDouble(timeToSecondMile.substring(timeToSecondMile.indexOf(":") + 1));
        double fullRunSeconds = Double.parseDouble(fullRunTime.substring(fullRunTime.indexOf(":") + 1));
        if (startToSecondMileSeconds > fullRunSeconds) {
            thirdSplitMinutes--;
            fullRunSeconds += 60;
        }

        double thirdSplitSeconds = fullRunSeconds - startToSecondMileSeconds;
         
        if (thirdSplitSeconds < 10) {
            return thirdSplitMinutes + ":0" + Math.round(thirdSplitSeconds * 1000) / 1000.0;
        } else {
            return thirdSplitMinutes + ":" + Math.round(thirdSplitSeconds * 1000) / 1000.0;
        } 
    }
}
