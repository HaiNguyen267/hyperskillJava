package readability;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        input = input.replaceAll("[!?]", ".");

        String[] sentences = input.split("\\. ");

        int words = 0;
        for (String sentence : sentences) {
            words += sentence.split(" ").length;;
        }

        double average = (double) words / sentences.length;
        if (average <= 10) {
            System.out.println("EASY");
        } else {
            System.out.println("HARD");
        }

    }
}
