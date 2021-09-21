package life;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Algorithms {
    private static int generation = 0;

    static private int countBlobs(Universe life) {
        int count = 0;
        for (int i = 0; i < life.getSize(); i++) {
            for (int j = 0; j < life.getSize(); j++) {
                if (life.getCells()[i][j].isBlob()) count++;
            }
        }
        return count;
    }

    static private int neighborsNumber(Cell[][] cells, int size,  int rows, int cols) {
        int count = 0;

        for (int i = rows - 1; i <= rows + 1; i++) {
            for (int j = cols - 1; j <= cols + 1; j++) {
                if (i == rows && j == cols) continue;

                int actualRow;
                if (i < 0) actualRow = size - 1;
                else if (i > size - 1) actualRow = 0;
                else actualRow = i;

                int actualCol;
                if (j < 0) actualCol = size - 1;
                else if (j > size - 1) actualCol = 0;
                else actualCol = j;

                if (cells[actualRow][actualCol].isBlob()) count++;
            }
        }
        return count;
    }

    public static void liveFor(Universe life, int generations, GameOfLife gameOfLife) throws InterruptedException {
        do {
            generation++;
            int alive = countBlobs(life);
            gameOfLife.getInfoReference().nextGen(generation, alive);

            TimeUnit.MILLISECONDS.sleep(100);

            Cell[][] tab = new Cell[life.getSize()][life.getSize()];
            for (int i = 0; i < life.getSize(); i++) {
                for (int j = 0; j < life.getSize(); j++) {
                    tab[i][j] = life.getCells()[i][j].isBlob() ? Cell.BLOB : Cell.EMPTY;
                }
            }
            for (int i = 0; i < life.getSize(); i++) {
                for (int j = 0; j < life.getSize(); j++) {

                    if (life.getCells()[i][j].isEmpty() &&
                            neighborsNumber(life.getCells(), life.getSize(), i, j) == 3) {

                        tab[i][j] = Cell.BLOB;
                    }

                    if (life.getCells()[i][j].isBlob() &&
                            (neighborsNumber(life.getCells(), life.getSize(), i, j) > 3 ||
                                    neighborsNumber(life.getCells(), life.getSize(), i, j) < 2)) {

                        tab[i][j] = Cell.EMPTY;
                    }
                }
            }
            life.setCells(tab);
            for (int i = 0; i < life.getSize(); i++) {
                for (int j = 0; j < life.getSize(); j++) {
                    if (tab[i][j].isBlob())
                        gameOfLife.getCells().getCells()[i][j].setColor(Color.white.darker());
                    else
                        gameOfLife.getCells().getCells()[i][j].setColor(Color.darkGray.darker());
                }
            }
        } while (true);

    }
}