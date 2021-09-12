import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int row1 = sc.nextInt();
        int column1 = sc.nextInt();

        // input the array1
        int[][] array1 = new int[row1][column1];
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < column1; j++) {
                array1[i][j] = sc.nextInt();
            }
        }


        int row2 = sc.nextInt();
        int column2 = sc.nextInt();

        // when 2 arrays are dimensionally similar
        if (row1 == row2 && column1 == column2) {
            //input the array2
            int[][] array2 = new int[row1][column1];
            for (int i = 0; i < row2; i++) {
                for (int j = 0; j < column2; j++) {
                    array2[i][j] = sc.nextInt();
                }
            }

            /** CALCULATE AND PRINT THE RESULT **/
            int[][] array3 = new int[row2][column2];
            // calculate and print
            for (int i = 0; i < row2; i++) {
                for (int j = 0; j < column2; j++) {
                    array3[i][j] = array1[i][j] + array2[i][j];
                    System.out.print(array3[i][j] + " ");
                }
                System.out.println();
            }
        }
        else {
            System.out.println();
            System.out.println("ERROR");
        }
    }
}
