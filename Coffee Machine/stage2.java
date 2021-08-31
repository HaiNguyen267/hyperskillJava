package machine;
import java.util.*;

public class CoffeeMachine {
   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write how many cups of coffee you will need: " );
        int cupNUm = sc.nextInt();
        System.out.println("For " + cupNUm + " cups of coffee you will need:");
        System.out.println(cupNUm * 200 + " ml of water");
        System.out.println(cupNUm * 50 + " ml of milk");
        System.out.println(cupNUm * 15 + " g of coffee beans");
    }
}
