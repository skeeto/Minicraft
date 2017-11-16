package ac.novel.common.entity.particle;

import ac.novel.common.entity.Entity;
import ac.novel.common.gfx.Color;
import ac.novel.common.gfx.Screen;
import ac.novel.common.sound.Sound;

public class SmashParticle extends Entity {
	private int time = 0;

	public SmashParticle(int x, int y) {
		this.x = x;
		this.y = y;
		Sound.monsterHurt.play();
	}

	public void tick() {
		time++;
		if (time > 10) {
			remove();
		}
	}

	public void render(Screen screen) {
		int col = Color.get(-1, 555, 555, 555);
		screen.render(x - 8, y - 8, 5 + 12 * 32, col, 2);
		screen.render(x - 0, y - 8, 5 + 12 * 32, col, 3);
		screen.render(x - 8, y - 0, 5 + 12 * 32, col, 0);
		screen.render(x - 0, y - 0, 5 + 12 * 32, col, 1);
	}
}
