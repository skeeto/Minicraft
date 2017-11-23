package ac.novel.common;

import java.io.Serializable;

import ac.novel.common.entity.Player;
import ac.novel.common.level.Level;
import ac.novel.server.Game;

public class Save implements Serializable {
    private static final long serialVersionUID = 123L;
	
	public Player player;
	public int gameTime;
	public boolean hasWon;
	public Level[] levels;
	public int currentLevel;
	
	public Save(Game game) {
		this.player = game.player;
		this.gameTime = game.gameTime;
		this.hasWon = game.hasWon;
		this.levels = game.levels;
		this.currentLevel = game.currentLevel;
	}
}
