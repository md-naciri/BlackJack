package com.blackjack;
import java.util.Scanner;


public class Game {

    public static void start() {
        Scanner scanner = new Scanner(System.in);

        boolean playAgain = true;
        int[][] sortedCards = Shuffle.sortedCards();
        int[][] cards = Shuffle.melanger_jeu_cartes(sortedCards);
        int[][][] pioch = Shuffle.piocher_n_cartes(cards);
        int[][] cartes_piochees = pioch[0];
        int[][] cartes_restantes = pioch[1];
        int[][] roomCards = new int[0][];
        int dealerScore = 0;
        int playerScore = 0;

        boolean check = false;

        while (playAgain) {
            if(!check){
                System.out.println("\nWelcome to Blackjack!");
                System.out.println("1. Start Game");
                System.out.println("2. Rules");
                System.out.println("3. Quit");
                System.out.print("Enter your choice: ");
            }
            else {
                System.out.println("1. Continue");
                System.out.println("2. Rules");
                System.out.println("3. Quit");
                System.out.print("Enter your choice: ");
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    check = true;
                    int bet = bet();
                    int[][][] playGame = playGame(cartes_piochees, cartes_restantes, roomCards, dealerScore, playerScore);
                    cartes_piochees = playGame[0];
                    cartes_restantes = playGame[1];
                    dealerScore = playGame[2][0][0];
                    playerScore = playGame[3][0][0];
                    roomCards = playGame[4];
                    break;
                case 2:
                    displayRules();
                    break;
                case 3:
                    playAgain = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }



    public static int[][][] playGame(int[][] cartes_piochees, int[][] cartes_restantes, int[][] roomCards, int dealerScore, int playerScore) {

        if(cartes_piochees.length<4){
            int[][][] pioch = Shuffle.deffausser_cartes(cartes_piochees, cartes_restantes, roomCards);
            cartes_piochees = pioch[0];
            cartes_restantes = pioch[1];
            roomCards = new int[0][];
            displayShuffleMessage();
        }
        Scanner scanner = new Scanner(System.in);

        int[][] dealerHand = new int[0][];
        int[][] playerHand = new int[0][];



        /*System.out.println(Arrays.deepToString(cartes_piochees));
        System.out.println(Arrays.deepToString(cartes_restantes));
        System.out.println(Arrays.deepToString(roomCards));*/


        int[][][] handHelper;

        for (int i = 0; i < 2; i++) {
            handHelper = hit(cartes_piochees, playerHand);
            playerHand = handHelper[0];
            cartes_piochees = handHelper[1];

            handHelper = hit(cartes_piochees, dealerHand);
            dealerHand = handHelper[0];
            cartes_piochees = handHelper[1];
        }

        System.out.println("\nDealer Hand\n");
        displayUpsideCard();
        displayOneCardDrawing(dealerHand[1], "\u001B[33m");

        System.out.println("\nPlayer Hand:\n");
        displayCardDrawing(playerHand, "\u001B[32m");
        System.out.println("score: "+score(playerHand));

        while (score(playerHand)<=21){
            boolean check = false;
            boolean shuffle = false;
            String choice;
            if (score(playerHand)==21){
                choice = "2";
            }else {
                System.out.println("\n1. Hit");
                System.out.println("2. Stand");
                System.out.println("3. Quit");
                System.out.print("Enter your choice: ");
                choice = scanner.next();
                scanner.nextLine(); // Consume the newline character
            }
            switch (choice) {
                case "1":
                    if(cartes_piochees.length<1){
                        System.out.println("No card left");
                        //displayShuffleMessage();
                        shuffle = true;
                        break;
                    }
                    handHelper = hit(cartes_piochees, playerHand);
                    playerHand = handHelper[0];
                    cartes_piochees = handHelper[1];
                    System.out.println("\nPlayer Hand:\n");
                    displayCardDrawing(playerHand, "\u001B[32m");
                    System.out.println("score: "+score(playerHand));
                    break;
                case "2":
                    if(cartes_piochees.length<1){
                        displayShuffleMessage();
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

        System.out.println("\n-----------Round Info:-----------");
        System.out.println("\nDealer Hand\n");
        displayCardDrawing(dealerHand, "\u001B[33m");
        System.out.println("score: "+score(dealerHand));


        System.out.println("\nPlayer Hand:\n");
        displayCardDrawing(playerHand, "\u001B[32m");
        System.out.println("score: "+score(playerHand));

        if (score(playerHand)>21){
            System.out.println("\nYou Lose\n");
            dealerScore++;
        }
        else if (score(dealerHand)>21) {
            System.out.println("\nYou Win\n");
            playerScore++;
        }
        else if (score(playerHand) < score(dealerHand)) {
            System.out.println("\nYou Lose\n");
            dealerScore++;
        }
        else if (score(playerHand) > score(dealerHand)) {
            System.out.println("\nYou Win\n");
            playerScore++;
        }
        else System.out.println("\nIt's a draw\n");

        roomCards = Shuffle.concatArrays(roomCards, dealerHand);
        roomCards = Shuffle.concatArrays(roomCards, playerHand);
        System.out.println("Your total score: "+playerScore);
        System.out.println(("Dealer total score: "+dealerScore));

        int[][][] result = new int[5][][];
        result[0] = cartes_piochees;
        result[1] = cartes_restantes;
        result[2] = new int[][]{new int[]{dealerScore}};
        result[3] = new int[][]{new int[]{playerScore}};
        result[4] = roomCards;
        return result;
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
    public static void displayRules() {
        System.out.println("\nSeriously, all pumped up for Blackjack but zero clue about the rules?\n" +
                "Fear not! A little Google search will transform you from a lost soul to a Blackjack maestro.\n" +
                "Get ready to conquer the tables, man! \uD83C\uDFB2\n");
    }

    public static void displayShuffleMessage() {
        System.out.println("\nThere are no left cards, the dealer is shuffling the cards");
        String[] count = {"B","e"," ","p","a","t","i","e","n","t"," ",".",".","."};
        for (int i = 0; i < count.length ; i++) {
            System.out.print(count[i]);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static String symbolize(int value) {
        if (value == 1) {
            return "A";
        } else if (value == 11) {
            return "J";
        } else if (value == 12) {
            return "Q";
        } else if (value == 13) {
            return "K";
        } else {
            return String.valueOf(value);
        }
    }

    public static void displayUpsideCard() {
        System.out.println("\u001B[33m"+"  _____");
        System.out.println(" |     |");
        System.out.println(" | ^_^ |");
        System.out.println(" |     |");
        System.out.println("  ¯¯¯¯¯"+"\u001B[0m");
    }

    public static void displayCardDrawing(int[][] cards, String color) {
        String[] suits = {"♦", "♥", "♠", "♣"};
        String line1 = "";
        String line2 = "";
        String line3 = "";
        String line4 = "";
        String line5 = "";

        for (int[] card : cards) {
            int value = card[0];
            int suitIndex = card[1] - 1; // Subtract 1 to map to the suits array
            String suit = suits[suitIndex];
            //System.out.print("[" + symbolize(value) + suit + "] ");
            if (value == 10) {
                System.out.println(color+"  _____");
                System.out.println(" |" + symbolize(value) + "   |");
                System.out.println(" |  " + suit + "  |");
                System.out.println(" |   " + symbolize(value) + "|");
                System.out.println("  ¯¯¯¯¯"+"\u001B[0m");
            }
            else {
                System.out.println(color+"  _____");
                System.out.println(" |" + symbolize(value) + "    |");
                System.out.println(" |  " + suit + "  |");
                System.out.println(" |    " + symbolize(value) + "|");
                System.out.println("  ¯¯¯¯¯"+"\u001B[0m");
            }
        }
        /*for (int[] card : cards) {
            int value = card[0];
            int suitIndex = card[1] - 1; // Subtract 1 to map to the suits array
            String suit = suits[suitIndex];
            //System.out.print("[" + symbolize(value) + suit + "] ");
            if (value == 10) {
                line1+= "  "+"  _____";
                line2+= "  "+" |" + symbolize(value) + "   |";
                line3+= "  "+" |  " + suit + "  |";
                line4+= "  "+" |   " + symbolize(value) + "|";
                line5+= "  "+"  ¯¯¯¯¯";
            }
            else {
                line1+= "  "+"  _____";
                line2+= "  "+" |" + symbolize(value) + "    |";
                line3+= "  "+" |  " + suit + "  |";
                line4+= "  "+" |    " + symbolize(value) + "|";
                line5+= "  "+"  ¯¯¯¯¯";
            }
        }
        String toPrint = line1+"\n"+line2+"\n"+line3+"\n"+line4+"\n"+line5;
        System.out.println(toPrint);*/
    }

    public static void displayOneCardDrawing(int[] card,String color){

        String[] suits = {"♦", "♥", "♠", "♣"};
        int value = card[0];
        int suitIndex = card[1] - 1; // Subtract 1 to map to the suits array
        String suit = suits[suitIndex];
        //System.out.print("[" + symbolize(value) + suit + "] ");
        if (value == 10) {
            System.out.println(color+"  _____");
            System.out.println(" |" + symbolize(value) + "   |");
            System.out.println(" |  " + suit + "  |");
            System.out.println(" |   " + symbolize(value) + "|");
            System.out.println("  ¯¯¯¯¯"+"\u001B[0m");
        }
        else {
            System.out.println(color+"  _____");
            System.out.println(" |" + symbolize(value) + "    |");
            System.out.println(" |  " + suit + "  |");
            System.out.println(" |    " + symbolize(value) + "|");
            System.out.println("  ¯¯¯¯¯"+"\u001B[0m");
        }
    }

    public static int bet() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("place a bet: ");
        int bet = scanner.nextInt();
        return bet;
    }
}
