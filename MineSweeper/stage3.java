package hWs;

import java.util.Scanner;

public class Main {
    static String[][] grids = new String[9][9];
    // initialize the grids
    static {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grids[i][j] = "0";
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
           if (!grids[up][jOfMine].equals("X") ) {grids[up][jOfMine] = String.valueOf(Integer.parseInt(grids[up][jOfMine]) + 1);}
           // if the northwest neighbor is not X, it will be added 1
           if (hasLeftSide && !grids[up][left].equals("X") ) {grids[up][left] = String.valueOf(Integer.parseInt(grids[up][left]) + 1);}
           // if the northeast neighbor is not X, it will be added 1
           if (hasRightSide && !grids[up][right].equals("X") ) {grids[up][right] = String.valueOf(Integer.parseInt(grids[up][right]) + 1);}

        }
        if (hasDownSide) {
            // if the south neighbor is not X, it will be added 1
            if (!grids[down][jOfMine].equals("X") ) {grids[down][jOfMine] = String.valueOf(Integer.parseInt(grids[down][jOfMine]) + 1);}
            // if the southwest neighbor is not X, it will be added 1
            if (hasLeftSide && !grids[down][left].equals("X") ) {grids[down][left] = String.valueOf(Integer.parseInt(grids[down][left]) + 1);}
            // if the southeast neighbor is not X, it will be added 1
            if (hasRightSide && !grids[down][right].equals("X") ) {grids[down][right] = String.valueOf(Integer.parseInt(grids[down][right]) + 1);}

        }

        // if the right neighbor is not X, it will be added 1
        if (hasRightSide && !grids[iOfMine][right].equals("X")) {grids[iOfMine][right] = String.valueOf(Integer.parseInt(grids[iOfMine][right]) + 1);}
        // if the left neighbor is not X, it will be added 1
        if (hasLeftSide && !grids[iOfMine][left].equals("X")) {grids[iOfMine][left] = String.valueOf(Integer.parseInt(grids[iOfMine][left]) + 1);}
    }

    private static void printGrids() {
        for (int i = 0; i < grids.length; i++) {

            for (int j = 0; j < grids[i].length; j++) {
                if (grids[i][j].equals("0")) {
                    System.out.print(".");
                } else {
                    System.out.print(grids[i][j]);
                }
            }
            System.out.println();
        }
    }
}
