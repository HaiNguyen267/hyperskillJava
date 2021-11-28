
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Input the number of cards:");
        int cardNum = Integer.parseInt(sc.nextLine());

        int count = 1;

        Map<String, String> linkedHashMap = new LinkedHashMap<>();

        while (count <= cardNum) {

            System.out.println(String.format("Card #%d:", count));
            String card = sc.nextLine();
            System.out.println(String.format("The definition for card #%d:", count));
            String definition = sc.nextLine();

            linkedHashMap.put(card, definition);
            count ++;

        }


        count = 1;

        for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
            System.out.println(String.format("Print the definition of \"%s\" :", entry.getKey()));
            String answer = sc.nextLine();

            if (answer.equals(entry.getValue())) {
                System.out.println("Correct!");
            } else {
                System.out.println(String.format("Wrong. The right answer is \"%s\" .", entry.getValue()));
            }
        }
    }

}
