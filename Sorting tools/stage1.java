package sorting;

import java.util.*;

public class Main {
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int total = 0;
        long max = Integer.MIN_VALUE;
        int repeat = 0;

        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();

            if (number > max) {
                max = number;
                repeat = 1;
            } else if (number == max) {
                repeat ++;
            }

            total ++;
        }

        System.out.println(String.format("Total numbers: %d.", total));
        System.out.println(String.format("The greatest number: %d (%d time(s)).", max, repeat));
    }
}
