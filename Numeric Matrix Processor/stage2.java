
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


        int number = sc.nextInt();
        
        // calculate and print
        System.out.println();
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < column1; j++) {
                array1[i][j] = array1[i][j] * number;
                System.out.print(array1[i][j] + " ");
            }
            System.out.println();
        }
    }
}
