package week1;

public class VariablesAndAssignmentOperatorsHomework {
    public static void main(String[] args) {
        questionOne();
        questionTwo();
        questionThree();
        questionFour();
        questionFive();
        questionSix();

    }

    private static void questionSix() {
        double a, b, c, x1, x2;
        a = 1;
        b = 0;
        c = 0;
        x1 = (-b + Math.sqrt(Math.pow(b, 2) - 4*a*c)) / (2*a);
        x2 = (-b - Math.sqrt(Math.pow(b, 2) - 4*a*c)) / (2*a);
        if (Double.isNaN(x1) && Double.isNaN(x2)) {
            System.out.println("There are no roots");
        } else {
            System.out.println("The roots are " + x1 + " & " + x2);
        }
    }

    private static void questionFive() {
        double pennies, nickles, dimes, quarters, loonies, toonies, money;
        pennies = 15;
        nickles = 7;
        dimes = 3;
        quarters = 12;
        loonies = 3;
        toonies = 8;
        money = (0.01*pennies) + (0.05*nickles) + (0.1*dimes) + (0.25*quarters) + (loonies) + (2*toonies);
        System.out.println("The total balance is $" + money);
    }

    private static void questionFour() {
        double x1, x2, y1, y2, slope;
        x1 = 2;
        y1 = 3;
        x2 = 5;
        y2 = 7;
        slope = (y2 - y1)/(x2 - x1);
        System.out.println("The slope of the line is " + slope);
    }

    private static void questionThree() {
        double a, b, c, x, y;
        a = 3;
        b = 7;
        c = 13.2;
        x = 5.43;
        y = a * Math.pow(x, 2) + b * x + c;
        System.out.println("The answer to the equation is " + y);
    }

    private static void questionTwo() {
        double radius, volume;
        radius = 10;
        volume = 4.0/3.0 * Math.PI * Math.pow(radius, 3);
        System.out.println("The volume of the sphere is " + volume);
    }

    private static void questionOne() {
        double radius, area;
        radius = 10;
        area = Math.PI * Math.pow(radius, 2);
        System.out.println("The area of the circle is " + area);
    }
}
