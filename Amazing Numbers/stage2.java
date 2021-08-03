import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        boolean even = false;
        boolean buzz  = false;
        boolean duck = false;
        boolean natural = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a natural number:");
        int number = sc.nextInt();
        String numberString = Integer.toString(number);


        // Natural, Even or odd, buzz or not, duck or not
        if ( number > 0){
            natural = true;

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
        }

        // print results
        if( natural == true){
            System.out.println("Properties of " + number);
            System.out.println("        even:"+  even);
            System.out.println("         odd:" + !even);
            System.out.println("        buzz:" + buzz);
            System.out.println("        duck:" + duck);
        }
        else{
            System.out.println("This number is not natural!");
        }
    }
}

