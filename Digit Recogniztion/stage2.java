package recognition;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        
        // setting weights and biases
        int[][] weightsOf1 = {{-1, 1, -1},  {-1, 1, -1}, {-1, 1, -1},  {-1, 1, -1},  {-1, 1, -1}};
        int[][] weightsOf2 = {{1, 1, 1},   {-1, -1, 1},  {1, 1, 1},    {1, -1, -1},  {1, 1, 1}};
        int[][] weightsOf3 = {{1, 1, 1},   {-1, -1, 1},  {1, 1, 1},    {-1, -1, 1},  {1, 1, 1}};
        int[][] weightsOf4 = {{1, -1, 1},  {1, -1, 1},   {1, 1, 1},    {-1, -1, 1},  {-1, -1, 1}};
        int[][] weightsOf5 = {{1, 1, 1},   {1, -1, -1},  {1, 1, 1},    {-1, -1, 1},  {1, 1, 1}};
        int[][] weightsOf6 = {{1, 1, 1},   {1, -1, -1},  {1, 1, 1},    {1, -1, 1},   {1, 1, 1}};
        int[][] weightsOf7 = {{1, 1, 1},   {-1, -1, 1},  {-1, -1, 1},  {-1, -1, 1},  {-1, -1, 1}};
        int[][] weightsOf8 = {{1, 1, 1},   {1, -1, 1},   {1, 1, 1},    {1, -1, 1},   {1, 1, 1}};
        int[][] weightsOf9 = {{1, 1, 1},   {1, -1, 1},   {1, 1, 1},    {-1, -1, 1},  {1, 1, 1}};
        int[][] weightsOf0 = {{1, 1, 1},   {1, -1, 1},   {1, -1, 1},   {1, -1, 1},   {1, 1, 1}};

        int[][][] weightArray = {weightsOf1, weightsOf2, weightsOf3
                            , weightsOf4, weightsOf5, weightsOf6
                            , weightsOf7, weightsOf8, weightsOf9, weightsOf0};


        int[] bias = {6, 1, 0, 2, 0, -1, 3, -2, -1, -1};


        int[] sumArrays = new int[10];


        // ask for inputting the grid
        Scanner sc = new Scanner(System.in);
        int[][] grids = new int[5][3];

        System.out.println("Input grid: ");
        // convert the grid to grid of 1 and 0
        for (int i = 0; i < 5; i++) {
            String input = sc.nextLine();
            for (int j = 0; j < input.length(); j++) {
                grids[i][j] = input.charAt(j) == 'X'? 1: 0;
            }
        }

        int maxSum = 0;
        int number = 0;

        // calculate output neurons and put them in an array
        for (int i = 0; i < weightArray.length; i++) {
            // calculate the ith output neuron (1, 2, 3, ..., 9, 0)
            for (int j = 0; j < weightArray[i].length; j++) {
                // add the weight of blue cells (weight of white cell is -1, blue is 1)
                for (int k = 0; k < weightArray[i][j].length; k++) {
                    // grid[i][k] represent the presence of the cell, 1 for presence, 0 for absence
                    sumArrays[i] += grids[j][k] * weightArray[i][j][k];
                }
            }
            // add output neuron to its corresponding bias
            sumArrays[i] += bias[i];

            if (sumArrays[i] > maxSum) {
                // find the "brightest" output neuron
                maxSum = sumArrays[i];
                number = i < 9 ? i + 1 : 0;
            }


        }

        System.out.println("This number is "+ number);

    }


}
