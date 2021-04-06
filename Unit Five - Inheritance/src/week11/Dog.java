package week11;

public class Dog extends Animal {
    public Dog(int weight) {
        super(weight);
    }
    
    public void makeSound() {
        System.out.println("Woof!");
    }
}
