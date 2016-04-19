package com.mojang.controllers.crafting;

import com.mojang.controllers.entity.Player;
import com.mojang.views.item.ResourceItem;
import com.mojang.views.item.resource.Resource;

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
