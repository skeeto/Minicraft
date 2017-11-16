package ac.novel.common.item;

import java.io.Serializable;

import ac.novel.common.entity.Entity;
import ac.novel.common.entity.Furniture;
import ac.novel.common.entity.Player;
import ac.novel.common.gfx.Color;
import ac.novel.common.gfx.Font;
import ac.novel.common.gfx.Screen;

public class PowerGloveItem extends Item implements Serializable {
	public int getColor() {
		return Color.get(-1, 100, 320, 430);
	}

	public int getSprite() {
		return 7 + 4 * 32;
	}

	public void renderIcon(Screen screen, int x, int y) {
		screen.render(x, y, getSprite(), getColor(), 0);
	}

	public void renderInventory(Screen screen, int x, int y) {
		screen.render(x, y, getSprite(), getColor(), 0);
		Font.draw(getName(), screen, x + 8, y, Color.get(-1, 555, 555, 555));
	}

	public String getName() {
		return "Pow glove";
	}

	public boolean interact(Player player, Entity entity, int attackDir) {
		if (entity instanceof Furniture) {
			Furniture f = (Furniture) entity;
			f.take(player);
			return true;
		}
		return false;
	}
}