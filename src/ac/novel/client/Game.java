package ac.novel.client;

import ac.novel.common.InitInterface;
import ac.novel.common.InputHandlerInterface;
import ac.novel.common.Save;
import ac.novel.common.SaveInterface;
import ac.novel.common.entity.Player;
import ac.novel.common.gfx.Screen;
import ac.novel.common.gfx.SpriteSheet;
import ac.novel.common.level.Level;
import ac.novel.common.screen.Menu;
import ac.novel.common.screen.TitleMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Game extends ac.novel.common.Game {
	protected static final long serialVersionUID = 3L;
//	private InputHandlerClient input;

    public void start(InputHandlerClient inputHandlerClient) {
        running = true;
        input = inputHandlerClient;
        this.addKeyListener(input);
        new Thread(this).start();
    }
    
    @Override
    protected void init() {
		int pp = 0;
		for (int r = 0; r < 6; r++) {
			for (int g = 0; g < 6; g++) {
				for (int b = 0; b < 6; b++) {
					int rr = (r * 255 / 5);
					int gg = (g * 255 / 5);
					int bb = (b * 255 / 5);
					int mid = (rr * 30 + gg * 59 + bb * 11) / 100;

					int r1 = ((rr + mid * 1) / 2) * 230 / 255 + 10;
					int g1 = ((gg + mid * 1) / 2) * 230 / 255 + 10;
					int b1 = ((bb + mid * 1) / 2) * 230 / 255 + 10;
					colors[pp++] = r1 << 16 | g1 << 8 | b1;

				}
			}
		}
		try {
			screen = new Screen(WIDTH, HEIGHT, new SpriteSheet(ImageIO.read(Game.class.getResourceAsStream("/icons.png"))));
			lightScreen = new Screen(WIDTH, HEIGHT, new SpriteSheet(ImageIO.read(Game.class.getResourceAsStream("/icons.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
            InputHandlerClient inputHandlerClient = new InputHandlerClient(stub);
            System.err.println("Client got remote InputHandler");
//            InitInterface initStub = (InitInterface) registry.lookup("Init");
            SaveInterface saveStub = (SaveInterface) registry.lookup("GetSave");
//            ArrayList<String> savedState = initStub.getSavedState();
            
            Save savedGame = saveStub.getSave();
            game.playerDeadTime = 0;
            game.wonTimer = 0;
            game.gameTime = savedGame.gameTime;
            game.hasWon = savedGame.hasWon;

            game.levels = savedGame.levels;
            game.currentLevel = savedGame.currentLevel;

            game.level = game.levels[game.currentLevel];
            game.player = savedGame.player;
            game.player.game = game;
            game.player.input = inputHandlerClient;
            
            game.start(inputHandlerClient);
//            game.player.findStartPos(game.level);
            game.setMenu(null);

            System.err.println("Client got remote saved game");
            System.err.println(String.format("Save dump: %d %d %d", savedGame.currentLevel, savedGame.player.x, savedGame.player.y));
            System.err.println("Game Started");
            System.err.println(game.player.x + " " + game.player);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tick() {
        if (!hasFocus()) {
            input.releaseAll();
        }
        super.tick();
    }
    
}
