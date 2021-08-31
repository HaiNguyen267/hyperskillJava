
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;

class Main {
    enum Coffee {
        ESPRESSO (250, 0, 16, 4),
        LATTE (350 , 75, 20, 7),
        CAPPUCCINO (200, 100, 12, 6);

        private final int waterNeeded;
        private final int milkNeeded;
        private final int beansNeeded;
        private final int profit;

        Coffee(int waterNeeded, int milkNeeded, int beansNeeded, int profit) {
            this.waterNeeded = waterNeeded;
            this.milkNeeded = milkNeeded;
            this.beansNeeded = beansNeeded;
            this.profit = profit;
        }
    }

    static long water = 400;
    static long milk = 540;
    static long beans = 120;
    static long cups = 9;
    static long money = 550;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (true) {
        System.out.println("");
        System.out.println("Write action (buy, fill, take, remaining, exit):  ");
        String option = sc.nextLine();

        if (option.equals("buy")) {
            buy();
        }
        else if (option.equals("fill")) {
            fill();
        }
        else if (option.equals("take")) {
            take();
        }
        else if (option.equals("remaining")) {
            showInfo();
        }
        else if (option.equals("exit")) {
            break;
        }
    }
  }
  public static void showInfo() {
      System.out.println("The coffee machine has: ");
      System.out.println(water + " ml of water");
      System.out.println(milk + " ml of milk");
      System.out.println(beans + " g of coffee beans");
      System.out.println(cups + " disposable cups");
      System.out.println("$" + money + " of money");
  }

  public static void makeCoffee (Coffee coffee) {
        boolean enoughWater = false;
        boolean enoughMilk = false;
        boolean enoughBeans = false;
        boolean enoughCup = false;

        if (water >= coffee.waterNeeded) {
            enoughWater = true;
        }
        else {
            System.out.println("Sorry, not enough water!");
        }

        if (milk >= coffee.milkNeeded) {
            enoughMilk = true;
        }
        else {
            System.out.println("Sorry, not enough milk!");
        }

        if (beans >= coffee.beansNeeded) {
            enoughBeans = true;
        }
        else {
            System.out.println("Sorry, not enough coffee beans!");
        }

        if (cups >= 1) {
            enoughCup = true;
        }
        else {
            System.out.println("Sorry, not enough disposable cups!");
        }

        if (enoughWater && enoughMilk && enoughBeans && enoughCup) {
            System.out.println("I have enough resources, making you a coffee!");
            water -= coffee.waterNeeded;
            milk -= coffee.milkNeeded;
            beans -= coffee.beansNeeded;
            cups -= 1;
            money += coffee.profit;
        }

    }
  public static void buy() {
      Scanner sc = new Scanner(System.in);
      System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
      String option = sc.nextLine();
      if (!option.equals("back")){
          if (option.equals("1")) {
                makeCoffee(Coffee.ESPRESSO);
          }
          else if (option.equals("2")) {
              makeCoffee(Coffee.LATTE);

          }
          else if (option.equals("3")) {
              makeCoffee(Coffee.CAPPUCCINO);
          }
      }
  }
  public static void fill() {
      Scanner sc = new Scanner(System.in);
      System.out.println("Write how many ml of water you want to add:");
      water += sc.nextLong();
      System.out.println("Write how many ml of milk you want to add: ");
      milk += sc.nextLong();
      System.out.println("Write how many grams of coffee beans you want to add: ");
      beans += sc.nextLong();
      System.out.println("Write how many disposable cups of coffee you want to add: ");
      cups += sc.nextLong();
  }
  public static void take() {
      System.out.println("I gave you $" + money);
      money -= money;
  }


}
