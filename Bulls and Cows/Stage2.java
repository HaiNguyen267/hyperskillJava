package bullscows;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
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
