package week4;

public class CompareTo {
    public static void main(String[] args) {
        String s1 = "Hello"; // String literal
        String s2 = "Goodbye";
        String s3 = new String("Hello"); // reference to a String
        String s4 = "Hello";
        String s5 = "Zoo";
        String s6 = "Zzo";
        String s7 = "ZzoA";

        // review of equals method
        System.out.println(s1.equals(s2)); // equals checks if s1 and s2 have the same sequence of characters
        System.out.println(s1.equals(s3));
        System.out.println(s1 == s3); // == checks if they share the same reference (Here they do not given that one is a reference and one is a String literal)
        System.out.println(s1 == s4); // == checks if they share the same reference (Here they do - they are two String literals)



        System.out.println(s1.compareTo(s3)); // Two strings that are equal will return 0.
        System.out.println(s1.compareTo(s2)); // Hello > Goodbye because the compareTo method compares the first letters of each String using their ASCII table values. Since H is further in the alphabet than G, it has a greater value on the ASCII table, making Hello > Goodbye.
        System.out.println(s1.compareTo(s5)); // Hello < Zoo because 'H' < 'Z' on the ASCII tbale (H = 72, Z = 90). Returns -18, because the difference between 90 and 72 is 18.
        System.out.println(s5.compareTo(s1));
        System.out.println(s5.compareTo(s6)); // Checks first character, sees that they are the same, so it checks the second character, and sees that 'o' < 'z', so it returns a negative.
        System.out.println(s6.compareTo(s7)); // s6 and s7 are the same except s7 has more characters and is therefore larger, so it returns a negative.
    }
}