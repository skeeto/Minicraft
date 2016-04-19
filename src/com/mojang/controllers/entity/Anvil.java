package com.mojang.controllers.entity;

import com.mojang.controllers.crafting.Crafting;
import com.mojang.views.CraftingMenu;
import com.mojang.views.gfx.Color;

public class Anvil extends Furniture {
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