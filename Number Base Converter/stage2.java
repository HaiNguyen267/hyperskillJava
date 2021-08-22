
import java.util.*;

class Main {
    public static void main (String[] args){

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Do you want to convert /from decimal or /to decimal? (To quit type /exit)");
            String requestInput = sc.nextLine();

            if (requestInput.equals("/exit")){
                break;
            }

            if (requestInput.equals("/from")) {
                System.out.println("Enter a number in decimal system:");
                long decimalNumber = sc.nextLong();
                System.out.println("Enter target base: ");
                int base = sc.nextInt();

                StringBuilder resultFormDecimal = new StringBuilder();

                if (base == 2) {
                    while (decimalNumber > 0) {
                        if (decimalNumber % 2 == 0) {
                            resultFormDecimal.append("0");
                            decimalNumber /= 2;
                        } else {
                            resultFormDecimal.append(Long.toString(decimalNumber % 2));
                            decimalNumber = (decimalNumber - (decimalNumber % 2)) / 2;
                        }

                    }
                }
                else if (base == 8) {
                    while (decimalNumber > 0) {
                        if (decimalNumber % 8 == 0) {
                            resultFormDecimal.append("0");
                            decimalNumber /= 8;
                        } else {
                            resultFormDecimal.append(Long.toString(decimalNumber % 8));
                            decimalNumber = (decimalNumber - (decimalNumber % 8)) / 8;
                        }

                    }
                }
                else if (base == 16) {
                    while (decimalNumber > 0) {
                        if (decimalNumber % 16 == 0) {
                            resultFormDecimal.append("0");
                            decimalNumber /= 16;
                        } else {
                            long reminder = decimalNumber % 16;
                            if (reminder <= 9) {
                                resultFormDecimal.append(Long.toString(decimalNumber % 16));
                            } else if (reminder > 9) {
                                String letter = null;

                                switch ((int) reminder) {
                                    case 10:
                                        letter = "a";
                                        break;
                                    case 11:
                                        letter = "b";
                                        break;
                                    case 12:
                                        letter = "c";
                                        break;
                                    case 13:
                                        letter = "d";
                                        break;
                                    case 14:
                                        letter = "e";
                                        break;
                                    case 15:
                                        letter = "f";
                                        break;

                                }

                                resultFormDecimal.append(letter);
                            }
                            decimalNumber = (decimalNumber - (decimalNumber % 16)) / 16;
                        }

                    }
                }

                System.out.println("Conversion result: " + resultFormDecimal.reverse().toString());
            }

           if (requestInput.equals("/to")) {
                System.out.println("Enter source number:");
                String numberInput = sc.nextLine();
                System.out.println("Enter source base: ");
                int sourceBase = sc.nextInt();


                long resultToDecimal = 0;

                if (sourceBase == 2) {
                    StringBuilder sb = new StringBuilder(numberInput);
                    String binaryReversed = sb.reverse().toString();

                    for (int i = 0; i < binaryReversed.length(); i++) {
                        int value = Character.getNumericValue(binaryReversed.charAt(i));
                        resultToDecimal += (value) * Math.pow(2, i);

                    }
                }
                else if (sourceBase == 8) {

                    for (int i = 0; i < numberInput.length() - 1; i++) {
                        int value = Character.getNumericValue(numberInput.charAt(i));
                        resultToDecimal = (resultToDecimal + value) * 8;
                    }
                    resultToDecimal += Character.getNumericValue(numberInput.charAt(numberInput.length() - 1));

                }
                else if (sourceBase == 16) {

                    for (int i = 0; i < numberInput.length() - 1; i++) {
                        int value = 0;

                        if (!Character.isDigit(numberInput.charAt(i))) {

                            switch (numberInput.charAt(i)) {
                                case 'a':
                                    value = 10;
                                    break;
                                case 'b':
                                    value = 11;
                                    break;
                                case 'c':
                                    value = 12;
                                    break;
                                case 'd':
                                    value = 13;
                                    break;
                                case 'e':
                                    value = 14;
                                    break;
                                case 'f':
                                    value = 15;
                                    break;

                            }
                        } else {
                            value = Character.getNumericValue(numberInput.charAt(i));
                        }

                        resultToDecimal = (resultToDecimal + value) * 16;
                    }
                    resultToDecimal += Character.getNumericValue(numberInput.charAt(numberInput.length() - 1));

                }

                System.out.println("Conversion to decimal result: " + resultToDecimal);

            }


        }

    }

}
