
import java.util.*;

public class Main {
    static double income = 0;
    static Map<Integer, Map<String, Double>> map = new LinkedHashMap<>();// add 4 categories to the map
    static {
        map.put(0, new LinkedHashMap<>());
        map.put(1, new LinkedHashMap<>());
        map.put(2, new LinkedHashMap<>());
        map.put(3, new LinkedHashMap<>());
        map.put(4, new LinkedHashMap<>());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String instruction = """
                
                Choose an action:
                1) Add income
                2) Add purchase
                3) Show list of purchases
                4) Balance
                0) Exit
                
                """;

        boolean done = false;

        while(!done) {
            System.out.println(instruction);
            int option = sc.nextInt();

            switch(option) {
                case 0 :
                    System.out.println("Bye!");
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


            }
        }
    }

    public static void option1_addIncome() {
        Scanner sc = new Scanner(System.in);
        System.out.println("How much is your income?");
        double newIncome = sc.nextDouble();
        income += newIncome;
        System.out.println("Income was added");
    }

    public static void option2_addPurchase() {
        Scanner sc = new Scanner(System.in);
        String instruction = """
        
                1. Food
                2. Clothes
                2. Entertainment
                4. Other
                5. Back
                """;

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
        String instruction = """
        
                1. Food
                2. Clothes
                3. Entertainment
                4. Other
                5. All
                6. Back
                """;

        // count the number of items in all categories to check if all categories are empty
        int count = 0;
        for (Map<String, Double> category : map.values()) {
            count += category.values().size();
        }

        if (count == 0) {
            System.out.println("The purchase list is empty");
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
                            option == 3? "Entertainment:" :
                            "Other:";

        System.out.println(category);

        if (map.get(option).values().size() == 0) {
            System.out.println("The purchase list is empty!");
        } else {
            for (Map.Entry<String, Double> entry : map.get(option).entrySet()) {
                System.out.println(entry.getKey() + " $" + entry.getValue());
            }
            System.out.println("Total sum: " + totalPriceOfCategory(map.get(option)));

        }
    }

    public static void option4_showBalance() {
        double totalExpense = 0L;

        for (Map<String, Double> category : map.values()) {
            totalExpense += totalPriceOfCategory(category);
        }

        double balance = income - totalExpense;
        System.out.println(String.format("Balance: $%.2f", balance));
    }
    private static double totalPriceOfCategory(Map<String, Double> category) {
        double expense = 0L;

        for (Map.Entry<String, Double> entry : category.entrySet()) {
            expense += entry.getValue();
        }

        return expense;
    }
}
