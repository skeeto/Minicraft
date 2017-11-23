package ac.novel.common.item;

import java.io.Serializable;

import ac.novel.common.entity.Entity;
import ac.novel.common.entity.ItemEntity;
import ac.novel.common.entity.Player;
import ac.novel.common.gfx.Screen;
import ac.novel.common.level.Level;
import ac.novel.common.level.tile.Tile;
import ac.novel.common.screen.ListItem;

public class Item implements ListItem, Serializable {
    private static final long serialVersionUID = 123L;
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
