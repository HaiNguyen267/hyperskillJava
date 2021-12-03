package flashcards;

import java.io.*;
import java.util.*;


public class Main {

    static Map<String, String> flashCards = new LinkedHashMap<>();
    static StringBuilder log = new StringBuilder();
    static Map<String, Integer> mistakeCounter = new LinkedHashMap<>();
    public static void main(String[] args) throws IOException, ClassNotFoundException {


        Scanner sc = new Scanner(System.in);


        List<String> cmdArgs = Arrays.asList(args);

        if (cmdArgs.contains("-import")) {
            String fileName = cmdArgs.get(cmdArgs.indexOf("-import") + 1);
            deserialize(fileName);
        }


        boolean done = false;
        String instruction = "Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):";

        while (!done) {
            printAndLog(instruction, true);
            String action = sc.nextLine();
            printAndLog(action, false);

            switch (action) {
                case "add":
                     addCard();
                     break;
                case "remove":
                    removeCard();
                    break;
                case "import":
                    importCards();
                    break;
                case "export":
                    exportCards();
                    break;
                case "ask":
                    ask();
                    break;
                case "exit":
                    printAndLog("Bye bye!", true);
                    done = true;
                    break;
                case "log":
                    saveLog();
                    break;
                case "hardest card":
                    hardestCards();
                    break;
                case "reset stats":
                    resetStatics();
                    break;
            }
        }

        if (cmdArgs.contains("-export")) {
            String fileName = cmdArgs.get(cmdArgs.indexOf("-export") + 1);
            serialize(fileName);
        }


    }

    private static void addCard() {
        Scanner sc = new Scanner(System.in);

        printAndLog("The card:", true);
        String term = sc.nextLine();
        printAndLog(term, false);

        if (flashCards.containsKey(term)) {
            printAndLog(String.format("The card \"%s\" already exists.", term), true);

        } else {
            printAndLog("The definition of the card: ", true);
            String definition = sc.nextLine();
            printAndLog(definition, false);
            if (flashCards.containsValue(definition)) {
                printAndLog(String.format("The definition \"%s\" already exists. ", definition), true);
            } else {
                flashCards.put(term, definition);
                printAndLog(String.format("The pair (\"%s\":\"%s\") has been added.", term, definition), true);
                mistakeCounter.put(term, 0);

            }
        }

    }

    private static void removeCard() {
        Scanner sc = new Scanner(System.in);

        printAndLog("Which card?", true);
        String term = sc.nextLine();
        printAndLog(term, false);

        if (flashCards.containsKey(term)) {
            flashCards.remove(term);

            printAndLog("The card has been removed.", true);
        } else {
            String msg = String.format("Can't remove \"%s\": there is no such card.", term);
            printAndLog(msg, true);
        }
    }

    private static void importCards() throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        printAndLog("File name:", true);
        String fileName = sc.nextLine();
        printAndLog(fileName, false);

