package ApFreeResponsePractice;

public class Meal {
    
    private String name;
    private double cost;

    public Meal(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
    
    public String toString() {
        return name + " meal, $" + cost;
    }
}
