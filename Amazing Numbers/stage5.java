import java.util.Locale;
import java.util.Scanner;
class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Amazing Numbers!");
        supportedRequest();

        while (true) {
            System.out.println();
            System.out.print("Enter a request: ");
            String inputString = sc.nextLine().toLowerCase(Locale.ROOT);

            /** READ THE INPUT **/
            String[] parts = inputString.split(" ");
            long number = 0; //represents a starting number
            long quantity = 1; //shows how many consecutive numbers are to be printed
            String propertyMode = null;
            boolean firstParaIsCorrect = false;
            boolean secondParaIsCorrect = false;
            boolean thirdParaIsCorrect = false;

            boolean inputIsCorrect = false;


            /** CHECK THE INPUT IS CORRECT OR NOT **/
            //check the first parameter when there are >= 1 parameters
            if (parts.length >= 1){

                try {
                    number = Long.parseLong(parts[0]);

                    if (number > 0) {
                        firstParaIsCorrect = true;
                        inputIsCorrect = true;
                    }
                    else if (number == 0){
                        System.out.println("Goodbye!");
                        break;
                    }
                    else {
                        System.out.println("The first parameter should be a natural number or zero.");
                    }
                }
                catch  (NumberFormatException e){
                    firstParaIsCorrect = false;
                    inputIsCorrect = false;
                    System.out.println("The first parameter should be a natural number or zero.");
                }
            }
            // check the second parameter when there are >= 2 parameters
            if (parts.length >= 2 && firstParaIsCorrect){
                try {
                    quantity = Long.parseLong(parts[1]);

                    if (quantity > 0) {
                        secondParaIsCorrect = true;
                        inputIsCorrect = true;
                    }
                    else {
                        secondParaIsCorrect = false;
                        inputIsCorrect = false;
                        System.out.println("The second parameter should be a natural number or zero.");
                    }
                }
                catch  (NumberFormatException e){
                    secondParaIsCorrect = false;
                    inputIsCorrect = false;
                    System.out.println("The second parameter should be a natural number or zero.");
                }
            }
            // check the third parameter when there are >= 3 parameters
            if (parts.length == 3 && firstParaIsCorrect && secondParaIsCorrect){

                    propertyMode = parts[2];
                    if (propertyMode.equals("even") || propertyMode.equals("odd") || propertyMode.equals("buzz") || propertyMode.equals("duck")|| propertyMode.equals("palindromic") || propertyMode.equals("gapful")|| propertyMode.equals("spy")) {

                        thirdParaIsCorrect = true;
                        inputIsCorrect = true;
                    }
                    else {
                        inputIsCorrect = false;
                        System.out.println("The property [" + propertyMode.toUpperCase() + "] is wrong.");
                        System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY]");
                    }


            }

            /** SHOW THE PROPERTIES AS REQUESTED **/
            if (inputIsCorrect){

                if (parts.length == 1){
                    showProperties(number, false);
                }

                if (parts.length == 2){
                    showProperties(number, quantity);
                }

                if (parts.length >= 3){
                    showProperties(number, quantity, propertyMode);
                }
            }
        }
    }

    public static void supportedRequest() {
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("   * the first parameter represents a starting number;");
        System.out.println("   * the second parameters show how many consecutive numbers are to be processed;");
        System.out.println("- two natural numbers and a property to search for;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");

    }

    public static void showProperties (long number, boolean verticallyPrinting){
        boolean buzz = isBuzz(number);
        boolean duck = isDuck(number);
        boolean palindromic = isPalindromic(number);
        boolean gapful = isGapful(number);
        boolean even = isEven(number);
        boolean spy = isSpy(number);

        if (verticallyPrinting){
            String resultBuzz = (buzz) ?  "buzz" : "";
            String resultDuck = (!buzz && duck) ? "duck" : (duck) ? ", duck" : "";
            String resultPalindromic = (!duck && !buzz && palindromic) ? "palindromic" : ((duck || buzz) && palindromic) ? ", palindromic" : "";
            String resultGapful = (!duck && !buzz && !palindromic && gapful) ? "gapful" : ((duck || buzz || palindromic)  && gapful) ? ", gapful" : "";
            String resultSpy =  (!duck && !buzz && !palindromic && !gapful) ? "spy" : ((duck || buzz || palindromic || gapful)  && spy) ? ", spy" : "";
            ;
            String resultEven = (!duck && !buzz && !palindromic && !gapful && !spy && even) ? "even" : (!duck && !buzz && !palindromic && !gapful && !spy && !even) ? "odd" : ((duck || buzz || palindromic || gapful || spy) && even) ? ", even" : ", odd";
            System.out.println(number + " is "+ resultBuzz + resultDuck + resultPalindromic + resultGapful + resultSpy+ resultEven);
        }
        else {
            System.out.println();
            System.out.println("Properties of " + number);
            System.out.println("        buzz: " + buzz);
            System.out.println("        duck: " + duck);
            System.out.println(" palindromic: " + palindromic);
            System.out.println("         spy: " + spy);
            System.out.println("      gapful: " + gapful);
            System.out.println("        even: " + even);
            System.out.println("         odd: " + !even);
        }
    }

    public static void showProperties (long number, long quantity){
        long time = 0;

        while ( time < quantity){
            showProperties(number, true);
            number ++;
            time ++;
        }
    }

    public static void showProperties (long number, long quantity, String propertyMode){
        long time = 0;

        while ( time < quantity){

            if (propertyMode.equals("even")){
                if (isEven(number)){
                    showProperties(number, true);
                    time++;
                    number += 2; // the next number will be even again
                }
                else {
                    number++; // if number is odd. it plus 1 to be even,
                    // when it is even, it plus 2 to always be even
                }
            }

            if (propertyMode.equals("odd")){
                if (!isEven(number)){
                    showProperties(number, true);
                    time++;
                    number += 2; // the next number will be odd again
                }
                else {
                    number++; // if number is even. it plus 1 to be odd,
                    // when it is odd, it plus 2 to always be odd
                }
            }

            if (propertyMode.equals("buzz")){
                if (isBuzz(number)){
                    showProperties(number, true);
                    time++;
                }
                number++;
            }

            if (propertyMode.equals("duck")){
                if (isDuck(number)){
                    showProperties(number, true);
                    time++;
                }
                number++;
            }

            if (propertyMode.equals("palindromic")){
                if (isPalindromic(number)){
                    showProperties(number, true);
                    time++;
                }
                number++;
            }

            if (propertyMode.equals("gapful")){
                if (isGapful(number)){
                    showProperties(number, true);
                    time++;
                }
                number++;
            }

            if (propertyMode.equals("spy")){
                if (isSpy(number)){
                    showProperties(number, true);
                    time++;
                }
                number++;
            }
        }
    }

    public static boolean isEven (long number){
        boolean isEvenNumber = false;

        if (number % 2 == 0){
            isEvenNumber = true;
        }
        return isEvenNumber;
    }

    public static boolean isBuzz (long number){
        boolean isBuzzNumber = false;

        String numberString =  Long.toString(number);
        // buzz or not
        if (number % 7 == 0 || numberString.endsWith("7")) {
            isBuzzNumber = true;
        }
        return isBuzzNumber;
    }

    public static boolean isDuck (long number){
        boolean isDuckNumber = false;

        String numberString =  Long.toString(number);
        // duck or not
        if (numberString.startsWith("0")){
            for (int i = 1; i < numberString.length(); i++) {
                if (numberString.charAt(i) == '0'){
                    isDuckNumber = true;
                }
            }
        }
        else {
            for (char x : numberString.toCharArray()){
                if (x == '0'){
                    isDuckNumber = true;
                }
            }
        }
        return isDuckNumber;
    }

    public static boolean isPalindromic (long number){
        boolean isPalindromicNumber = false;

        String numberString =  Long.toString(number);
        StringBuilder sb = new StringBuilder(numberString);
        if (numberString.equals(sb.reverse().toString())){
            isPalindromicNumber = true;
        }
        return isPalindromicNumber;
    }

    public static boolean isGapful (long number){
        boolean isGapfulNumber = false;
        String numberString =  Long.toString(number);

        if (numberString.length() >= 3) {
            char firstDigit = numberString.charAt(0);
            char lastDigit = numberString.charAt(numberString.length() - 1);

            String firstAndLastDigit = String.valueOf(firstDigit) + String.valueOf(lastDigit);
            int gapfulDigit = Integer.parseInt(firstAndLastDigit);

            if ( number % gapfulDigit == 0){
                isGapfulNumber = true;
            }
        }

        return isGapfulNumber;
    }

    public static boolean isSpy (long number) {
        boolean isSpyNumber = false;
        long sum = 0;
        long production = 1;

        String numberString = Long.toString(number);

        for (char x: numberString.toCharArray()) {
            sum += Character.getNumericValue(x);
        }

        for (char x: numberString.toCharArray()) {
            production *= Character.getNumericValue(x);
        }

        if (sum == production){
            isSpyNumber = true;
        }
        return isSpyNumber;
    }
}
