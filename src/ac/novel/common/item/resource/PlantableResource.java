package ac.novel.common.item.resource;

import ac.novel.common.entity.Player;
import ac.novel.common.level.Level;
import ac.novel.common.level.tile.Tile;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class PlantableResource extends Resource {
    private static final long serialVersionUID = 42L;
	private List<Tile> sourceTiles;
	private Tile targetTile;

	public PlantableResource(String name, int sprite, int color, Tile targetTile, Tile... sourceTiles1) {
		this(name, sprite, color, targetTile, Arrays.asList(sourceTiles1));
	}

	public PlantableResource(String name, int sprite, int color, Tile targetTile, List<Tile> sourceTiles) {
		super(name, sprite, color);
		this.sourceTiles = sourceTiles;
		this.targetTile = targetTile;
	}

	public boolean interactOn(Tile tile, Level level, int xt, int yt, Player player, int attackDir) {
		if (sourceTiles.contains(tile)) {
			level.setTile(xt, yt, targetTile, 0);
			return true;
		}
		return false;
	}
}
