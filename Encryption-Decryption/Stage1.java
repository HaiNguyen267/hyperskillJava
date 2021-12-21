package encryptdecrypt;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("we found a treasure!");

        for (int i = 0; i < sb.length(); i++) {
            char ch = sb.charAt(i);
            if (Character.isLetter(ch)) {
                //155 is the sum 2 uppercase letters that are corresponding end position. Eg (A-Z, B-Y)
                //219 is the sum 2 lowerrcase letters that are corresponding end position. Eg (a-z, b-y)
                char counterPart = (char) (155 - (int) ch); // if the character is uppercase

                if (Character.isLowerCase(ch)) {
                    counterPart = (char) (219 - (int) ch);
                }
                sb.setCharAt(i, counterPart);
            }
        }
        System.out.println(sb.toString());

    }
}
