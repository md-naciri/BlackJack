package com.blackjack;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!\n");
        //Shuffle.sortedCards().forEach(e-> System.out.println(e[0] + " " + e[1]));
        //List<int[]> r = Shuffle.tirer_une_carte(Shuffle.sortedCards());
        //r.forEach(e-> System.out.println(e[0] + " " + e[1]));
        //for (int[] array : r) {
        //    System.out.println(Arrays.toString(array));
        //}
        //List<int[]> r1 = Shuffle.extraire_ieme_carte(Shuffle.sortedCards(), 3);





/*
        List<int[]> cards = Shuffle.sortedCards();
        List<int[]> shuffledCards = Shuffle.melanger_jeu_cartes(cards);
        for (int[] array : shuffledCards) {
            System.out.println(Arrays.toString(array));
        }

*/


        List<List<int[]>> result = Shuffle.piocher_n_cartes(Shuffle.melanger_jeu_cartes(Shuffle.sortedCards()));

        System.out.println("Cartes piochées:");
        for (int[] card : result.get(0)) {
            System.out.println(Arrays.toString(card));
        }

        System.out.println("\nCartes restantes:");
        for (int[] card : result.get(1)) {
            System.out.println(Arrays.toString(card));
        }



    }
}
