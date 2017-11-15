package ac.novel.server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ac.novel.common.entity.Chest;
import ac.novel.common.entity.Entity;
import ac.novel.common.entity.Inventory;
import ac.novel.common.entity.ItemEntity;
import ac.novel.common.entity.Lantern;
import ac.novel.common.entity.Player;
import ac.novel.common.entity.Spark;
import ac.novel.common.entity.particle.TextParticle;
import ac.novel.common.item.Item;

public class Save {

	public static String extension = ".miniplussave";

	List<String> data;
	Game game;

	/// this saves world options
	public Save(ac.novel.common.Game game2) {
		
		data = new ArrayList<>();
		this.game = (Game) game2;
		writeGame("Game");
		// writePrefs("KeyPrefs");
		writeWorld("Level");
		writePlayer("Player", game2.player);
		writeInventory("Inventory", game2.player);
		writeEntities("Entities");
	}


	public static void writeFile(String filename, String[] lines) throws IOException {
		try (BufferedWriter br = new BufferedWriter(new FileWriter(filename))) {
			br.write(String.join(System.lineSeparator(), lines));
		}
	}

	public void writeToFile(String filename, List<String> savedata) {
		try {
			writeToFile(filename, savedata.toArray(new String[0]), true);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		data.clear();
		//Game.render(); // AH HA!!! HERE'S AN IMPORTANT STATEMENT!!!!
	}

	public static void writeToFile(String filename, String[] savedata, boolean isWorldSave) throws IOException {
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
			// bufferedWriter.write(content);
			for (int i = 0; i < savedata.length; i++) {
				bufferedWriter.write(savedata[i]);
				if (isWorldSave) {
					bufferedWriter.write(",");
					if (filename.contains("Level5") && i == savedata.length - 1) {
						bufferedWriter.write(",");
					}
				} else
					bufferedWriter.write("\n");
			}
		}
	}

	public void writeGame(String filename) {
		
		//TODO
		data.add(String.valueOf(game.tickCount));
		data.add(String.valueOf(game.gameTime));
		//writeToFile(location + filename + extension, data);
	}

	public void writeWorld(String filename) {
		for (int l = 0; l < game.levels.length; l++) {
			data.add(String.valueOf(game.levels[l].depth));

			for (int x = 0; x < game.levels[l].w; x++) {
				for (int y = 0; y < game.levels[l].h; y++) {
					data.add(String.valueOf(game.levels[l].getTile(x, y).toString()));
				}
			}

			//writeToFile(location + filename + l + extension, data);
		}

		for (int l = 0; l < game.levels.length; l++) {
			for (int x = 0; x < game.levels[l].w; x++) {
				for (int y = 0; y < game.levels[l].h; y++) {
					data.add(String.valueOf(game.levels[l].getData(x, y)));
				}
			}

			//writeToFile(location + filename + l + "data" + extension, data);
		}

	}

	public void writePlayer(String filename, Player player) {
		writePlayer(player, data);
		//writeToFile(location + filename + extension, data);
	}

	public void writePlayer(Player player, List<String> data) {
		//data.clear();
		data.add(String.valueOf(player.x));
		data.add(String.valueOf(player.y));
		data.add(String.valueOf(player.health));
		data.add(String.valueOf(player.score));
		// data.add(String.valueOf(player.ac));
		data.add("25"); // TODO filler; remove this, but make sure not to break the Load class's
						// LoadPlayer() method while doing so.
		data.add(String.valueOf(game.currentLevel));
//		data.add(Settings.getIdx("mode")
//				+ (Game.isMode("score") ? ";" + Game.scoreTime + ";" + Settings.get("scoretime") : ""));

	}

	public void writeInventory(String filename, Player player) {
		writeInventory(player, data);
		//writeToFile(location + filename + extension, data);
	}

	public static void writeInventory(Player player, List<String> data) {
		//data.clear();
		Inventory inventory = player.inventory;

		for (int i = 0; i < inventory.invSize(); i++) {
			if (inventory.get(i) instanceof Item) {
				data.add(inventory.get(i).getName() + ";" + (inventory.count((Item) inventory.get(i))));
			}
		}
	}

	public void writeEntities(String filename) {
		for (int l = 0; l < game.levels.length; l++) {
			//TODO not so sure 
			for (Entity e : game.levels[l].getEntities()) {
				String saved = writeEntity(e, true);
				if (saved.length() > 0)
					data.add(saved);
			}
		}

		//writeToFile(location + filename + extension, data);
	}

	public String writeEntity(Entity e, boolean isLocalSave) {
		String name = e.getClass().getName().replace("minicraft.entity.", "");
		// name = name.substring(name.lastIndexOf(".")+1);
		StringBuilder extradata = new StringBuilder();

		// don't even write ItemEntities or particle effects; Spark... will probably is
		// saved, eventually; it presents an unfair cheat to remove the sparks by
		// reloading the Game.

		// if(e instanceof Particle) return ""; // TODO I don't want to, but there are
		// complications.

		if (e instanceof Chest) {
			Chest chest = (Chest) e;

			for (int ii = 0; ii < chest.inventory.invSize(); ii++) {
				Item item = chest.inventory.get(ii);
				extradata.append(":").append(item.getName());
			}

		}

		if (e instanceof Lantern) {
			extradata.append(":").append(((Lantern) e));
		}


		
		if (e instanceof ItemEntity)
			extradata.append(":").append(((ItemEntity) e).getData());
		if (e instanceof Spark)
			extradata.append(":").append(((Spark) e).getData());
		if (e instanceof TextParticle)
			extradata.append(":").append(((TextParticle) e));
		
		// else // is a local save

		int depth = 0;
		if (e.level == null)
			System.out.println("WARNING: saving entity with no level reference: " + e + "; setting level to surface");
		else
			depth = e.level.depth;

		extradata.append(":").append(game.levels[depth]);

		return name + "[" + e.x + ":" + e.y + extradata + "]";
	}
}
