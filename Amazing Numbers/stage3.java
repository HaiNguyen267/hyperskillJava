import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        boolean even;
        boolean buzz;
        boolean duck;
        boolean palindromic;
        String numberStringReversed;

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter 0 to exit.");


        while (true){
            even = false;
            buzz  = false;
            duck = false;
            palindromic = false;

            numberStringReversed = "";
            System.out.println();
            System.out.print("Enter a request: ");
            long number = sc.nextLong();
            System.out.println();
            String numberString = Long.toString(number);

            if ( number < 0 ){
                System.out.println("The first parameter should be a natural number or zero.");
            }
            if ( number == 0 ){
                System.out.println("Goodbye!");
                break;
            }
            if ( number > 0){

                // even, odd
                if( number % 2 == 0){
                    even = true;
                }



                // buzz  not
                if (number % 7 == 0 || number % 10 == 7) {
                    buzz = true;
                }


                // duck or not
                if( numberString.startsWith("0")){
                    for (int i = 1; i < numberString.length() ; i++) {
                        if( numberString.charAt(i) == '0' ){
                            duck = true;
                        }
                    }
                }
                else {
                    for( char x : numberString.toCharArray()){
                        if( x == '0'){
                            duck = true;
                        }

                    }
                }

                // palindromic or not
                for (int i = numberString.length() - 1; i >= 0 ; i--) {
                    numberStringReversed += numberString.charAt(i);
                }
                if ( numberStringReversed.equals(numberString)){
                    palindromic = true;
                }



                // print result
                System.out.println("Properties of " + number);
                System.out.println("        even: " + even);
                System.out.println("         odd: " + !even);
                System.out.println("        buzz: " + buzz);
                System.out.println("        duck: " + duck);
                System.out.println(" palindromic: " + palindromic);
            }


        }




    }


}
