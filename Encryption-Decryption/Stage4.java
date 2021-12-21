package encryptdecrypt;



import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Scanner sc = new Scanner(System.in);
        // initialize the variables
        String mode = "enc";
        String data = "";
        int key = 0;
        
        List<String> cmdArgs = Arrays.asList(args);

        // read the command-line arguments
        if (cmdArgs.contains("-mode")) {
            int indexOfMode = cmdArgs.indexOf("-mode");
            mode = cmdArgs.get(indexOfMode + 1);
        }
        if (cmdArgs.contains("-key")) {
            int indexOfKey = cmdArgs.indexOf("-key");
            key = Integer.parseInt(cmdArgs.get(indexOfKey + 1));
        }
        if (cmdArgs.contains("-data")) {
            int indexOfData = cmdArgs.indexOf("-data");
            data = cmdArgs.get(indexOfData + 1);
        }
        
        
        // processing
        if (mode.equals("enc")) {
            String encrypted = encrypt(data, key);
            System.out.println(encrypted);
        } else if (mode.equals("dec")) {
            String decrypted = decrypt(data, key);
            System.out.println(decrypted);
        }
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


}
