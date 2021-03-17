package week8;

/**
 * The GameCube will model a simple Die that could be used for a board game, a dice game,
 *  or any game that requires a random number.
 * 
 * The calss will contain:
 *  State: the current state of this GameCube.
 *  Behaviour: the things that we can do with the GameCube (should be public, you want people to be able to use the GameCube methods).
 */
public class GameCube {
   private int topSide;     // attributes for a GameCube (private, people do not need to acess these here).
   private int numSides;    // instance variables (used to identify the current state of the object).

   /**
    * Method to create a game cube - create a constructor.
    * The purpose of the constructor is to create an instance of the class.
    * In that way, the purpose is to set the initial state of the object.
    * (In java) the constructor is a method with the same name as the class.
    * 
    * Return type of constructor is missing and is not needed.
    * When the method is done the user will recieve an instance of a GameCube.
    *
    * The constructor's job is to initialize/instantiate the state of the object and
    *  return an instance of the class.
    */
    public GameCube(int numberOfSides) {
        numSides = numberOfSides;
        roll();
    }

    /**
     * Overloaded the constructor.
     * The no arguement constructor is sometimes called the Default constructor.
     */
    public GameCube() {
        numSides = 6;
        roll();
    }

    /**
     * When you roll a GameCube, you are just setting the top side.
     */
    public void roll() {
        topSide = (int) (Math.random() * numSides + 1);
    }

    /**
     * @return the value of the topside for the GameCube.
     */
    public int getTopSide() {
        return topSide;
    }
}
