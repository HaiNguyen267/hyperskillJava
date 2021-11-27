package budget;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Main {
    static double balance = 0L;
    static File purchaseData = new File("purchases.txt");
    static Map<Integer, Map<String, Double>> map = new LinkedHashMap<>();// add 4 categories to the map
    static {
        map.put(0, new LinkedHashMap<>());
        map.put(1, new LinkedHashMap<>());
        map.put(2, new LinkedHashMap<>());
        map.put(3, new LinkedHashMap<>());
        map.put(4, new LinkedHashMap<>());
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);

        String instruction = "\nChoose your action:\n" +
                "1) Add income\n" +
                "2) Add purchase\n" +
                "3) Show list of purchases\n" +
                "4) Balance\n" +
                "5) Save\n" +
                "6) Load\n" +
                "7) Analyze (sort)\n" +
                "0) Exit\n"
                ;


        boolean done = false;

        while(!done) {
            System.out.println(instruction);
            int option = sc.nextInt();

            switch(option) {
                case 0 :
                    System.out.println("\nBye!");
                    done = true;
                    break;
                case 1:
                    option1_addIncome();
                    break;
                case 2:
                    option2_addPurchase();
                    break;
                case 3:
                    option3_showPurchase();
                    break;
                case 4:
                    option4_showBalance();
                    break;
                case 5:
                    option5_Save();
                    break;
                case 6:
                    option6_load();
                    break;
                case 7:
                    option7_analyze();
                    break;



            }
        }
    }

    public static void option1_addIncome() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nHow much is your income?");
        double newIncome = sc.nextDouble();
        balance += newIncome;
        System.out.println("Income was added");
    }

    public static void option2_addPurchase() {
        Scanner sc = new Scanner(System.in);
        String instruction = "\nChoose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) Back\n";

        boolean done = false;

        while(!done) {
            System.out.println(instruction);
            int option = sc.nextInt();

            if(option < 5) {
                addToCategory(option);
            } else {
                done = true;
            }

        }

    }
    private static void addToCategory(int option) {
        Scanner sc = new Scanner(System.in);

        System.out.println("What is the item?");
        String name = sc.nextLine();;
        System.out.println("What's its price?");
        double price = sc.nextDouble();

        map.get(option).put(name, price);
        map.get(0).put(name, price);


    }

    public static void option3_showPurchase() {
        Scanner sc = new Scanner(System.in);
        String instruction = "\nChoose the type of purchases\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) All\n" +
                "6) Back\n";

        // count the number of items in all categories to check if all categories are empty
        int count = 0;
        for (Map<String, Double> category : map.values()) {
            count += category.values().size();
        }

        if (count == 0) {
            System.out.println("\nThe purchase list is empty");
        } else {
            boolean done = false;
            while(!done) {
                System.out.println(instruction);
                int option = sc.nextInt();

                if (option < 5) {
                    showACategory(option);
                } else if (option == 5) {
                    showACategory(0);
                }
                else {
                    done = true;
                }

            }
        }

    }
    private static void showACategory(int option) {

        String category =  option == 0? "All:" :
                            option == 1? "Food:" :
                            option == 2? "Clothes:" :
                            option == 3? "Entertainment:" : "Other:";

        System.out.println("\n" + category);

        if (map.get(option).values().size() == 0) {
            System.out.println("The purchase list is empty!");
        } else {
            for (Map.Entry<String, Double> entry : map.get(option).entrySet()) {
                BigDecimal price = new BigDecimal(entry.getValue()).setScale(2, RoundingMode.HALF_UP);
                System.out.println(entry.getKey() + " $" + price);
            }
            System.out.println("Total sum: " + totalPriceOfCategory(map.get(option)));

        }
    }

    public static void option4_showBalance() {
        double totalExpense = 0L;

        for (double price : map.get(0).values()) {
            totalExpense += price;
        }

        balance -= totalExpense;
        System.out.println(String.format("\nBalance: $%.3f", balance));
    }
    private static double totalPriceOfCategory(Map<String, Double> category) {
        double expense = 0L;

        for (Map.Entry<String, Double> entry : category.entrySet()) {
            expense += entry.getValue();
        }

        return expense;
    }

    public static void option5_Save() throws IOException {
        FileOutputStream fos = new FileOutputStream(purchaseData.getPath());
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(map);
        oos.writeDouble(balance);
        oos.close();

    }

    public static void option6_load() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(purchaseData.getPath());
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);

        map = (Map<Integer, Map<String, Double>>) ois.readObject();
        balance = ois.readDouble();
        ois.close();
        System.out.println("\nPurchases were loaded!");

    }

    public static void option7_analyze() {
        Scanner sc = new Scanner(System.in);

        String instruction = "\nHow do you want to sort?\n" +
                "1) Sort all purchases\n" +
                "2) Sort by type\n" +
                "3) Sort certain type\n" +
                "4) Back\n";




        boolean done = false;

        while(!done) {
            System.out.println(instruction);
            int option = sc.nextInt();

            switch(option) {
                case 1:
                    sortAll();
                    break;
                case 2:
                    sortByType();
                    break;
                case 3:
                    sortBYCertainType();
                    break;
                case 4:
                    done = true;
                    break;
            }
        }
    }



    private static void sortAll() {
        Map<String, Double> newSortedForAll = sorting(map.get(0));

        printSortedMap(newSortedForAll);
    }

    private static void sortByType() {

        double foodTotalPrice = totalPriceOfCategory(map.get(1));
        double clothesTotalPrice = totalPriceOfCategory(map.get(2));
        double entertainmentTotalPrice = totalPriceOfCategory(map.get(3));
        double otherTotalPrice = totalPriceOfCategory(map.get(4));

        Map<String, Double> categoryMap = new HashMap<String, Double>();

        categoryMap.put("Food", foodTotalPrice);
        categoryMap.put("Clothes", clothesTotalPrice);
        categoryMap.put("Entertainment", entertainmentTotalPrice);
        categoryMap.put("Other", otherTotalPrice);

        Map<String, Double> sortedCategoryMap = sorting(categoryMap);



        for (Map.Entry<String, Double> entry : sortedCategoryMap.entrySet()) {
            BigDecimal price = new BigDecimal(entry.getValue()).setScale(2, RoundingMode.HALF_UP);
            System.out.println(entry.getKey() + " - $" + price);
        }


    }

    private static void sortBYCertainType() {
        Scanner sc = new Scanner(System.in);

        String instruction = "\nChoose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n";

        System.out.println(instruction);
        int option = sc.nextInt();

        switch (option) {
            case 1:
                printSortedMap(sorting(map.get(1)));
                break;
            case 2:
                printSortedMap(sorting(map.get(2)));
                break;
            case 3:
                printSortedMap(sorting(map.get(3)));
                break;
            case 4:
                printSortedMap(sorting(map.get(4)));
                break;
        }

    }
    private static Map<String, Double> sorting(Map<String, Double> inputLinkedHashMap) {
        List<String> keylist = new ArrayList();
        List<Double> valueList = new ArrayList();

        for (Map.Entry<String, Double> entry : inputLinkedHashMap.entrySet()) {
            keylist.add(entry.getKey());
            valueList.add(entry.getValue());
        }

        Map<String, Double> newSortedMap = new LinkedHashMap<String, Double>();

        for (int i = 0; i < valueList.size(); i++) {
            int maxIndex = i;
            for (int j = i + 1; j < valueList.size(); j++) {
                if (valueList.get(j) > valueList.get(maxIndex)) {

                    maxIndex = j;
//
                }
            }
            String tempKey = keylist.get(i);
            double tempValue = valueList.get(i);

            newSortedMap.put(keylist.get(maxIndex), valueList.get(maxIndex));

            keylist.set(i, keylist.get(maxIndex));
            valueList.set(i, valueList.get(maxIndex));

            keylist.set(maxIndex, tempKey);
            valueList.set(maxIndex, tempValue);




        }
        return newSortedMap;
    }

    private static void printSortedMap(Map<String, Double> sortedMap) {

        if (sortedMap.keySet().size() == 0) {
            System.out.println("\nThe list is empty!");
        } else {
            for (Map.Entry<String, Double> entry : sortedMap.entrySet()) {
                BigDecimal price = new BigDecimal(entry.getValue()).setScale(2, RoundingMode.HALF_UP);
                System.out.println(entry.getKey() + " $" + price);
            }
        }
    }
}
