package ac.novel.common.crafting;

import java.io.Serializable;

import ac.novel.common.entity.Player;
import ac.novel.common.item.ResourceItem;
import ac.novel.common.item.resource.Resource;

public class ResourceRecipe extends Recipe {
	private Resource resource;

	public ResourceRecipe(Resource resource) {
		super(new ResourceItem(resource, 1));
		this.resource = resource;
	}

	public void craft(Player player) {
		player.inventory.add(0, new ResourceItem(resource, 1));
	}
}
