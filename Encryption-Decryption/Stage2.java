package encryptdecrypt;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder(sc.nextLine());

        int shiftKey = Integer.parseInt(sc.nextLine());
        int minLowerCase = 97; // a
        int minUpperCase = 65; // A

        for (int i = 0; i < sb.length(); i++) {
            char ch = sb.charAt(i);
            
            if (Character.isLetter(ch)) {
                int min = minLowerCase; // a

                if (Character.isUpperCase(ch)) {
                    min = minUpperCase; // A
                }
                
                char shifted = (char) (((ch - min + shiftKey) % 26) + min);
                sb.setCharAt(i, shifted);

            }
        }


        System.out.println(sb.toString());

    }
}

