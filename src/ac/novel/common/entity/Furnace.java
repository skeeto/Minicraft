package ac.novel.common.entity;

import java.io.Serializable;

import ac.novel.common.crafting.Crafting;
import ac.novel.common.gfx.Color;
import ac.novel.common.screen.CraftingMenu;

public class Furnace extends Furniture implements Serializable {
	public Furnace() {
		super("Furnace");
		col = Color.get(-1, 000, 222, 333);
		sprite = 3;
		xr = 3;
		yr = 2;
	}

	public boolean use(Player player, int attackDir) {
		player.game.setMenu(new CraftingMenu(Crafting.furnaceRecipes, player));
		return true;
	}
}