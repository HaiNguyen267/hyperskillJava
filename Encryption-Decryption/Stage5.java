package encryptdecrypt;


import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        //Scanner sc = new Scanner(System.in);
        // initialize the variables
        String mode = "enc";
        String data = "";
        int key = 0;
        String fileOutput = "";
        boolean error = false;
        List<String> cmdArgs = Arrays.asList(args);

        // read the command-line arguments
        if (cmdArgs.contains("-mode")) {
            error = checkArgument(cmdArgs, "-mode");

            if (!error) {
                int indexOfMode = cmdArgs.indexOf("-mode");
                mode = cmdArgs.get(indexOfMode + 1);
            }

        }
        if (cmdArgs.contains("-key")) {
            error = checkArgument(cmdArgs, "-key");

            if (!error) {
                int indexOfKey = cmdArgs.indexOf("-key");
                key = Integer.parseInt(cmdArgs.get(indexOfKey + 1));
            }

        }

        if (cmdArgs.contains("-in")) {
            error = checkArgument(cmdArgs, "-in");
            if (!error) {
                int indexOfIn = cmdArgs.indexOf("-in");
                String inputFileName = cmdArgs.get(indexOfIn + 1);
                Scanner scanner = new Scanner(new File(inputFileName));
                data = scanner.nextLine();
                scanner.close();
            }
        }
        if (cmdArgs.contains("-data")) {
            error = checkArgument(cmdArgs, "-data");

            if (!error) {
                int indexOfData = cmdArgs.indexOf("-data");
                data = cmdArgs.get(indexOfData + 1);
            }
        }
        if (cmdArgs.contains("-out")) {
            error = checkArgument(cmdArgs, "-out");

            if (!error) {
                int indexOfOut = cmdArgs.indexOf("-out");
                fileOutput = cmdArgs.get(indexOfOut + 1);
            }
        }

        // processing

        if (!error) {
            if (mode.equals("enc")) {
                String encrypted = encrypt(data, key);
                if (fileOutput.equals("")) {
                    System.out.println(encrypted);
                } else {
                    saveToFile(encrypted, fileOutput);
                }
            } else if (mode.equals("dec")) {
                String decrypted = decrypt(data, key);
                if (fileOutput.equals("")) {
                    System.out.println(decrypted);
                } else {
                    saveToFile(decrypted, fileOutput);
                }
            }
        } else {
            System.out.println("Error");
        }
    }

    private static void saveToFile(String text, String fileOutput) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileOutput);
        writer.println(text);
        writer.close();
    }

    private static String encrypt(String text, int key) {
        StringBuilder sb = new StringBuilder(text);
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            char shifted = (char) ((ch + key) % 256);
            sb.setCharAt(i, shifted);

        }
        return sb.toString();
    }
    private static String decrypt(String text, int key) {
        StringBuilder sb = new StringBuilder(text);

        for (int i = 0; i < sb.length(); i++) {
            char ch = sb.charAt(i);
            char reshifted = (char) ((ch- key) % 256);
            sb.setCharAt(i, reshifted);
        }
        return sb.toString();
    }

    private static boolean checkArgument(List<String> cmdArgs, String type) {
        String regex = "-mode|-key|-data|-in|-out";

        int indexOfPara = cmdArgs.indexOf(type);
        if (indexOfPara < cmdArgs.size() - 1) {
            String value = cmdArgs.get(indexOfPara + 1);

            if (!value.matches(regex)) {
                return false;
            }
        }

        return true;
    }

}
