

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Stage1 {

    enum BattleShip{
        CARRIER("Aircraft Carrier", 5,0),
        BATTLESHIP("Battleship", 4, 1),
        CRUISER("Submarine", 3, 2),
        SUBMARINE("Cruiser", 3, 3),
        DESTROYER("Destroyer", 2, 4);

        public final String name;
        public int cells;
        public int order;

        BattleShip(String name, int size, int order) {
            this.name = name;
            this.cells = size;
            this.order = order;
        }

    }

    public static void main(String[] args) throws WrongLocation, FileNotFoundException {
        //Scanner sc = new Scanner(new File("C:\\Users\\NKcomputer\\Desktop\\input.txt"));
        Scanner sc = new Scanner(System.in);

        /*SETTING THE GRIDS*/

        BattleShip[] ships = {BattleShip.CARRIER, BattleShip.BATTLESHIP, BattleShip.CRUISER, BattleShip.SUBMARINE, BattleShip.DESTROYER};

        String[][] fogGrids1 = new String[11][11];
        String[][] fogGrids2 = new String[11][11];
        String[][] grids1 = new String[11][11];
        String[][] grids2 = new String[11][11];

        setup(fogGrids1);
        setup(fogGrids2);
        setup(grids1);
        setup(grids2);

        String[][][][] playField = {{fogGrids1, grids1}, {fogGrids2, grids2}};

        int[][] shipCells = {{5, 4, 3, 3, 2}, {5, 4, 3, 3, 2}};
        int[][][] shipLocations = new int[2][5][2];
        int currentPlayer = 0;

        placeTheShips(playField, 0, shipLocations);
        pressEnter();
        placeTheShips(playField, 1, shipLocations);
        pressEnter();
        /*PLACE THE SHIPS*/



        /*SHOOTING THE SHIPS*/
        int[] numberOfSunkShip = {0, 0};
        //sc = new Scanner(System.in);


        while (numberOfSunkShip[0] < 5 && numberOfSunkShip[1] < 5) {

            try {
                currentPlayer %= 2;
                printPlayField(playField, currentPlayer);
                System.out.printf(String.format("Player %d, it's your turn: \n", currentPlayer + 1));
                int[] shotCoordinates = getLocationInput(sc.nextLine(), true);
                if (shot(shotCoordinates, playField, currentPlayer, shipLocations, shipCells) == 1) {
                    numberOfSunkShip[currentPlayer] ++;
                    if (numberOfSunkShip[currentPlayer] == 5) {
                        /*GAME FINISHED*/
                        System.out.println("You sank the last ship. You won. Congratulations!");
                        break;
                    }
                }

                currentPlayer ++;
                pressEnter();

            } catch (WrongLocation e) { System.out.println(e.getMessage());}

        }


        
    }




    public static void setup(String[][] grids){

        for (int i = 0; i < grids.length; i++) {

            for (int j = 0; j < grids[i].length; j++) {

                if (i == 0 && j == 0) {
                    grids[i][j] = "  ";
                } else if (i == 0) {
                    grids[i][j] = j + " ";
                } else if (j == 0) {
                    grids[i][j] = Character.toString((char) i + 64) + " ";
                } else {
                    grids[i][j] = "~ ";
                }
            }
        }
    }

    public static void placeTheShips(String[][][][] playField, int playerNumber, int[][][] shipLocations) throws FileNotFoundException {
        //File inputFile = new File(playerNumber == 0 ? "C:\\Users\\NKcomputer\\Desktop\\input.txt" : "C:\\Users\\NKcomputer\\Desktop\\input2.txt");
        //Scanner sc = new Scanner(inputFile);
        Scanner sc = new Scanner(System.in);
        String player = playerNumber == 0 ? "Player 1" : "Player 2";
        BattleShip[] ships = {BattleShip.CARRIER, BattleShip.BATTLESHIP, BattleShip.CRUISER, BattleShip.SUBMARINE, BattleShip.DESTROYER};
        int numberOfPlacedShips = 0;

        System.out.printf("%s,place your ships on the game field\n", player);
        printGrids(playField[playerNumber][1]);
        String instruction = String.format("\nEnter the coordinates of the %s (%d cells)\n", ships[numberOfPlacedShips].name, ships[numberOfPlacedShips].cells);

        while (numberOfPlacedShips < 5 && sc.hasNext()) {
            try {

                System.out.println(instruction);
                String input = sc.nextLine();
                int[] location = getLocationInput(input);

                if (checkLocationIsCorrect(ships[numberOfPlacedShips], location)) {

                    //System.out.println("shipNum before: " + shipNum);
                    setLocation(ships[numberOfPlacedShips], location, playField[playerNumber][1], shipLocations, playerNumber);
                    printGrids(playField[playerNumber][1]);

                    numberOfPlacedShips++;
                    if (numberOfPlacedShips <= 4) {
                        // next instruction
                        instruction = String.format("\nEnter the coordinates of the %s (%d cells): \n", ships[numberOfPlacedShips].name, ships[numberOfPlacedShips].cells);
                    }
                    //System.out.println("shipNum: "+ shipNum);
                }
            } catch (WrongLocation e) {
                instruction = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

    public static void pressEnter() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPress Enter and pass the move to another player");
        sc.nextLine();
    }

    private static int[] getLocationInput(String input) {

        String[] location = input.split(" ");
        int fromY = (int)location[0].charAt(0) - 64;
        int fromX = Integer.parseInt(location[0].substring(1));

        int toY = (int)location[1].charAt(0) - 64;
        int toX = Integer.parseInt(location[1].substring(1));
        //System.out.println(Arrays.toString(new int[]{fromY, fromX, toY, toX}));
        //System.out.println(Arrays.toString(new int[]{Math.min(fromY, toY), Math.min(fromX, toX), Math.max(fromY, toY), Math.max(fromX, toX)}));
        return new int[]{Math.min(fromY, toY), Math.min(fromX, toX), Math.max(fromY, toY), Math.max(fromX, toX)};
    }

    private static int[] getLocationInput(String input, boolean isShot) throws WrongLocation {

        int yCoordinate = (int) input.charAt(0) - 64;
        int xCoordinate = Integer.parseInt(input.substring(1));

        if (yCoordinate >= 1 && yCoordinate <=10
                && xCoordinate >= 1 && xCoordinate <= 10) {
            return new int[]{yCoordinate, xCoordinate};

        } else {
            throw new WrongLocation("Error! You entered the wrong coordinates! Try again:");

        }
    }

    private static boolean checkLocationIsCorrect(BattleShip ship, int[] location) throws WrongLocation {

        boolean isInsideTheGrid = true;
        boolean correctLocation = false;

        for (int number : location) {
            if (number < 1 || number > 10) {
                isInsideTheGrid = false;
                throw new WrongLocation("Error! Out of the grids! Try again: ");
            }
        }

        int xDifference = Math.abs(location[3] - location[1]);
        int yDifference = Math.abs(location[2] - location[0]);

        if (xDifference == 0 && yDifference + 1 == ship.cells) {
            correctLocation = true;
        } else if (xDifference + 1== ship.cells && yDifference == 0) {
            correctLocation = true;
        }
        if (correctLocation) {
            return true;
        } else {
            throw new WrongLocation(String.format("Error! Wrong length of the %s! Try again: ", ship.name));
        }

    }

    public static void setLocation(BattleShip ship, int[] locations, String[][] grids, int[][][] shipLocations, int playerNumber) throws WrongLocation {
        boolean horizontal = locations[0] == locations[2];

        int fromY = locations[0];
        int fromX = locations[1];
        int toY = locations[2];
        int toX = locations[3];


        boolean hasNorthSide = fromY > 1; // along with horizontal
        boolean hasSouthSide = toY < 10; // along with horizontal
        boolean hasWestSide = fromX > 1;// along with vertical
        boolean hasEastSide = toX < 10;// along with vertical

        if (horizontal) {

            /*
                           North close
                           | | | | | |
             west close <- O O O O O O -> east close
                           | | | | | |
                           South close

             */


            int yUnchanged = fromY; // the Y coordinate of the cells is the same (fromY = toY)

            // check west side close to another ship or not
            if (hasWestSide && grids[yUnchanged][fromX-1].equals("O ")) {
                throw new WrongLocation("Error! You place the ship too close to another one. Try again: ");
            }

            // check east side close to another ship or not
            if (hasEastSide && grids[yUnchanged][toX + 1].equals("O ")) {
                throw new WrongLocation("Error! You place the ship too close to another one. Try again: ");
            }

            for (int j = fromX; j <= toX ; j++) {
                // check the ship cross another ship or not, if not place the ship
                if (grids[yUnchanged][j].equals("O ")) {
                    throw new WrongLocation("Error! You place the ship cross another one. Try again: ");
                } else {
                    grids[yUnchanged][j] = "O ";
                }

                // check the north side along the ship whether it close to another ship or not
                if (hasNorthSide && grids[yUnchanged - 1][j].equals("O ")) {
                    throw new WrongLocation("Error! You place the ship too close to another one. Try again: ");
                }

                // check the south side along the ship whether it close to another ship or not
                if (hasSouthSide && grids[yUnchanged + 1][j].equals("O ")) {
                    throw new WrongLocation("Error! You place the ship too close to another one. Try again: ");
                }
            }
        } else {

             /*
                           North close
                               |
                 west close <- O -> east close
                 west close <- O -> east close
                 west close <- O -> east close
                 west close <- O -> east close
                 west close <- O -> east close
                               |
                           South close

             */

            int xUnchanged = fromX; // the X coordinate of the cells is the same (fromX = toX)
            // check north side close to another ship or not
            if (hasNorthSide && grids[fromY - 1][xUnchanged].equals("O ")) {
                throw new WrongLocation("Error! You place the ship too close to another one. Try again: ");
            }

            // check south side close to another ship or no
            if (hasSouthSide && grids[toY + 1][xUnchanged].equals("O ")) {
                throw new WrongLocation("Error! You place the ship too close to another one. Try again: ");
            }

            for (int i = fromY; i <= toY ; i++) {
                // check the ship cross another ship or not, if not place the ship
                if (grids[i][xUnchanged].equals("O ")) {
                    throw new WrongLocation("Error! You place the ship cross another one. Try again: ");
                } else {
                    grids[i][xUnchanged] = "O ";
                }


                // check the west side along the ship whether it close to another ship or not
                if (hasWestSide && grids[i][xUnchanged - 1].equals("O ")) {
                    throw new WrongLocation("Error! You place the ship too close to another one. Try again: ");
                }

                // check the east side along the ship whether it close to another ship or not
                if (hasEastSide && grids[i][xUnchanged + 1].equals("O ")) {
                    throw new WrongLocation("Error! You place the ship too close to another one. Try again: ");
                }
            }
        }

        shipLocations[playerNumber][ship.order] = new int[]{fromY, fromX, toY, toX};
        //System.out.println(Arrays.toString(ship.location[0]) + ", " + Arrays.toString(ship.location[1]));
    }

    public static int shot(int[] coordinates, String[][][][] playField, int playerNumber,int[][][] shipLocations, int[][] shipCells) {

        String[][] attackingFogGrids = playField[playerNumber][0];
        int otherPlayer = (playerNumber + 1) % 2;
        String[][] enemyGrids = playField[otherPlayer][1];

        int numberOfShipBeingSunk = 0; // if a ship is completely sunk after being hit, it will 1 otherwise 0
        int yCoordinate = coordinates[0];
        int xCoordinate = coordinates[1];
        boolean hitTheShip = false;
        boolean sinkTheSHip = false;

        /// check the shot hits the ship, sinks the ship or misses the ship
        if (enemyGrids[yCoordinate][xCoordinate].equals("O ")) {
            hitTheShip = true;

            for (BattleShip ship : BattleShip.values()) {
                /* if the ship is horizontally placed, the ship.location[o][0] (fromY) = ship.location[1][0] (toY)
                    , if the ship is vertically placed, the ship.location[0][1] (fromX) = ship.location[1][1] (toX)

                    either ship.location[0][0] = ship.location[1][0] or ship.location[0][1] = ship.location[1][1]
                    the if block below check the shot hits which ship
                 */

                if (yCoordinate >= shipLocations[otherPlayer][ship.order][0] && yCoordinate <= shipLocations[otherPlayer][ship.order][2]
                    && xCoordinate >= shipLocations[otherPlayer][ship.order][1] && xCoordinate <= shipLocations[otherPlayer][ship.order][3]) {

                    shipCells[otherPlayer][ship.order] --;

                    if (shipCells[otherPlayer][ship.order] == 0) {
                        numberOfShipBeingSunk = 1;
                        sinkTheSHip = true;
                    }
                    break;
                }
            }
        }

        // print the enemyGrids and message
        printGrids(attackingFogGrids);
        if (hitTheShip && sinkTheSHip) {
            attackingFogGrids[yCoordinate][xCoordinate] = "X ";
            enemyGrids[yCoordinate][xCoordinate] = "X ";
            System.out.println("\nYou sank a ship!");
        } else if (hitTheShip){
            attackingFogGrids[yCoordinate][xCoordinate] = "X ";
            enemyGrids[yCoordinate][xCoordinate] = "X ";
            System.out.println("\nYou hit a ship!");
        } else {
            attackingFogGrids[yCoordinate][xCoordinate] = "M ";
            enemyGrids[yCoordinate][xCoordinate] = "M ";
            System.out.println("\nYou missed!");

        }

        return numberOfShipBeingSunk;
    }

    public static void printGrids(String[][] grids) {

        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                System.out.print(grids[i][j]);
            }
            System.out.println();
        }

    }

    public static void printPlayField(String[][][][] playField, int playerNumber) {
        printGrids(playField[playerNumber][0]);
        System.out.println("---------------------");
        printGrids(playField[playerNumber][1]);
    }

}
class WrongLocation extends Exception {
    public WrongLocation(String msg){
        super(msg);
    }
}

