import java.util.*;
import java.lang.*;

public class Battleship {
    public static void main(String[] args) {

        // Welcome message
        System.out.println("**** Welcome to the Battle Ships game ****\n");
        System.out.println("Right now, the sea is empty.\n\n");

        char[][] ocean = new char[10][10]; // Ocean map

        // Print empty Ocean
        System.out.println("  0123456789  ");
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
        System.out.println("  0123456789  ");

        // Ask user to enter coordinate of 5 ships
        int[] xy;
        boolean check;

        System.out.println("Deploy your ships:");
        int j = 1;
        do {
            xy = requestCoordinate(j);
            check = checkRepeatedCoordinate(xy, ocean);
            if (check) {
                System.out.println("The coordinates entered for ship no. " + j + " have already been entered. Enter them again.");
            } else {
                ocean[xy[0]][xy[1]] = 1;
                j++;
            }
        } while (check | j < 6);

        // Print ocean with user's ships
        System.out.println("  0123456789  ");
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
        System.out.println("  0123456789  ");

        // Generating coordinates for the computer's ships
        System.out.println("\nComputer is deploying ships");

        Random rand = new Random();
        int i = 0;
        do {
            xy[0] = rand.nextInt(9);
            xy[1] = rand.nextInt(9);
            check = checkRepeatedCoordinate(xy, ocean);
            if (!check) {
                ocean[xy[0]][xy[1]] = 2;
                i++;
                System.out.println(i + ". ship DEPLOYED");
            }
        } while (check | i < 5);

        // User turn to enter coordinates
        System.out.println("------------------------------");
        System.out.println("YOUR TURN");
        Scanner input = new Scanner(System.in);

        // initial ships count
        int remUserShips = 5;
        int remCompShips = 5;

        do {
            int x;
            int y;

            System.out.print("Enter X coordinate: ");
            x = input.nextInt();
            x = checkCoordinateOK(x);

            System.out.print("Enter Y coordinate: ");
            y = input.nextInt();
            y = checkCoordinateOK(y);

            if (ocean[x][y] == 2) {
                System.out.println("Boom! You sunk the ship!");
                ocean[x][y] = 20; // hit
                remCompShips--;
            } else if (ocean[x][y] == 1) {
                System.out.println("Oh no, you sunk your own ship :(");
                ocean[x][y] = 10; // sunk your own ship
                remUserShips--;
            } else {
                System.out.println("Sorry, you missed");
                if (ocean[x][y] == 0) {
                    ocean[x][y] = 98;
                } else if (ocean[x][y] == 99) {
                    ocean[x][y] = 101;
                    }
                }

            // Computer turn
            System.out.println("COMPUTER'S TURN");
            do {
                x = rand.nextInt(9);
                y = rand.nextInt(9);
            } while (ocean[x][y] == 99 | ocean[x][y] == 101 ); // check if already guessed

            if (ocean[x][y] == 1) {
                System.out.println("The Computer sunk one of your ships!\n");
                ocean[x][y] = 10; // hit
                remUserShips--;
            } else if (ocean[x][y] == 2) {
                System.out.println("The Computer sunk one of its own ships!\n");
                ocean[x][y] = 20; // sunk its own ship
                remCompShips--;
            } else {
                System.out.println("Computer missed\n");
                if (ocean[x][y] == 0) {
                    ocean[x][y] = 99; // missed
                }
            }

            printOcean(ocean);
            System.out.println("\nYour ships: " + remUserShips + " | Computer ships: " + remCompShips);
            System.out.println("------------------------------------------\n");

        } while ((remCompShips != 0) & (remUserShips != 0));

        if (remCompShips == 0 & remUserShips == 0) {
            System.out.println("It's a tie!");
        } else if (remUserShips == 0) {
            System.out.println("Game over, the Computer wins the battle :(");
        } else {
            System.out.println("Hooray! You win the battle:)");
        }
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

        System.out.print("Enter X coordinate for your " + i + ". ship: ");
        int x = input.nextInt();
        x = checkCoordinateOK(x);

        System.out.print("Enter Y coordinate for your " + i + ". ship: ");
        int y = input.nextInt();
        y = checkCoordinateOK(y);

        return new int[]{x, y};
    }
    public static boolean checkRepeatedCoordinate(int[] xy, char[][] ocean) {
        boolean check = false;
        if (ocean[xy[0]][xy[1]] != 0) {
            check = true;
        }
        return check;
    }
    public static void printOcean(char[][] ocean) {
        System.out.println("  0123456789  ");
        for (int row = 0; row < ocean.length; row++) {
            System.out.print(row + "|");
            for (int col = 0; col < ocean[row].length; col++) {
                if (ocean[row][col] == 0) {
                    System.out.print(" ");
                } else if (ocean[row][col] == 1) {
                    System.out.print("@");
                } else if (ocean[row][col] == 10) {
                    System.out.print("x");
                } else if (ocean[row][col] == 20) {
                    System.out.print("!");
                } else if (ocean[row][col] == 98 | ocean[row][col] == 101) {
                    System.out.print("-");
                } else
                    System.out.print(" ");
            }
            System.out.println("|" + row);
        }
        System.out.println("  0123456789  ");
    }

}