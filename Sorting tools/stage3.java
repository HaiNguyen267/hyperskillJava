package hSt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String type = "word";
        if (args[0].equals("-dataType")) {
            type = args[1];
        }
        if (Arrays.asList(args).contains("-sortIntegers")) {
            type = "sortIntegers";
        }

        int total = 0;
        String maxValue = "";
        double repeat = 0;
        if (type.equals("sortIntegers")) {

                    List<Long> list = new ArrayList<>();

                    while (scanner.hasNextLong()) {
                        list.add(scanner.nextLong());
                        total ++;
                    }

                    long[] sortedArray = new long[list.size()];

                    for (int i = 0; i < list.size(); i++) { sortedArray[i] = list.get(i); }

                    insertionSort(sortedArray);

                    String str = Arrays.toString(sortedArray);
                    str = str.replaceAll("[\\[\\],]", "");
                    System.out.println(String.format("Total numbers: %d.", total));
                    System.out.println(String.format("Sorted data: %s", str));
                }
        else if (type.equals("long")) {
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
            int percent = (int) (repeat * 100) / total;
            System.out.println(String.format("Total numbers: %d.", total));
            System.out.println(String.format("The greatest number: %d (%d time(s), %d%%)", greatestNumber, (int)repeat, percent));

        }
        else if (type.equals("line")) {

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

            int percent = (int)  (repeat * 100) / total;
            //maxValue = maxLengthLine;
            System.out.println(String.format("Total lines: %d.", total));
            System.out.println(("The longest line:"));
            System.out.println(maxLengthLine);
            System.out.println(String.format("(%d time(s), %d%%).", (int)repeat, percent));

        }
        else if (type.equals("word")) {

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


            int percent = (int)  (repeat * 100)/ total;
            //maxValue = maxLengthWord;
            System.out.println(String.format("Total words: %d.", total));
            System.out.println(String.format("The longest word: %s (%d time(s), %d%%).", longestWord, (int)repeat, percent));

        }

    }

    public static void insertionSort(long[] array) {

        for (int i = 0; i < array.length; i++) {

            long current = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > current) {
                array[j + 1] = array[j];
                j --;
            }

            array[j + 1] = current;
        }
    }
}
