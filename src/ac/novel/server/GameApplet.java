package ac.novel.server;

import java.applet.Applet;
import java.awt.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import ac.novel.common.InputHandler;
import ac.novel.common.InputHandlerInterface;

public class GameApplet extends Applet {
	private static final long serialVersionUID = 1L;

	private Game game = new Game();

	public void init() {
		setLayout(new BorderLayout());
		add(game, BorderLayout.CENTER);
	}

	public void start() {
		try {
			game.start();
			InputHandler obj = new InputHandler(game);
			InputHandlerInterface stub = (InputHandlerInterface) UnicastRemoteObject.exportObject(obj, 1234);

			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("InputHandler", stub);
			System.err.println("Server ready");

		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

	public void stop() {
		game.stop();
	}
}
