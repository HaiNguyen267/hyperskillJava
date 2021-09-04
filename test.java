class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int m = sc.nextInt();

        String [][] map = new String[n][n];
        int [][] mapOfNumber = new int[n][n];
        Random random = new Random(s);

        // create a map, this is a the first generation
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = random.nextBoolean() == true? "0" : " ";
             }
        }

        System.out.println("initial pattern: ");
        showMap(map);

        mapOfNumber= convertToMapOfNumberOfNeighborhood(map);
        System.out.println("initial mapOfNumber: ");
        showMap(mapOfNumber);


            nextGeneration(map, mapOfNumber);
            System.out.println("time 1:");
            System.out.println("map of time 1: ");
            showMap(map);
            System.out.println("mapOfNumber of time 1: ");
        mapOfNumber = convertToMapOfNumberOfNeighborhood(map);
        showMap(mapOfNumber);


    }

    public static void nextGeneration (String[][] map, int [][] mapOfNumber) {


        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (mapOfNumber[i][j] == 3) {
                    map[i][j] = "0";
                }
                else {
                    map[i][j] = " ";
                }
            }
        }

        //mapOfNumber = convertToMapOfNumberOfNeighborhood(map);
    }


    public static int[][] convertToMapOfNumberOfNeighborhood (String[][] map){

        int[][] mapOfNumber = new int[map.length][map.length];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {

                int xLeft = j - 1; int yBottom = i + 1;
                int xCenter = j; int yCenter = i;
                int xRight = j + 1; int yTop = i - 1;

                if (xLeft < 0) {
                    xLeft = map.length - 1;
                }
                if (xRight > map.length - 1)
                {
                    xRight = 0;
                }

                if (yTop < 0) {
                    yTop = map.length - 1;
                }
                if (yBottom > map.length - 1) {
                    yBottom = 0;
                }


                int numberOfAliveNeighbor = 0;

                if (map[yTop][xLeft].equals("0")) {
                    numberOfAliveNeighbor ++;
                }
                if (map[yTop][xCenter].equals("0")) {
                    numberOfAliveNeighbor ++;
                }
                if (map[yTop][xRight].equals("0")) {
                    numberOfAliveNeighbor ++;
                }

                if (map[yCenter][xLeft].equals("0")) {
                    numberOfAliveNeighbor ++;
                }
                if (map[yCenter][xRight].equals("0")) {
                    numberOfAliveNeighbor ++;
                }

                if (map[yBottom][xLeft].equals("0")) {
                    numberOfAliveNeighbor ++;
                }
                if (map[yBottom][xCenter].equals("0")) {
                    numberOfAliveNeighbor ++;
                }
                if (map[yBottom][xRight].equals("0")) {
                    numberOfAliveNeighbor ++;
                }

                mapOfNumber[i][j] = numberOfAliveNeighbor;
            }
        }

        return mapOfNumber;
    }
    
    public static void  showMap (String[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }

    }
    public static void  showMap (int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }

    }

}

