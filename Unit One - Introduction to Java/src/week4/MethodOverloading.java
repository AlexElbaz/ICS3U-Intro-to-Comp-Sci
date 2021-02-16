package week4;

public class MethodOverloading {
    public void one(int x) {
        System.out.println(x);
    }

    public void one(int x, int y) {
        System.out.println(x);
    }

    /* Cannot create this method becasue it would be a duplicate method. Arguement list must be different.
    public void one(int y) {
        System.out.println(y);
    }
    */
}
