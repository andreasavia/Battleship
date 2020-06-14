import java.util.Arrays;
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
        Scanner input = new Scanner(System.in);
        int[][] userShips = {{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};

        int[] xy;
        boolean check;

        System.out.println("Deploy your ships:");
        for (int i = 1; i <= 5; i++) {
            xy = requestCoordinate(i);
            check = checkRepeatedCoordinate(xy,userShips);
            while (check) {
                System.out.println("The coordinates entered for ship no. " + i + " have already been entered. Enter them again.");
                xy = requestCoordinate(i);
                check = checkRepeatedCoordinate(xy,userShips);
            }
            userShips[i-1] = xy;
            ocean[xy[0]][xy[1]] = 1;
        }

        // Print Ocean with user's ships
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





        //TODO Record the ship as 1 and printed out the Ocean Map using @

        //TODO Deploy computer ships


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

        int[] xy = {x,y};

        return xy;
    }
    public static boolean checkRepeatedCoordinate(int[] xy, int[][] userShips) {
        boolean check = false;
        for (int i = 0; i < userShips.length; i++) {
            check = Arrays.equals(xy,userShips[i]);
            if (check) {
                break;
            }
        }
        return check;
    }
}