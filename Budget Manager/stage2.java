package Budget_Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// using strategy

// interface Strategy {
//     //Scanner scanner = new Scanner(System.in);
//     public void execute();
// }
// class Option0 implements Strategy {

//     @Override
//     public void execute() {
//         System.out.println("Bye!");
//         Main.exit = true;
//     }
// }
// class Option1 implements Strategy {

//     @Override
//     public void execute() {
//         Scanner scanner = new Scanner(System.in);
//         System.out.println("Enter income: ");
//         double newIncome = scanner.nextDouble();
//         Main.income += newIncome;
//         System.out.println("Income was added!");

//     }
// }
// class Option2 implements Strategy {

//     @Override
//     public void execute() {
//         Scanner scanner = new Scanner(System.in);
//         System.out.println("Enter purchase name: ");
//         String item = scanner.nextLine();
//         System.out.println("Enter its price: ");
//         double price = scanner.nextDouble();
//         Main.expense += price;
//         Main.purchaseList.add(item + " $" + price);
//     }
// }
// class Option3 implements Strategy {
//     @Override
//     public void execute() {

//         if (Main.purchaseList.size() == 0) {
//             System.out.println("The puchase list is empty");
//             System.out.println();
//         } else {
//             for (String item : Main.purchaseList) {
//                 System.out.println(item);
//             }
//             System.out.println(Main.expense);
//         }
//     }
// }
// class Option4 implements Strategy {

//     @Override
//     public void execute() {
//         System.out.println(String.format("Balance: $%.2f\n", Main.income - Main.expense));
//     }
// }

// class Algorithm {
//     Strategy strategy;

//     public Algorithm(Strategy strategy) {
//         this.strategy = strategy;
//     }

//     public void execute() {
//         this.strategy.execute();
//     }
// }


public class Main {

    static double income = 0.0;
    static double expense = 0.0;
    static List<String> purchaseList = new ArrayList<String>();
    static boolean exit = false;



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        Option0 option0 = new Option0();
//        Option1 option1 = new Option1();
//        Option2 option2 = new Option2();
//        Option3 option3 = new Option3();
//        Option4 option4 = new Option4();
//
//        Strategy[] options = {option0, option1, option2, option3, option4};

        while (!exit) {
            printMessage();
            int option = sc.nextInt();

//            Algorithm algorithm = new Algorithm(options[option]);
//            algorithm.execute();
          
          // note: if you want to use strategy approach, uncomment the comments and comment the if else statement below

            if (option >=0 & option <=4) {
                switch (option) {
                    case 0:
                        option0();
                        break;
                    case 1:
                        option1();
                        break;
                    case 2:
                        option2();
                        break;
                    case 3:
                        option3();
                        break;
                    case 4:
                        option4();
                        break;
                }
            } else {
                throw new IllegalArgumentException("Please enter number from 0 to 4");
            }
        }

    }

    public static void printMessage() {
        System.out.println("\nChoose your action: \n" +
                "1) Add income\n" +
                "2) Add purchase\n" +
                "3) Show list of purchases\n" +
                "4) Balance\n" +
                "0) Exit\n");
    }

    public static void option0() {
        System.out.println("Bye!");
        exit = true;
    }

    public static void option1() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter income: ");
        double newIncome = scanner.nextDouble();
        income += newIncome;
        System.out.println("Income was added!");
    }

    public static void option2() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter purchase name: ");
        String item = scanner.nextLine();
        System.out.println("Enter its price: ");
        double price = scanner.nextDouble();
        expense += price;
        purchaseList.add(item + " $" + price);
    }

    public static void option3() {
        if (purchaseList.size() == 0) {
            System.out.println("The puchase list is empty");
            System.out.println();
        } else {
            for (String item : purchaseList) {
                System.out.println(item);
            }
            System.out.println(expense);
        }
    }

    public static void option4() {
        System.out.println(String.format("Balance: $%.2f",income - expense));

    }
}
