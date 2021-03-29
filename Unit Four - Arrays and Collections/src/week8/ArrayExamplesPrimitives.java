public class ArrayExamplesPrimitives {
    public static void main(String[] args) {
        exampleOne();
        exampleTwo();
    }


    private static void exampleTwo() {
        int[] nums = {7, 2, 27, -98, 113}; 
            // Curly braces initialize an array and assign values to it all in one step.
        
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        System.out.println(sum);

        sum = 0;
        for (int num : nums) {
            sum += num;
        }
        System.out.println(sum);
    }


    /**
     * Declare an array of primitive ints
     * Access the elements in the array of ints
     */
    private static void exampleOne() {
        // Side note, this method must be static because static methods can only call other
        //  static methods, and main (a static method) is calling this method.

        int[] numbers = new int[5];    // 1. We have declared a reference to an int array.
                                       // 2. Assigned a variable to an int array of size/length 5.

         // Arrays have NO methods, it is NOT a class.
         numbers[0] = 7;
         numbers[1] = 2;
         numbers[2] = 12;
         numbers[3] = -87;

         // Length is a property of arrays which identifies the number of elements it can hold.
         System.out.println("The length (size) of the array is:" + numbers.length);
    }
}
