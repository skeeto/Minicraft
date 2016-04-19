package com.mojang.controllers.entity;

import com.mojang.controllers.crafting.Crafting;
import com.mojang.views.CraftingMenu;
import com.mojang.views.gfx.Color;

public class Workbench extends Furniture {
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