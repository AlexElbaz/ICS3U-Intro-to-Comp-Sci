package week3;

public class MoreFunctions {
    public static void main(String[] args) {
        int number = 83642;
        int sum = getSum(number);
        System.out.println(sum);

        sum = getSumVersion2(number);
        System.out.println(sum);

        String timeString = "5:34.221";
        double timeInSeconds = convertToSeconds(timeString);
        System.out.println(timeInSeconds);
    }

    /**
     * Converts a time into a double (seconds)
     * 
     * @param timeString time in the format of "mm:ss.sss"
     * @return converted time in seconds
     */
    private static double convertToSeconds(String timeString) {
        int minutesAsSeconds = Integer.parseInt(timeString.substring(0, timeString.indexOf(":"))) * 60;
        return minutesAsSeconds + Double.parseDouble(timeString.substring(timeString.indexOf(":") + 1));
    }

   /**
    * getSumVersion2 has been overloaded
    * Overloaded means there is more than 1 version of the function (with the same name)
    * The difference between the two (aka how Java can not freak out having two versions) is the arguement/parameter list
    * Notice that this version below, and the one below that, have difference input data types. One takes an int and the other takes a String.
    * Java can determing which function to use based on the data type you input. If you input an integer, it will run the first version of the function, if you input a String, it will run the second version.
    * 
    * @param number takes an integer number. The version below takes a string of numbers.
    * @return Returns the second version of the function, basically just making it so that you can add the individual digits of a number no matter if you input a String or an Integer
    */
    public static int getSumVersion2(int number) {
        String numberAsString = "" + number;
        
        return getSumVersion2(numberAsString);
    }

    public static int getSumVersion2(String number) {
        String numberAsString = "" + number;
        int digit1 = Integer.parseInt(numberAsString.substring(0, 1));
        int digit2 = Integer.parseInt(numberAsString.substring(1, 2));
        int digit3 = Integer.parseInt(numberAsString.substring(2, 3));
        int digit4 = Integer.parseInt(numberAsString.substring(3, 4));
        int digit5 = Integer.parseInt(numberAsString.substring(4, 5));
        
        return digit1 + digit2 + digit3 + digit4 + digit5;
    }

    /**
     * The method will return the sum of the 5 individual digits of a 5 digit number
     * Ex. 12346 --> 13
     * 
     * @param number
     * @return the sum of the individual digits of number
     */
    public static int getSum(int number) {
        int digit1 = number / 10000;
        int digit2 = number / 1000 % 10;
        int digit3 = number / 100 % 10;
        int digit4 = number / 10 % 10;
        int digit5 = number % 10;
        
        return digit1 + digit2 + digit3 + digit4 + digit5;
    }
}
