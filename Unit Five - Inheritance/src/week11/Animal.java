package week11;

public abstract class Animal {
    private int weight;

    public Animal(int weight) {
        this.weight = weight;
    }

    public abstract void makeSound(); // Abstract methods do not need a body ({}).

    public void eat() {
        weight++;
    }

    public int getWeight() {
        return weight;
    }
}
