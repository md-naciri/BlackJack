package com.blackjack;

import java.util.Arrays;
import java.util.List;

public class Main {
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
    public static void main(String[] args) {
        String[] suits = {"♦", "♥", "♠", "♣"};
        /*for (int[] card : Shuffle.sortedCards()) {
            int value = card[0];
            int suitIndex = card[1] - 1; // Subtract 1 to map to the suits array
            String suit = suits[suitIndex];
            //System.out.print("[" + symbolize(value) + suit + "] ");
            System.out.println("  _____");
            System.out.println(" |" + symbolize(value) + "    |");
            System.out.println(" |  " + suit + "  |");
            System.out.println(" |    " + symbolize(value) + "|");
            System.out.println("  ¯¯¯¯¯");
        }*/
        String message = "test";
        System.out.println("\u001B[30m" + message + "\u001B[0m");  // Black
        System.out.println("\u001B[31m" + message + "\u001B[0m");  // Red
        System.out.println("\u001B[32m" + message + "\u001B[0m");  // Green
        System.out.println("\u001B[33m" + message + "\u001B[0m");  // Yellow
        System.out.println("\u001B[34m" + message + "\u001B[0m");  // Blue
        System.out.println("\u001B[35m" + message + "\u001B[0m");  // Magenta
        System.out.println("\u001B[36m" + message + "\u001B[0m");  // Cyan
        System.out.println("\u001B[37m" + message + "\u001B[0m");  // White

        /*
        int[][] cards = Shuffle.sortedCards();

        int[][][] result = Shuffle.piocher_n_cartes(Shuffle.melanger_jeu_cartes(cards));
        int[][] cartes_piochees = result[0];
        int[][] cartes_restantes = result[1];

        System.out.println("Cartes piochées:");
        for (int[] card : cartes_piochees) {
            System.out.println(Arrays.toString(card));
        }

        System.out.println("Cartes restantes:");
        for (int[] card : cartes_restantes) {
            System.out.println(Arrays.toString(card));
        }

        int[][][] defauss = Shuffle.deffausser_cartes(cartes_piochees, cartes_restantes);
        int[][] cartes_piochees1 = defauss[0];
        int[][] cartes_restantes1 = defauss[1];
        System.out.println("\nMn ba3d defauss:\n");
        System.out.println("Cartes piochées:");
        for (int[] card : cartes_piochees1) {
            System.out.println(Arrays.toString(card));
        }
        System.out.println("Cartes restantes:");
        for (int[] card : cartes_restantes1) {
            System.out.println(Arrays.toString(card));
        }
        */


        /*int[][][] finalResult = Shuffle.tirer_une_carte(cards);

        int[] extractedElement = finalResult[0][0];
        System.out.println("Extracted Element: Rank: " + extractedElement[0] + ", Suit: " + extractedElement[1]);

        int[][] remainingCards = finalResult[1];
        System.out.println("Remaining Cards:");

        for (int i = 0; i < remainingCards.length; i++) {
            int[] card = remainingCards[i];
            int suitIndex = remainingCards[i][1] - 1;
            String suit = suits[suitIndex];
            System.out.println("[" + symbolize(card[0]) + suit + "] ");
        }

        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        int[][] sh_cards = Shuffle.melanger_jeu_cartes(cards);
        for (int i = 0; i < sh_cards.length; i++) {
            int[] card = sh_cards[i];
            int suitIndex = sh_cards[i][1] - 1;
            String suit = suits[suitIndex];
            System.out.println("[" + symbolize(card[0]) + suit + "] ");
        }*/



        Game.start();



    }
}
