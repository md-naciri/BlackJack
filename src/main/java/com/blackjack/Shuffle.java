package com.blackjack;
import java.util.Random;

public class Shuffle {

    public static int[][] sortedCards() {
        int rank = 1;
        int suit = 1;
        int totalCards = 4 * 13;
        int[][] cards = new int[totalCards][2];
        int index = 0;

        for (int s = suit; s <= 4; s++) {
            rank = (s == suit) ? rank : 1;
            for (int r = rank; r <= 13; r++) {
                cards[index][0] = r;
                cards[index][1] = s;
                index++;
            }
        }
        return cards;
    }

    public static int[][][] extraire_ieme_carte(int[][] cards, int index) {
        int[] element = cards[index];

        int[][] resultList = new int[cards.length - 1][];
        int resultIndex = 0;

        for (int i = 0; i < cards.length; i++) {
            if (i != index) {
                resultList[resultIndex++] = cards[i];
            }
        }

        int[][][] finalResult = new int[2][][];
        finalResult[0] = new int[1][];
        finalResult[0][0] = element;
        finalResult[1] = resultList;

        return finalResult;
    }

    public static int[][][] tirer_une_carte(int[][] cards) {
        Random random = new Random();
        int randomIndex = random.nextInt(cards.length);
        return extraire_ieme_carte(cards, randomIndex);
    }

    public static int[][] melanger_jeu_cartes(int[][] cards) {
        if (cards.length == 1) return new int[][] { cards[0] };

        int[][][] drawnCard = tirer_une_carte(cards);
        int[][] shuffledCards = melanger_jeu_cartes(drawnCard[1]);

        return concatArrays(new int[][] { drawnCard[0][0] }, shuffledCards);
    }

    public static int[][] concatArrays(int[][] a, int[][] b) {
        int[][] result = new int[a.length + b.length][];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i];
        }
        for (int i = 0; i < b.length; i++) {
            result[a.length + i] = b[i];
        }
        return result;
    }

    public static int[][][] piocher_n_cartes(int[][] cards) {
        Random random = new Random();
        int randomIndex = random.nextInt(9) + 21; // Generate a random index between 21 and 29 (inclusive)

        int[][] cartes_piochees = new int[randomIndex + 1][];
        int[][] cartes_restantes = new int[cards.length - (randomIndex + 1)][];

        // Fill the 'cartes_piochees' array
        for (int i = 0; i <= randomIndex; i++) {
            cartes_piochees[i] = cards[i];
        }

        // Fill the 'cartes_restantes' array
        for (int i = randomIndex + 1; i < cards.length; i++) {
            cartes_restantes[i - (randomIndex + 1)] = cards[i];
        }

        int[][][] result = new int[2][][];
        result[0] = cartes_piochees;
        result[1] = cartes_restantes;

        return result;
    }

    public static int[][][] deffausser_cartes(int[][] card1, int[][] card2) {
        int[][] combinedCards = concatArrays(card1, card2);
        return piocher_n_cartes(melanger_jeu_cartes(combinedCards));
    }
}


