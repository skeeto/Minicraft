package ac.novel.common.crafting;

import java.io.Serializable;

import ac.novel.common.entity.Player;
import ac.novel.common.item.ToolItem;
import ac.novel.common.item.ToolType;

public class ToolRecipe extends Recipe {
    private static final long serialVersionUID = 42L;
	private ToolType type;
	private int level;

	public ToolRecipe(ToolType type, int level) {
		super(new ToolItem(type, level));
		this.type = type;
		this.level = level;
	}

	public void craft(Player player) {
		player.inventory.add(0, new ToolItem(type, level));
	}
}
