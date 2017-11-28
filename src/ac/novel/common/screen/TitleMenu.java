package ac.novel.common.screen;

import ac.novel.common.gfx.Color;
import ac.novel.common.gfx.Font;
import ac.novel.common.gfx.Screen;
import ac.novel.common.sound.Sound;

public class TitleMenu extends Menu {
	private int selected = 0;

	private static final String[] options = { "Start game", "How to play", "About" };

	public TitleMenu() {
	}

	public void tick() {
		if (input.up.clicked) selected--;
		if (input.down.clicked) selected++;

		int len = options.length;
		if (selected < 0) selected += len;
		if (selected >= len) selected -= len;

		if (input.attack.clicked || input.menu.clicked) {
			if (selected == 0) {
				Sound.test.play();
				game.resetGame();
				game.setMenu(null);
			}
			if (selected == 1) game.setMenu(new InstructionsMenu(this));
			if (selected == 2) game.setMenu(new AboutMenu(this));
		}
	}

	public void render(Screen screen) {
		screen.clear(0);

		int h = 14;
		int w = 18;
		int titleColor = Color.get(0, 400, 500, 555);
//		int xo = (screen.w - w * 8) / 2; // X offset
//		int yo = 24;  // Y offset
		int xo = 7; // X offset
		int yo = -1;  // Y offset
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				screen.render(xo + x * 8, yo + y * 8, (x + 13) + (y + 16) * 32, titleColor, 0);
			}
		}
		
		Font.draw(Integer.toString(input.getUpClients()), screen, 47, 53, Color.get(-1, 000, 000, 500));
		Font.draw(Integer.toString(input.getDownClients()), screen, 47, 76, Color.get(-1, 000, 000, 500));
		Font.draw(Integer.toString(input.getLeftClients()), screen, 34, 64, Color.get(-1, 000, 000, 500));
		Font.draw(Integer.toString(input.getRightClients()), screen, 59, 64, Color.get(-1, 000, 000, 500));
		Font.draw(Integer.toString(input.getMenuClients()), screen, 94, 76, Color.get(-1, 000, 000, 555));
		Font.draw(Integer.toString(input.getAttackClients()), screen, 118, 76, Color.get(-1, 000, 000, 555));
		
		Font.draw("(Arrow keys,X and C)", screen, 0, screen.h - 8, Color.get(-1, 000, 000, 400));
	}
}