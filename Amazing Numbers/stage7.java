
import java.util.*;

class Main {

    public static void main (String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        supportedRequest();

        while (true) {
            System.out.println();
            System.out.print("Enter a request: ");
            String inputString = sc.nextLine().toLowerCase(Locale.ROOT);

            /** READ THE INPUT **/
            String[] parts = inputString.split(" ");
            long number = 0; //represents a starting number
            long quantity = 1; //shows how many consecutive numbers are to be printed
            boolean firstParaIsCorrect = false;
            boolean secondParaIsCorrect = false;
            boolean inputIsCorrect = false;
            List<String> availablePropertyMode =
                    Arrays.asList("even", "odd", "buzz", "duck", "palindromic", "gapful", "spy", "square", "sunny", "jumping");

            List<String> listOfProperties = new ArrayList<String>();
            List<String> listOfIncorrectPropertyPara = new ArrayList<String>();

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
                inputIsCorrect = false;
                try {
                    quantity = Long.parseLong(parts[1]);

                    if (quantity > 0) {
                        secondParaIsCorrect = true;
                        inputIsCorrect = true;
                    }
                    else {
                        secondParaIsCorrect = false;
                        System.out.println("The second parameter should be a natural number or zero.");
                    }
                }
                catch  (NumberFormatException e){
                    secondParaIsCorrect = false;
                    inputIsCorrect = false;
                    System.out.println("The second parameter should be a natural number or zero.");
                }
            }

            // when there are >= 3 parameters
            // from third parameters, all parameters refer to property mode
            // this code below read all the property parameters
            // firstly, it checks whether user types correct properties or not
            // secondly, check the mutually exclusive variables
            if (parts.length >= 3 && firstParaIsCorrect && secondParaIsCorrect) {
                inputIsCorrect = false;

                // read the parameters
                for (int i = 2; i < parts.length; i++){
                    listOfProperties.add(parts[i]);
                }
                /** check the correct of the parameters*/
                boolean propertyParaIsCorrect = false;
                // put all incorrect property parameters into a list
                for (String p : listOfProperties){
                    if (!availablePropertyMode.contains(p)){
                        listOfIncorrectPropertyPara.add(p);
                    }
                }
                // if a list of incorrect property parameters has variables
                if (listOfIncorrectPropertyPara.size() == 0) {
                    propertyParaIsCorrect = true;
                } else {
                    Object [] arrayOfIncorrectPropertyPara= listOfIncorrectPropertyPara.toArray();
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < arrayOfIncorrectPropertyPara.length - 1; i++){
                        sb.append(arrayOfIncorrectPropertyPara[i] + ", ");
                    }


                    sb.append(arrayOfIncorrectPropertyPara[(arrayOfIncorrectPropertyPara.length - 1)]);
                    // when there is only 1 property parameter is wrong
                    if (arrayOfIncorrectPropertyPara.length == 1) {
                        System.out.println("The property [" + sb.toString().toUpperCase() + "] is wrong.");

                    }
                    else {
                        System.out.println("The properties [" + sb.toString().toUpperCase() + "] are wrong.");
                    }
                    System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]");
                }

                /** if all parameter are correct, then check the mutually exclusive variables*/
                if (propertyParaIsCorrect) {
                    boolean mutuallyExclusive = false;
                    if (listOfProperties.contains("even") && listOfProperties.contains("odd")) {
                        System.out.println("The request contains mutually exclusive properties: [ODD, EVEN]");
                        System.out.println("There are no numbers with these properties.");
                        mutuallyExclusive = true;
                    }
                    if (listOfProperties.contains("duck") && listOfProperties.contains("spy")) {
                        System.out.println("The request contains mutually exclusive properties: [DUCK, SPY]");
                        System.out.println("There are no numbers with these properties.");
                        mutuallyExclusive = true;
                    }
                    if (listOfProperties.contains("sunny") && listOfProperties.contains("square")) {
                        System.out.println("The request contains mutually exclusive properties: [SUNNY, SQUARE]");
                        System.out.println("There are no numbers with these properties.");
                        mutuallyExclusive = true;
                    }

                    if (!mutuallyExclusive ) {
                        inputIsCorrect = true;
                    }
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
                    showProperties(number, quantity, listOfProperties);
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
        System.out.println("- two natural numbers and two properties to search for;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");

    }

