package com.mojang.views.level.tile;

import com.mojang.controllers.entity.AirWizard;
import com.mojang.controllers.entity.Entity;
import com.mojang.models.level.Level;
import com.mojang.views.gfx.Screen;

public class InfiniteFallTile extends Tile {
	public InfiniteFallTile(int id) {
		super(id);
	}

	public void render(Screen screen, Level level, int x, int y) {
	}

	public void tick(Level level, int xt, int yt) {
	}

	public boolean mayPass(Level level, int x, int y, Entity e) {
		if (e instanceof AirWizard) return true;
		return false;
	}
}
