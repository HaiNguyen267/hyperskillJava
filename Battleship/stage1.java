

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

        BattleShip[] ships = {BattleShip.CARRIER, BattleShip.BATTLESHIP, BattleShip.CRUISER,
                BattleShip.SUBMARINE, BattleShip.DESTROYER};


        String[][] grids = new String[11][11];
        setup(grids);

        String instruction = String.format("\nEnter the coordinates of the %s (%d cells)\n",
                ships[shipNum].name, ships[shipNum].size);

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
                    instruction = String.format("\nEnter the coordinates of the %s (%d cells): \n",
                            ships[shipNum].name, ships[shipNum].size);
                    System.out.println("shipNum: "+ shipNum);
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
        return new int[]{fromY, fromX, toY, toX};
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

        int xDifference = Math.abs(location[3] - location[1]);
        int yDifference = Math.abs(location[2] - location[0]);

        if (xDifference == 0 && yDifference + 1 == ship.size) {
            correctLocation = true;
        } else if (xDifference + 1== ship.size && yDifference == 0) {
            correctLocation = true;
        }
        if (correctLocation) {
            return true;
        } else {
            throw new WrongLocation(String.format("Error! Wrong length of the %s! Try again: ", ship.name));
        }

    }

    public static void setLocation(BattleShip ship, int[] location, String[][] grids) throws WrongLocation {
        boolean horizontal = location[2] - location[0] == 0;

        for (int i = 0; i < grids.length; i++) {

            for (int j = 0; j < grids[i].length; j++) {

                if (horizontal) {

                    if (i == location[0] && j >= Math.min(location[1], location[3])  && j <= Math.max(location[1], location[3]) ) {

                        //check close
                        if (Math.min(location[1], location[3]) > 1 && j == Math.min(location[1], location[3])) {
                            if (grids[i][j - 1].equals("O ")) {
                                throw new WrongLocation("Error! You placed it too close to another one. Try again:");
                            }
                        } // check head1
                        if (Math.max(location[1], location[3]) < 10 && j == Math.max(location[1], location[3])) {
                            if (grids[i][j + 1].equals("O ")) {
                                throw new WrongLocation("Error! You placed it too close to another one. Try again:");
                            }
                        } // check head2

                        if (i < 10) {
                            if (grids[i + 1][j].equals("O ")) {
                                throw new WrongLocation("Error! You placed it too close to another one. Try again:");
                            }
                        }
                        if (i > 1) {
                            if (grids[i - 1][j].equals("O ")) {
                                throw new WrongLocation("Error! You placed it too close to another one. Try again:");
                            }
                        }

                        if (grids[i][j].equals("O ")) {
                            throw new WrongLocation("Error! A ship cannot cross other");
                        } else {
                            grids[i][j] = "O ";
                        }
                    }

                } else  {

                    if  (j == location[1] && i >= Math.min(location[0], location[2])  && i <= Math.max(location[0], location[2])) {
                        //check close
                        if (Math.min(location[0], location[2]) > 1 && i == Math.min(location[0], location[2])) {
                            if (grids[i - 1][j].equals("O ")) {
                                //System.out.println("head123");
                                throw new WrongLocation("Error! You placed it too close to another one. Try again:");
                            }
                        } // check head1
                        if (Math.max(location[0], location[2]) < 10 && i == Math.max(location[0], location[2])) {
                            if (grids[i + 1][j].equals("O ")) {
                                //System.out.println("head456");
                                throw new WrongLocation("Error! You placed it too close to another one. Try again:");
                            }
                        } // check head2

                        if (j < 10) {
                            if (grids[i][j + 1].equals("O ")) {
                                //System.out.println("side1123");
                                throw new WrongLocation("Error! You placed it too close to another one. Try again:");
                            }
                        }
                        if (j > 1) {
                            if (grids[i][j - 1].equals("O ")) {
                                //System.out.println("soide456");
                                throw new WrongLocation("Error! You placed it too close to another one. Try again:");
                            }
                        }

                        if (grids[i][j].equals("O ")) {
                                throw new WrongLocation("Error! A ship cannot cross other");
                            } else {
                                grids[i][j] = "O ";
                            }
                    }
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

