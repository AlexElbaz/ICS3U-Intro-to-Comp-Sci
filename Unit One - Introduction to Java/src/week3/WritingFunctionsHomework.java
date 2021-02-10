package week3;

public class WritingFunctionsHomework {
    
    public static void main(String[] args) {
        
        double cost = 985.00;
        double tax = 5.5; 
        double totalPurchasePrice = getTotalPrice(cost, tax);
        System.out.println("The total purchase price is $" + totalPurchasePrice);

        double length = 4.5;
        double width = 2.3;
        double area = getArea(length, width);
        double perimeter = getPerimeter(length, width);
        System.out.println("Perimeter = " + perimeter + " feet");
        System.out.println("Area = " + area + " feet");

        int minutesInYear = 365 * 24 * 60;
        System.out.println("The amount of minutes in a year is" + minutesInYear);

        int days = 365;
        double distanceTravelled = getLightDistance(days);
        System.out.println("The distance light travels in a year is " + distanceTravelled + "km");

        int wonGames = 110;
        int lostGames = 44;
        double winPercentage = getWinPercentage(wonGames, lostGames);
        System.out.println("[INSTERT TEAM NAME HERE]'s win percentage is " + winPercentage + "%");

        int mass = 10;
        int speed = 12;
        double momentum = getMomentum(mass, speed);
        System.out.println("The momentum of the object is " + momentum + "kg.m/s");

        double degreesFahrenheit = 98.0;
        double degreesCelsius = convertToCelsius(degreesFahrenheit);
        System.out.println("The temperature is " + degreesCelsius + "C");

        double positiveNumber = 36;
        double squaredNumber = Math.pow(positiveNumber, 2);
        double squareRootedNumber = Math.sqrt(positiveNumber);
        System.out.println("The number squared is " + squaredNumber);
        System.out.println("The number square rooted is " + squareRootedNumber);

        int itemsSold = 25;
        double payDue = getPayDue(itemsSold);
        System.out.println("The pay due is $" + payDue);

        // Question 10 is effectively the same as question two given the method in which I solved question 2.

        double kMass = 12.5;
        double kSpeed = 20.0;
        double kineticEnergy = getKineticEnergy(kMass, kSpeed);
        System.out.println("The kinetic energy of the moving object is " + kineticEnergy);
    }

    private static double getKineticEnergy(double kMass, double kSpeed) {
        return ((double) 1/2) * kMass * Math.pow(kSpeed, 2);
    }

    private static double getPayDue(int itemsSold) {
        return itemsSold * 0.27;
    }

    private static double convertToCelsius(double degreesFahrenheit) {
        return (5 * (degreesFahrenheit - 32)) / 9;
    }

    private static double getMomentum(int mass, int speed) {
        return (double) mass * speed;
    }

    private static double getWinPercentage(int wonGames, int lostGames) {
        return (double) wonGames / (wonGames + lostGames) * 100;
    }

    private static double getLightDistance(int days) {
        return ((double)300000000 / 1000) * 60 * 60 * 24 * days;
    }

    private static double getArea(double length, double width) {
        return length * width;
    }

    private static double getPerimeter(double length, double width) {
        return 2 * (length + width);
    }

    private static double getTotalPrice(double cost, double tax) {
        return cost * ((tax / 100) + 1);
    }
}
