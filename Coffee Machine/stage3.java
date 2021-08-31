package machine;
import java.util.*;

public class CoffeeMachine {
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write how many ml of water the coffee machine has: ");
        long water = sc.nextLong();
        System.out.println("Write how many ml of milk the coffee machine has: ");
        long milk = sc.nextLong();
        System.out.println("Write how many grams of coffee beans the coffee machine has: ");
        long coffeeBeans = sc.nextLong();
        System.out.println("Write how many cups of coffee you will need: ");
        long cupNum = sc.nextLong();

        int availableCups = (int) Math.min((Math.min(water / 200, milk / 50 )), coffeeBeans / 15);

        if (availableCups > cupNum) {
            System.out.printf("Yes, I can make that amount of coffee (and even %d more than that)", availableCups - cupNum);
        }
        else if (availableCups == cupNum) {
            System.out.println("Yes, I can make that amount of coffee ");
        }
        else if (availableCups < cupNum) {
            System.out.printf("No, I can make only %d cup(s) of coffee", availableCups);
        }
    }
}
