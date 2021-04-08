package week11.Interfaces;

public class Square implements Shape {
    private int side;

    @Override
    public double getPerimeter() {
        return side * 4;
    }

    @Override
    public double getArea() {
        return Math.pow(side, 2);
    }
    
}
