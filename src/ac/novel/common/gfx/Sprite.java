package ac.novel.common.gfx;

import java.io.Serializable;

public class Sprite implements Serializable {
	public int x, y;
	public int img;
	public int col;
	public int bits;

	public Sprite(int x, int y, int img, int col, int bits) {
		this.x = x;
		this.y = y;
		this.img = img;
		this.col = col;
		this.bits = bits;
	}
}
