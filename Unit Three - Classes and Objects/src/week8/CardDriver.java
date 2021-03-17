package week8;

public class CardDriver {
    public static void main(String[] args) {
        Card card1 = new Card("5", "Hearts");
        Card card2 = new Card("Ace", "Diamonds");

        System.out.println(card1.getSuit());
        
        
        // System.out.println(Card.getSuit()); // Java freaks out out. Cannot call a non-static method through the class name.

        System.out.println(card1.compareTo(card2));
        System.out.println(Card.compare(card1, card2));
            // Static version of the compareTo() method above. Notice how it is called by the
            //  class name (Card), while comparetTo() is called by an instance of the class.
    }
}
