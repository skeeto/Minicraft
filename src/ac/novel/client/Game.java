package ac.novel.client;

import ac.novel.common.InitInterface;
import ac.novel.common.InputHandlerInterface;

import javax.swing.*;
import java.awt.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Game extends ac.novel.common.Game {

    public void start(InputHandlerInterface inputHandlerServerInterface) {
        running = true;
        input = new InputHandlerClient(inputHandlerServerInterface);
        this.addKeyListener(input);
        new Thread(this).start();
    }

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

        // RMI
        System.err.println("Client before RMI");
        String reg_host = "localhost";
        int reg_port = 1234;

        try {
            Registry registry = LocateRegistry.getRegistry(reg_host,reg_port);
            InputHandlerInterface stub = (InputHandlerInterface) registry.lookup("InputHandler");
            InitInterface initStub = (InitInterface) registry.lookup("Init");
            System.err.println("Client got remote InputHandler");
            ArrayList<String> savedState = initStub.getSavedState();
            game.start(stub);
            System.err.println("Game Started");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
