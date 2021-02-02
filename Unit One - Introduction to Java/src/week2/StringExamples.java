package week2;

public class StringExamples {
    public static void main(String[] args) {
        exampleOne();
        exampleTwo();
        exampleThree();
    }



    private static void exampleThree() {
        String s1 = new String("Steve");         // Builds a new string EVERYTIME
        String s2 = new String("Steve");
        String s3 = "Steve";                   // If you declare a String like we did for s3 using String a = "Blah blah" (not using constructor) then do the same for s4, making the strings for s3 and s4 identical, it reuses the string, it does not create a new one like the contructor method. That is why (s3 == s4) returns true where (s1 == s2) does not.
        String s4 = "Steve";                  // String literal - Java stores all the String literals (strings created using String a = "blah blah") in a table and reuses them, as mentioned above (line 15).

        System.out.println(s1 == s2);       // == is the equality operator, = is the assignment operator. Also note that s1 == s2 is incorrect for comparing strings. It returns false because s1 is not the string "Steve", it is a reference to it. i.e. s1 and s2 are referencing different strings and therefore not equal.
        System.out.println(s3 == s4);      // Using == to compare String literals returns true, mentioned on line 15 comment.

        System.out.println(s1.equals(s2));  // checks if 2 strings are equal through the proper method (.equals method).
    }

    private static void exampleTwo() {
        String courseCode = "ICS3U AP";
        int x = courseCode.length();
        String sub = courseCode.substring(2, 5);   // Returns "S3U". Stars at index 2 and ends at index 4 (2 inclusive 5 exclusive) --> [2, 5) --> 2 <= x < 5
        String sub2 = courseCode.substring(2);    // Returns "S3U AP". The first paramater (the only one given in this case) refers to the starting index. If, like in this example, there is only one parameter present, the substring function will begin at the index of the paramater given (2) and go until the end of the string.
        int inOf = courseCode.indexOf("S");      // Returns 2. .indexOf will look for the first instance of the character or characters you input and return the index of that character, or the index first of those characters. If a character appears twice, it will return ONLY the index of the first instance of that character. If a character does not appear in the searched String, it will return -1.

        System.out.println("The length of \"" + courseCode + "\" is: " + x);
        System.out.println(sub);
        System.out.println(sub2);
        System.out.println(inOf);
    }

    private static void exampleOne() {
        String simpleText = "This is a String.";
        int number = 7;

        System.out.println(simpleText);
        System.out.println(number);
    }
}
