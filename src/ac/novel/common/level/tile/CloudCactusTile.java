package ac.novel.common.level.tile;

import ac.novel.common.entity.AirWizard;
import ac.novel.common.entity.Entity;
import ac.novel.common.entity.Mob;
import ac.novel.common.entity.Player;
import ac.novel.common.entity.particle.SmashParticle;
import ac.novel.common.entity.particle.TextParticle;
import ac.novel.common.gfx.Color;
import ac.novel.common.gfx.Screen;
import ac.novel.common.item.Item;
import ac.novel.common.item.ToolItem;
import ac.novel.common.item.ToolType;
import ac.novel.common.level.Level;

public class CloudCactusTile extends Tile {
	public CloudCactusTile(int id) {
		super(id);
	}

	public void render(Screen screen, Level level, int x, int y) {
		int color = Color.get(444, 111, 333, 555);
		screen.render(x * 16 + 0, y * 16 + 0, 17 + 1 * 32, color, 0);
		screen.render(x * 16 + 8, y * 16 + 0, 18 + 1 * 32, color, 0);
		screen.render(x * 16 + 0, y * 16 + 8, 17 + 2 * 32, color, 0);
		screen.render(x * 16 + 8, y * 16 + 8, 18 + 2 * 32, color, 0);
	}

	public boolean mayPass(Level level, int x, int y, Entity e) {
		if (e instanceof AirWizard) return true;
		return false;
	}

	public void hurt(Level level, int x, int y, Mob source, int dmg, int attackDir) {
		hurt(level, x, y, 0);
	}

	public boolean interact(Level level, int xt, int yt, Player player, Item item, int attackDir) {
		if (item instanceof ToolItem) {
			ToolItem tool = (ToolItem) item;
			if (tool.type == ToolType.pickaxe) {
				if (player.payStamina(6 - tool.level)) {
					hurt(level, xt, yt, 1);
					return true;
				}
			}
		}
		return false;
	}

	public void hurt(Level level, int x, int y, int dmg) {
		int damage = level.getData(x, y) + 1;
		level.add(new SmashParticle(x * 16 + 8, y * 16 + 8));
		level.add(new TextParticle("" + dmg, x * 16 + 8, y * 16 + 8, Color.get(-1, 500, 500, 500)));
		if (dmg > 0) {
			if (damage >= 10) {
				level.setTile(x, y, Tile.cloud, 0);
			} else {
				level.setData(x, y, damage);
			}
		}
	}

	public void bumpedInto(Level level, int x, int y, Entity entity) {
		if (entity instanceof AirWizard) return;
		entity.hurt(this, x, y, 3);
	}
}