    public static void showProperties (long number, boolean horizontallyPrinting){
        boolean buzz = isBuzz(number);
        boolean duck = isDuck(number);
        boolean palindromic = isPalindromic(number);
        boolean gapful = isGapful(number);
        boolean even = isEven(number);
        boolean spy = isSpy(number);
        boolean sunny = isSunny(number);
        boolean square = isSquare(number);
        boolean jumping = isJumping(number);

        if (horizontallyPrinting){
            ArrayList <String> results = new ArrayList<String>();
            if (buzz){
                results.add("buzz ,");
            }
            if (duck){
                results.add("duck, ");
            }
            if (palindromic){
                results.add("palindromic, ");
            }
            if (gapful){
                results.add("gapful, ");
            }
            if (spy){
                results.add("spy, " );
            }
            if (square){
                results.add("square, ");
            }
            if (sunny){
                results.add("sunny, ");
            }
            if (jumping){
                results.add("jumping, ");
            }
            if (even){
                results.add("even");
            }
            else {
                results.add("odd");
            }

            String listOfResult = "";
            for (String str: results) {
                listOfResult += str;
            }
            System.out.println(number + " is " + listOfResult);
        }
        else {
            System.out.println();
            System.out.println("Properties of " + number);
            System.out.println("        buzz: " + buzz);
            System.out.println("        duck: " + duck);
            System.out.println(" palindromic: " + palindromic);
            System.out.println("      gapful: " + gapful);
            System.out.println("         spy: " + spy);
            System.out.println("      square: " + square);
            System.out.println("       sunny: " + sunny);
            System.out.println("     jumping:" + jumping);
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

    public static void showProperties (long number, long quantity, List lisOfProperties) {
        int times = 0;
        while (times < quantity) {
            boolean numberIsSatisfied = false;
            int counter = 0;
            for (int i = 0; i < lisOfProperties.size(); i++){
                if (isCertainType(number, (String) lisOfProperties.get(i))){
                    numberIsSatisfied = true;
                    counter ++;
                }


            }

            if (counter == lisOfProperties.size() ) {
                times ++;
                showProperties(number, true);
            }
            number ++;
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

    public static boolean isSunny(long number) {
        boolean isSunnyNumber = false;

        if (number > 2) {
            double x = Math.sqrt(number + 1);
            int square = (int) x * (int)x;
            if (square == number + 1) {
                isSunnyNumber = true;
            }
        }
        return isSunnyNumber;
    }

    public static boolean isSquare (long number) {
        boolean isSquareNumber = false;

        double x = Math.sqrt(number);
        double square = (int) x * (int) x;
        if (square == number){
            isSquareNumber = true;
        }

        return isSquareNumber;
    }

    public static boolean isJumping (long number) {
        boolean isJumpingNumber = false;

        String numberString = Long.toString(number);
        int counter = 0;
        for (int i = 0; i < numberString.length() - 1; i ++){
            long num1 = Character.getNumericValue(numberString.charAt(i));
            long num2 = Character.getNumericValue(numberString.charAt(i+1));

            if (num1 + 1 == num2 || num1 - 1 == num2){
                counter ++;
            }
        }
        if (counter == numberString.length() - 1){
            isJumpingNumber = true;
        }

        return isJumpingNumber;
    }

    public static boolean isCertainType (long number, String propertyMode){
        boolean isThatType = false;

        if (propertyMode.equals("even")){
            if(isEven(number)){
                isThatType = true;
            }
        }
        else if (propertyMode.equals("odd")){
            if(!isEven(number)){
                isThatType = true;
            }
        }
        else if (propertyMode.equals("buzz")){
            if(isBuzz(number)){
                isThatType = true;
            }
        }
        else if (propertyMode.equals("duck")){
            if(isDuck(number)){
                isThatType = true;
            }
        }
        else if (propertyMode.equals("palindromic")){
            if(isPalindromic(number)){
                isThatType = true;
            }
        }
        else if (propertyMode.equals("gapful")){
            if(isGapful(number)){
                isThatType = true;
            }
        }
        else if (propertyMode.equals("spy")){
            if(isSpy(number)){
                isThatType = true;
            }
        }
        else if (propertyMode.equals("sunny")){
            if(isSunny(number)){
                isThatType = true;
            }
        }
        else if (propertyMode.equals("square")){
            if(isSquare(number)){
                isThatType = true;
            }
        }
        else if (propertyMode.equals("jumping")){
            if (isJumping(number)){
                isThatType = true;
            }
        }
        return isThatType;
    }

}
