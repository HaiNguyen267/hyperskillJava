package readability;


import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
public class Main {
    public static void main(String[] args) throws IOException {


        String input = Files.readString(Path.of(args[0]));
        double numberOfCharacters = input.replaceAll("\\s","").length();
        double numberOfWords = input.split(" ").length;
        double numberOfSentences = input.split("[!?.]").length;

        double temp1 = numberOfCharacters / numberOfWords;
        double temp2 = numberOfWords / numberOfSentences;
        BigDecimal score = new BigDecimal("4.71");

        score = score.multiply(new BigDecimal(temp1));
        score = score.add(new BigDecimal("0.5").multiply(new BigDecimal(temp2)));
        score = score.subtract(new BigDecimal("21.43"));

       score = score.setScale(2, RoundingMode.HALF_UP);
        int integerScore = score.setScale(0, RoundingMode.UP).intValue();
        String people = integerScore == 1? "5-6":
                        integerScore == 2? "6-7":
                        integerScore == 3? "7-9":
                        integerScore == 4? "9-10":
                        integerScore == 5? "10-11":
                        integerScore == 6? "11-12":
                        integerScore == 7? "12-13":
                        integerScore == 8? "13-14":
                        integerScore == 9? "14-15":
                        integerScore == 10? "15-16":
                        integerScore == 11? "16-17":
                        integerScore == 12? "17-18":
                        integerScore == 13? "18-24":
                        "24+";

        people = people + "-year-olds";

        System.out.println("Words: " + (int)numberOfWords);
        System.out.println("Sentences: " + (int)numberOfSentences);
        System.out.println("Characters: " + (int)numberOfCharacters);
        System.out.println("The score is: " + score);
        System.out.println("This text should be understood by " + people);

    }

}
