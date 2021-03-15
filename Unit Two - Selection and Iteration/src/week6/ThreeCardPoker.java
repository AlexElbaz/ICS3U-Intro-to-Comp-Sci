package week6;

import java.util.Scanner;

public class ThreeCardPoker {

    private static final int NUM_SUITS = 4;
    private static final int DIAMONDS = 0;
    private static final int  HEARTS = 1;
    private static final int SPADES = 2;
    private static final int CLUBS = 3;

    private static final int NUM_FACES = 13;
    private static final int JACK = 11;
    private static final int QUEEN = 12;
    private static final int KING = 13;
    private static final int ACE = 14;

    private static final int MIN_MONEY_TO_PLAY = 100;
        // You need at least $100 to make an ante bet and still be able to bet on your hand.
    private static final int MIN_BET = 50;
    private static final int MAX_BET = 100;

    /**
     * I decided to rate hands using an integer based on what combination (pair, flush, etc.)
     *  they had. To avoid magic numbers I assigned integers to combinations in ascending order
     * of poker ranking. i.e. straight flush is best hand, so it gets the highest integer value.
     */
    private static final int STRAIGHT_FLUSH = 5;
    private static final int THREE_OF_A_KIND = 4;
    private static final int STRAIGHT = 3;
    private static final int FLUSH = 2;
    private static final int PAIR = 1;

    private static final int STRAIGHT_FLUSH_PAYOUT = 41;
        // Though the Straight Flush payout is 40 to 1, this variable (and the others to the
        //  line break below) are 1 higher than their respective payouts. This is because I
        //  subtract the bet right after they make it so when they cash out they get 1 times
        //  more than the payout to effectively get their original bet back.
    private static final int THEE_OF_A_KIND_PAYOUT = 31;
    private static final int STRAIGHT_PAYOUT = 6;
    private static final int FLUSH_PAYOUT = 4;
    private static final int PAIR_PAYOUT = 2;

    private static final int PLAY_BET_PAYOUT = 4;
        // The full payout for winning the round is your ante bet times two (you get your
        //  original bet back and then 1 more times your bet) and your play bet times two
        //  (for the same reason as your ante). Since your play bet and your ante bet are
        //  required to be the same, the full payout for winning the round is basically 4
        //  times either your play bet or your ante bet. 


    private static final int CARD_ONE = 1;
    private static final int CARD_TWO = 2;
    private static final int CARD_THREE = 3;
    




    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        boolean playAgain = false;
        boolean validInput = false;
        boolean doneOnce = false;

        while (!validInput) {
            if (!doneOnce) {
                System.out.print("Would you like to play a game of poker (Y/N)? ");
                doneOnce = true;
            }

            String wantsToPlay = in.nextLine().toUpperCase();

            if (wantsToPlay.equals("Y")) {
                playAgain = true;
                validInput = true;
            } else if (wantsToPlay.equals("N")) {
                System.exit(0);
            } else {
                System.out.print("Please input only Y or N: ");
            }
        }

        int money = getStartingMoney(in); 
        System.out.println();
        System.out.println("You currently have $" + money + ".");
        System.out.println();
        
