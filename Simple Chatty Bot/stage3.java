package bot;

import java.util.Scanner;

public class SimpleBot {
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! My name is Aid.");
        System.out.println("I was created in 2018.");
        System.out.println("Please, remind me your name.");

        String name = scanner.nextLine();

        System.out.println("What a great name you have, " + name + "!");
        System.out.println("Let me guess your age.");
        System.out.println("Enter remainders of dividing your age by 3, 5 and 7.");

        int r1 = scanner.nextInt();
        int r2 = scanner.nextInt();
        int r3 = scanner.nextInt();


        int temp = 0;
        while (temp % 3 != r1 || temp % 5 != r2 || temp % 7 != r3) {
            temp ++;
        }
        int yourAge = temp;
        System.out.println("Your age is "+ yourAge +"; that's a good time to start programming!");
    }

}
