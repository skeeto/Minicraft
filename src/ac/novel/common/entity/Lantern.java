package ac.novel.common.entity;

import java.io.Serializable;

import ac.novel.common.gfx.Color;

public class Lantern extends Furniture implements Serializable {
	public Lantern() {
		super("Lantern");
		col = Color.get(-1, 000, 111, 555);
		sprite = 5;
		xr = 3;
		yr = 2;
	}

	public int getLightRadius() {
		return 8;
	}
}