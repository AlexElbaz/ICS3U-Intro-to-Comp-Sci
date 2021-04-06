package week11;

/**
 * If you do not explicitly create a constructor for a class, java will create a constructor
 * that looks like the one below. (This is why errors arise. If the parent function doesn't
 * have a no arguement constructor, the child class will freak out).
 * 
 * public Box() {
 *   super();
 * }
*/

// This means that a Box is a Rectangle.
// The parent class of the Box is the Rectangle.
public class Box extends Rectangle {

    private double height;

    public Box(double length, double width, double height) {
        super(length, width);   // If you call the parent constructor, you MUST do it FIRST!!!
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getVolume() {
        return super.getArea() * height;
            // Though the above line will work without the super., you should probably put it
            //  as if this class has a getArea method, the above line will use that,
            //  (like line 55 would use line 49's is square without super). 
            //  ONLY if it DOES NOT have a getArea method will it look for the parent's getArea().
    }

    public double getSurfaceArea() {
        return (super.getArea() * 2) + (getLength() * height * 2) + (getWidth() * height * 2);
    }
    
    public double getPerimeter() {
        return (super.getPerimeter() * 2) + (height * 4);

    }

    public boolean isSquare() {
        return false;
    }

    public boolean isCube() {
        return super.isSquare() && height == getLength();
    }
}
