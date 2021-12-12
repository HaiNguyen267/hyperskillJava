package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        // array of 36 possible characters
        String[] characters = {"0", "1", "2", "3", "4", "5", "6", "7","8" ,"9",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
                "o", "p", "q", "r", "s", "t", "u", "v", "w"," x", "y", "z"};

        System.out.println("Input the secret code's length:");
        int secretCodeLength = Integer.parseInt(sc.nextLine());
        System.out.println("Input the number of possible symbols in the code:");
        int possibleRange = Integer.parseInt(sc.nextLine()); // because index starts from 0


        if (secretCodeLength < 36) {
            StringBuilder sb = new StringBuilder();

            // create the secret code
            while (sb.length() < secretCodeLength) {

                String digit = characters[(random.nextInt(possibleRange - 1))]; // random.nextInt(9 - 0 + 1) + 1

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

            // create the strings for the information line
            String stars = ""; // the string of * with the length of the secret code
            for (int i = 0; i < secretCodeLength; i++) {
                stars = stars + "*";
            }
            
            String digitRange = ""; // 0-9
            String letterRange = ""; // a-z
            if (possibleRange <= 10) {
                if (possibleRange == 1) {
                    digitRange = "0";
                } else {
                    digitRange = "0-" + characters[possibleRange - 1]; // 0-...
                }
            } else {
                digitRange = "0-9"; // meaning all digits are contained in possible symbols
                
                if (possibleRange == 11) {
                    letterRange = "a";
                } else {
                    letterRange = "a-" + characters[possibleRange - 1]; // a-...
                }
            }
            String symbolRange = (possibleRange <= 10) ? String.format("(%s)", digitRange) : String.format("(%s, %s)", digitRange, letterRange);
            
            // play
            System.out.println(String.format("The secret is prepared: %s %s.", stars, symbolRange));
            System.out.println("Okay, let's start a game!");
            int bulls = 0;
            int cows = 0;
            int count = 1;

            while (bulls < secretCodeLength) {
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
            System.out.println(String.format("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.", secretCodeLength));
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
