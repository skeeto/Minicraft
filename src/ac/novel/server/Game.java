package ac.novel.server;

import ac.novel.common.InitInterface;
import ac.novel.common.InputHandler;
import ac.novel.common.InputHandlerInterface;
import ac.novel.common.SaveInterface;

import javax.swing.*;
import java.awt.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Game extends ac.novel.common.Game {
	protected static final long serialVersionUID = 2L;

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
            game.start();
//            SaveInterface saveObj = new Save();
//            InitInterface initObj = new Init();
            SaveInterface saveObj = new GetSave(game);

            int port = 1234;
            // Bind the remote object's stub in the registry
            // Naming.rebind("rmi://localhost:" + port + "/InputHandler", obj);


//            SaveInterface saveStub = (SaveInterface) UnicastRemoteObject.exportObject(saveObj, port);
//            InitInterface initStub = (InitInterface) UnicastRemoteObject.exportObject(initObj, port);
            SaveInterface saveStub = (SaveInterface) UnicastRemoteObject.exportObject(saveObj, port);
            InputHandlerInterface stub = (InputHandlerInterface) UnicastRemoteObject.exportObject(game.input, port);
            Registry reg = LocateRegistry.createRegistry(port);
            System.err.println("Server is ready from main");
            
            reg.rebind("InputHandler", stub);
//            reg.rebind("Save", saveStub);
//            reg.rebind("Init", initStub);
            reg.rebind("GetSave", saveStub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
