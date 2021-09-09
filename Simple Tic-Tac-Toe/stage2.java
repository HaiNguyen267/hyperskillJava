package tictactoe;
import java.util.*;
public class Main {
       public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter cell: ");
        String input = sc.nextLine();
        
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                int value = (i * 3) + j;
                System.out.print(input.charAt(value) + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

}
