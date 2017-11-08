package ac.novel.common.level.tile;

import ac.novel.common.entity.Entity;
import ac.novel.common.entity.ItemEntity;
import ac.novel.common.entity.Mob;
import ac.novel.common.entity.Player;
import ac.novel.common.gfx.Color;
import ac.novel.common.gfx.Screen;
import ac.novel.common.item.Item;
import ac.novel.common.item.ResourceItem;
import ac.novel.common.item.ToolItem;
import ac.novel.common.item.ToolType;
import ac.novel.common.item.resource.Resource;
import ac.novel.common.level.Level;

public class SandTile extends Tile {
	public SandTile(int id) {
		super(id);
		connectsToSand = true;
	}

	public void render(Screen screen, Level level, int x, int y) {
		int col = Color.get(level.sandColor + 2, level.sandColor, level.sandColor - 110, level.sandColor - 110);
		int transitionColor = Color.get(level.sandColor - 110, level.sandColor, level.sandColor - 110, level.dirtColor);

		boolean u = !level.getTile(x, y - 1).connectsToSand;
		boolean d = !level.getTile(x, y + 1).connectsToSand;
		boolean l = !level.getTile(x - 1, y).connectsToSand;
		boolean r = !level.getTile(x + 1, y).connectsToSand;

		boolean steppedOn = level.getData(x, y) > 0;

		if (!u && !l) {
			if (!steppedOn)
				screen.render(x * 16 + 0, y * 16 + 0, 0, col, 0);
			else
				screen.render(x * 16 + 0, y * 16 + 0, 3 + 1 * 32, col, 0);
		} else
			screen.render(x * 16 + 0, y * 16 + 0, (l ? 11 : 12) + (u ? 0 : 1) * 32, transitionColor, 0);

		if (!u && !r) {
			screen.render(x * 16 + 8, y * 16 + 0, 1, col, 0);
		} else
			screen.render(x * 16 + 8, y * 16 + 0, (r ? 13 : 12) + (u ? 0 : 1) * 32, transitionColor, 0);

		if (!d && !l) {
			screen.render(x * 16 + 0, y * 16 + 8, 2, col, 0);
		} else
			screen.render(x * 16 + 0, y * 16 + 8, (l ? 11 : 12) + (d ? 2 : 1) * 32, transitionColor, 0);
		if (!d && !r) {
			if (!steppedOn)
				screen.render(x * 16 + 8, y * 16 + 8, 3, col, 0);
			else
				screen.render(x * 16 + 8, y * 16 + 8, 3 + 1 * 32, col, 0);

		} else
			screen.render(x * 16 + 8, y * 16 + 8, (r ? 13 : 12) + (d ? 2 : 1) * 32, transitionColor, 0);
	}

	public void tick(Level level, int x, int y) {
		int d = level.getData(x, y);
		if (d > 0) level.setData(x, y, d - 1);
	}

	public void steppedOn(Level level, int x, int y, Entity entity) {
		if (entity instanceof Mob) {
			level.setData(x, y, 10);
		}
	}

	public boolean interact(Level level, int xt, int yt, Player player, Item item, int attackDir) {
		if (item instanceof ToolItem) {
			ToolItem tool = (ToolItem) item;
			if (tool.type == ToolType.shovel) {
				if (player.payStamina(4 - tool.level)) {
					level.setTile(xt, yt, Tile.dirt, 0);
					level.add(new ItemEntity(new ResourceItem(Resource.sand), xt * 16 + random.nextInt(10) + 3, yt * 16 + random.nextInt(10) + 3));
					return true;
				}
			}
		}
		return false;
	}
}
