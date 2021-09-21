package life;

import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame implements Runnable {
    private final InfoPanel infoReference;
    private final BoardPanel cells;

    public GameOfLife() {
        super("Game of  Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(400, 300);

        JPanel main = new JPanel();
        main.setVisible(true);
        main.setBackground(Color.darkGray.darker());
        main.setLayout(new FlowLayout(FlowLayout.LEFT));

        infoReference = new InfoPanel();
        main.add(infoReference);

        cells = new BoardPanel();
        main.add(cells);
        add(main);
    }


    public InfoPanel getInfoReference() {
        return infoReference;
    }

    public BoardPanel getCells() {
        return cells;
    }

    @Override
    public void run() {}
}