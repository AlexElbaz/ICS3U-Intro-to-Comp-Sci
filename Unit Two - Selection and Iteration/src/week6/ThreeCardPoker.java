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
     * I decided to rate hands using an integer based on what combination (pair, flush, etc.) they had.
     *  To avoid magic numbers I assigned integers to combinations in ascending order of poker ranking.
     *  i.e. straight flush is best hand, so it gets the highest integer value.
     */
    private static final int STRAIGHT_FLUSH = 5;
    private static final int THREE_OF_A_KIND = 4;
    private static final int STRAIGHT = 3;
    private static final int FLUSH = 2;
    private static final int PAIR = 1;

    private static final int STRAIGHT_FLUSH_PAYOUT = 41;
        // Though the Straight Flush payout is 40 to 1, this variable (and the others to the line break below) are
        //  1 higher than their respective payouts. This is because I subtract the bet right after they make it
        //  so when they cash out they get 1 times more than the payout to effectively get their original bet back.
    private static final int THEE_OF_A_KIND_PAYOUT = 31;
    private static final int STRAIGHT_PAYOUT = 6;
    private static final int FLUSH_PAYOUT = 4;
    private static final int PAIR_PAYOUT = 2;

    private static final int PLAY_BET_PAYOUT = 4;
        // The full payout for winning the round is your ante bet times two (you get your original bet back and
        //  then 1 more times your bet) and your play bet times two (for the same reason as your ante). Since your 
        //  play bet and your ante bet are required to be the same, the full payout for winning the round is  
        //  basically 4 times either your play bet or your ante bet. 


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
                // I chose to subtract this bet (and all the following bets) from the player's wallet right away.
                //  This is mainly to avoid having to subtract money from the player later and instead only have to
                //  add money.
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
                // If the player's playBet is > 0, also known as the player decided to bet on their hand,
                //  then check further.
                if (dealerHighCard <= JACK && dealerHandRating < PAIR) {
                    // If the dealer has a hand of Jack-high or worse, apply rule A (only give playBet back),
                    //  and don't compare the hands.
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
                        //  (the winnings will just be the original playBet because if there is a tie rule A applies
                        //  (player loses ante but gets playBet returned).)
                        System.out.println("You and the dealer tied. You get your play bet of $" + playBet + " back, but lose your ante bet of $" + anteBet + ".");
                    } else {
                        // If the player lost then say that they lost and what they lost.
                        System.out.println("Unfortunately you lost the round. You lost a total of $" + playBet * 2 + ".");
                    }
                }
            } else {
                // If the player's playBet is <= 0, also known as the player folded, then tell them what they lost.
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
     * This function is designed to compare the player and dealer's hands to determine the winner of a round
     *  and the winnings of the player.
     * 
     * @param playerHandRating is an integer rating assigned to the player's hand based off the combination they posses.
     * @param dealerHandRating is an integer rating assigned to the dealer's hand based off the combination they posses.
     * @param playerFirstCard is the player's first card's numerical value.
     * @param playerSecondCard is the player's second card's numerical value.
     * @param playerThirdCard is the player's third card's numerical value.
     * @param dealerFirstCard is the dealer's first card's numerical value.
     * @param dealerSecondCard is the dealer's second card's numerical value.
     * @param dealerThirdCard is the dealer's third card's numerical value.
     * @param playerHighCard is the player's highest card's numerical value.
     * @param dealerHighCard is the dealer's highest card's numerical value.
     * @param playBet is the numerical value of the bet the player made on their hand (same as their ante).
     * @return the player's winnings (which are added to the player's wallet and used to determine the winner, loser, etc. of the round).
     */
    private static int compareHands(int playerHandRating, int dealerHandRating, int playerFirstCard, int playerSecondCard, int playerThirdCard, int dealerFirstCard, int dealerSecondCard, int dealerThirdCard, int playerHighCard, int dealerHighCard, int playBet) {
        int playerWinnings = 0;
 
            if ((playerFirstCard == dealerFirstCard || playerFirstCard == dealerSecondCard || playerFirstCard == dealerThirdCard) && (playerSecondCard == dealerFirstCard || playerSecondCard == dealerSecondCard || playerSecondCard == dealerThirdCard) && (playerThirdCard == dealerFirstCard || playerThirdCard == dealerSecondCard || playerThirdCard == dealerThirdCard)) {
                // Checks if the player's cards are the exact same (not including suit) as the dealer's cards,
                //  i.e. that there is a tie.
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
                if (playerHighCard > dealerHighCard) {
                    playerWinnings = (playBet * PLAY_BET_PAYOUT);
                } else if (playerHighCard == dealerHighCard) {
                    int playerSecondHighCard = getSecondHighCard(playerFirstCard, playerSecondCard, playerThirdCard);
                    int dealerSecondHighCard = getSecondHighCard(dealerFirstCard, dealerSecondCard, dealerThirdCard);

                    if (playerSecondHighCard > dealerSecondHighCard) {
                        playerWinnings = (playBet * PLAY_BET_PAYOUT);
                    } else if (playerSecondHighCard == dealerSecondHighCard) {
                        int playerThirdHighCard = getThirdHighCard(playerFirstCard, playerSecondCard, playerThirdCard);
                        int dealerThirdHighCard = getThirdHighCard(dealerFirstCard, dealerSecondCard, dealerThirdCard); 

                        if (playerThirdHighCard > dealerThirdHighCard) {
                            playerWinnings = (playBet * PLAY_BET_PAYOUT);
                        } else if (playerThirdHighCard == dealerThirdHighCard) {
                            playerWinnings = playBet;
                        }
                    }
                }
            } else if (playerHandRating == PAIR && dealerHandRating == PAIR) {
                // If both the player and dealer have pairs, get the numerical values of the pairs and compare them.
                int playerPairHighCard = getPairHighCard(playerFirstCard, playerSecondCard, playerThirdCard);
                int dealerPairHighCard = getPairHighCard(dealerFirstCard, dealerSecondCard, dealerThirdCard);

                if (playerPairHighCard > dealerPairHighCard) {
                    playerWinnings = (playBet * PLAY_BET_PAYOUT);
                } else if (playerPairHighCard == dealerPairHighCard) {
                    // If the pairs have the same numerical values, then check the remaining card to determine the winner.
                    if (playerHighCard > dealerHighCard) {
                        playerWinnings = (playBet * PLAY_BET_PAYOUT);
                    } else if (playerHighCard == dealerHighCard) {
                        // If the remaining card is the same, then there is a tie.
                        playerWinnings = playBet;
                    }
                }
            } else if (playerHandRating == THREE_OF_A_KIND && dealerHandRating == THREE_OF_A_KIND) {
                // If both the player and dealer have three of a kinds, check who has the better three of a kind.
                if (playerFirstCard > dealerFirstCard) {
                    // As all cards (numerically) are the same in a three of a kind, just check any card from the
                    //  hands to determine who's three of a kind is composed of higher value cards.
                    playerWinnings = (playBet * PLAY_BET_PAYOUT);
                } else if (playerFirstCard == dealerFirstCard) {
                    playerWinnings = playBet;
                }
            }
        return playerWinnings;
    }

    /**
     * This function gives an integer rating to a hand depending on what combination(s) it possesses.
     *  Rather than checking all the specific cases of who has what combinations, this allows easy comparison of 
     *  the player and dealer's hands.
     * 
     * @param firstCard is numerical value of the first card inputted (either from the player or dealer).
     * @param secondCard is numerical value of the second card inputted.
     * @param thirdCard is numerical value of the third card inputted.
     * @param hand is the hand of whoever this function was called for (either the player or dealer).
     *  The hand is used to get the full individual cards from a hand, including suits, as suits are required to
     *  calculate whether a hand has a flush.
     * @return
     */
    private static int rateHand(int firstCard, int secondCard, int thirdCard, String hand) {
        String fullFirstCard = getCardFromHand(hand, CARD_ONE);
        String fullSecondCard = getCardFromHand(hand, CARD_TWO);
        String fullThirdCard = getCardFromHand(hand, CARD_THREE);
        
        if (checkStraight(firstCard, secondCard, thirdCard) && checkFlush(fullFirstCard, fullSecondCard, fullThirdCard)) {
            // If checkStraight and checkFlush (return booleans) return true, then return the highest hand rating
            //  (because straight + flush = straight flush = best hand).
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
     * This function uses the hand rating assigned to the player's hand to determine what combination they have 
     *  and in that regard what they shoud win according to the pair plus payout chart.
     * 
     * @param playerHandRating is the integer rating assigned to the player's hand based on the combination they posses.
     * @param pairPlusBet is the amount of money the player bet on having a hand with a pair or better (pair plus).
     * @return the amount of money the player won from their pair plus bet (which is added to the player's wallet and used to determine if the player won or lost the pair plus bet).
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
     * This function gets the value of the highest card in the hand inputted. 
     * 
     * @param firstCard is the numerical value of the first card inputted.
     * @param secondCard is the numerical value of the first card inputted.
     * @param thirdCard is the numerical value of the first card inputted.
     * @return the value of the highest card in the inputted hand.
     */
    private static int getHighCard(int firstCard, int secondCard, int thirdCard) {
        int get_max = Math.max(firstCard, Math.max(secondCard, thirdCard));
            // Gets the higher of two of the three cards in a hand and then the higher of the remaining card
            //  and the higher of the two previous cards.
        int get_min = Math.min(firstCard, Math.min(secondCard, thirdCard));
            // Gets the lower of two of the three cards in a hand and then the lower of the remaining card
            // and the lower of the two previous cards.
        int get_mid = (firstCard + secondCard + thirdCard) - (get_max + get_min);
            // Gets the middle card.

        if (get_max == ACE && get_min == 2 && get_mid == 3) {
            // Checks if the hand has a straight of ace, 2, 3 in which case ace becomes low meaning the high card
            //  changes from 14 (ace) to 3 (the highest card of the straight).
            get_max = 3;
        }
        return get_max;
    }

    /**
     * This function gets the value of the second highest card in the hand inputted.
     *  This is only really used to break a tie in the case both the player and dealer have the same high card.
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
     *  This is only used to break a tie on the rare occasion that both the high card and second high card are the
     *  same for both the player and dealer.
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
            // Checks if there is an ace, 2, 3 run in which case the lowest card changes from 2 to 1 because the ace becomes low.
            get_min = 1;
        }
        return get_min;
    }

    /**
     * This function determines who has a pair of higher value (a pair composed of higher value cards)
     *  when both the player and dealer have pairs.
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
            // Since ace is able to be low or high, this makes sure that if you have an ace, 2, and 3 straight
            //  it returns true.
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
        if (firstCard.substring(firstCard.length() - 1).equals(secondCard.substring(secondCard.length() - 1)) && secondCard.substring(secondCard.length() - 1).equals(thirdCard.substring(thirdCard.length() - 1))) {
            // If the suits of all the cards match (first card suit matches second and second card suit matches third)
            //  then return true.
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
            // If the hand has a pair (first card matches the second, second matches third, OR first matches third)
            //  return true.
            return true;
        } else {
            return false;
        }
    }

    /**
     * This function gets whether the player wants to bet on their hand and if so, returns their bet
     *  (which is just the previously inputted ante bet because as per the rules the ante bet and play bet must
     *  be the same).
     * 
     * @param in is the scanner required to get input from the player.
     * @param anteBet is the previously inputted ante bet used to determine the playBet if the player chooses to bet on their hand
     * @return the amount the player wants to bet on their hand (which is the ante unless they don't want to bet in which case it returns 0).
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
     * @param money is the amount of money the player currently has, used to confirm that the player has enough to make their bet and still play the round.
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
                    betAmountInt = Integer.parseInt(betAmountStr.substring(1));
                    if (betAmountInt < MIN_BET) {
                        System.out.print("Please input at least $50: ");
                    } else if (betAmountInt > MAX_BET) {
                        System.out.print("Please input no more than $100: ");
                    } else if (betAmountInt > money/2) {
                        // Makes sure that the bet the player is trying to make is no more than half their current
                        //  money so that they will still have the money play the round.
                        System.out.print("You do not have that much money, please bet a lower amount: ");
                    } else {
                        validBet = true;
                    }
                } else {
                    System.out.print("Please put a number in the format $mm(m): ");
                }
            } catch (Exception e) {
                // In this try catch there were two exceptions I needed to catch, so I found that the most efficient
                //  way to do that was to catch all exceptions as I respond the same way to all (expected) exceptions.
                System.out.print("Please put a number in the format $mm(m): ");
            }
        }
        return betAmountInt;
    }

    /**
     * 
     * @param in
     * @param money
     * @return
     */
    private static int getPairPlus(Scanner in, int money) {
        boolean wantsBet = false;
        boolean validInput = false;
        boolean validBet = false;
        boolean hasDone = false;
        int betAmountInt = 0;
        
        while (!validInput) {
            if (!hasDone) {
                System.out.print("Do you want to place a pair plus bet (Y/N)? ");
                hasDone = true;
            }
            String temp = in.nextLine().toUpperCase();
            if (temp.equals("Y")) {
                if (money < MIN_BET + MIN_MONEY_TO_PLAY) {
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
            while (!validBet) {
            
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
                    System.out.print("Please put a number in the format $mm(m): ");
                }
            }
        }
        return betAmountInt;
    }

    /**
     * 
     * @param in
     * @return
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
            } catch (Exception e) {                         // I needed to catch multiple exceptions (NumberFormatException and NoSuchElementException) and just catching all exceptions was the most efficient way to do it because I follow the same procedure no matter the exception that is thrown (at least no matter which of the exceptions I want is thrown).
                System.out.print("Please put a number in the format $mmm: ");
            }
        }
        return startingMoneyInt;
    }

    /**
     * 
     * @param hand
     * @param cardWanted
     * @return
     */
    private static int getCardNumber(String hand, int cardWanted) {
        String temp = hand.substring(hand.indexOf(" ") + 1);
        if (cardWanted == 1) {
            String card = checkFaceValues(hand.substring(0, hand.indexOf(" ")));
            return Integer.parseInt(card.substring(0, card.length() - 1));
        } else if (cardWanted == 2) {
            String card = checkFaceValues(temp.substring(0, temp.indexOf(" ")));
            return Integer.parseInt(card.substring(0, card.length() - 1));
        } else {
            String card = checkFaceValues(temp.substring(temp.indexOf(" ") + 1, temp.length() - 1));  // minus 1 because I had to account for a space after the third card/at the end of the hand (and temp) string(s).
            return Integer.parseInt(card.substring(0, card.length() - 1));
        }   
    }

    /**
     * 
     * @param hand
     * @param cardWanted
     * @return
     */
    private static String getCardFromHand(String hand, int cardWanted) {
        String temp = hand.substring(hand.indexOf(" ") + 1);
        if (cardWanted == 1) {
            return checkFaceValues(hand.substring(0, hand.indexOf(" ")));
        } else if (cardWanted == 2) {
            return checkFaceValues(temp.substring(0, temp.indexOf(" ")));
        } else {
            return checkFaceValues(temp.substring(temp.indexOf(" ") + 1, temp.length() - 1)); // minus 1 because I have to account for a space at the end of the hand (and temp) string(s).
        }
    }

    /**
     * 
     * @param card
     * @return
     */
    private static String checkFaceValues(String card) {
        if (card.substring(0, 1).equals("J")) {
            return "11" + card.substring(card.length() - 1);
        } else if (card.substring(0, 1).equals("Q")) {
            return "12" + card.substring(card.length() - 1);
        } else if (card.substring(0, 1).equals("K")) {
            return "13" + card.substring(card.length() - 1);
        } else if (card.substring(0, 1).equals("A")) {
            return "14" + card.substring(card.length() - 1);
        } else {
        return card;
        }
    }

    /**
     * 
     * @return
     */
    private static String dealCards() {
        String cards = "";

        for (int i = 0; i < 3; i++) {
            Boolean hasCard = false;
            while (!hasCard) {
                String card = getCard();
                if (isUnique(cards, card)) {
                    cards += card + " ";
                    hasCard = true;
                }
            }
        }

        return cards;
    }

    /**
     * 
     * @param playerHand
     * @param card
     * @return
     */
    private static boolean isUnique(String playerHand, String card) {
        return playerHand.indexOf(card) == -1;
    }

    /**
     * 
     * @return
     */
    private static String getCard() {
        return getFace() + getSuit();
    }

    /**
     * 
     * @return
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

    /**
     * 
     * @return
     */
    private static String getFace() {
        int face = (int) (Math.random() * NUM_FACES + 2);
        
        if (face >= 2 && face <= 10) {
           return "" + face;
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
}