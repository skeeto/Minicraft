package ac.novel.common.screen;

import ac.novel.common.entity.Player;
import ac.novel.common.gfx.Font;
import ac.novel.common.gfx.Screen;
import ac.novel.common.item.Item;

public class InventoryMenu extends Menu {
	private Player player;
	private int selected = 0;

	public InventoryMenu(Player player) {
		this.player = player;

		if (player.activeItem != null) {
			player.inventory.items.add(0, player.activeItem);
			player.activeItem = null;
		}
	}

	public void tick() {
		if (input.menu.clicked) game.setMenu(null);

		if (input.up.clicked) selected--;
		if (input.down.clicked) selected++;

		int len = player.inventory.items.size();
		if (len == 0) selected = 0;
		if (selected < 0) selected += len;
		if (selected >= len) selected -= len;

		if (input.attack.clicked && len > 0) {
			Item item = player.inventory.items.remove(selected);
			player.activeItem = item;
			game.setMenu(null);
		}
	}

	public void render(Screen screen) {
		Font.renderFrame(screen, "inventory", 1, 1, 12, 11);
		renderItemList(screen, 1, 1, 12, 11, player.inventory.items, selected);
	}
}