package com.blackjack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*String message = "test";
        System.out.println("\u001B[30m" + message + "\u001B[0m");  // Black
        System.out.println("\u001B[31m" + message + "\u001B[0m");  // Red
        System.out.println("\u001B[32m" + message + "\u001B[0m");  // Green
        System.out.println("\u001B[33m" + message + "\u001B[0m");  // Yellow
        System.out.println("\u001B[34m" + message + "\u001B[0m");  // Blue
        System.out.println("\u001B[35m" + message + "\u001B[0m");  // Magenta
        System.out.println("\u001B[36m" + message + "\u001B[0m");  // Cyan
        System.out.println("\u001B[37m" + message + "\u001B[0m");  // White
        System.out.println("\u001B[90m" + message + "\u001B[0m");  // Bright Black
        System.out.println("\u001B[91m" + message + "\u001B[0m");  // Bright Red
        System.out.println("\u001B[92m" + message + "\u001B[0m");  // Bright Green
        System.out.println("\u001B[93m" + message + "\u001B[0m");  // Bright Yellow
        System.out.println("\u001B[94m" + message + "\u001B[0m");  // Bright Blue
        System.out.println("\u001B[95m" + message + "\u001B[0m");  // Bright Magenta
        System.out.println("\u001B[96m" + message + "\u001B[0m");  // Bright Cyan
        System.out.println("\u001B[97m" + message + "\u001B[0m");  // Bright White*/


        System.out.println("\n" +
                "█░█░█ █▀▀ █░░ █▀▀ █▀█ █▀▄▀█ █▀▀   ▀█▀ █▀█   ▄▀█ █▄░█ ▄▀█ █▀ █▀   █▀▀ █░█ █▀▀ █▄▀ █ █▄░█ █▀▀   █▀▀ ▄▀█ █▀ █ █▄░█ █▀█\n" +
                "▀▄▀▄▀ ██▄ █▄▄ █▄▄ █▄█ █░▀░█ ██▄   ░█░ █▄█   █▀█ █░▀█ █▀█ ▄█ ▄█   █▀░ █▄█ █▄▄ █░█ █ █░▀█ █▄█   █▄▄ █▀█ ▄█ █ █░▀█ █▄█");


        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the total money you want to play with: ");
        int initialMoney = scanner.nextInt();

        Game game = new Game(initialMoney);
        game.start();



    }
}
