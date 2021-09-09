import java.util.*;
class Main {
    public static void main (String[] args) {
      Scanner sc = new Scanner(System.in);
      int x = 0;
      int o = 0;
      boolean xWin = false;
      boolean oWin = false;
      int count = 0;
      char [][] grid = new char[3][3];
      String initialString = "_________";
      grid = createGridFromString(initialString.replace("_", " "));

      showGrid(grid);
      while (!xWin && !oWin  && count < 9) {

          System.out.println("Enter the coordinates:");
          String coordinates = sc.nextLine();
          String status = statusOfCoordinates(grid, coordinates);
          if (status.equals("satisfied")) {
              char symbol = (count % 2 == 0) ? 'X' : 'O';
              changeTheGrid(grid, coordinates, symbol);
              xWin = checkWinning(grid, 'X');
              oWin = checkWinning(grid, 'O');
              count++;
              showGrid(grid);
          }
          else {
              printErrorMessage(status);
          }

      }
      if (xWin) {
          System.out.println("X wins");
      }
      else if (oWin) {
          System.out.println("O wins");
      }
    }
    public static char[][] createGridFromString (String str) {
        char[][] grid = new char[3][3];


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = str.charAt(i * 3 + j);
            }

        }

        return grid;
    }
    public static void showGrid (char[][] grid) {

        System.out.println("---------");
        for (int i = 0; i < grid.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
    public static String statusOfCoordinates (char[][] grid, String coordinates) {
        String status;
        String[] parts = coordinates.split(" ");

        String coor1 = parts[0];
        String coor2 = parts[1];

        try {
            int value1 = Integer.parseInt(coor1);
            int value2 = Integer.parseInt(coor2);

                if (value1 <=3 && value2 <=3) {
                    if (grid[value1 - 1][value2 - 1] == ' ') {
                        status = "satisfied";
                    }
                    else {
                        status = "occupied";
                }

            }
                else {
                    status = ">4";
                }

        }
        catch (NumberFormatException e) {
            status = "NaN";
        }

        return status;
    }
    public static void changeTheGrid (char[][] grid, String coordinates, char symbol) {
     String[] parts = coordinates.split(" ");
     int value1 = Integer.parseInt(parts[0]);
     int value2 = Integer.parseInt(parts[1]);
     grid[value1 - 1][value2 - 1] = symbol;
    }
    public static boolean checkWinning (char[][] grid, char symbol) {
        boolean winning = false;
        for (int i = 0; i < grid.length; i++) {
            // horizontal
            if (grid[i][0] == grid[i][1] && grid[i][0] == grid[i][2] && grid[i][0] == symbol) {
                winning = true;
            }
            // vertical
            if (grid[0][i] == grid[1][i] && grid[0][i] == grid[2][i] && grid[0][i] == symbol) {
                winning = true;
            }

        }
        // diagonal
        if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] && grid[0][0] == symbol
                ||  grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0] && grid[0][2] == symbol) {
            winning = true;
        }

        return winning;
    }
    public static void printErrorMessage (String error) {
        if (error.equals("NaN")) {
            System.out.println("You should enter numbers!");
        }
        else if (error.equals(">4")) {
            System.out.println("Coordinates should be from 1 to 3!");
        }
        else if (error.equals("occupied")) {
            System.out.println("This cell is occupied! Choose another one!");
        }
    }
}
