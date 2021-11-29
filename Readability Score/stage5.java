
import java.io.*;
import java.util.*;


public class Main {

    static Map<String, String> flashCards = new LinkedHashMap<>();
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);

        boolean done = false;
        String instruction = "Input the action (add, remove, import, export, ask, exit):";

        while (!done) {
            System.out.println(instruction);
            String action = sc.nextLine();

            switch (action) {
                case "add" -> addCard();
                case "remove" -> removeCard();
                case "import" -> importCards();
                case "export" -> exportCards();
                case "ask" -> ask();
                case "exit" -> {
                    System.out.println("Bye bye!");
                    done = true;
                }
            }
        }

        

    }

    private static void addCard() {
        Scanner sc = new Scanner(System.in);

        System.out.println("The card:");
        String term = sc.nextLine();
        if (flashCards.containsKey(term)) {
            System.out.println(String.format("The card \"%s\" already exists.", term));
        } else {
            System.out.println("The definition of the card: ");
            String definition = sc.nextLine();

            if (flashCards.containsValue(definition)) {
                System.out.println(String.format("The definition \"%s\" already exists. ", definition));
            } else {
                flashCards.put(term, definition);
                System.out.println(String.format("The pair (\"%s\":\"%s\") has been added.", term, definition));

            }
        }

    }

    private static void removeCard() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Which card?");
        String term = sc.nextLine();

        if (flashCards.containsKey(term)) {
            flashCards.remove(term);
            System.out.println("The card has been removed.");
        } else {
            System.out.println(String.format("Can't remove \"%s\": there is no such card.", term));
        }
    }


    private static void importCards() throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("File name:");
        String fileName = sc.nextLine();

        if (new File(fileName).exists()) {

            Map<String, String> imported = (Map<String, String>) deserialize(fileName);

            for (Map.Entry<String, String> entry : imported.entrySet()) {
                flashCards.put(entry.getKey(), entry.getValue());
            }

            if (imported.entrySet().size() == 1) {
                System.out.println("1 card has been loaded");
            } else {
                System.out.println(String.format("%d cards have been loaded", imported.entrySet().size()));
            }
        } else {
            System.out.println("File not found");
        }
    }

    private static void exportCards() throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Input the file name:");
        String fileName = sc.nextLine();

        serialize(flashCards, fileName);

        if (flashCards.entrySet().size() == 1) {
            System.out.println("1 card has been saved");
        } else {
            System.out.println(String.format("%d cards have been saved", flashCards.entrySet().size()));
        }
    }

    private static void ask() {
        Scanner sc = new Scanner(System.in);

        System.out.println("How many times to ask?");
        int times = Integer.parseInt(sc.nextLine());

        int count = 0;

        while (count < times) {
            for (String term : flashCards.keySet()) {
                System.out.println(String.format("Print the definition for \"%s\":", term));
                String answer = sc.nextLine();

                if (flashCards.get(term).equals(answer)) {
                    System.out.println("Correct!");
                } else {
                    if (flashCards.containsValue(answer)) {
                        String correctTerm = findKeyFromValue(answer);
                        System.out.println(String.format("Wrong. The right answer is \"%s\", but your definition is correct for \"%s\".", flashCards.get(term), correctTerm));
                    } else {
                        System.out.println(String.format("Wrong. The right answer is \"%s\"", flashCards.get(term)));
                    }
                }

                count ++;

                if (count == times) {
                    break;
                }
            }
        }

    }

    private static String findKeyFromValue(String answer) {

        for (Map.Entry<String, String> entry : flashCards.entrySet()) {
            if (entry.getValue().equals(answer)) {
                return entry.getKey();
            }
        }

        return null;
    }

    private static void serialize(Object obj, String path) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(obj);
        oos.close();

    }

    private static Object deserialize(String path) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);

        Object obj = ois.readObject();
        ois.close();
        return obj;
    }

}
