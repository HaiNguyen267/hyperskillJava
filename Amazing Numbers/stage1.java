import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a natural number:");
        int number = sc.nextInt();

        // Not natural
        if( number <= 0){
            System.out.println("This number is not natural!");

        }


        // Even and odd
        if ( number > 0){
            if( number % 2 == 0){
                System.out.println("This number is Even.");
            }
            else {
                System.out.println("This number is Odd.");
            }

            // Is a fuzz number or not and explanation
            if (number % 7 == 0 || number % 10 == 7){
                System.out.println("It is a Buzz number.");
                System.out.println("Explanation: ");
                if ( number % 7 == 0 && number % 10 == 7){
                    System.out.println(number + " is divisible by 7 and ends with 7.");
                 }
                else if (number % 7 == 0){
                    System.out.println(number + " is divisible by 7.");
                }
                else{
                    System.out.println(number + " ends with 7.");
                }
            }
            else {
                System.out.println("It is not a Buzz number.");
                System.out.println("Explanation: ");
                System.out.println(number + " is neither divisible by 7 nor does it end with 7.");
            }
        }

    }
}
