 public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Input grid :");

        int[][] weights = {  {2, 1, 2}, {4, -4, 4}, {2, -1, 2}};
        char[][] grids = new char[3][3];
        int result = 0;

        for (int i = 0; i < 3; i++) {

            String input = sc.nextLine();
            for (int j = 0; j < 3; j++) {

                grids[i][j] = input.charAt(j);

                if (grids[i][j] == 'X') {
                    result += weights[i][j];
                }

            }
        }

        result -= 5;

        if (result < 0) {
            System.out.println("This number is 1");
        }
        else {
            System.out.println("This number is 0");
        }
    }
