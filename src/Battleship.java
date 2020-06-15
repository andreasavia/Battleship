import java.util.Random;
import java.util.Scanner;

public class Battleship {
    public static void main(String[] args) {

        // Welcome message
        System.out.println("**** Welcome to the Battle Ships game ****\n");
        System.out.println("Right now, the sea is empty.\n\n");

        char[][] ocean = new char[10][10]; // Ocean map

        // Print empty Ocean
        printOceanStrip();
        for (int row = 0; row < ocean.length; row++) {
            System.out.print(row + "|");
            for (int col = 0; col < ocean[row].length; col++) {
                if (ocean[row][col] == 0) {
                    System.out.print(" ");
                } else {
                    System.out.print(ocean[row][col]);
                }
            }
            System.out.println("|" + row);
        }
        printOceanStrip();

        // Ask user to enter coordinate of 5 ships
        int[] xy;
        boolean check;

        System.out.println("Deploy your ships:");
        int j = 1;
        do {
            xy = requestCoordinate(j);
            check = checkRepeatedCoordinate(xy,ocean);
            if (check) {
                System.out.println("The coordinates entered for ship no. " + j + " have already been entered. Enter them again.");
            } else {
                ocean[xy[0]][xy[1]] = 1;
                j++;
            }
        } while (check | j < 6);

        // Print ocean with user's ships
        printOceanStrip();
        for (int row = 0; row < ocean.length; row++) {
            System.out.print(row + "|");
            for (int col = 0; col < ocean[row].length; col++) {
                if (ocean[row][col] == 0) {
                    System.out.print(" ");
                } else if (ocean[row][col] == 1) {
                    System.out.print("@");
                } else {
                    System.out.print(ocean[row][col]);
                }
            }
            System.out.println("|" + row);
        }
        printOceanStrip();

        // Generating coordinates for the computer's ships
        System.out.println("\nDeploying the computer's ships:");

        Random rand = new Random();
        int i = 0;
        do {
            xy[0] = rand.nextInt(9);
            xy[1] = rand.nextInt(9);
            check = checkRepeatedCoordinate(xy,ocean);
            if (!check) {
                ocean[xy[0]][xy[1]] = 2;
                i++;
            }
        } while (check | i < 5);

        // Print ocean with user's ships
        printOceanStrip();
        for (int row = 0; row < ocean.length; row++) {
            System.out.print(row + "|");
            for (int col = 0; col < ocean[row].length; col++) {
                if (ocean[row][col] == 0) {
                    System.out.print(" ");
                } else if (ocean[row][col] == 1) {
                    System.out.print("@");
                } else if (ocean[row][col] == 2) {
                    System.out.print("a");
                } else {
                    System.out.print(ocean[row][col]);
                }
            }
            System.out.println("|" + row);
        }
        printOceanStrip();

    }

    public static void printOceanStrip() {
        char[] oceanStrip = {'0','0','0','1','2','3','4','5','6','7','8','9','0','0'};
        System.out.print(" "+" ");
        for (int col = 2; col < oceanStrip.length - 2 ; col++) {
            System.out.print(oceanStrip[col]);
        }
        System.out.println(" "+" ");
    }

    public static int checkCoordinateOK(int a) {
        Scanner input = new Scanner(System.in);
        while (a < 0 | a > 9) {
            System.out.print("Enter a valid coordinate between 0 and 9: ");
            a = input.nextInt();
        }
        return a;
    }

    public static int[] requestCoordinate(int i) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter X coordinate for your ship no. " + i + ": ");
        int x = input.nextInt();
        x = checkCoordinateOK(x);

        System.out.print("Enter Y coordinate for your ship no. " + i + ": ");
        int y = input.nextInt();
        y = checkCoordinateOK(y);

        return new int[]{x,y};
    }

    public static boolean checkRepeatedCoordinate(int[] xy, char[][] ocean) {
        boolean check = false;
            if (ocean[xy[0]][xy[1]]!=0) {
                check = true;
            }
        return check;
    }
}