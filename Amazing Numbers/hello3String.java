import java.util.Scanner;
class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        int n = 999999999;// if the input is correct n will change
        // otherwise n is that number unchanged input will be printed
        String numberString = "";
        String firstHalf = "";
        String lastHalf = "";

        // read the number in the string
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                n = Character.getNumericValue(input.charAt(i));
            }

        }

        if (n < input.length() && n != 0) {

            String inputReplaced = input.replace(numberString, "").trim();

            // first half of the string
            for (int i = 0; i < n; i++) {
                firstHalf += inputReplaced.charAt(i);
            }

            // last half of the string
            lastHalf = inputReplaced.replace(firstHalf, "");

            // printing string after processing
            System.out.println(lastHalf + firstHalf);
        }
        else if (n == 0){
            String inputReplaced = input.replace(numberString, "").trim();
            System.out.println(inputReplaced);
        }
        else {
            System.out.println(input);
        }

    }
}
