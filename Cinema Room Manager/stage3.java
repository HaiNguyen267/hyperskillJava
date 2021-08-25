package cinema;
	import java.util.Scanner;
	public class Cinema {
	 public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	

	        System.out.println("Enter the number of rows:");
	        int rowNum = sc.nextInt();
	        System.out.println("Enter the number of seats in each row:");
	        int seatNum = sc.nextInt();
	

	        int frontRow = rowNum / 2;
	        int totalSeat = rowNum * seatNum;
	        int price = 10;
	

	

	        // print cinema 1
	        System.out.println("Cinema: ");
	        for (int i = 0; i < rowNum + 1; i++) {
	            for (int j = 0; j < seatNum + 1; j++) {
	                if(i == 0 && j == 0){
	                    System.out.print(" " + " ");;
	                }
	                else if(i == 0){
	                    System.out.print(j + " ");
	                }
	                else if(j == 0){
	                    System.out.print(i + " ");;
	                }
	

	                else {
	                    System.out.print("S" + " "); ;
	                }
	

	

	            }
	            System.out.println();
	        }
	

	        // booking
	        System.out.println("Enter a row number:");
	        int chosenRow = sc.nextInt();
	        System.out.println("Enter a seat number in that row:");
	        int chosenSeat = sc.nextInt();
	

	                // calculate the price
	

	  
	      if(totalSeat > 60 && chosenRow <= frontRow){
	            price = 10;
	        }
	       else if (totalSeat > 60 ){
	           price = 8;
	        }
	        System.out.println("Ticket price: $" + price);
	

	        // print cinema2
	        System.out.println("Cinema: ");
	        for (int i = 0; i < rowNum + 1; i++) {
	            for (int j = 0; j < seatNum + 1; j++) {
	                if(i == 0 && j ==0){
	                    System.out.print(" ");;
	                }
	                else if(i == 0){
	                    System.out.print(j + " ");
	                }
	                else if(j == 0){
	                    System.out.print(i + " ");;
	                }
	

	

	                else if (i == chosenRow && j == chosenSeat){
	                    System.out.print("B "); ;
	                }
	                else {
	                    System.out.print("S ");
	                }
	

	

	            }
	            System.out.println();
	        }
	

	

	

	    }
	}