        while (playAgain == true) {
            if (money < MIN_MONEY_TO_PLAY) {
                System.out.println("Sorry, you do not have the minimum amount needed to play.");
                System.exit(0);
            }

            int pairPlusBet = getPairPlus(in, money);
            money -= pairPlusBet;
                // I chose to subtract this bet (and all the following bets) from the player's
                //  wallet right away. This is mainly to avoid having to subtract money from
                //  the player later and instead only have to add money.
            int anteBet = getAnte(in, money);
            money -= anteBet;
            System.out.println();

            String playerHand = dealCards();
            System.out.println("Your hand is: " + playerHand);
            System.out.println("The dealer's hand is: ?? ?? ??");
            System.out.println();

            String dealerHand = dealCards();

            int playBet = getplayBet(in, anteBet);
            money -= playBet;

            int playerFirstCardNumber = getCardNumber(playerHand, CARD_ONE);
            int playerSecondCardNumber = getCardNumber(playerHand, CARD_TWO);
            int playerThirdCardNumber = getCardNumber(playerHand, CARD_THREE);
            int playerHighCard = getHighCard(playerFirstCardNumber, playerSecondCardNumber, playerThirdCardNumber);
            int playerHandRating = rateHand(playerFirstCardNumber, playerSecondCardNumber, playerThirdCardNumber, playerHand);

            int dealerFirstCardNumber = getCardNumber(dealerHand, CARD_ONE);
            int dealerSecondCardNumber = getCardNumber(dealerHand, CARD_TWO);
            int dealerThirdCardNumber = getCardNumber(dealerHand, CARD_THREE);
            int dealerHighCard = getHighCard(dealerFirstCardNumber, dealerSecondCardNumber, dealerThirdCardNumber);
            int dealerHandRating = rateHand(dealerFirstCardNumber, dealerSecondCardNumber, dealerThirdCardNumber, dealerHand);

            System.out.println();
            System.out.println("Your hand is: " + playerHand);
            System.out.println("The dealer's hand is: " + dealerHand);
            System.out.println();

            if (playBet > 0) {
                // If the player's playBet is > 0, also known as the player decided to bet on
                //  their hand, then check further.
                if (dealerHighCard <= JACK && dealerHandRating < PAIR) {
                    // If the dealer has a hand of Jack-high or worse, apply rule A (only give
                    // playBet back), and don't compare the hands.
                    money += playBet;
                    System.out.println("Unfortunately the dealer didn't qualify. You get your play bet of $" + playBet + " back, but lose your ante bet of $" + anteBet + ".");
                } else {
                    // If the dealer's hand qualifies then compare the hands.
                    int playerWinnings = compareHands(playerHandRating, dealerHandRating , playerFirstCardNumber, playerSecondCardNumber, playerThirdCardNumber, dealerFirstCardNumber, dealerSecondCardNumber, dealerThirdCardNumber, playerHighCard, dealerHighCard, playBet);
                    money += playerWinnings; // Add the player's play winnings to their wallet
                    if (playerWinnings >= playBet * PLAY_BET_PAYOUT) {
                        // If the player won the round then say that they won and what they won.
                        System.out.println("You won the round! You got $" + playerWinnings/2 + "!");
                    } else if (playerWinnings == playBet) {                  
                        // If the player and dealer tied then say that they tied.
                        //  (the winnings will just be the original playBet because if there is
                        //  a tie rule A applies (player loses ante but gets playBet returned).
                        System.out.println("You and the dealer tied. You get your play bet of $" + playBet + " back, but lose your ante bet of $" + anteBet + ".");
                    } else {
                        // If the player lost then say that they lost and what they lost.
                        System.out.println("Unfortunately you lost the round. You lost a total of $" + playBet * 2 + ".");
                    }
                }
            } else {
                // If the player's playBet is <= 0, also known as the player folded, then tell
                //  them what they lost.
                System.out.println("You lost your ante bet of $" + anteBet + ".");
            }
            
            if (pairPlusBet > 0) {
                // If the player made a pair plus bet, then check if they won anything.
                int pairPlusWinnings = checkPairPlus(playerHandRating, pairPlusBet);
                money += pairPlusWinnings; // Add the player's pairPlus winnings to their wallet.

                if (pairPlusWinnings > 0) {
                    System.out.println("You won your pair plus bet! You got $" + pairPlusWinnings/2 + "!");
                } else {
                    System.out.println("You lost your pair plus bet of $" + pairPlusBet + ".");
                }
            }

            System.out.println("You now have a total of $" + money + ".");
            System.out.println();

            validInput = false;
            doneOnce = false;

            while (!validInput) {
                if (!doneOnce) {
                    System.out.print("Would you like to play more poker (Y/N)? ");
                    doneOnce = true;
                }
    
                String wantsToPlay = in.nextLine().toUpperCase();
    
                if (wantsToPlay.equals("Y")) {
                    playAgain = true;
                    validInput = true;
                    System.out.println();
                    System.out.println("You currently have $" + money + ".");
                    System.out.println();
                } else if (wantsToPlay.equals("N")) {
                    System.exit(0);
                } else {
                    System.out.print("Please input only Y or N: ");
                }
            }
        }
    }



    /**
     * This function is designed to compare the player and dealer's hands to determine the
     *  winner of a round and the winnings of the player.
     * 
     * @param playerHandRating is an integer rating assigned to the player's hand based off
     *   the combination they posses.
     * @param dealerHandRating is an integer rating assigned to the dealer's hand based off
     *   the combination they posses.
     * @param playerFirstCard is the numerical value of the player's first card.
     * @param playerSecondCard is the numerical value of the player's second card.
     * @param playerThirdCard is the numerical value of the player's third card.
     * @param dealerFirstCard is the numerical value of the dealer's first card.
     * @param dealerSecondCard is the numerical value of the dealer's second card.
     * @param dealerThirdCard is the numerical value of the dealer's third card.
     * @param playerHighCard is the numerical value of the highest card in the player's hand.
     * @param dealerHighCard is the numerical value of the highest card in the dealer's hand.
     * @param playBet is the numerical value of the bet the player made on their hand
     *   (same as their ante).
     * @return the player's winnings (which are added to the player's wallet and used to
     *   determine the winner, loser, etc. of the round).
     */
    private static int compareHands(int playerHandRating, int dealerHandRating, int playerFirstCard, int playerSecondCard, int playerThirdCard, int dealerFirstCard,
                                    int dealerSecondCard, int dealerThirdCard, int playerHighCard, int dealerHighCard, int playBet) {
        int playerWinnings = 0;
 
            if ((playerFirstCard == dealerFirstCard || playerFirstCard == dealerSecondCard || playerFirstCard == dealerThirdCard) &&
                (playerSecondCard == dealerFirstCard || playerSecondCard == dealerSecondCard || playerSecondCard == dealerThirdCard) &&
                (playerThirdCard == dealerFirstCard || playerThirdCard == dealerSecondCard || playerThirdCard == dealerThirdCard)) {
                // Checks if the player's cards are the exact same (not including suit) as the
                // dealer's cards, i.e. that there is a tie.
                playerWinnings = playBet;
            } else if (playerHandRating == STRAIGHT_FLUSH && dealerHandRating < STRAIGHT_FLUSH) {
                playerWinnings = (playBet * PLAY_BET_PAYOUT);
            } else if (playerHandRating == THREE_OF_A_KIND && dealerHandRating < THREE_OF_A_KIND) {
                playerWinnings = (playBet * PLAY_BET_PAYOUT);
            } else if (playerHandRating == STRAIGHT && dealerHandRating < STRAIGHT) {
                playerWinnings = (playBet * PLAY_BET_PAYOUT);
            } else if (playerHandRating == FLUSH && dealerHandRating < FLUSH) {
                playerWinnings = (playBet * PLAY_BET_PAYOUT);
            } else if (playerHandRating == PAIR && dealerHandRating < PAIR) {
                playerWinnings = (playBet * PLAY_BET_PAYOUT);
            } else if (playerHandRating < PAIR && dealerHandRating < PAIR) {
                playerWinnings = compareHighCards(playerHighCard, dealerHighCard, playBet, playerFirstCard, playerSecondCard, playerThirdCard, dealerFirstCard, dealerSecondCard, dealerThirdCard);
            } else if (playerHandRating == STRAIGHT_FLUSH && dealerHandRating == STRAIGHT_FLUSH) {
                if (playerFirstCard + playerSecondCard + playerThirdCard > dealerFirstCard + dealerSecondCard + dealerThirdCard) {
                    // If the player's three cards add up to more than the dealer's three cards,
                    //  and they both have straight flushes, then logically the player has a
                    //  straight flush composed of higher cards than the dealer, so they win.
                    playerWinnings = (playBet * PLAY_BET_PAYOUT);
                } else if (playerFirstCard + playerSecondCard + playerThirdCard == dealerFirstCard + dealerSecondCard + dealerThirdCard) {
                    // Same principle as the above if, but checking if the straights are the same,
                    //  indicating a tie.
                    playerWinnings = playBet;
                }
            } else if (playerHandRating == THREE_OF_A_KIND && dealerHandRating == THREE_OF_A_KIND) {
                // If both the player and dealer have three of a kinds, check who has the
                //  better three of a kind.
                if (playerFirstCard > dealerFirstCard) {
                    // As all cards (numerically) are the same in a three of a kind, just check
                    //  any card from the hands to determine who's three of a kind is composed
                    //  of higher value cards.
                    playerWinnings = (playBet * PLAY_BET_PAYOUT);
                } else if (playerFirstCard == dealerFirstCard) {
                    playerWinnings = playBet;
                }
            } else if (playerHandRating == STRAIGHT && dealerHandRating == STRAIGHT) {
                if (playerFirstCard + playerSecondCard + playerThirdCard > dealerFirstCard + dealerSecondCard + dealerThirdCard) {
                    // Same principle as when the player and dealer have straight flushes.
                    //  This checks to see who's straight is of higher value.
                    playerWinnings = (playBet * PLAY_BET_PAYOUT);
                } else if (playerFirstCard + playerSecondCard + playerThirdCard == dealerFirstCard + dealerSecondCard + dealerThirdCard) {
                    playerWinnings = playBet;
                }
            } else if (playerHandRating == FLUSH && dealerHandRating == FLUSH) {
                playerWinnings = compareHighCards(playerHighCard, dealerHighCard, playBet, playerFirstCard, playerSecondCard, playerThirdCard, dealerFirstCard, dealerSecondCard, dealerThirdCard);
            } else if (playerHandRating == PAIR && dealerHandRating == PAIR) {
                // If both the player and dealer have pairs, get the numerical values of the
                //  pairs and compare them.
                int playerPairHighCard = getPairHighCard(playerFirstCard, playerSecondCard, playerThirdCard);
                int dealerPairHighCard = getPairHighCard(dealerFirstCard, dealerSecondCard, dealerThirdCard);

                if (playerPairHighCard > dealerPairHighCard) {
                    playerWinnings = (playBet * PLAY_BET_PAYOUT);
                } else if (playerPairHighCard == dealerPairHighCard) {
                    // If the pairs have the same numerical values, then check the remaining
                    //  card to determine the winner.
                    if (playerHighCard > dealerHighCard) {
                        playerWinnings = (playBet * PLAY_BET_PAYOUT);
                    } else if (playerHighCard == dealerHighCard) {
                        // If the remaining card is the same, then there is a tie.
                        playerWinnings = playBet;
                    }
                }
            }
        return playerWinnings;
    }

    /**
     * This function gives an integer rating to a hand depending on what combination(s) it
     *  possesses. Rather than checking all the specific cases of who has what combinations,
     *  this allows easy comparison of the player and dealer's hands.
     * 
     * @param firstCard is numerical value of the first card inputted (either from the player
     *   or dealer).
     * @param secondCard is numerical value of the second card inputted.
     * @param thirdCard is numerical value of the third card inputted.
     * @param hand is the hand of whoever this function was called for (either player or dealer).
     *   The hand is used to get the full individual cards from a hand, including suits, as suits
     *   are required to calculate whether a hand has a flush.
     * @return
     */
    private static int rateHand(int firstCard, int secondCard, int thirdCard, String hand) {
        String fullFirstCard = getCardFromHand(hand, CARD_ONE);
        String fullSecondCard = getCardFromHand(hand, CARD_TWO);
        String fullThirdCard = getCardFromHand(hand, CARD_THREE);
        
        if (checkStraight(firstCard, secondCard, thirdCard) && checkFlush(fullFirstCard, fullSecondCard, fullThirdCard)) {
            // If checkStraight and checkFlush (return booleans) return true, then return the
            //  highest hand rating (because straight + flush = straight flush = best hand).
            //  All the below else ifs work in the same fashion  (all the methods return booleans).
            return STRAIGHT_FLUSH;
        } else if (checkThreeOfAKind(firstCard, secondCard, thirdCard)) {
            return THREE_OF_A_KIND;
        } else if (checkStraight(firstCard, secondCard, thirdCard)) {
            return STRAIGHT;
        } else if (checkFlush(fullFirstCard, fullSecondCard, fullThirdCard)) {
            return FLUSH;
        } else if (checkPair(firstCard, secondCard, thirdCard)) {
            return PAIR;
        } else {
            return 0;
        }
    }

    /**
     * This function uses the hand rating assigned to the player's hand to determine what
     *  combination they have and in that regard what they shoud win according to the pair plus
     *  payout chart.
     * 
     * @param playerHandRating is the integer rating assigned to the player's hand based on the
     *   combination they posses.
     * @param pairPlusBet is the amount of money the player bet on having a hand with a pair or
     *   better (pair plus).
     * @return the amount of money the player won from their pair plus bet (which is added to
     *   the player's wallet and used to determine if the player won or lost the pair plus bet).
     */
    private static int checkPairPlus(int playerHandRating, int pairPlusBet) {
        int pairPluswinnings = 0;

        if (playerHandRating == STRAIGHT_FLUSH) {
            pairPluswinnings = pairPlusBet * STRAIGHT_FLUSH_PAYOUT;
        } else if (playerHandRating == FLUSH) {
            pairPluswinnings = pairPlusBet * FLUSH_PAYOUT;
        } else if (playerHandRating == STRAIGHT) {
            pairPluswinnings = pairPlusBet * STRAIGHT_PAYOUT;
        } else if (playerHandRating == THREE_OF_A_KIND) {
            pairPluswinnings = pairPlusBet * THEE_OF_A_KIND_PAYOUT;
        } else if (playerHandRating == PAIR) {
            pairPluswinnings = pairPlusBet * PAIR_PAYOUT;
        }
        return pairPluswinnings;
    }

    /**
     * This function compares the player and dealer's high cards to determine who won and lost,
     *  or if there was a tie. The function will account for cases where the highest card from
     *  both hands is the same, where the second highest card is also the same, and where the 
     *  third highest (or lowest) card is also the same.
     * 
     * @param playerHighCard is the numerical value of the highest card in the player's hand.
     * @param dealerHighCard is the numerical value of the highest card in the dealer's hand.
     * @param playBet is the bet the player made on their hand.
     * @param playerFirstCard is the numerical value of the player's first card.
     * @param playerSecondCard is the numerical value of the player's second card.
     * @param playerThirdCard is the numerical value of the player's third card.
     * @param dealerFirstCard is the numerical value of the dealer's first card.
     * @param dealerSecondCard is the numerical value of the dealer's second card.
     * @param dealerThirdCard is the numerical value of the dealer's third card.
     * @return the player's winnings after comparing the high cards (which will be 0 if the
     *   player lost).
     */
    private static int compareHighCards(int playerHighCard, int dealerHighCard, int playBet, int playerFirstCard, int playerSecondCard,
                                        int playerThirdCard, int dealerFirstCard, int dealerSecondCard, int dealerThirdCard) {
        if (playerHighCard > dealerHighCard) {
            return playBet * PLAY_BET_PAYOUT;
        } else if (playerHighCard == dealerHighCard) {
            // If the highest cards from both hand are equal, get the second highest cards and
            //  compare them.
            int playerSecondHighCard = getSecondHighCard(playerFirstCard, playerSecondCard, playerThirdCard);
            int dealerSecondHighCard = getSecondHighCard(dealerFirstCard, dealerSecondCard, dealerThirdCard);

            if (playerSecondHighCard > dealerSecondHighCard) {
                return playBet * PLAY_BET_PAYOUT;
            } else if (playerSecondHighCard == dealerSecondHighCard) {
                // if the second highest cards are equal, then get the third highest cards and
                //  compare them.
                int playerThirdHighCard = getThirdHighCard(playerFirstCard, playerSecondCard, playerThirdCard);
                int dealerThirdHighCard = getThirdHighCard(dealerFirstCard, dealerSecondCard, dealerThirdCard); 

                if (playerThirdHighCard > dealerThirdHighCard) {
                    return playBet * PLAY_BET_PAYOUT;
                } else {
                    // I don't need to check if the third highest cards are equal because if they
                    //  are then both hands are the exact same, meaning I would have already caught
                    //  the tie in my first check for a tie at the beginning of compareHands().
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * This function gets the value of the highest card in the hand inputted. 
     * 
     * @param firstCard is the numerical value of the first card inputted.
     * @param secondCard is the numerical value of the first card inputted.
     * @param thirdCard is the numerical value of the first card inputted.
     * @return the value of the highest card in the inputted hand.
     */
    private static int getHighCard(int firstCard, int secondCard, int thirdCard) {
        int get_max = Math.max(firstCard, Math.max(secondCard, thirdCard));
            // Gets the higher of two of the three cards in a hand and then the higher of the
            //  remaining card and the higher of the two previous cards.
        int get_min = Math.min(firstCard, Math.min(secondCard, thirdCard));
            // Gets the lower of two of the three cards in a hand and then the lower of the
            //  remaining card and the lower of the two previous cards.
        int get_mid = (firstCard + secondCard + thirdCard) - (get_max + get_min);
            // Gets the middle card.

        if (get_max == ACE && get_min == 2 && get_mid == 3) {
            // Checks if the hand has a straight of ace, 2, 3 in which case ace becomes low meaning
            // the high card changes from 14 (ace) to 3 (the highest card of the straight).
            get_max = 3;
        }
        return get_max;
    }

    /**
     * This function gets the value of the second highest card in the hand inputted.
     *  This is only really used to break a tie in the case both the player and dealer have the
     *  same high card.
     * 
     * @param firstCard is the numerical value of the first card inputted.
     * @param secondCard is the numerical value of the first card inputted.
     * @param thirdCard is the numerical value of the first card inputted.
     * @return the value of the second highest card in the inputted hand.
     */
    private static int getSecondHighCard(int firstCard, int secondCard, int thirdCard) {
        int get_max = Math.max(firstCard, Math.max(secondCard, thirdCard)); 
        int get_min = Math.min(firstCard, Math.min(secondCard, thirdCard));
        int get_mid = (firstCard + secondCard + thirdCard) - (get_max + get_min);

        return get_mid;
    }

    /**
     * This function gets the value of the lowest card in the hand inputted.
     *  This is only used to break a tie on the rare occasion that both the high card and
     *  second high card are the same for both the player and dealer.
     * 
     * @param firstCard is the numerical value of the first card inputted.
     * @param secondCard is the numerical value of the first card inputted.
     * @param thirdCard is the numerical value of the first card inputted.
     * @return the value of the lowest card in the inputted hand.
     */
    private static int getThirdHighCard(int firstCard, int secondCard, int thirdCard) {
        int get_max = Math.max(firstCard, Math.max(secondCard, thirdCard)); 
        int get_min = Math.min(firstCard, Math.min(secondCard, thirdCard));
        int get_mid = (firstCard + secondCard + thirdCard) - (get_max + get_min);

        if (get_max == ACE && get_min == 2 && get_mid == 3) {
            // Checks if there is an ace, 2, 3 run in which case the lowest card changes from
            //  2 to 1 because the ace becomes low.
            get_min = 1;
        }
        return get_min;
    }

    /**
     * This function determines who has a pair of higher value (a pair composed of higher value 
     *  cards) when both the player and dealer have pairs.
     * 
     * @param firstCard is the numerical value of the first card inputted.
     * @param secondCard is the numerical value of the second card inputted.
     * @param thirdCard is the numerical value of the third card inputted.
     * @return the numerical value of the cards that make up the pair in a hand.
     */
    private static int getPairHighCard(int firstCard, int secondCard, int thirdCard) {
        if (firstCard == secondCard) {
            return firstCard;
        } else if (firstCard == thirdCard) {
            return firstCard;
        } else if (secondCard == thirdCard) {
            return secondCard;
        } else {
            return 0;
        }
    }

    /**
     * This function determines weather a hand has a three of a kind (all three cards the same).
     * 
     * @param firstCard is the numerical value of the first card inputted.
     * @param secondCard is the numerical value of the second card inputted.
     * @param thirdCard is the numerical value of the third card inputted.
     * @return true if the hand has a three of a kind and false if it does not.
     */
    private static boolean checkThreeOfAKind(int firstCard, int secondCard, int thirdCard) {
        if (firstCard == secondCard && secondCard == thirdCard) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This function checks if a hand has a straight (all cards have concecutive numerical values).
     * 
     * @param firstCard is the numerical value of the first card inputted.
     * @param secondCard is the numerical value of the second card inputted.
     * @param thirdCard is the numerical value of the third card inputted.
     * @return true if the hand has a straight and false if it does not.
     */
    private static boolean checkStraight(int firstCard, int secondCard, int thirdCard) {
        int get_max = Math.max(firstCard, Math.max(secondCard, thirdCard)); 
        int get_min = Math.min(firstCard, Math.min(secondCard, thirdCard));
        int get_mid = (firstCard + secondCard + thirdCard) - (get_max + get_min);

        if (get_min == 2 && get_mid == 3 && get_max == ACE) {
            // Since ace is able to be low or high, this makes sure that if you have an ace, 2,
            //  and 3 straight it returns true.
            return true;
        }
        
        if (get_max == get_mid + 1 && get_mid == get_min + 1) {
            // If the difference between cards is 1 then return true.
            return true;
        } else {
            return false;
        }
    }

    /**
     * This function is checks if a hand has a flush (all cards have the same suit).
     * 
     * @param firstCard is the entirety of first card inputted (includes suit).
     * @param secondCard is the entirety of second card inputted.
     * @param thirdCard is the entirety of third card inputted.
     * @return true if the hand has a flush and false if it does not.
     */
    private static boolean checkFlush(String firstCard, String secondCard, String thirdCard) {        
        if (firstCard.substring(firstCard.length() - 1).equals(secondCard.substring(secondCard.length() - 1)) &&
            secondCard.substring(secondCard.length() - 1).equals(thirdCard.substring(thirdCard.length() - 1))) {
            // If the suits of all the cards match (first card suit matches second and second
            // card suit matches third) then return true.
            return true;
        } else {
            return false;
        }
    }

    /**
     * This function checks if a hand has a pair (two cards with the same numerical value).
     * 
     * @param firstCard is the numerical value of the first card inputted.
     * @param secondCard is the numerical value of the second card inputted.
     * @param thirdCard is the numerical value of the third card inputted.
     * @return true if the hand has a pair and false if it does not.
     */
    private static boolean checkPair(int firstCard, int secondCard, int thirdCard) {
        if (firstCard == secondCard || secondCard == thirdCard || firstCard == thirdCard) {
            // If the hand has a pair (first card matches the second, second matches third,
            //  OR first matches third) return true.
            return true;
        } else {
            return false;
        }
    }

    /**
     * This function gets whether the player wants to bet on their hand and if so, returns their
     *  bet (which is just the previously inputted ante bet because as per the rules the ante
     *  bet and play bet must be the same).
     * 
     * @param in is the scanner required to get input from the player.
     * @param anteBet is the previously inputted ante bet used to determine the playBet if the
     *   player chooses to bet on their hand
     * @return the amount of money the player wants to bet on their hand (which is just the
     *   ante unless they don't want to bet in which case it returns 0).
     */
    private static int getplayBet(Scanner in, int anteBet) {
        boolean validInput = false;
        boolean hasDone = false;
        int betAmountInt = 0;
        
        while (!validInput) {
            if (!hasDone) {
                System.out.print("Do you want to bet on your hand (Y/N)? ");
                hasDone = true;
            }
            String temp = in.nextLine().toUpperCase();
            if (temp.equals("Y")) {
                betAmountInt = anteBet;
                validInput = true;
            } else if (temp.equals("N")) {
                return 0;
            } else {
                System.out.print("Please input only a Y or N: ");
            }
        }
        return betAmountInt;
    }

    /**
     * This function gets the player's ante bet.
     * 
     * @param in is the scanner required to get input from the player.
     * @param money is the amount of money the player currently has, used to confirm that the
     *   player has enough money to make their bet and still play the round.
     * @return the amount that the player wants to bet for their ante.
     */
    private static int getAnte(Scanner in, int money) {
        boolean validBet = false;
        boolean hasDone = false;
        int betAmountInt = 0;

        while (!validBet) {
            if (!hasDone) {
                System.out.print("How much would you like to bet for your ante ($50-$100): ");
                hasDone = true;
            }
            try {
                String betAmountStr =  in.nextLine();

                if (betAmountStr.substring(0, 1).equals("$")) {
                    // Makes sure that I have one $ in input (which I want) so I know I am
                    //  cutting the betAmountStr string at the correct place for it to be
                    //  parsable and still be the full, correct number.
                    betAmountInt = Integer.parseInt(betAmountStr.substring(1));
                    if (betAmountInt < MIN_BET) {
                        System.out.print("Please input at least $50: ");
                    } else if (betAmountInt > MAX_BET) {
                        System.out.print("Please input no more than $100: ");
                    } else if (betAmountInt > money/2) {
                        // Makes sure that the bet the player is trying to make is no more than
                        //  half their current money so that they will still have the money play
                        //  the round.
                        System.out.print("You do not have that much money, please bet a lower amount: ");
                    } else {
                        validBet = true;
                    }
                } else {
                    System.out.print("Please put a number in the format $mm(m): ");
                }
            } catch (Exception e) {
                // In this try catch there were two exceptions I needed to catch
                //  (NumberFormatException and NoSuchElementException), so I found that the most
                //  efficient way to do that was to catch all exceptions as I respond the same
                //  way to all (expected) exceptions.
                System.out.print("Please put a number in the format $mm(m): ");
            }
        }
        return betAmountInt;
    }

    /**
     * This function gets whether the player wants to place a pair plus bet (a bet on whether
     *  the player will have a pair or better in their hand), and if so how much they want to bet.
     * 
     * @param in is the scanner required to get input from the player.
     * @param money is the amount of money the player currently has, used to make sure the
     *   player has enough money to make their bet and still bet for their ante and play the round.
     * @return the amount of money the player wants to bet for their pair plus (which returns 0
     *   in the case that they do not want to make a pair plus bet).
     */
    private static int getPairPlus(Scanner in, int money) {
        boolean wantsBet = false;
        boolean validInput = false;
        boolean validBet = false;
        boolean hasDone = false;
        int betAmountInt = 0;
        
        while (!validInput) {   // This while gets whether the player wants to bet.
            if (!hasDone) {
                System.out.print("Do you want to place a pair plus bet (Y/N)? ");
                hasDone = true;
            }
            String temp = in.nextLine().toUpperCase();
            if (temp.equals("Y")) {
                if (money < MIN_BET + MIN_MONEY_TO_PLAY) {
                    // If their money is less than $150 (the minimum amount needed to make all
                    //  three bets) then tell them they are unable to make the minimum bet for
                    //  pair plus while still being able to afford an ante and play bet.
                    System.out.println("You do not have enough money to make this bet (in order to play the round).");
                    return 0;
                } else {
                    wantsBet = true;
                    validInput = true;
                }
            } else if (temp.equals("N")) {
                return 0;
            } else {
                System.out.print("Please input only a Y or N: ");
            }
        }

        hasDone = false;

        if (wantsBet) {
            while (!validBet) {  // If the player wants to bet, this while gets their bet amount.
            
                if (!hasDone) {
                    System.out.print("How much would you like to bet ($50-$100): ");
                    hasDone = true;
                }

                try {
                    String betAmountStr =  in.nextLine();

                    if (betAmountStr.substring(0, 1).equals("$")) {
                        betAmountInt = Integer.parseInt(betAmountStr.substring(1));
                        if (betAmountInt < MIN_BET) {
                            System.out.print("Please input at least $50: ");
                        } else if (betAmountInt > MAX_BET) {
                            System.out.print("Please input no more than $100: ");
                        } else if (betAmountInt > money - MIN_MONEY_TO_PLAY) {
                            System.out.print("In order to play the round you cannot bet that much, please bet a lower amount: ");
                        } else {
                            validBet = true;
                        }
                    } else {
                        System.out.print("Please put a number in the format $mm(m): ");
                    }
                } catch (Exception e) {
                    // I had to catch multiple exceptions and found that catching all exceptions
                    //  was most efficient, same as in getAnte.
                    System.out.print("Please put a number in the format $mm(m): ");
                }
            }
        }
        return betAmountInt;
    }

    /**
     * This function gets the amount of money that the player wants to start with and assigns
     *  it to the money variable.
     * 
     * @param in is the scanner required to input from the player.
     * @return the amount of money that the player wants to start with.
     */
    private static int getStartingMoney(Scanner in) {
        boolean validInput = false;
        boolean hasDone = false;
        int startingMoneyInt = 0;
        while (!validInput) {
            if (!hasDone) {
                System.out.print("How much money would you like to start with? ");
                hasDone = true;
            }
            try {
                String startingMoneyString = in.nextLine();
                if (startingMoneyString.substring(0, 1).equals("$")) {
                    startingMoneyInt = Integer.parseInt(startingMoneyString.substring(1));
                    if (startingMoneyInt < MIN_MONEY_TO_PLAY) {
                        System.out.print("Please input at least $100: ");
                    } else {
                        validInput = true;
                    }
                } else {
                    System.out.print("Please put a number in the format $mmm: ");
                }
            } catch (Exception e) {
                // Same thing for the same reason as getAnte.
                System.out.print("Please put a number in the format $mmm: ");
            }
        }
        return startingMoneyInt;
    }

    /**
     * This function gets the numerical value of a certain card (either 1, 2, or 3) from a hand 
     *  depending on what card is input as the cardWanted. ("3D 4H 8C", 1 == 3).
     * 
     * @param hand is a full hand of three cards (either the player or dealer's).
     * @param cardWanted is the numerical value of the card wanted, either 1, 2, or 3,
     *   used to determine which card's value is returned.
     * @return the numerical value of a card, indicated by the cardWanted variable, from the hand.
     */
    private static int getCardNumber(String hand, int cardWanted) {
        String temp = hand.substring(hand.indexOf(" ") + 1);
            // The hand that was inputted exluding the first card (starts at the second card).
        if (cardWanted == 1) {
            // If when called the function said that it wanted the first card (CARD_ONE),
            //  then give it back the first card.
            String card = checkFaceValues(hand.substring(0, hand.indexOf(" ")));
                // Gets the first card from the hand, and uses the checkFaceValues() function
                //  to turn face cards into their numerical counterparts (J = 11, etc.).
            return Integer.parseInt(card.substring(0, card.length() - 1));
                // Returns the parsed card (without the suit, so that the string is parsable).
        } else if (cardWanted == 2) {
            // If the second card is wanted, return the second card.
            String card = checkFaceValues(temp.substring(0, temp.indexOf(" ")));
            return Integer.parseInt(card.substring(0, card.length() - 1));
        } else {
            // Otherwise, the third card is wanted so return that.
            String card = checkFaceValues(temp.substring(temp.indexOf(" ") + 1, temp.length() - 1));
                // minus 1 after temp.length() because I had to account for an extra space after
                //  the third card in the hand, i.e. at the end of the hand (and temp) string(s).
            return Integer.parseInt(card.substring(0, card.length() - 1));
        }   
    }

    /**
     * This function gets a certain card from a hand depending on what card is input as
     *  cardWanted. ("3D 4H 8C", 1 == 3D).
     * 
     * @param hand is a full hand of three cards (either the player or dealer's). 
     * @param cardWanted is the numerical value of the card wanted, used to determine which card
     *   is returned.
     * @return a (full) card (includes suits), indicated by the cardWanted variable, from the hand.
     */
    private static String getCardFromHand(String hand, int cardWanted) {
        String temp = hand.substring(hand.indexOf(" ") + 1);
            // The hand that was inputted exluding the first card (starts at the second card).
        if (cardWanted == 1) {
            // Same reason as in getCardNumber().
            //  (the else if and else are for the same reason as in getCardNumber() as well).
            return checkFaceValues(hand.substring(0, hand.indexOf(" ")));
                // Similar to getCardNumber(), but this method returns the full card as a String
                //  rather than the card's value as an int.
        } else if (cardWanted == 2) {
            return checkFaceValues(temp.substring(0, temp.indexOf(" ")));
        } else {
            return checkFaceValues(temp.substring(temp.indexOf(" ") + 1, temp.length() - 1));
                // minus 1 after temp.length() because I had to account for an extra space after
                //  the third card in the hand, i.e. at the end of the hand (and temp) string(s).
        }
    }

    /**
     * This function converts face cards (jack, queen, king, and ace) into their respective
     *  numerical values. (JD = 11D).
     * 
     * @param card is the card inputted which gets checked for faces and converted if necessary.
     * @return the card inputted such that if it is a face card it gets converted to the
     *   form 00S (QD = 12D) and if it is not it remains untampered (2D = 2D).
     */
    private static String checkFaceValues(String card) {
        if (card.substring(0, 1).equals("J")) {
            // If the card's first character is "J", indicating a jack, then return 11 plus the
            // suit. The same concept applies to the below else ifs.
            return "11" + card.substring(card.length() - 1);
        } else if (card.substring(0, 1).equals("Q")) {
            return "12" + card.substring(card.length() - 1);
        } else if (card.substring(0, 1).equals("K")) {
            return "13" + card.substring(card.length() - 1);
        } else if (card.substring(0, 1).equals("A")) {
            return "14" + card.substring(card.length() - 1);
        } else {
            // If the first character is none of the above, i.e. the card is not a face card,
            //  then just return the original card.
        return card;
        }
    }

    /**
     * This function creates hands of three cards used for playing the Three Card Poker game.
     * 
     * @return the hand created (a set of three cards separated by spaces).
     */
    private static String dealCards() {
        String cards = "";

        for (int i = 0; i < 3; i++) {
            Boolean hasCard = false;
            while (!hasCard) {
                String card = getCard();
                if (isUnique(cards, card)) {
                    // This along with the while loop makes sure that there cannot be
                    //  the exact same card more than once in a given hand.
                    cards += card + " ";
                        // Due to this line of code there is an extra space at the end of the
                        //  hand string produced by this function. This is why in getCardNumber()
                        //  and getCardFromHand() I had to subtract 1 from the length of the
                        //  temp string like I mentioned.
                    hasCard = true;
                }
            }
        }

        return cards;
    }

    /**
     * This function makes sure that a given card in unique from the others in its hand.
     * 
     * @param hand is the portion of the hand we currently have (as we are creating the hand at
     *   this point) inputted to be compared to the new card.
     * @param card is the card inputted to be compared to the existing portion of the hand
     *   (and added to hand if it is unique).
     * @return true if the card is unique and false if it is not.
     */
    private static boolean isUnique(String hand, String card) {
        return hand.indexOf(card) == -1;
            // Checks if the card does not appear in the hand and returns true if it does not
            //  (and false if it does).
    }

    /**
     * This function combines the getFace() and getSuit() functions to create a full card with
     *  a face/number and suit (i.e. 3D, JS, etc.).
     * 
     * @return a full card.
     */
    private static String getCard() {
        return getFace() + getSuit();
    }

    /**
     * This function generates a random number between 2 and 14 (encasing all possible
     *  numerical values for cards in a deck, numbers above 10 being face cards) and determines
     *  the face of the card using the number generated.
     * 
     * @return the face of the card. The face is just the number if it is <= 10, or a letter
     *   (J,Q,K,A) coresponding to the number's value (J=11, etc.) if it is > 10 (face cards).
     */
    private static String getFace() {
        int face = (int) (Math.random() * NUM_FACES + 2);
        
        if (face >= 2 && face <= 10) {
           return "" + face;
            // If the card's face is 2-10, return it as the number but as a String rather
            //  than an int.
        } else if (face == JACK) {
            return "J";
        } else if (face == QUEEN) {
            return "Q";
        } else if (face == KING) {
            return "K";
        } else if (face == ACE) {
            return "A";
        } else {
            return null;
        }
    }

    /**
     * This function generates a random number between 0 and 3 and assigns a suit based on that
     *  number.
     * 
     * @return the suit, either D (diamond), H (hearts), S (spades), or C (clubs).
     */
    private static String getSuit() {
        int suit = (int) (Math.random() * NUM_SUITS);
        
        if (suit == DIAMONDS) {
            return "D";
        } else if (suit == HEARTS) {
            return "H";
        } else if (suit == SPADES) {
            return "S";
        } else if (suit == CLUBS) {
            return "C";
        } else {
            return null;
        }
    }
}