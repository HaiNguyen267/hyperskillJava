package flashcards;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String card = sc.nextLine();
        String definition = sc.nextLine();
        String answer = sc.nextLine();
  
        if (answer.equals(definition)) {
            System.out.println("Yor answer is right!");
        } else {
            System.out.println("Your answer is wrong...");
        }
        
    }
}
