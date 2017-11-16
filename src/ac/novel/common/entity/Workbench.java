package ac.novel.common.entity;

import java.io.Serializable;

import ac.novel.common.crafting.Crafting;
import ac.novel.common.gfx.Color;
import ac.novel.common.screen.CraftingMenu;

public class Workbench extends Furniture implements Serializable {
	public Workbench() {
		super("Workbench");
		col = Color.get(-1, 100, 321, 431);
		sprite = 4;
		xr = 3;
		yr = 2;
	}

	public boolean use(Player player, int attackDir) {
		player.game.setMenu(new CraftingMenu(Crafting.workbenchRecipes, player));
		return true;
	}
}