package cinema;
	

	import java.util.Scanner;
	

	public class Cinema {
	  public static void main(String[] args) {
	

	

	

	                int rowNum;
	                int seatNum;
	                int option;
	                int income;
	                int numberOfPurchased = 0;
	

	                String[][] array;
	

	                Scanner sc = new Scanner(System.in);
	

	                System.out.println("Enter the number of rows: ");
	                rowNum = sc.nextInt();
	                System.out.println("Enter the number of seats in each row: ");
	                seatNum = sc.nextInt();
	

	                array = new String [rowNum + 1][seatNum + 1];
	                buildCinema(array);
	                while (true){
	                    System.out.println("1. Show the seats");
	                    System.out.println("2. Buy a ticket");
	                    System.out.println("3. Statistics");
	                    System.out.println("0. Exit");
	                    option = sc.nextInt();
	

	                    if (option == 0){
	                        break;
	                    }
	

	                    switch (option){
	                        case 1:
	                            printingCinema(array);
	                            break;
	                        case 2:
	                            booking(array, rowNum, seatNum);
	                            break;
	                        case 3:
	                            statistic(array, rowNum, seatNum);
	                            break;
	                    }
	                }
	

	

	

	                }
	

	

	            public static void buildCinema(String[][] arr){
	                for (int i = 0; i < arr.length ; i++) {
	                    for (int j = 0; j < arr[i].length ; j++) {
	                        if ( i == 0 && j == 0){
	                            arr[i][j] = " " + " ";
	                        }
	                        else if ( i == 0){
	                            arr[i][j] = Integer.toString(j) + " ";
	                        }
	                        else if ( j == 0){
	                            arr[i][j] = Integer.toString(i) + " ";
	                        }
	                        else {
	                            arr[i][j] = "S ";
	                        }
	                    }
	                }
	            }
	            public static void booking(String [][] arr, int rowNum, int seatNum){
	                Scanner sc = new Scanner(System.in);
	                int price;
	                 System.out.println("Enter a row number:");
	                int rowChosen = sc.nextInt();
	                System.out.println("Enter a seat number in that row:");
	                int seatChosen= sc.nextInt();     
	

	                if ( rowChosen > rowNum || seatChosen > seatNum){
	                    System.out.println("Wrong input");
	                    booking(arr, rowNum, seatNum);
	                }
	                else if ( arr[rowChosen][seatChosen] == "B ") {
	                    System.out.println("That ticket has already been purchased");
	                    booking(arr, rowNum, seatNum);
	                }
	                else {
	                    arr[rowChosen][seatChosen] = "B ";
	                }
	                
	                if( rowChosen > rowNum / 2 && rowNum * seatNum > 60){
	                    price = 8;
	                }
	                else {
	                    price = 10;
	                }
	

	                System.out.println("Ticket price: $" + price);
	            }
	            public static void printingCinema(String [][] arr){
	                System.out.println("Cinema: ");
	                for (int i = 0; i < arr.length; i++) {
	                    for (int j = 0; j < arr[1].length; j++) {
	                        System.out.print(arr[i][j]);
	                    }
	                    System.out.println();
	                }
	            }
	            public static void statistic(String [][] arr, int rowNum, int seatNum){
	                int purchasedSeatNum = 0;
	                int currenIncome = 0;
	                int price;
	                int totalIncome;
	                float percentage;
	

	                for (int i = 0; i < arr.length; i++) {
	                    for (int j = 0; j < arr[1].length; j++) {
	                        if (arr[i][j] == "B "){
	                            purchasedSeatNum += 1;
	                            if(i > rowNum / 2  && rowNum * seatNum > 60){
	                                price = 8;
	                            }
	                            else{
	                                price = 10;
	                            }
	                            currenIncome += price;
	                    }
	                }
	            }
	                System.out.println("Number of purchased tickets: " + purchasedSeatNum);
	                percentage =  (float) purchasedSeatNum * 100 / (float)(rowNum * seatNum);
	

	                System.out.printf("Percentage: %.2f", percentage);
	                System.out.println("%");
	                System.out.println("Current income: $" + currenIncome);
	

	                if( rowNum * seatNum <= 60){
	                    totalIncome = rowNum * seatNum * 10;
	                }
	                else {
	                    int frontRow = rowNum / 2;
	                    int backRow = rowNum - frontRow;
	                    totalIncome = (frontRow * seatNum * 10) + (backRow * seatNum * 8);
	                }
	                System.out.println("Total income: $" + totalIncome);
	            }
	

	

	

	}

