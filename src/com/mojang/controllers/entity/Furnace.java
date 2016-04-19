package com.mojang.controllers.entity;

import com.mojang.controllers.crafting.Crafting;
import com.mojang.views.CraftingMenu;
import com.mojang.views.gfx.Color;

public class Furnace extends Furniture {
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