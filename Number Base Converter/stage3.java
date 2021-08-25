
import java.math.BigInteger;
import java.util.*;

class Main {
    public static void main (String[] args) {
        /** input base -> base 10 -> requested base **/
        while (true) {
            boolean backToPreviousChoice = false;
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter two numbers in format: {source base} {target base} (To quit type /exit): ");
            String input = sc.nextLine();
            if (input.equals("/exit")) {
                break;
            }

            if (backToPreviousChoice) {
                continue;
            }

            else {

                String[] parts = input.split(" ");
                int baseToDecimal = Integer.parseInt(parts[0]);
                int decimalToBase = Integer.parseInt(parts[1]);

                while (!backToPreviousChoice) {
                    System.out.println("Enter number in base " + baseToDecimal + " to convert to base " + decimalToBase + " (To go back type /back) ");
                    String numberStringInput = sc.nextLine();

                    if (numberStringInput.equals("/back")) {
                        backToPreviousChoice = true;
                    }
                    else {
                        long temp = toDecimal(baseToDecimal, numberStringInput);
                        String conversionResult = fromDecimal(temp, decimalToBase);
                        System.out.println("Conversion result: " + conversionResult);

                    }
                }
            }
        }
    }
    

    public static long toDecimal (int courseBase, String numberString) {
        StringBuilder sb = new StringBuilder();
        long sum = 0;
        int length = numberString.length();
        for (int i = 0; i < length - 1; i++) {
            int value = 0;
            if (!Character.isDigit(numberString.charAt(i))) {
                value = numberString.charAt(i) - 97 + 10;
            }
            else {
                value = Character.getNumericValue(numberString.charAt(i));
            }
            sum = (sum + value) * courseBase;
        }
        if (!Character.isDigit(numberString.charAt(length - 1))) {
            int value = numberString.charAt(length - 1) - 97 + 10;
            sum += value;
        }
        else {
            int value = Character.getNumericValue(numberString.charAt(length - 1));
            sum += value;
        }
        return sum;
    }

    public static String fromDecimal (long number, int base) {
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            long reminder = number % base;

            if (reminder == 0) {
                sb.append("0");
                number /= base;
            }
            else {
                if (reminder > 9) {
                    char ch = (char) (97 + (reminder - 10));
                    sb.append(ch);
                    number = (number - reminder) / base;
                }
                else {
                    sb.append(reminder);
                    number = (number - reminder) / base;
                }
            }
        }
        return sb.reverse().toString();
    }

}
