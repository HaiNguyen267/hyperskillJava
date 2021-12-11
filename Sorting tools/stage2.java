package hSt;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String type = "word";
        if (args[0].equals("-dataType")) {
            type = args[1];
        }

        int total = 0;
        String maxValue = "";
        int repeat = 0;

        if (type.equals("long")) {
            long greatestNumber = Integer.MIN_VALUE;

            while (scanner.hasNextLong()) {
                long number = scanner.nextLong();

                if (number > greatestNumber) {
                    greatestNumber = number;
                    repeat = 1;
                } else if (number == greatestNumber){
                    repeat ++;
                }

                total ++;
            }

            //maxValue = String.valueOf(max);
            int percent = (int) ((double) (repeat * 100)/ total);
            System.out.println(String.format("Total numbers: %d.", total));
            System.out.println(String.format("The greatest number: %d (%d time(s), %d%%)", greatestNumber, repeat, percent));

        } else if (type.equals("line")) {

            int longest = Integer.MIN_VALUE;
            String maxLengthLine = "";

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.length() > longest) {
                    longest = line.length();
                    maxLengthLine = line;
                    repeat = 1;
                } else if (line.length() == longest) {
                    repeat ++;
                }

                total ++;
            }

            int percent = (int) ((double) (repeat * 100)/ total);
            //maxValue = maxLengthLine;
            System.out.println(String.format("Total lines: %d.", total));
            System.out.println(("The longest line:"));
            System.out.println(maxLengthLine);
            System.out.println(String.format("(%d time(s), %d%%).", repeat, percent));

        } else if (type.equals("word")) {

            int longest = Integer.MIN_VALUE;
            String longestWord = "";

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] words = line.split("\\s+");

                for (String word : words) {
                    if (word.length() > longest) {
                        longest = word.length();
                        longestWord = word;
                        repeat = 1;
                    } else if (word.length() == longest) {
                        repeat ++;
                    }
                    total ++;
                }
            }


            int percent = (int) ((double) (repeat * 100)/ total);
            //maxValue = maxLengthWord;
            System.out.println(String.format("Total words:  %d.", total));
            System.out.println(String.format("The longest word: %s (%d time(s), %d%%).", longestWord, repeat, percent));

        }



    }


}
