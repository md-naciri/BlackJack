package com.blackjack;

public class CardDesign {
    private static final String ANSI_RESET = "\u001B[0m";
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

    public static void displayUpsideCard(String color) {
        System.out.println("\u001B[33m"+"  _____");
        System.out.println(" |     |");
        System.out.println(" | ^_^ |");
        System.out.println(" |     |");
        System.out.println("  ¯¯¯¯¯"+ANSI_RESET);
    }

    public static void displayCardDrawing(int[][] cards, String color) {
        String[] suits = {"♦", "♥", "♠", "♣"};
        String line1 = "";
        String line2 = "";
        String line3 = "";
        String line4 = "";
        String line5 = "";
        int i=0;
        for (int[] card : cards) {
            int value = card[0];
            int suitIndex = card[1] - 1; // Subtract 1 to map to the suits array
            String suit = suits[suitIndex];
            //System.out.print("[" + symbolize(value) + suit + "] ");
            if (i>0) {line1+=" "; line5+=" ";}
            if (value == 10) {
                line1+= " "+"  _____";
                line2+= " "+" |" + symbolize(value) + "   |";
                line3+= " "+" |  " + suit + "  |";
                line4+= " "+" |   " + symbolize(value) + "|";
                line5+= " "+"  ¯¯¯¯¯";
            }
            else {
                line1+= " "+"  _____";
                line2+= " "+" |" + symbolize(value) + "    |";
                line3+= " "+" |  " + suit + "  |";
                line4+= " "+" |    " + symbolize(value) + "|";
                line5+= " "+"  ¯¯¯¯¯";
            }
            i++;
        }
        String toPrint = line1+"\n"+line2+"\n"+line3+"\n"+line4+"\n"+line5;
        System.out.println(color+toPrint+ANSI_RESET);

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
            System.out.println("  ¯¯¯¯¯"+ANSI_RESET);
        }
        else {
            System.out.println(color+"  _____");
            System.out.println(" |" + symbolize(value) + "    |");
            System.out.println(" |  " + suit + "  |");
            System.out.println(" |    " + symbolize(value) + "|");
            System.out.println("  ¯¯¯¯¯"+ANSI_RESET);
        }
    }
}
