package ac.novel.common;

import java.io.Serializable;

import ac.novel.common.entity.Player;
import ac.novel.common.level.Level;
import ac.novel.server.Game;

public class Save implements Serializable {
	
	public Player player;
	public int gameTime;
	public boolean hasWon;
	public Level[] levels;
	public int currentLevel;
	
//	playerDeadTime = 0;
//	wonTimer = 0;
//	gameTime = 0;
//	hasWon = false;
//
//	levels = new Level[5];
//	currentLevel = 3;
//
//	levels[4] = new Level(128, 128, 1, null);
//	levels[3] = new Level(128, 128, 0, levels[4]);
//	levels[2] = new Level(128, 128, -1, levels[3]);
//	levels[1] = new Level(128, 128, -2, levels[2]);
//	levels[0] = new Level(128, 128, -3, levels[1]);
//
//	level = levels[currentLevel];
//	player = new Player(this, input);
//	player.findStartPos(level);
//
//	level.add(player);
//
//	for (int i = 0; i < 5; i++) {
//		levels[i].trySpawn(5000);
//	}
	
	public Save(Game game) {
		this.player = game.player.getPlayer();
		this.gameTime = game.gameTime;
		this.hasWon = game.hasWon;
		this.levels = game.levels;
		this.currentLevel = game.currentLevel;
	}
}
