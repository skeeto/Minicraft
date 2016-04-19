package com.mojang.views.item;

import com.mojang.controllers.entity.Entity;
import com.mojang.controllers.entity.ItemEntity;
import com.mojang.controllers.entity.Player;
import com.mojang.models.level.*;
import com.mojang.views.ListItem;
import com.mojang.views.gfx.Screen;
import com.mojang.views.level.tile.Tile;

public class Item implements ListItem {
	public int getColor() {
		return 0;
	}

	public int getSprite() {
		return 0;
	}

	public void onTake(ItemEntity itemEntity) {
	}

	public void renderInventory(Screen screen, int x, int y) {
	}

	public boolean interact(Player player, Entity entity, int attackDir) {
		return false;
	}

	public void renderIcon(Screen screen, int x, int y) {
	}

	public boolean interactOn(Tile tile, Level level, int xt, int yt, Player player, int attackDir) {
		return false;
	}
	
	public boolean isDepleted() {
		return false;
	}

	public boolean canAttack() {
		return false;
	}

	public int getAttackDamageBonus(Entity e) {
		return 0;
	}

	public String getName() {
		return "";
	}

	public boolean matches(Item item) {
		return item.getClass() == getClass();
	}
}