
import java.util.*;
class Main {

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = 0;
        int o = 0;
        int emptyCell = 0;
        boolean xWin = false;
        boolean oWin = false;
        boolean impossible = false;

        System.out.println("Enter cells: ");
        String input = sc.nextLine();

        char[][] grid = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = input.charAt(i * 3 + j);
            }
        }
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'X') {
                x++;
            }
            else if (input.charAt(i) == 'O') {
                o++;
            }
            else if (input.charAt(i) == '_') {
                emptyCell++;
            }
        }

        /**PRINT THE GRID**/
        System.out.println("--------- ");
        for (int i = 0; i < grid.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.print("| \n");
        }
        System.out.println("--------- ");
        if (x >= o + 2 || x <= o - 2 ) {
            impossible = true;
            System.out.println("Impossible");
        }

        if (!impossible) {
            xWin = checkWinning(grid, 'X');
            oWin = checkWinning(grid, 'O');
            if (xWin && oWin ) {
                System.out.println("Impossible");
            }
            else if (!xWin && ! oWin && emptyCell > 0) {
                System.out.println("Game not finished");
            }
            else if (!xWin && !oWin && emptyCell == 0) {
                System.out.println("Draw");
            }
            else if (xWin) {
                System.out.println("X wins");
            }
            else if (oWin) {
                System.out.println("i dont know why");
                System.out.println("O wins");
            }
        }




    }
    public static boolean checkWinning (char [][] grid, char symbol) {
        boolean winning = false;
        for (int i = 0; i < grid.length; i++) {

            if (grid[0][i] == grid[1][i]
            &&  grid[1][i] == grid[2][i]
            &&  grid[0][i] == symbol) {
                winning = true;
            }

            if (grid[i][0] == grid[i][1]
                && grid[i][1] ==  grid[i][2]
                && grid[i][0] == symbol) {
                winning = true;
            }

        }
        if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] && grid[0][0] == symbol
        || grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0] && grid[0][2] == symbol) {
            winning = true;
        }

        return winning;
    }

}
