package hWs;

import java.util.Scanner;

public class Main {
    static int[][] realGrids = new int[9][9];
    static int[][] gridsToPrint = new int[9][9];

    static int mineNum = 0;
    static int mineLeft = 0;
    static int markedCells = 0;
    static int safeCells = 0;
    // initialize the grids
    static {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                realGrids[i][j] = 0;
                gridsToPrint[i][j] = 0;
            }
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many mines do you want on the field?");
        mineNum = Integer.parseInt(sc.nextLine());
        mineLeft = mineNum;
        safeCells = 81 - mineNum; // 81 cells in total and there are mineNum mines and the rest are safe cells
        placeMinesRandomly(mineNum);
        printGrids(gridsToPrint); // print the foggy grids


        boolean firstFree = true;
        boolean finished = false;

        while (!finished) {

            System.out.println("Set/unset mines marks or claim a cell as free: ");

            String inputCoordinate = sc.nextLine();

            // check if the first input is for exploring
            if (firstFree && parseCoordinates(inputCoordinate)[2] == 0) {
                int[] firstCell = new int[]{parseCoordinates(inputCoordinate)[0], parseCoordinates(inputCoordinate)[1]}; // parse the coordinates and only take the coordinates of the first cell
                // create new grids with the first exploring cell is not a mine
                placeMinesRandomly(mineNum, firstCell);
            }
            firstFree = false;

            setGrids(inputCoordinate);

            boolean markedAllCorrectMines = mineLeft == 0 || markedCells == mineNum;
            boolean exploredAllSafeCells = safeCells == 0;

            System.out.println("safe cells:" + safeCells);
            // check game finished
            if (markedAllCorrectMines || exploredAllSafeCells) {
                finished = true;
                showAllTheMines(); // display all the mines in the gridsToPrint
                printGrids(gridsToPrint);
                System.out.println("Congratulations! You found all mines!");
            }

            printGrids(gridsToPrint);




        }

    }

    private static void updateTheSafeCells() {
        safeCells = 81 - mineNum;
        for (int i = 0; i < gridsToPrint.length; i++) {
            for (int j = 0; j < gridsToPrint.length; j++) {
                if (gridsToPrint[i][j] == -3 || gridsToPrint[i][j] > 0) {
                    safeCells --;
                }
            }
        }
    }

    private static void showAllTheMines() {
        for (int i = 0; i < realGrids.length; i++) {
            for (int j = 0; j < realGrids[i].length; j++) {
                if (realGrids[i][j] == -1) {
                    gridsToPrint[i][j] = -1;
                }
            }
        }
    }

    private static void placeMinesRandomly(int mineNum, int[] firstCell) {
        int count = 0;
        // initialize again in the case of recursion
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                realGrids[i][j] = 0;
                gridsToPrint[i][j] = 0;
            }
        }
        while (count < mineNum) {
            int randomI = (int) (Math.random() *9);
            int randomJ = (int) (Math.random() *9);

            while (realGrids[randomI][randomJ] == -1) {
                randomI = (int) (Math.random() * 9);
                randomJ = (int) (Math.random() * 9);
            }

            realGrids[randomI][randomJ] = -1;// -1 means a mine
            count++;

        }
        // set value (number) for neighbors of each mine
        for (int i = 0; i < realGrids.length; i++) {
            for (int j = 0; j < realGrids[i].length; j++) {
                if (realGrids[i][j] == -1) {
                    setValueForNeighbors(i, j);
                }
            }
        }
        // the grids have a mine in the coordinates where user choose in the first time, the grids must be created again
        if (gridsToPrint[firstCell[0]][firstCell[1]] == -1) {
            placeMinesRandomly(mineNum, firstCell);
        }
    }
    private static void placeMinesRandomly(int mineNum) {
        int count = 0;

        while (count < mineNum) {
            int randomI = (int) (Math.random() *9);
            int randomJ = (int) (Math.random() *9);

            while (realGrids[randomI][randomJ] == -1) {
                randomI = (int) (Math.random() * 9);
                randomJ = (int) (Math.random() * 9);
            }

            realGrids[randomI][randomJ] = -1;// -1 means a mine

            count++;

        }

        // set value (number) for neighbors of each mine
        for (int i = 0; i < realGrids.length; i++) {
            for (int j = 0; j < realGrids[i].length; j++) {
                if (realGrids[i][j] == -1) {
                    setValueForNeighbors(i, j);
                }
            }
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
           if (realGrids[up][jOfMine] != -1 ) {
               realGrids[up][jOfMine] ++;
           }
           // if the northwest neighbor is not X, it will be added 1
           if (hasLeftSide && realGrids[up][left] != -1 ) {
               realGrids[up][left] ++;
           }
           // if the northeast neighbor is not X, it will be added 1
           if (hasRightSide && realGrids[up][right] != -1) {
               realGrids[up][right]++;
           }

        }
        if (hasDownSide) {
            // if the south neighbor is not X, it will be added 1
            if (realGrids[down][jOfMine] != -1 ) {
                realGrids[down][jOfMine] ++;
            }

            // if the southwest neighbor is not X, it will be added 1
            if (hasLeftSide && realGrids[down][left] != -1 ) {
                realGrids[down][left]++;
            }
            // if the southeast neighbor is not X, it will be added 1
            if (hasRightSide && realGrids[down][right] != -1 ) {
                realGrids[down][right]++;
            }

        }

        // if the right neighbor is not X, it will be added 1
        if (hasRightSide && realGrids[iOfMine][right] != -1) {
            realGrids[iOfMine][right] ++;
        }
        // if the left neighbor is not X, it will be added 1
        if (hasLeftSide && realGrids[iOfMine][left] != -1) {
            realGrids[iOfMine][left] ++;
        }
    }

    private static void setGrids(String input) {
        int[] coordinates = parseCoordinates(input);
        int mode = coordinates[2]; // 0 for "free", 1 for "mine"
        int i = coordinates[0];
        int j = coordinates[1];

        // user wants to explore
        if (mode == 0) {
            // user steps on a mine
            if (realGrids[i][j] == -1) {
                System.out.println("You stepped on a mine and failed!");
                showAllTheMines();// display all the mines in the gridsToPrint
                printGrids(gridsToPrint); // print full grids and exit
                System.exit(0);
            } else if (realGrids[i][j] > 0){
                gridsToPrint[i][j] = realGrids[i][j]; // if the cell is a number,update the gridsToPrint the cell from realGrids in the coordinate
                //safeCells --;
            } else {
                floodfill(i, j); // if the cell is empty
            }
        } else if (mode == 1) {
            // if the cell is unmarked
            if (gridsToPrint[i][j] != -2) {
                if (realGrids[i][j] == -1) {
                    mineLeft--; // if the cell is a mine and the user marks it
                    gridsToPrint[i][j] = -2;// if the cell is a mine or safe cell and it is not marked, it will be marked
                } else if (realGrids[i][j] > 0) {
                    gridsToPrint[i][j] = -2; // if it is a number, it will be shown
                } else {
                    gridsToPrint[i][j] = -2; // if the cell is a mine or safe cell and it is not marked, it will be marked
                }
                markedCells++;
            } else {
                if (realGrids[i][j] == -1) {
                    mineLeft++; // if the cell is a mine and the user unmarks it
                }
                markedCells--;
                gridsToPrint[i][j] = 0; // if it is  marked, it will be unmarked
            }

        }


        updateTheSafeCells();

    }

    private static int[] parseCoordinates(String input) {
        int iCoordinate = Integer.parseInt((input.split(" ")[1])) - 1;
        int jCoordinate = Integer.parseInt((input.split(" ")[0])) - 1;
        String mode = input.split(" ")[2];

        int modeNum = 0;
        if (mode.equals("mine")) {
             modeNum = 1;
        }
        return new int[] {iCoordinate, jCoordinate, modeNum};
    }

    private static void printGrids(int[][] grids) {
        System.out.println(" |123456789|\n" +
                "-|---------|");

        for (int i = 0; i < grids.length; i++) {
            System.out.print((i + 1) + "|");
            for (int j = 0; j < grids[i].length; j++) {

                if (grids[i][j] == -3) {
                    System.out.print("/"); // explored cell
                    //safeCells --; // count the "/" and subtract it to the safeCells
                } else if (grids[i][j] == -2) {
                    System.out.print("*"); // marked cell
                } else if (grids[i][j] == -1){
                    System.out.print("X"); // mine
                } else if (grids[i][j] == 0) {
                    System.out.print("."); // safe cell "."
                } else {
                    System.out.print(grids[i][j]); // number
                    //safeCells --;
                }
            }
            System.out.println("|");
        }
        System.out.println("-|---------|");
    }

    private static void floodfill(int i, int j) {

        boolean outOfGrids = i > 8 || i < 0 || j > 8 || j < 0;
        boolean isNumber = !outOfGrids &&  realGrids[i][j] > 0; // !outOfGrids to make sure i and j still in grids, other this line will throw ArrayOutOfIndexException
        boolean isMine = !outOfGrids && realGrids[i][j] == -1;
        boolean explored = (!outOfGrids && gridsToPrint[i][j] == -3) || isNumber;// !outOfGrids to make sure i and j still in grids, other this line will throw ArrayOutOfIndexException

        if (isNumber ) {
            gridsToPrint[i][j] = realGrids[i][j]; // if the cell is a number, it will be shown
        }
        if (!outOfGrids && !isNumber && !isMine && !explored) {
            //safeCells --;
            gridsToPrint[i][j] = -3; // -3 means "/" symbol

            floodfill(i - 1, j); // south neighbor
            floodfill(i - 1, j -1); // southwest neighbor
            floodfill(i - 1, j + 1); // southeast neighbor
            floodfill(i + 1, j); // south neighbor
            floodfill(i + 1, j - 1); // southwest neighbor
            floodfill(i + 1, j + 1); // southeast neighbor
            floodfill(i, j + 1); // right neighbor
            floodfill(i, j -1); // left neighbor



        }

    }
}
