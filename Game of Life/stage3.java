
import java.io.IOException;
import java.util.*;
class Main {
    public static void main (String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = (int) Math.random() * 142;
        int m = 12;

        Universe universe = new Universe(n, s);
        universe.createTheUniverse();
        universe.generateUniverse(m);


    }
}
class Universe {
    int seed;
    int n;
    String[][] map;
    int[][] mapOfNumber;
    int aliveCells;
    int deadCells;
    int generation = 1;

    public Universe (int n, int seed) {
        this.n = n;
        this.seed = seed;
        this.map = new String[n][n];
        this.mapOfNumber = new int[n][n];
    }

    public void createTheUniverse() {
        Random random = new Random(this.seed);

        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                this.map[i][j] = random.nextBoolean() == true ? "O" : " ";
            }
        }
    }

    public void showUniverse() {
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                System.out.print(this.map[i][j]);
            }
            System.out.println();
        }


    }
    private void deadAndAlive() {
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                if (this.map[i][j].equals("O")) {
                    this.aliveCells ++;
                }
                if (this.map[i][j].equals(" ")) {
                    this.deadCells ++;
                }
            }
        }
    }

    public void generateUniverse(int times) throws InterruptedException {
        for (int i = 0; i < times; i++) {
            clearConsole();
			deadAndAlive();
            System.out.println("Generation #" + this.generation);
            System.out.println("Alive: " + this.aliveCells);
            mapToMapOfNumber();
            mapOfNumberToMap();
            showUniverse();
            Thread.sleep(1000);
            this.generation ++;
        }
    }
    private void mapOfNumberToMap() {
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                if (this.map[i][j].equals("O") && (this.mapOfNumber[i][j] < 2 || this.mapOfNumber[i][j] > 3)) {
                    this.map[i][j] = " ";
                }
                else if (this.map[i][j].equals(" ") && this.mapOfNumber[i][j] == 3) {
                    this.map[i][j] = "O";
                }
            }
        }
    }
    private void mapToMapOfNumber() {
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                this.mapOfNumber[i][j] = countNumOfAliveNeighbor(i, j);
            }
        }
    }
    private int countNumOfAliveNeighbor(int i, int j) {
        int numOfAliveNeighbor = 0;

        int iMinusOne = i - 1;
        if (iMinusOne < 0) {
            iMinusOne = this.n - 1;
        }

        int iPlusOne = i + 1;
        if (iPlusOne > this.n - 1) {
            iPlusOne = 0;
        }

        int jMinusOne = j - 1;
        if (jMinusOne < 0) {
            jMinusOne = this.n - 1;
        }

        int jPlusOne = j + 1;
        if (jPlusOne > this.n - 1) {
            jPlusOne = 0;
        }

        // northwest
        if (this.map[iMinusOne][jMinusOne].equals("O")) {
            numOfAliveNeighbor ++;
        }
        // north
        if (this.map[iMinusOne][j].equals("O")) {
            numOfAliveNeighbor ++;
        }
        // northeast
        if (this.map[iMinusOne][jPlusOne].equals("O")) {
            numOfAliveNeighbor ++;
        }

        // west
        if (this.map[i][jMinusOne].equals("O")) {
            numOfAliveNeighbor ++;
        }
        // east
        if (this.map[i][jPlusOne].equals("O")) {
            numOfAliveNeighbor ++;
        }

        // southwest
        if (this.map[iPlusOne][jMinusOne].equals("O")) {
            numOfAliveNeighbor ++;
        }
        // south
        if (this.map[iPlusOne][j].equals("O")) {
            numOfAliveNeighbor ++;
        }
        // southeast
        if (this.map[iPlusOne][jPlusOne].equals("O")) {
            numOfAliveNeighbor ++;
        }

        return numOfAliveNeighbor;
    }

    private static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        }
        catch (IOException | InterruptedException e) {}
    }
}
