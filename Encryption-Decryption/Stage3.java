package encryptdecrypt;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String mode = sc.nextLine();
        String text = sc.nextLine();
        int key = Integer.parseInt(sc.nextLine());

        if (mode.equals("enc")) {
            String encrypted = encrypt(text, key);
            System.out.println(encrypted);
        } else if (mode.equals("dec")) {
            String decrypted = decrypt(text, key);
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

