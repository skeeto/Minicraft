package ac.novel.server;

import ac.novel.common.InputHandler;
import ac.novel.common.InputHandlerInterface;
import ac.novel.common.gfx.Screen;
import ac.novel.common.gfx.SpriteSheet;
import ac.novel.common.screen.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Game extends ac.novel.common.Game {

    static final String NAME = "Minicraft Server";

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

        try {
//            game.setMenu(null);
            game.start();
            int port = 1234;
            // Bind the remote object's stub in the registry
//			Naming.rebind("rmi://localhost:" + port + "/InputHandler", obj);

            InputHandlerInterface stub = (InputHandlerInterface) UnicastRemoteObject.exportObject(game.input, port);
            Registry reg = LocateRegistry.createRegistry(port);
            System.err.println("Server is ready from main");
            reg.rebind("InputHandler", stub);
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
        super.init();
        setMenu(null);
    }

}
