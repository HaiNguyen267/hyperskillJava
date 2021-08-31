import java.util.*;

class Main {
    static long water = 400;
    static long milk = 540;
    static long beans = 120;
    static long cups = 9;
    static long money = 550;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    showInfo();

    System.out.println("Write action (buy, fill, take): ");
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

      showInfo();
  }
  public static void showInfo() {
      System.out.println("The coffee machine has: ");
      System.out.println(water + " ml of water");
      System.out.println(milk + " ml of milk");
      System.out.println(beans + " g of coffee beans");
      System.out.println(cups + " disposable cups");
      System.out.println("$" + money + " of money");
  }
  public static void buy() {
      Scanner sc = new Scanner(System.in);
      System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
      int option = sc.nextInt();
      if (option == 1) {
          water -= 250;
          beans -= 16;
          money += 4;
      }
      else if (option == 2) {
          water -= 350;
          milk -= 75;
          beans -= 20;
          money += 7;
      }
      else if (option == 3) {
          water -= 200;
          milk -= 100;
          beans -= 12;
          money += 6;
      }
      cups --;
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
      System.out.println("I gave you %" + money);
      money -= money;
  }
}
