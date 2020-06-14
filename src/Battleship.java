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

        // Scanner input = new Scanner(System.in);


       //TODO Ask user to enter the coordinate of 5 ships

        /*
        System.out.print("Enter X coordinate for your ship: ");
        int x = input.nextInt();
        System.out.print("Enter X coordinate for your ship: ");
        int y = input.nextInt();

         */

        //TODO Check the user has not entered the same coordinate twice or has entered invalid coordinates
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
}