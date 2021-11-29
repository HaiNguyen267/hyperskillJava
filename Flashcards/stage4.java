import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Input the number of cards:");
        int cardNum = Integer.parseInt(sc.nextLine());

        int count = 1;

        List<String> nameList = new ArrayList();
        List<String> definitionList = new ArrayList();

        while (count <= cardNum) {

            System.out.println(String.format("Card #%d:", count));
            String card = sc.nextLine();
            while (nameList.contains(card)) {
                System.out.println(String.format("The term \"%s\" already exists. Try again:", card));
                card = sc.nextLine();
            }

            System.out.println(String.format("The definition for card #%d:", count));
            String definition = sc.nextLine();
            while (definitionList.contains(definition)) {
                System.out.println(String.format("The term \"%s\" already exists. Try again:", definition));
                definition = sc.nextLine();
            }

            nameList.add(card);
            definitionList.add(definition);
            count ++;

        }


        for (int i = 0; i < definitionList.size(); i++) {
            System.out.println(String.format("Print the definition of \"%s\" :", nameList.get(i)));
            String answer = sc.nextLine();

            if (definitionList.get(i).equals(answer)) {
                System.out.println("Correct!");
            } else {
                if (definitionList.contains(answer)) {
                    System.out.println(String.format("Wrong. The right answer is \"%s\", but your definition is correct for \"%s\".", definitionList.get(i), nameList.get(definitionList.indexOf(answer))));
                } else {
                    System.out.println(String.format("Wrong. The right answer is \"%s\" .", nameList.get(i)));
                }
            }
        }

    }

}
