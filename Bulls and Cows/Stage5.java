package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length:");
        int length = Integer.parseInt(sc.nextLine());

        if (length < 10) {
            StringBuilder sb = new StringBuilder();

            // create the secret code
            while (sb.length() < length) {

                String digit = String.valueOf(random.nextInt(10)); // random.nextInt(9 - 0 + 1) + 1

                    if (!sb.toString().contains(digit)) {
                        if (sb.length() == 0) {
                            if (!digit.equals("0")) {
                                sb.append(digit); // only add non-zero digit to the first position
                            }
                        } else {
                            sb.append(digit);
                        }
                    }

            }
            String secretCode = sb.toString();

            // play
            System.out.println("Okay, let's start a game!");
            int bulls = 0;
            int cows = 0;
            int count = 1;

            while (bulls < length) {
                bulls = 0; // reset the bulls for the next turn
                cows = 0; // reset the cows for the next turn

                System.out.println(String.format("Turn %d:", count));
                String input = sc.nextLine();

                // count the bulls and cows
                for (int i = 0; i < input.length(); i++) {

                    String digitOfInput = String.valueOf(input.charAt(i));

                    if (input.charAt(i) == secretCode.charAt(i)) {
                        bulls ++; // the input number has the same digit, same position
                    } else if (secretCode.contains(digitOfInput)){
                        cows ++; // the secret code contains the digit, but not in same position
                    }

                }

                count ++;


                // printing grade
                String bullNum = bulls > 1? "bulls" : bulls == 1? "bull" : "";
                String cowNum = cows > 1? "cows" : cows == 1? "cow" : "";
                if (bulls != 0 && cows != 0){
                    System.out.println(String.format("Grade: %d %s and %d %s", bulls, bullNum, cows, cowNum)); // having both bulls and cows
                } else if (bulls != 0) {
                    System.out.println(String.format("Grade: %d %s", bulls, bullNum)); // only bulls
                } else if (cows != 0) {
                    System.out.println(String.format("Grade: %d %s", cows, cowNum)); // only cows
                } else {
                    System.out.println("Grade: None"); // no bulls, no cows

                }
            }

            System.out.println("Congratulations! You guessed the secret code.");


        } else {
            System.out.println(String.format("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.", length));
        }
    }
    public static void mainz(String[] args) {
        Scanner sc = new Scanner(System.in);
        String secretCode = "6235";

        String input = sc.nextLine();
        int bull = 0;
        int cow = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == secretCode.charAt(i)) {
                bull ++;
            } else if (secretCode.contains(String.valueOf(input.charAt(i)))) {
                cow ++;
            }
        }


        if (bull != 0 || cow != 0) {
            System.out.println(String.format("Grade: %d bull(s) and %d cow(s). The secret code is 6235.", bull, cow));
        } else {
            System.out.println("Grade: None. The secret code is 6235.");
        }

    }
}
