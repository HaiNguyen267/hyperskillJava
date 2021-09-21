package life;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private final JLabel generationLabel;
    private final JLabel aliveLabel;

    InfoPanel() {
        setSize(300,300);
        setBackground(Color.darkGray.darker());

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setVisible(true);

        Font font = new Font("SansSerif", Font.PLAIN,12);

        generationLabel = new JLabel("Generation #0");
        generationLabel.setSize(285,15);
        generationLabel.setFont(font);
        generationLabel.setName("GenerationLabel");
        generationLabel.setForeground(Color.white.darker());
        add(generationLabel);

        aliveLabel = new JLabel("Alive: 0");
        aliveLabel.setSize(285,15);
        aliveLabel.setFont(font);
        aliveLabel.setName("AliveLabel");
        aliveLabel.setForeground(Color.white.darker());
        add(aliveLabel);

        JLabel placeholder = new JLabel(".             .");
        placeholder.setSize(285, 15);
        placeholder.setFont(font);
        add(placeholder);


    }

    public void nextGen(int gen, int alive) {
        generationLabel.setText("Generation #" + gen);
        aliveLabel.setText("Alive: " + alive);
    }

}