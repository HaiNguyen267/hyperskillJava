package bullscows;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int length = sc.nextInt();

        if (length < 10) {
            StringBuilder sb = new StringBuilder();

            while (sb.length() < length) {
                String pseudoRandomNumber = String.valueOf(System.nanoTime());
                for (int i = pseudoRandomNumber.length() - 1; i >= 0 ; i--) {
                    String digit = String.valueOf(pseudoRandomNumber.charAt(i));

                    if (!sb.toString().contains(digit) && sb.length() < length) {

                        if(sb.length() == 0)  {
                            if (!digit.equals("0")) {
                                sb.append(digit); // only add the non-zero digit tot eh first position
                            }
                        } else {
                            sb.append(digit); // if the position is not the first, all digits are accepted
                        }

                    }
                }
            }

            System.out.println(String.format("The random secret number is %s", sb.toString()));
           ;
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
