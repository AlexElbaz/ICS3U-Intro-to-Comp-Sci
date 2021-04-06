package week11;

public class Driver {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(10, 5);
        Rectangle r2 = new Rectangle(2, 2);

        System.out.println(r1.getArea());
        System.out.println(r1.getPerimeter());
        System.out.println(r1.isSquare());

        System.out.println();

        r1.setLength(r2.getLength());
        System.out.println(r1.getLength());
        r1.setWidth(r2.getWidth());
        System.out.println(r1.getWidth());
        System.out.println(r1.getArea());

        System.out.println();

        System.out.println(r2.isSquare());
        
        System.out.println();

        // Java by default will supply a no arguement constructor that does nothing (BUT call your parents no arguement constructor).
        //  Driver d = new Driver();

        // Java will ONLY create a Default Constructor if you do not create ANY constructors for the class.
        //  Rectangle r4 = new Rectangle();

        Box b1 = new Box(1, 2, 3);
        System.out.println(b1.getHeight());
        System.out.println(b1.getVolume());
        System.out.println(b1.getSurfaceArea());
        System.out.println(b1.isCube());
    }
}
