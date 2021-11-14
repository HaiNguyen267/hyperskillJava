package budget;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> purchaseList = new ArrayList<String>();
        double totalPrice = 0;

        while (sc.hasNextLine()) {
          
            String item = sc.nextLine();
            String[] parts = item.split("\\$");
            double price = Double.parseDouble(parts[1]);
          
            purchaseList.add(item);
            totalPrice += price;

        }

        for (String item : purchaseList) {
            System.out.println(item);
        }

        System.out.println(String.format("\nTotal: $%.2f", totalPrice));
    }
}
