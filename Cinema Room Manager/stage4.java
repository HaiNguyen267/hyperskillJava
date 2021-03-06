package cinema;
	

	import java.util.Scanner;
	

	public class Cinema {
	

	    public static void main(String[] args) {
	

	        Scanner sc = new Scanner(System.in);
	        int rows;
	        int seats;
	        int option;
	        String[][] cinemaRoom;
	

	        System.out.println("Enter the number of rows:");
	        rows = sc.nextInt() + 1;
	

	        System.out.println("Enter the number of seats in each row:");
	        seats = sc.nextInt() + 1;
	

	        cinemaRoom = new String[rows][seats];
	        buildCinema(cinemaRoom);
	

	

	        while (true) {
	

	            System.out.println("1. Show the seats");
	            System.out.println("2. Buy a ticket");
	            System.out.println("0. Exit");
	

	            option = sc.nextInt();
	            if (option == 0) {    
	                break;
	            }
	            switch(option) {
	                case 1:
	                    printCinema(cinemaRoom);
	                    break;
	                case 2:
	                    buyTicket(cinemaRoom, rows, seats);
	                    break;
	            }
	        }
	    }
	

	    public static void buildCinema(String[][] arr) {
	        for (int i = 0; i < arr.length; i++){
	            for (int j = 0; j < arr[i].length; j++) {
	                if (i == 0 && j != 0) {
	                    arr[i][j] = String.valueOf(j);
	                } else if (i != 0 && j == 0) {
	                    arr[i][j] = String.valueOf(i);
	                } else if (i != 0 && j >= 1 && j <= arr[i].length) {
	                    arr[i][j] = "S";
	                } else if (i == 0 && j == 0) {
	                    arr[i][j] = " ";
	                }
	            }
	        }
	    }
	

	    public static void printCinema(String[][] arr) {
	        System.out.println("Cinema:");
	        for (int i = 0; i < arr.length; i++) {
	            for (int j = 0; (arr[i] != null && j < arr[i].length); j++) {
	                System.out.print(arr[i][j] + " ");
	            }
	            System.out.println();
	        }
	    }
	

	    public static void buyTicket(String[][] arr, int rows, int seats){
	

	        Scanner sc = new Scanner(System.in);
	        int ticket;
	        int specificRow;
	        int specificSeat;
	        int totalSeats;
	

	        System.out.println("Enter a row number:");
	        specificRow = sc.nextInt();
	        System.out.println("Enter a seat number in that row:");
	        specificSeat = sc.nextInt();
	

	        totalSeats = (rows - 1) * (seats - 1);
	

	        for (int i = 0; i < arr.length; i++) {
	            for (int j = 0; j < arr[i].length; j++) {
	                if (i == specificRow && j == specificSeat) {
	                    arr[i][j] = "B";
	                }
	            }
	        }
	

	        if (totalSeats > 60) {
	            if (specificRow < rows / 2) {
	                ticket = 10;
	            } else {
	                ticket = 8;
	            }
	        } else {
	            ticket = 10;
	        }
	

	        System.out.println("Ticket price: $" + ticket);
	    }
	}

