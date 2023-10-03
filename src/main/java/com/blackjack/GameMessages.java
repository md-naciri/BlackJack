package com.blackjack;

public class GameMessages {
    public static void displayMenu1() {
        System.out.println("1. Start Game");
        System.out.println("2. Rules");
        System.out.println("3. Quit");
        System.out.print("Enter your choice: ");
    }
    public static void displayMenu2() {
        System.out.println("1. Continue");
        System.out.println("2. Rules");
        System.out.println("3. Quit");
        System.out.print("Enter your choice: ");
    }
    public static void displayRules() {
        System.out.println("\nSeriously, all pumped up for Blackjack but zero clue about the rules?\n" +
                "Fear not! A little Google search will transform you from a lost soul to a Blackjack maestro.\n" +
                "Get ready to conquer the tables, man! \uD83C\uDFB2\n");
    }
    public static void displayShuffleMessage() {
        System.out.println("\nThere are no left cards, the dealer is shuffling the cards");
        String[] count = {"B","e"," ","p","a","t","i","e","n","t"," ",".",".",".",".",".",".",".",".","."};
        for (int i = 0; i < count.length ; i++) {
            System.out.print(count[i]);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void charByCharDisplay(String[] snetence) {
        for (int i = 0; i < snetence.length ; i++) {
            System.out.print(snetence[i]);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
