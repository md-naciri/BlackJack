package com.blackjack;
import java.util.List;
import java.util.Scanner;
/*
public class Game {
    private List<int[]> deck;
    private List<int[]> playerHand;
    private List<int[]> dealerHand;

    public void start() {
        System.out.println("Welcome to Blackjack!");

        // Initialize the deck
        //deck = Shuffle.melanger_jeu_cartes(Shuffle.sortedCards());

        // Place bets
        int bet = getUserBet();

        // Deal initial cards
        playerHand = dealCard();
        dealerHand = dealCard();
        playerHand.addAll(dealCard());

        // Display initial hands
        System.out.println("Player's hand: ");
        displayHand(playerHand);
        System.out.println("Dealer's hand: ");
        displayDealerHand();

        // Player's turn
        boolean gameOver = playerTurn();

        // Dealer's turn
        if (!gameOver) {
            dealerTurn();
        }

        // Determine the winner
        determineWinner();
    }

    private int getUserBet() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your bet amount: ");
        return scanner.nextInt();
    }

    private List<int[]> dealCard() {
        return Shuffle.tirer_une_carte(deck);
    }

    private void displayHand(List<int[]> hand) {
        for (int[] card : hand) {
            System.out.println(getCardString(card));
        }
        System.out.println("Total score: " + calculateScore(hand));
    }

    private void displayDealerHand() {
        System.out.println(getCardString(dealerHand.get(0)));  // Show only the first card
        System.out.println("Hidden Card");
    }

    private String getCardString(int[] card) {
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};

        String rank = ranks[card[0] - 1];
        String suit = suits[card[1] - 1];

        return rank + " of " + suit;
    }

    private int calculateScore(List<int[]> hand) {
        int score = 0;
        int numAces = 0;

        for (int[] card : hand) {
            int rank = card[0];
            if (rank == 1) {  // Ace
                score += 11;
                numAces++;
            } else if (rank >= 10) {  // Face cards
                score += 10;
            } else {
                score += rank;
            }
        }

        // Adjust score for aces
        while (score > 21 && numAces > 0) {
            score -= 10;
            numAces--;
        }

        return score;
    }

    private boolean playerTurn() {
        Scanner scanner = new Scanner(System.in);
        String choice;

        while (true) {
            System.out.print("Choose an action: (H)it or (S)tand: ");
            choice = scanner.nextLine().trim().toUpperCase();

            if (choice.equals("H")) {
                playerHand.addAll(dealCard());
                System.out.println("Player's hand: ");
                displayHand(playerHand);

                if (calculateScore(playerHand) > 21) {
                    System.out.println("Bust! You lose.");
                    return true;
                }
            } else if (choice.equals("S")) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        return false;
    }

    private void dealerTurn() {
        System.out.println("Dealer's hand: ");
        displayHand(dealerHand);

        while (calculateScore(dealerHand) < 17) {
            dealerHand.addAll(dealCard());
            System.out.println("Dealer hits.");
            displayHand(dealerHand);
        }

        if (calculateScore(dealerHand) > 21) {
            System.out.println("Dealer busts! You win.");
        }
    }

    private void determineWinner() {
        int playerScore = calculateScore(playerHand);
        int dealerScore = calculateScore(dealerHand);

        System.out.println("Player's score: " + playerScore);
        System.out.println("Dealer's score: " + dealerScore);

        if (playerScore > dealerScore) {
            System.out.println("You win!");
        } else if (playerScore < dealerScore) {
            System.out.println("You lose.");
        } else {
            System.out.println("It's a tie.");
        }
    }
}*/