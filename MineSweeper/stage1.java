package minesweeper;


public class Main {
    static String[][] grids = new String[9][9];
    public static void main(String[] args) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grids[i][j] = ".";
            }
        }
        placeMinesRandomly();
        printGrids();
    }

    private static void printGrids() {
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                System.out.print(grids[i][j]);
            }
            System.out.println();
        }
    }

    private static void placeMinesRandomly() {
        int count = 0;

        while (count < 10) {
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
}
