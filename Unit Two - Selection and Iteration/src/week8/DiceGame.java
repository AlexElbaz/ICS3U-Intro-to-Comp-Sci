package week8;

public class DiceGame {
    public static void main(String[] args) {
        GameCube playerOne = new GameCube();    // New is used to instantiate/construct an instance of a Class.
        GameCube playerTwo = new GameCube();    // GameCube is the class...
                                                // PlayerOne and PlayerTwo are objects (instances of the GameCube class).
        

        int numRolls = 10;
        int scoreOne = 0;
        int scoreTwo = 0;

        for (int i = 0; i < numRolls; i++) {
            playerOne.roll();
            playerTwo.roll();

            System.out.println("Player 1: " + playerOne.getTopSide());
            System.out.println("Player 2: " + playerTwo.getTopSide());

            if (playerOne.getTopSide() > playerTwo.getTopSide()) {
                scoreOne++;
                System.out.println("PLAYER ONE WON");
            } else if (playerOne.getTopSide() < playerTwo.getTopSide()) {
                scoreTwo++;
                System.out.println("PLAYER TWO WON");
            } else {
                System.out.println("IT WAS A TIE");
            }
        }

        System.out.println("---------FINAL SCORE-----------");
        System.out.println("Player One Score: " + scoreOne);
        System.out.println("Player Two Score: " + scoreTwo);
    }
}
