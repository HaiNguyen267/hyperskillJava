package hWs;

import java.util.Scanner;

public class Main {
    static String[][] grids = new String[9][9];
    static int[][] gridsToPrint = new int[9][9];
    static int mineNum = 0;
    static int markedCells = 0;
    static int mineLeft = 0;
    // initialize the grids
    static {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grids[i][j] = "0";
                gridsToPrint[i][j] = 0;
            }
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many mines do you want on the field?");
        mineNum = Integer.parseInt(sc.nextLine());
        mineLeft = mineNum;


        placeMinesRandomly(mineNum);


        // only stop if the game finished
        while (mineLeft != 0 && markedCells != mineNum) {
            printGrids();
            System.out.println("Set/delete mines marks (x and y coordinates):");
            String inputCoordinate = sc.nextLine();

            // if the user try to mark, unmark a number (not a mine, not a marked or unmarked cell) in the grids, he has to input again.
            while (gridsToPrint[parseCoordinates(inputCoordinate)[0]][parseCoordinates(inputCoordinate)[1]] != -2 &&
                    gridsToPrint[parseCoordinates(inputCoordinate)[0]][parseCoordinates(inputCoordinate)[1]] != -1 &&
                    gridsToPrint[parseCoordinates(inputCoordinate)[0]][parseCoordinates(inputCoordinate)[1]] != 0) {

                // -2 means a marked cell
                // -1 means a mine
                //  0 means a unmarked cell (safe cell)
                System.out.println("There is a number here!");
                System.out.println("Set/delete mines marks (x and y coordinates):");
                inputCoordinate = sc.nextLine();
            }

            setGrids(inputCoordinate);

        }

        printGrids();
        System.out.println("Congratulations! You found all mines!");
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
            gridsToPrint[randomI][randomJ] = -1; // -1 means a mine
            setValueForNeighbors(randomI, randomJ);
            count++;

        }
    }

    private static void setValueForNeighbors(int iOfMine, int jOfMine) {
        // check if the mine has right or left neighbor
        boolean hasRightSide = jOfMine < 8;
        boolean hasLeftSide = jOfMine > 0;

        // check if the mine has north or south neighbor
        boolean hasUpSide = iOfMine > 0;
        boolean hasDownSide = iOfMine < 8;

        int up = iOfMine - 1;
        int down = iOfMine + 1;
        int right = jOfMine + 1;
        int left = jOfMine - 1;

        if (hasUpSide) {
            // if the north neighbor is not X, it will be added 1
           if (!grids[up][jOfMine].equals("X") ) {
               //grids[up][jOfMine] = String.valueOf(Integer.parseInt(grids[up][jOfMine]) + 1);
               gridsToPrint[up][jOfMine] ++;
           }
           // if the northwest neighbor is not X, it will be added 1
           if (hasLeftSide && !grids[up][left].equals("X") ) {
               //grids[up][left] = String.valueOf(Integer.parseInt(grids[up][left]) + 1);
               gridsToPrint[up][left] ++;
           }
           // if the northeast neighbor is not X, it will be added 1
           if (hasRightSide && !grids[up][right].equals("X") ) {
               //grids[up][right] = String.valueOf(Integer.parseInt(grids[up][right]) + 1);
               gridsToPrint[up][right]++;
           }

        }
        if (hasDownSide) {
            // if the south neighbor is not X, it will be added 1
            if (!grids[down][jOfMine].equals("X") ) {
                //grids[down][jOfMine] = String.valueOf(Integer.parseInt(grids[down][jOfMine]) + 1);
                gridsToPrint[down][jOfMine] ++;
            }

            // if the southwest neighbor is not X, it will be added 1
            if (hasLeftSide && !grids[down][left].equals("X") ) {
                //grids[down][left] = String.valueOf(Integer.parseInt(grids[down][left]) + 1);
                gridsToPrint[down][left]++;
            }
            // if the southeast neighbor is not X, it will be added 1
            if (hasRightSide && !grids[down][right].equals("X") ) {
                //grids[down][right] = String.valueOf(Integer.parseInt(grids[down][right]) + 1);
                gridsToPrint[down][right]++;
            }

        }

        // if the right neighbor is not X, it will be added 1
        if (hasRightSide && !grids[iOfMine][right].equals("X")) {
            //grids[iOfMine][right] = String.valueOf(Integer.parseInt(grids[iOfMine][right]) + 1);
            gridsToPrint[iOfMine][right] ++;
        }
        // if the left neighbor is not X, it will be added 1
        if (hasLeftSide && !grids[iOfMine][left].equals("X")) {
            //grids[iOfMine][left] = String.valueOf(Integer.parseInt(grids[iOfMine][left]) + 1);
            gridsToPrint[iOfMine][left] ++;
        }
    }

    private static void setGrids(String input) {
        int[] coordinates = parseCoordinates(input);
        if (gridsToPrint[coordinates[0]][coordinates[1]] == 0 || gridsToPrint[coordinates[0]][coordinates[1]] == -1) {

            gridsToPrint[coordinates[0]][coordinates[1]] = -2; // -2 mean marked
            markedCells ++; // if the user marks a cell

            if (gridsToPrint[coordinates[0]][coordinates[1]] == -1) {
                mineLeft --; // if the cell user marks is a mine
            }
        } else if (gridsToPrint[coordinates[0]][coordinates[1]] == -2){

            markedCells --; // if the user unmark a cell

            if (gridsToPrint[coordinates[0]][coordinates[1]] == -1) {
                mineLeft ++; // if the cell user unmark is a mine
            }
        }
    }

    private static int[] parseCoordinates(String input) {
        int iCoordinate = Integer.parseInt((input.split(" ")[1])) - 1;
        int jCoordinate = Integer.parseInt((input.split(" ")[0])) - 1;

        return new int[] {iCoordinate, jCoordinate};
    }

    private static void printGrids() {
        System.out.println(" |123456789|\n" +
                "-|---------|");
        for (int i = 0; i < gridsToPrint.length; i++) {
            System.out.print((i + 1) + "|");
            for (int j = 0; j < gridsToPrint[i].length; j++) {


                if (gridsToPrint[i][j] == -2) {
                    System.out.print("*");
                } else if (gridsToPrint[i][j] == -1){
                    System.out.print(".");
                } else if (gridsToPrint[i][j] == 0) {
                    System.out.print(".");
                } else {
                    System.out.print(gridsToPrint[i][j]);
                }
            }
            System.out.println("|");
        }
        System.out.println("-|---------|");
    }
}
