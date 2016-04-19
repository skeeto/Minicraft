package com.mojang.controllers.entity;

import com.mojang.controllers.crafting.Crafting;
import com.mojang.views.CraftingMenu;
import com.mojang.views.gfx.Color;

public class Oven extends Furniture {
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