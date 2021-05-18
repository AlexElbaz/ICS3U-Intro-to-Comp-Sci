package ApFreeResponsePractice;

public class DeluxeMeal extends Meal {

    private String sideDish;
    private String drink;

    public DeluxeMeal(String name, String sideDish, String drink, double cost) {
        super(name, cost + 3);
        this.sideDish = sideDish;
        this.drink = drink;
    }

    public String toString() {
        return "deluxe " + super.toString();
    }
    
}