package ac.novel.common.entity;

import java.io.Serializable;

import ac.novel.common.crafting.Crafting;
import ac.novel.common.gfx.Color;
import ac.novel.common.screen.CraftingMenu;

public class Oven extends Furniture {
    private static final long serialVersionUID = 42L;
	public Oven() {
		super("Oven");
		col = Color.get(-1, 000, 332, 442);
		sprite = 2;
		xr = 3;
		yr = 2;
	}

	public boolean use(Player player, int attackDir) {
		player.game.setMenu(new CraftingMenu(Crafting.ovenRecipes, player));
		return true;
	}
}
