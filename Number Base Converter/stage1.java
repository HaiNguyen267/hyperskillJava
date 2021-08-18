import java.util.*;

class Main {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number in decimal system: ");
        long decimalNumber = sc.nextLong();
        System.out.println("Enter target base: ");
        int base = sc.nextInt();
        StringBuilder sb = new StringBuilder();


        if (base == 2) {
            while (decimalNumber > 0) {
                if (decimalNumber % 2 == 0) {
                    sb.append("0");
                    decimalNumber /= 2;
                }
                else {
                    sb.append(Long.toString(decimalNumber % 2));
                    decimalNumber = (decimalNumber - (decimalNumber % 2)) / 2;
                }

            }
        }
        if (base == 8) {
            while (decimalNumber > 0) {
                if (decimalNumber % 8 == 0) {
                    sb.append("0");
                    decimalNumber /= 8;
                }
                else {
                    sb.append(Long.toString(decimalNumber % 8));
                    decimalNumber = (decimalNumber - (decimalNumber % 8)) / 8;
                }

            }
        }
        if (base == 16) {
            while (decimalNumber > 0) {
                if (decimalNumber % 16 == 0) {
                    sb.append("0");
                    decimalNumber /= 16;
                }
                else {
                    long reminder = decimalNumber % 16;
                    if (reminder <= 9) {
                        sb.append(Long.toString(decimalNumber % 16));
                    }
                    else if (reminder > 9) {
                        String letter = null;

                        switch ((int) reminder) {
                            case 10:
                                letter = "A";
                                break;
                            case 11:
                                letter = "B";
                                break;
                            case 12:
                                letter = "C";
                                break;
                            case 13:
                                letter = "D";
                                break;
                            case 14:
                                letter = "E";
                                break;
                            case 15:
                                letter = "F";
                                break;

                        }

                        sb.append(letter);
                    }
                    decimalNumber = (decimalNumber - (decimalNumber % 16)) / 16;
                }

            }
        }

        System.out.println("Conversion result: " + sb.reverse().toString());

    }

}
