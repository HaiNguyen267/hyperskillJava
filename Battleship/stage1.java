
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    enum BattleShip{
        CARRIER("Aircraft Carrier", 5),
        BATTLESHIP("Battleship", 4),
        CRUISER("Submarine", 3),
        SUBMARINE("Cruiser", 3),
        DESTROYER("Destroyer", 2);

        public final String name;
        public final int size;

        BattleShip(String name, int size) {
            this.name = name;
            this.size = size;
        }

    }

    public static void main(String[] args) throws WrongLocation {
        Scanner sc = new Scanner(System.in);

        int shipNum = 0;

        BattleShip[] ships = {BattleShip.CARRIER, BattleShip.BATTLESHIP, BattleShip.CRUISER, BattleShip.SUBMARINE, BattleShip.DESTROYER};


        String[][] grids = new String[11][11];
        setup(grids);

        String instruction = String.format("\nEnter the coordinates of the %s (%d cells)\n", ships[shipNum].name, ships[shipNum].size);

        printGrids(grids);
        while (shipNum < 5) {
            try {

                System.out.println(instruction);
                String input = sc.nextLine();
                int[] location = getLocationInput(input);

                if (checkLocation(ships[shipNum], location)) {

                    setLocation(ships[shipNum], location, grids);
                    printGrids(grids);
                    shipNum++;
                    instruction = String.format("\nEnter the coordinates of the %s (%d cells): \n", ships[shipNum].name, ships[shipNum].size);
                    //System.out.println("shipNum: "+ shipNum);
                }
            } catch (WrongLocation e) {
                instruction = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }
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

    public static int[] getLocationInput(String input) {

        String[] location = input.split(" ");
        int fromY = (int)location[0].charAt(0) - 64;
        int fromX = Integer.parseInt(location[0].substring(1));

        int toY = (int)location[1].charAt(0) - 64;
        int toX = Integer.parseInt(location[1].substring(1));
        //System.out.println(Arrays.toString(new int[]{fromY, fromX, toY, toX}));
        //System.out.println(Arrays.toString(new int[]{Math.min(fromY, toY), Math.min(fromX, toX), Math.max(fromY, toY), Math.max(fromX, toX)}));
        
        
        // the cordinates of the beginning cells of the ship will always be <= the coordinates of the ending cells
        /* the ship will be placed from left to right (if it is horizontal)
            or top to bottom (if it is vertical)
        */
        return new int[]{Math.min(fromY, toY), Math.min(fromX, toX), Math.max(fromY, toY), Math.max(fromX, toX)}; 
    }

    public static boolean checkLocation(BattleShip ship, int[] location) throws WrongLocation {

        boolean isInsideTheGrid = true;
        boolean correctLocation = false;

        for (int number : location) {
            if (number < 1 || number > 10) {
                isInsideTheGrid = false;
                throw new WrongLocation("Error! Out of the grids! Try again: ");
            }
        }

        int xDifference = location[3] - location[1];
        int yDifference = location[2] - location[0];

        if (xDifference == 0 && yDifference + 1 == ship.size) {
            correctLocation = true; // when the ship is placed vertically
        } else if (xDifference + 1== ship.size && yDifference == 0) {
            correctLocation = true; // when the ship is placed horizontally
        }
        if (correctLocation) {
            return true;
        } else {
            throw new WrongLocation(String.format("Error! Wrong length of the %s! Try again: ", ship.name));
        }

    }

    public static void setLocation(BattleShip ship, int[] location, String[][] grids) throws WrongLocation {
        boolean horizontal = location[0] == location[2]; // check the ship is horizontally or vertically placed

        int fromY = location[0];
        int fromX = location[1];
        int toY = location[2];
        int toX = location[3];


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


    }

    public static void printGrids(String[][] grids) {

        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                System.out.print(grids[i][j]);
            }
            System.out.println();
        }

    }


}
class WrongLocation extends Exception {
    public WrongLocation(String msg){
        super(msg);
    }
}
