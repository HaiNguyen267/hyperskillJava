

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;

class Main {
    public static void main (String[] args)  {
        // input base -> base 10 -> requested base
        while (true) {
            boolean backToPreviousChoice = false;
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter two numbers in format: {source base} {target base} (To quit type /exit): ");
            String input = sc.nextLine();
            if (input.equals("/exit")) {
                break;
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
                        String temp = toDecimal(numberStringInput, baseToDecimal);
                        String conversionResult = fromDecimal(temp, decimalToBase);
                        System.out.println("Conversion result: " + conversionResult);

                    }
                }
            }
        }
    }
    public static String toDecimal (String numberString, int base) {
        boolean containsFractional = false;
        StringBuilder resultDecimal = new StringBuilder();
        StringBuilder resultFractional = new StringBuilder("0.00000");
        String decPart = null;

        // convert the FRACTIONAL PART from source base to decimal (base 10)
        // the default fractional part for the final decimal number is .00000
        if (numberString.contains(".")) {
            String[] parts = numberString.split("\\.");
            decPart = parts[0];
            String fracPart = parts[1];
            containsFractional = true;

            // last digit of fractional part
            double fracConvertedToDecimal = valueOfChar(fracPart.charAt(fracPart.length() - 1));

            // convert the fractional part
            for (int i = fracPart.length() - 2; i >= 0; i--) {
                int value = valueOfChar(fracPart.charAt(i));
                fracConvertedToDecimal = (fracConvertedToDecimal + value) / base;
            }
            resultFractional = new StringBuilder(Double.toString(fracConvertedToDecimal));

            // remove the first charater "0", by default the length of fractional part is at least 5
            resultFractional.deleteCharAt(0);
            if (resultFractional.length() < 5){
                while (resultFractional.length() < 5){
                    resultFractional.append("0");
                }
            }

        }
        else {
            decPart = numberString;
        }


        // convert the DECIMAL PART from source base to decimal (base 10)
        long sum = 0;
        for (int i = 0; i < decPart.length() - 1; i++) {
            int value = valueOfChar(decPart.charAt(i));
            sum = (sum + value ) * base ;
        }
        sum += valueOfChar(decPart.charAt(decPart.length() - 1)); // plus the last digit
        resultDecimal = new StringBuilder(Long.toString(sum));


        // this is the FINAL decimal number with decimal part and fractional part
        if (!containsFractional){
            return resultDecimal.toString();
        }
        return resultDecimal.append(resultFractional.toString()).toString();

  }
    public static String fromDecimal (String decimalString, int base) {
        boolean containsFractional = false;
        //System.out.println("decimal string" + decimalString);
        String decPart = null;
        StringBuilder resultDecPart = new StringBuilder();
        StringBuilder resultFracPart = new StringBuilder();

        if (decimalString.contains(".")){

            String[] parts = decimalString.split("\\.");
            decPart = parts[0];
            String fracPart = "0." + parts[1];

            containsFractional= true;

            // convert FRACTIONAL PART from decimal to requested base
            double fracNum = Double.parseDouble(fracPart);
            StringBuilder storeFrom6thFractional = new StringBuilder();
            int valueOf6thFractional = 0;

            while (fracNum != 0.0 && resultFracPart.length() < 10 && storeFrom6thFractional.length() < 5) {
                fracNum = fracNum * base;
               // System.out.println("fraNum : " + fracNum);
                //System.out.println("(int) fracNum: "  + (int) fracNum );

                if (resultFracPart.length() < 5) {
                    resultFracPart.append( charOfValue((int) fracNum)); // (int)fracNum is the integer part
                }
                else if (resultFracPart.length() >= 5) {
                    storeFrom6thFractional.append(charOfValue((int) fracNum));
                    for (int i = storeFrom6thFractional.length() - 1; i >= 1; i--){
                        if (valueOfChar(storeFrom6thFractional.charAt(i)) > base / 2) {
                            storeFrom6thFractional.setCharAt(i-1, charOfValue(valueOfChar(storeFrom6thFractional.charAt(i) ) + 1));

                        }

                    }
                    valueOf6thFractional = valueOfChar(storeFrom6thFractional.charAt(0));
                }

                if (fracNum > 1) {
                 //   System.out.println("FracNum > 1 :" + fracNum);
                    fracNum = fracNum - (int)fracNum;
                }

                if (valueOf6thFractional > base / 2) {
                    resultFracPart.setCharAt(4, charOfValue(valueOfChar(resultFracPart.charAt(4)) + 1));
                }
               // System.out.println("length: " + resultFracPart.length());
            }

            if (resultFracPart.length() < 5) {
                while (resultFracPart.length() < 5) {
                    resultFracPart.append("0");
                }
            }
        }
        else {
            decPart = decimalString;
        }

        // convert DECIMAL PART from decimal to requested base
        long decNum = Long.parseLong(decPart);
        while (decNum > 0) {
            int reminder = (int)(decNum % base);

            if (reminder == 0) {
                resultDecPart.append("0");
                decNum /= base;
            }
            else {
                if (reminder > 9) {
                    resultDecPart.append(charOfValue(reminder));
                }
                else {
                    resultDecPart.append(reminder);
                }
                decNum = (decNum - reminder) / base;
            }
        }

        if (!containsFractional) {
            return resultDecPart.reverse().toString();
        }
        return resultDecPart.reverse().append(".").append(resultFracPart.toString()).toString();
    }
    public static char charOfValue (int value) {
        char ch = 0;
        if (value > 9) {
            ch = (char) (value + 97 - 10);
        }
        else {
            ch = (char)(value+ '0');
        }
        return ch;
    }
    public static int valueOfChar (char digit) {
        int value = 0;
        if (!Character.isDigit(digit)) {
            value = digit - 97 + 10;
        }
        else {
            value = digit - 48;
        }
        return value;
    }

}
