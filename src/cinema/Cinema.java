package cinema;

import java.util.Objects;
import java.util.Scanner;

public class Cinema {

    private static String[][] cinema;
    private static int rows;
    private static int columns;
    private static int numberOfPurchasedTickets = 0;
    private static double percentage = 0.0;
    private static int currentIncome = 0;
    private static int totalIncome;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        columns = scanner.nextInt();

        cinema = new String[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cinema[i][j] = "S";
            }
        }

        if (rows * columns <= 60) {
            totalIncome = rows * columns * 10;
        } else {
            totalIncome = (10 * columns * (rows / 2)) + (8 * columns * (rows - rows / 2));
        }

        boolean running = true;
        while (running) {
            System.out.println("" +
                    "1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");

            int response = scanner.nextInt();

            switch (response) {
                case 1:
                    printCinema();
                    break;
                case 2:
                    boolean runBuyTicket = true;
                    while (runBuyTicket) {
                        System.out.println("Enter a row number:");
                        int rowNumber = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        int columnNumber = scanner.nextInt();
                        runBuyTicket = buyTicket(rowNumber, columnNumber);
                    }

                    break;
                case 3:
                    printStats();
                    break;
                case 0:
                    running = false;
                    break;
            }
        }
    }

    private static void printCinema() {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 0; i < cinema[0].length; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < cinema.length; i++) {
            System.out.print(i + 1 + " ");
            for (String s : cinema[i]) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }

    private static boolean buyTicket(int rowNumber, int columnNumber) {
        if (rowNumber - 1 > rows - 1 || columnNumber - 1 > columns - 1) {
            System.out.println("Wrong input!");
            return true;
        }
        if (Objects.equals(cinema[rowNumber - 1][columnNumber - 1], "B")) {
            System.out.println("That ticket has already been purchased!");
            return true;
        }
        cinema[rowNumber - 1][columnNumber - 1] = "B";
        int ticketPrice = rows * columns <= 60 ? 10 : rowNumber <= rows / 2 ? 10 : 8;
        System.out.println("Ticket price: $" + ticketPrice);
        currentIncome += ticketPrice;
        numberOfPurchasedTickets += 1;
        percentage += 1 / ((double) rows * columns) * 100;
        return false;
    }

    private static void printStats() {
        System.out.println(
                "Number of purchased tickets: " + numberOfPurchasedTickets + "\n" +
                        "Percentage: " + String.format("%.2f", percentage) + "%\n" +
                        "Current income: $" + currentIncome + "\n" +
                        "Total income: $" + totalIncome);
    }
}