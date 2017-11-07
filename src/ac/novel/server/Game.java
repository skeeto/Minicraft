package ac.novel.server;

import javax.swing.*;
import java.awt.*;

public class Game extends ac.novel.common.Game {
    public static void main(String[] args) {
        Game game = new Game();
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame(Game.NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(game, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }
}
