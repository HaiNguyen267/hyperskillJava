package life;
import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        Universe universe = new Universe(25);
        int generations = 100;

        GameOfLife board = new GameOfLife();
        SwingUtilities.invokeAndWait(board);
        Algorithms.liveFor(universe, generations, board);

    }
}
