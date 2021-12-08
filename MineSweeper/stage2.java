package minesweeper;


import java.util.Scanner;

public class Main {
    static String[][] grids = new String[9][9];
    // initialize the grids
    static {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grids[i][j] = ".";
            }
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many mines do you want on the field?");
        int mineNum = Integer.parseInt(sc.nextLine());


        placeMinesRandomly(mineNum);
        printGrids();
    }
    
    private static void placeMinesRandomly(int mineNum) {
        int count = 0;

        while (count < mineNum) {
            int randomI = (int) (Math.random() *9);
            int randomJ = (int) (Math.random() *9);

            while (grids[randomI][randomJ].equals("X")) {
                randomI = (int) (Math.random() * 9);
                randomJ = (int) (Math.random() * 9);
            }

            grids[randomI][randomJ] = "X";

            count++;

        }
    }

    private static void printGrids() {
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                System.out.print(grids[i][j]);
            }
            System.out.println();
        }
    }
}
