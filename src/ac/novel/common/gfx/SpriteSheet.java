package ac.novel.common.gfx;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class SpriteSheet implements Serializable {
	public int width, height;
	public int[] pixels;

	public SpriteSheet(BufferedImage image) {
		width = image.getWidth();
		height = image.getHeight();
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = (pixels[i] & 0xff) / 64;
		}
	}
}