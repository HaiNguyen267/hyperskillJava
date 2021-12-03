
package readability;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    static String twoSyllableRegex = ".*[aeiouy].+[aeiouy].*";
    static String polySyllablesRegex = ".*[aeiouy].+[aeiouy].+[aeiouy].*";
    static String lastLetterERegex = ".+e";
    static String doubleSyllablesRegex = ".+[aeiouy]{2}.+";

    public static void main(String[] args) throws IOException {

        String text = Files.readString(Path.of(args[0]));
        double character = text.replaceAll(" ", "").length();
        double words = text.split(" ").length;
        double sentences = text.split("[?!.]").length;
        double syllables = 0;
        double polySyllables = 0;


        String[] sentenceArr = text.split("[!?.\\s]");

        for (String word : sentenceArr) {
            String afterEdited = removeLastEAndDoubleSyllables(word);

            if (afterEdited.matches(polySyllablesRegex)) {
                polySyllables ++;
                syllables += 3;

            } else if (afterEdited.matches(twoSyllableRegex)){
                syllables += 2;
            } else {
                syllables ++;
            }
        }

        double ari = calculateARI(character, words, sentences);
        double fk = calculateFK(syllables, words, sentences);
        double smog = calculateSMOG(polySyllables, sentences);
        double cl = calculateColemanLian(character, words,sentences);

        int ageForARI = findAge(ari);
        int ageForFK = findAge(fk);
        int ageForSMOG = findAge(smog);
        int ageForCL = findAge(cl);
        double averageAge = (double)(ageForARI + ageForFK + ageForSMOG + ageForCL) / 4;

        System.out.println("Words:" + (int)words);
        System.out.println("Sentences: " + (int)sentences);
        System.out.println("Characters: " + (int)character);
        System.out.println("Syllables: " + (int)syllables);
        System.out.println("Polysyllables: " + (int)polySyllables);
        
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): all");
        Scanner sc = new Scanner(System.in);
        String type = sc.nextLine();



        switch (type) {

            case "ARI":
                System.out.println(String.format("Automated Readability Index: %.2f (about %d-year-olds).", ari, ageForARI));
                break;
            case "FK":
                System.out.println(String.format("Flesch–Kincaid readability tests:  %.2f (about %d-year-olds).", fk, ageForFK));
                break;
            case "SMOG":
                System.out.println(String.format("Simple Measure of Gobbledygook: %.2f (about %d-year-olds).", smog, ageForSMOG));
                break;
            case "CL":
                System.out.println(String.format("Coleman–Liau index: %.2f (about %d-year-olds).", cl, ageForCL));
                break;
            case "all":
                System.out.println(String.format("Automated Readability Index: %.2f (about %d-year-olds).", ari, ageForARI));
                System.out.println(String.format("Flesch–Kincaid readability tests: %.2f (about %d-year-olds).", fk, ageForFK));
                System.out.println(String.format("Simple Measure of Gobbledygook: %.2f (about %d-year-olds).", smog, ageForSMOG));
                System.out.println(String.format("Coleman–Liau index: %.2f (about %d-year-olds).", cl, ageForCL));

                System.out.println(String.format("\nThis text should be understood in average by 14.25-year-olds..", averageAge));
                break;

        }

    }


    public static String removeLastEAndDoubleSyllables(String word) {
        String newWord = word;
        if (word.matches(lastLetterERegex)) {
            newWord = newWord.substring(0, newWord.length() - 1) +'b';
        }

        if (newWord.matches(doubleSyllablesRegex)) {
            newWord = newWord.replaceAll(".[aeiouy]{2}.", "bab");
        }

        return newWord;

    }

    public static double calculateARI(double characters, double words, double sentences) {
        BigDecimal score = new BigDecimal("0");
        double temp1 = characters / words;
        score = score.add(new BigDecimal("4.71").multiply(new BigDecimal(temp1)));
        double temp2 = words / sentences;
        score = score.add(new BigDecimal("0.5").multiply(new BigDecimal(temp2)));
        score = score.subtract(new BigDecimal("21.43"));

        return score.setScale(2, RoundingMode.UP).doubleValue();
    }
    public static double calculateFK(double syllables, double words, double sentences) {
       BigDecimal score = new BigDecimal("0");
       double temp1 = words / sentences;
       score = score.add(new BigDecimal("0.39").multiply(new BigDecimal(temp1)));
       double temp2 = syllables / words;
       score = score.add(new BigDecimal("11.8").multiply(new BigDecimal(temp2)));

       score = score.subtract(new BigDecimal("15.59"));

       return score.setScale(2, RoundingMode.UP).doubleValue();
    }
    public static double calculateSMOG(double polySyllables, double sentences) {
        BigDecimal score = new BigDecimal("0");
        double temp1 = Math.sqrt(polySyllables * (30 / sentences));
        score = score.add(new BigDecimal("1.043").multiply(new BigDecimal(temp1)));
        score = score.add(new BigDecimal("3.1291"));

        return score.setScale(2, RoundingMode.UP).doubleValue();
    }
    public static double calculateColemanLian(double characters,double words, double sentences) {
        BigDecimal score = new BigDecimal("0");
        double L = characters / (words * 100);
        double S = sentences / (words * 100);

        score = score.add(new BigDecimal("0.0588").multiply(new BigDecimal(L)));
        score = score.subtract(new BigDecimal("0.296").multiply(new BigDecimal(S)));
        score = score.subtract(new BigDecimal("15.8"));

        return score.setScale(2, RoundingMode.UP).doubleValue();

    }
    public static int findAge(double score) {
        BigDecimal index = new BigDecimal(score);
        int integerValue = index.setScale(0, RoundingMode.UP).intValue();

        int age = 0;

        if (integerValue < 3) {
            age = integerValue + 5;
        } else {
            age = integerValue + 6;
        }

        if (integerValue >= 13) {
            age = 24;
        }

        return age;
    }

}
