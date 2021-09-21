package life;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
class Universe {

    private final int size;
    private Random rnd;
    private Cell[][] cells;

    public Universe(int size) {
        this.size = size;
        initRandom();
        initCells();
    }

    public Cell[][] getCells() {
        return cells;
    }

    public int getSize() {
        return size;
    }

    private void initCells() {
        cells = new Cell[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                cells[row][col] = rnd.nextBoolean() ? Cell.BLOB : Cell.EMPTY;
            }
        }
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    private void initRandom() {

        Date nowDate = new Date();
        rnd = new Random(nowDate.getTime());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                builder.append(cells[row][col].getValue());
            }
            builder.append('\n');
        }
        return builder.toString();
    }
}