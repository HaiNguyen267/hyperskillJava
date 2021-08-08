import java.util.Scanner;
class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Amazing Numbers!");
        supportedRequest();

        while (true) {
            System.out.println();
            System.out.print("Enter a request: ");
            String inputString = sc.nextLine();
            long number = 0; //represents a starting number
            long quantity = 1; //shows how many consecutive numbers are to be printed
            boolean moreThanOne = false;

            // check the input
            boolean inputIsCorrect = false;
            try {
                //when there are 2 parameters
                if (inputString.contains(" ")) {

                    String[] parts = inputString.split(" ");

                    long para1 =  Long.parseLong(parts[0]);
                    long para2 =  Long.parseLong(parts[1]);


                    if (para1 >= 0 && para2 >= 0) {
                        inputIsCorrect = true;
                    } else if (para1 < 0) {
                        System.out.println("The first parameter should be a natural number or zero.");
                    } else {
                        System.out.println("The second parameter should be a natural number or zero.");
                    }
                }
                // when there is 1 parameter
                else {
                    long para1 =  Long.parseLong(inputString);

                    if (para1 >= 0 ) {
                        inputIsCorrect = true;
                    } else {
                        System.out.println("The first parameter should be a natural number or zero.");
                    }

                }
            }
            // when input is not number
            catch (NumberFormatException e ){
                System.out.println("The first parameter should be a natural number or zero.");
            }

            if (inputIsCorrect) {
                // read the input
                if (inputString.contains(" ")) {
                    String[] parts = inputString.split(" ");
                    number =  Long.parseLong(parts[0]);
                    quantity =  Long.parseLong(parts[1]);
                    moreThanOne = quantity > 1;

                } else if (Long.parseLong(inputString) == 0) {
                    System.out.println("Goodbye!");
                    break;
                }
                else {
                    number = Long.parseLong(inputString);

                }

                // showing properties
                for (long i = number; i < number + quantity; i++) {
                    showProperties(i, moreThanOne);
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
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");

    }

    public static void showProperties(long number, boolean moreThanOne) {
        boolean buzz = false;
        boolean duck = false;
        boolean palindromic = false;
        boolean gapful = false;
        boolean even = false;

        String numberString =  Long.toString(number);
        // buzz or not
        if (number % 7 == 0 || numberString.endsWith("7")) {
            buzz = true;
        }

        // duck or not
        if (numberString.startsWith("0")){
            for (int i = 1; i < numberString.length(); i++) {
                if (numberString.charAt(i) == '0'){
                    duck = true;
                }
            }
        }
        else {
            for (char x : numberString.toCharArray()){
                if (x == '0'){
                    duck = true;
                }
            }
        }

        // palindromic or not
        StringBuilder  sb = new StringBuilder(numberString);
        if (numberString.equals(sb.reverse().toString())){
            palindromic = true;
        }

        // gapful or not
        if (numberString.length() >= 3) {
            char firstDigit = numberString.charAt(0);
            char lastDigit = numberString.charAt(numberString.length() - 1);

            String firstAndLastDigit = String.valueOf(firstDigit) + String.valueOf(lastDigit);
            int gapfulDigit = Integer.parseInt(firstAndLastDigit);

            if ( number % gapfulDigit == 0){
                gapful = true;
            }
        }

        // even or odd
        if (number % 2 == 0){
            even = true;
        }
        
        // print result
        if (moreThanOne){
            String resultBuzz = (buzz) ?  "buzz" : "";
            String resultDuck = (!buzz && duck) ? "duck" : (duck) ? ", duck" : "";
            String resultPalindromic = (!duck && !buzz && palindromic) ? "palindromic" : ((duck || buzz) && palindromic) ? ", palindromic" : "";
            String resultGapful = (!duck && !buzz && !palindromic && gapful) ? "gapful" : ((duck || buzz || palindromic)  && gapful) ? ", gapful" : "";
            String resultEven = (!duck && !buzz && !palindromic && !gapful && even) ? "even" : (!duck && !buzz && !palindromic && !gapful && !even) ? "odd" : ((duck || buzz || palindromic || gapful) && even) ? ", even" : ", odd";
            System.out.println(number + " is "+ resultBuzz + resultDuck + resultPalindromic + resultGapful + resultEven);
        }
        else {
            System.out.println();
            System.out.println("Properties of " + number);
            System.out.println("        buzz: " + buzz);
            System.out.println("        duck: " + duck);
            System.out.println(" palindromic: " + palindromic);
            System.out.println("      gapful: " + gapful);
            System.out.println("        even: " + even);
            System.out.println("         odd: " + !even);
        }
    }

}
