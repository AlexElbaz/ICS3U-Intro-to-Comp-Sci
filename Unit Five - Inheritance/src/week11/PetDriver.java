package week11;

public class PetDriver {
    public static void main(String[] args) {
        // Animal a1 = new Animal(10);   // Can no longer construct an Animal because the Animal class is now abstract.
        Animal d1 = new Dog(20);         // You are allowed to make a new Dog under the Animal class because a Dog is an Animal (Animal is Dog's parent class).
        Animal c1 = new Cat(5);          // ^^ Same.
            // When you make a reference to a parent class and declare it as a child class as
            //  we did above, it is called POLYMORPHISM!

        d1.makeSound();
            // If you were to take the empty makeSound method out of the Animal class this code
            //  would not complie. This is because while compiling, the code looks at this and
            //  goes "Animals do not have makeSound, therefore this does not work". However,
            //  at runtime, Java checks the instance of the Animal and uses that version of
            //  makeSound. This thing that Java does where it knows which method to use is called 
            //  dynamic binding.
        c1.makeSound();
        c1 = d1;            // Because both c1 and d1 are extensions of Animal, you can swap them.
        c1.makeSound();     // Now this line will print Woof! because c1 is now a dog.
    }
}
