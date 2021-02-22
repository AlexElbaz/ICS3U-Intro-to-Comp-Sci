package week4;

public class IfStatements {
    public static void main(String[] args) {
        exampleOne();
    }

    /**
     * Use if statements to perform logic in your code.
     * 
     * if (<boolean expression>) {
     *      What you want to do when the expression is true.
     * }else if (<boolean expression>) {    // else the above expression was false and we want to check another condition
     *      What you want to do when the expression is true.
     *  }else {
     *      What you want to happen if everything was false.
     * }
     */
    private static void exampleOne() {
        int mark = 87;
        String letterGrade;

        if (mark >= 90) {
            letterGrade = "A+";
        }else if (mark >= 80) {
            letterGrade = "A";
        }else if (mark >= 70) {
            letterGrade = "B";
        }else if (mark >= 60) {
            letterGrade = "C";
        }else if (mark >= 50) {
            letterGrade = "D";
        } else {
            letterGrade = "F";
        }

        System.out.println("You got an " + letterGrade);
    }
}