package com.blackjack;
import java.util.Arrays;
import java.util.Scanner;



public class Game {

    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BRIGHT_GREEN = "\u001B[92m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[91m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_WHITE = "\u001B[97m";

    private int[][] cartes_piochees;
    private int[][] cartes_restantes;
    private int[][] roomCards;
    private int sold;

    public Game(int initialMoney) {
        int[][] cards = Shuffle.melanger_jeu_cartes(Shuffle.sortedCards());
        this.cartes_piochees = Shuffle.piocher_n_cartes(cards)[0];
        this.cartes_restantes = Shuffle.piocher_n_cartes(cards)[1];
        this.roomCards = new int[0][];
        this.sold = initialMoney;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        boolean check = true;

        System.out.println("\nWelcome to Blackjack!");
        System.out.println("\nYour have: " + sold + "\uD83D\uDCB2");

        while (playAgain) {
            if (check) GameMessages.displayMenu1();
            else GameMessages.displayMenu2();
            String choice = scanner.next();
            scanner.nextLine();

            switch (choice) {
                case "1":
                    check = false;
                    playRound();
                    break;
                case "2":
                    GameMessages.displayRules();
                    break;
                case "3":
                    playAgain = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void playRound() {
        int bet = getBet();
        int[][][] playGame = playGame(cartes_piochees, cartes_restantes, roomCards, sold, bet);
        cartes_piochees = playGame[0];
        cartes_restantes = playGame[1];
        sold = playGame[2][0][0];
        roomCards = playGame[3];
    }



    public int getBet() {
        Scanner scanner = new Scanner(System.in);
        Boolean check = true;
        String bet = "";
        int intBet = 0;
        System.out.print("Place a bet: ");
        while (check) {
            bet = scanner.next();
            try {
                intBet = Integer.parseInt(bet);
                if ( intBet >= 1 && intBet <= sold) {
                    check = false;
                } else {
                    System.out.println("Invalid bet. Please place a bet between $1 and $" + sold +":");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid bet format! Please enter a valid bet:");
            }

        }
        return intBet;
    }



    public static int[][][] playGame(int[][] cartes_piochees, int[][] cartes_restantes, int[][] roomCards, int sold, int bet) {


        if(cartes_piochees.length<4){
            int[][][] pioch = Shuffle.deffausser_cartes(cartes_piochees, cartes_restantes, roomCards);
            cartes_piochees = pioch[0];
            cartes_restantes = pioch[1];
            roomCards = new int[0][];
            GameMessages.displayShuffleMessage();
        }

        int[][] dealerHand = new int[0][];
        int[][] playerHand = new int[0][];
        int[][][] handHelper;

        System.out.println("\n");
        System.out.println(Arrays.deepToString(cartes_piochees));
        System.out.println(Arrays.deepToString(cartes_restantes));
        System.out.println(Arrays.deepToString(roomCards));


        for (int i = 0; i < 2; i++) {
            handHelper = hit(cartes_piochees, playerHand);
            playerHand = handHelper[0];
            cartes_piochees = handHelper[1];

            handHelper = hit(cartes_piochees, dealerHand);
            dealerHand = handHelper[0];
            cartes_piochees = handHelper[1];
        }

        System.out.println("\nDealer Hand\n");
        CardDesign.displayUpsideCard(ANSI_YELLOW);
        CardDesign.displayOneCardDrawing(dealerHand[1], ANSI_YELLOW);

        System.out.println("\nPlayer Hand:\n");
        CardDesign.displayCardDrawing(playerHand, ANSI_GREEN);
        System.out.println("score: "+score(playerHand));

        while (score(playerHand)<=21){
            boolean check = false;
            boolean shuffle = false;
            String choice;
            choice = score(playerHand)==21 ? "2" : HitStandChoice();
            switch (choice) {
                case "1":
                    if(cartes_piochees.length<1){
                        System.out.println("No card left");
                        shuffle = true;
                        break;
                    }
                    handHelper = hit(cartes_piochees, playerHand);
                    playerHand = handHelper[0];
                    cartes_piochees = handHelper[1];
                    System.out.println("\nPlayer Hand:\n");
                    CardDesign.displayCardDrawing(playerHand, ANSI_GREEN);
                    System.out.println("score: "+score(playerHand));
                    break;
                case "2":
                    if(cartes_piochees.length<1){
                        System.out.println("No card left");
                        shuffle = true;
                        break;
                    }
                    if(score(dealerHand)>=17) {
                        check = true;
                        break;
                    }

                    handHelper = stand(cartes_piochees, dealerHand);
                    dealerHand = handHelper[0];
                    cartes_piochees = handHelper[1];
                    if(score(dealerHand)>=17) check = true;
                    break;
                case "3":
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            if (check || shuffle) break;

        }

        int scorePlayer = score(playerHand);
        int scoreDealer = score(dealerHand);

        System.out.println("\n-----------Round Info:-----------");
        System.out.println("\nDealer Hand\n");
        CardDesign.displayCardDrawing(dealerHand, ANSI_YELLOW);
        System.out.println("score: "+scoreDealer);

        System.out.println("\nPlayer Hand:\n");
        CardDesign.displayCardDrawing(playerHand, ANSI_GREEN);
        System.out.println("score: "+scorePlayer);

        sold = determineGameResult(scorePlayer, scoreDealer, bet, sold);
        System.out.println(ANSI_WHITE+"Your sold: "+sold+ANSI_RESET+"\n");

        if (sold<=0){
            System.out.println(ANSI_RED+"\nYou lose your money"+ANSI_RESET);
            GameMessages.charByCharDisplay(new String[]{"L","O","O","O","O","O","O","O","O","O","S","E","R"});
            System.exit(0);
        }

        roomCards = Shuffle.concatThreeArrays(playerHand, dealerHand, roomCards);

        return new int[][][]{cartes_piochees, cartes_restantes, new int[][]{new int[]{sold}}, roomCards};
    }

    private static String HitStandChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n1. Hit\n2. Stand\n3. Quit\nEnter your choice: ");
        return scanner.next();
    }


    public static int[][][] hit(int[][] cartes_piochees, int[][] hand) {
        int[][][] handHelper = Shuffle.extraire_ieme_carte(cartes_piochees, 0);
        int[] cardToAdd = handHelper[0][0];
        hand = addCardToHand(hand,cardToAdd);
        cartes_piochees = handHelper[1];
        int[][][] result = new int[2][][];
        result[0] = hand;
        result[1] = cartes_piochees;
        return result;
    }

    public static int[][][] stand(int[][] cartes_piochees, int[][] hand) {
        int score = score(hand);
        while (score < 17){
            int[][][] handHelper = Shuffle.extraire_ieme_carte(cartes_piochees, 0);
            int[] cardToAdd = handHelper[0][0];
            hand = addCardToHand(hand,cardToAdd);
            cartes_piochees = handHelper[1];
            score = score(hand);
        }
        int[][][] result = new int[2][][];
        result[0] = hand;
        result[1] = cartes_piochees;
        return result;
    }

    public static int[][] addCardToHand(int[][] hand, int[] card) {
        int[][] newHand = new int[hand.length + 1][];
        for (int i = 0; i < hand.length; i++) {
            newHand[i] = hand[i];
        }
        newHand[hand.length] = card;
        return newHand;
    }


    public static int score(int[][] hand) {
        int score = 0;
        for (int[] card: hand) {
            if (card[0]==11 || card[0]==12 || card[0]==13) score+=10;
            else score+=card[0];
        }
        for (int[] card: hand) {
            if (card[0]==1 && score<=11){
                score+=10;
            }
        }
        return score;
    }
    public static int determineGameResult(int scorePlayer, int scoreDealer, int bet, int sold){

        if (scorePlayer>21){
            System.out.println(ANSI_RED+"\nYou Lose"+ANSI_RESET);
            sold-=bet;
        }
        else if (scoreDealer>21) {
            System.out.println(ANSI_BRIGHT_GREEN+"\nYou Win"+ANSI_RESET);
            sold+=bet;
        }
        else if (scorePlayer < scoreDealer) {
            System.out.println(ANSI_RED+"\nYou Lose"+ANSI_RESET);
            sold-=bet;
        }
        else if (scorePlayer > scoreDealer) {
            System.out.println(ANSI_BRIGHT_GREEN+"\nYou Win"+ANSI_RESET);
            sold+=bet;
        }
        else System.out.println("\nIt's a draw");

        return sold;
    }
}
