package ac.novel.client;

import java.applet.Applet;
import java.awt.*;

public class GameApplet extends Applet {
	private static final long serialVersionUID = 1L;

	private Game game = new Game();

	public void init() {
		setLayout(new BorderLayout());
		add(game, BorderLayout.CENTER);
	}

	public void start() {
        game.start();
	}

	public void stop() {
		game.stop();
	}
}