        if (new File(fileName).exists()) {

            deserialize(fileName);
        } else {
            printAndLog("File not found", true);
        }

    }

    private static void exportCards() throws IOException {
        Scanner sc = new Scanner(System.in);

        printAndLog("Input the file name:", true);
        String fileName = sc.nextLine();
        printAndLog(fileName, false);

        serialize(fileName);
        if (flashCards.entrySet().size() == 1) {
            printAndLog("1 card has been saved", true);
        } else {
            String msg = String.format("%d cards have been saved", flashCards.entrySet().size());
            printAndLog(msg, true);
        }
    }

    private static void ask() {
        Scanner sc = new Scanner(System.in);

        printAndLog("How many times to ask?", true);
        int times = Integer.parseInt(sc.nextLine());
        printAndLog(String.valueOf(times), false);


        int count = 0;

        while (count < times) {
            for (String term : flashCards.keySet()) {
                String instruction = String.format("Print the definition for \"%s\":", term);
                printAndLog(instruction, true);
                String answer = sc.nextLine();

                printAndLog(answer, false);


                if (flashCards.get(term).equals(answer)) {
                    printAndLog("Correct!", true);
                } else {
                    if (flashCards.containsValue(answer)) {
                        String correctTerm = findKeyFromValue(answer);
                        String msg = String.format("Wrong. The right answer is \"%s\", but your definition is correct for \"%s\".", flashCards.get(term), correctTerm);
                        printAndLog(msg, true);
                    } else {
                        String msg = String.format("Wrong. The right answer is \"%s\"", flashCards.get(term));
                        printAndLog(msg, true);
                    }

                    mistakeCounter.put(term, mistakeCounter.get(term) + 1);
                }

                count ++;

                if (count == times) {
                    break;
                }
            }
        }

    }

    private static void printAndLog(String msg, boolean output) {
        if (output) {
            System.out.println(msg);
        }
        log.append(msg + "\n");
    }

    private static void hardestCards() {
        List<String> hardestTerm = new ArrayList<>();

        int mostMistake = 0;


        for (Map.Entry<String, Integer> entry : mistakeCounter.entrySet()) {
            // if the numbers of mistake of the term >= most mistake, add it the the list
            if (entry.getValue() >= mostMistake && entry.getValue() > 0) {
                // if the number of mistakes of the term > most mistake, whole the old element in the list is deleted
                if (entry.getValue() > mostMistake) {
                    hardestTerm.clear();
                    mostMistake = entry.getValue();
                }
                hardestTerm.add(entry.getKey());
            }
        }

        if (hardestTerm.size() == 0) {
            printAndLog("There are no cards with errors", true);
        } else if (hardestTerm.size() == 1) {
            String msg = String.format("The hardest card is \"%s\". You have %d errors answering it.\n", hardestTerm.get(0), mostMistake);
            printAndLog(msg, true);
        } else {
            StringBuilder hardestCards = new StringBuilder();
            for (int i = 0; i < hardestTerm.size(); i++) {
                if (i < hardestTerm.size() -1 ) {
                    hardestCards.append("\"" + hardestTerm.get(i) + "\", ");
                } else {
                    hardestCards.append("\"" + hardestTerm.get(i) + "\"");
                }
            }
            String msg = String.format("The hardest cards are %s. You have %d errors answering them.", hardestCards.toString(), mostMistake);
            printAndLog(msg, true);
        }
    }

    public static void resetStatics() {
        for (Map.Entry<String, Integer> entry : mistakeCounter.entrySet()) {
            mistakeCounter.put(entry.getKey(), 0);
        }
        printAndLog("Card statistics have been reset.", true);
    }

    public static void saveLog() throws IOException {
        Scanner sc = new Scanner(System.in);

        printAndLog("File name:", true);
        String fileName = sc.nextLine();
        printAndLog(fileName, false);

        serialize(log, fileName);
        printAndLog("The log has been saved.", true);

    }

    private static String findKeyFromValue(String answer) {

        for (Map.Entry<String, String> entry : flashCards.entrySet()) {
            if (entry.getValue().equals(answer)) {
                return entry.getKey();
            }
        }

        return null;
    }

    private static void serialize(String path) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);


        oos.writeObject(flashCards);
        oos.writeObject(mistakeCounter);
        oos.close();

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

        Map<String, String> importedFlashCards = (Map<String, String>) ois.readObject();
        Map<String, Integer> importedMistakeCounter = (Map<String, Integer>) ois.readObject();

        // importing the flashcards
        for (Map.Entry<String, String> entry : importedFlashCards.entrySet()) {
            flashCards.put(entry.getKey(), entry.getValue());
        }
        if (importedFlashCards.size() == 1) {
            printAndLog("1 card has been loaded", true);
        } else {
            String msg = String.format("%d cards have been loaded", importedFlashCards.entrySet().size());
            printAndLog(msg, true);
        }

        // importing the mistakeCounter
        for (Map.Entry<String, Integer> entry : importedMistakeCounter.entrySet()) {
            mistakeCounter.put(entry.getKey(), entry.getValue());
        }

        ois.close();


        return importedFlashCards;
    }
}
