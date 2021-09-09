import java.util.*;
class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter cells: ");
        String str = sc.nextLine().replace("_", " ");

        char[][] grid = createGridFromString(str);
        showGrid(grid);

        System.out.println("Enter the coordinates: ");
        String coordinateString = sc.nextLine();
        String status = statusOfCoordinates(grid, coordinateString);

        while (!status.equals("satisfied")) {
            if (status.equals("NaN")) {
                System.out.println("You should enter numbers!");
            }
            else if (status.equals(">4")) {
                System.out.println("Coordinates should be from 1 to 3!");
            }
            else if (status.equals("occupied")) {
                System.out.println("This cell is occupied! Choose another one!");
            }
            System.out.println("Enter the coordinates: ");
            coordinateString = sc.nextLine();
            status = statusOfCoordinates(grid, coordinateString);
        }

        changeTheGrid(grid, coordinateString);
        showGrid(grid);
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
    public static void changeTheGrid (char[][] grid, String coordinates) {
     String[] parts = coordinates.split(" ");
     int value1 = Integer.parseInt(parts[0]);
     int value2 = Integer.parseInt(parts[1]);
     grid[value1 - 1][value2 - 1] = 'X';
    }
}
