package com.blackjack;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shuffle {
    public static List<int[]> sortedCards() {
        int rank = 1;
        int suit = 1;
        List<int[]> cards = new ArrayList<>();
        for(int s = suit; s <= 4; s++){
            rank = (s == suit) ? rank : 1;
            for(int r = rank; r <= 13; r++){
                int[] rs = {r, s};
                cards.add(rs);
            }
        }
        return cards;
    }
    public static List<int[]> extraire_ieme_carte(List<int[]> lst, int index) {
        int[] element = lst.remove(index);
        List<int[]> resultList = new ArrayList<>();
        resultList.add(element);
        resultList.addAll(lst);
        return resultList;
    }

    public static List<int[]> tirer_une_carte(List<int[]> lst) {
        Random random = new Random();
        int randomIndex = random.nextInt(lst.size());
        return extraire_ieme_carte(lst, randomIndex);
    }
    public static List<int[]> melanger_jeu_cartes(List<int[]> lst) {
        if (lst.size() <= 1) {
            return lst;
        } else {
            List<int[]> result =  tirer_une_carte(lst);
            List<int[]> finalResult = new ArrayList<>();
            finalResult.add(result.get(0));
            finalResult.addAll(melanger_jeu_cartes(result.subList(1, result.size())));
            return finalResult;
        }
    }

    public static List<List<int[]>> piocher_n_cartes(List<int[]> lst) {
        List<List<int[]>> result = new ArrayList<>();

        Random random = new Random();
        int index = random.nextInt(9) + 21;

        List<int[]> cartes_piochees = new ArrayList<>(lst.subList(0, index));
        List<int[]> cartes_restantes = new ArrayList<>(lst.subList(index, lst.size()));

        result.add(cartes_piochees);
        result.add(cartes_restantes);

        return result;
    }
}

