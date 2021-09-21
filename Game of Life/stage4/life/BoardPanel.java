package life;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    static class CellPanel extends JPanel {
        public void setColor(Color cr) {
            setBackground(cr);
        }
    }

    private final CellPanel[][] cells;

    BoardPanel(){
        setSize(300,300);
        final int size = 25;
        setLayout(new GridLayout(size, size));
        setVisible(true);

        cells = new CellPanel[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new CellPanel();
                cells[i][j].setSize(12,12);
                cells[i][j].setVisible(true);
                cells[i][j].setBackground(Color.darkGray.darker());
                add(cells[i][j]);
            }
        }
    }

    public CellPanel[][] getCells() {
        return cells;
    }
}