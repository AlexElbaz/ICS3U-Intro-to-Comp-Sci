package week8;

public class Card {
    private String face;
    private String suit;



    public Card(String face, String suit) {
        this.face = face;
        this.suit = suit;
            // this. refers to the specific instance of the class being called. The this is on
            //  the left of the equals sign because it is saying that this object's "name"
            //  (refering to the previously unassigned attribute "name") is now "name"
            //  (refering to the name inputted by the call of this class).
    }

    public String getFace() {
        // If static is not written in the function name, then the function is by default
        //  non-static.
        return face;
    }

    public String getSuit() {
        return suit;
    }

    /**
     * This refers to the instance of the Card that called compareTo().
     * 
     * @param c
     * @return
     */
    public int compareTo(Card c) {
        return getValue(this) - getValue(c);
    }

    /**
     * Static version of the above compareTo() method.
     * 
     * @param c1
     * @param c2
     * @return
     */
    public static int compare(Card c1, Card c2) {
        // return getValue(c1) - getValue(c2);
            // Static methods cannot make calls to non-static methods.

        return getCardValue(c1) - getCardValue(c2);
    }





    /**
     * Helper method because it is only accessible by the class and allows another method to work.
     *  It is helping another method in the class.
     * 
     * @param c the card inputted
     * @return the value of the face of the card.
     */
    private int getValue(Card c) {
        String face = c.face;

        try {
            int temp = Integer.parseInt(face);
            return temp;
        } catch(Exception e) {
            if (face.equals("Jack")) {
                return 11;
            } else if (face.equals("Queen")) {
                return 12;
            } else if (face.equals("King")) {
                return 13;
            } else {
                return 14;
            }
        }
    }

    /**
     * This is just a static version of the above method so that the static compare() can
     *  get the value of a card (as the static compare() CANNOT call non-static methods).
     * 
     * @param c
     * @return
     */
    private static int getCardValue(Card c) {
        String face = c.face;

        try {
            int temp = Integer.parseInt(face);
            return temp;
        } catch(Exception e) {
            if (face.equals("Jack")) {
                return 11;
            } else if (face.equals("Queen")) {
                return 12;
            } else if (face.equals("King")) {
                return 13;
            } else {
                return 14;
            }
        }
    }

    /**
     * The word static means that a method or attribute in a class BELONGS to the class.
     *  And you would call or activate the method or attribute through the CLASS NAME.
     * 
     * Non-static methods belong to an object (card1 and card2 are objects, Card is the class).
     *  card1.getSuit(); - calling the non-static getSuit() method through an INSTANCE of the Card class.
     */
}
