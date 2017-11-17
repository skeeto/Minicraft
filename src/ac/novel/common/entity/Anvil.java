package ac.novel.common.entity;

import java.io.Serializable;

import ac.novel.common.crafting.Crafting;
import ac.novel.common.gfx.Color;
import ac.novel.common.screen.CraftingMenu;

public class Anvil extends Furniture {
    private static final long serialVersionUID = 42L;
	public Anvil() {
		super("Anvil");
		col = Color.get(-1, 000, 111, 222);
		sprite = 0;
		xr = 3;
		yr = 2;
	}

	public boolean use(Player player, int attackDir) {
		player.game.setMenu(new CraftingMenu(Crafting.anvilRecipes, player));
		return true;
	}
}
