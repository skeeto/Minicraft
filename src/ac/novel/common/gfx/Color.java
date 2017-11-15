package ac.novel.common.gfx;

public class Color {
	
	public static final int WHITE = Color.get(-1, 555);
	public static final int GRAY = Color.get(-1, 333);
	public static final int DARK_GRAY = Color.get(-1, 222);
	public static final int BLACK = Color.get(-1, 0);
	public static final int RED = Color.get(-1, 500);
	public static final int GREEN = Color.get(-1, 50);
	public static final int BLUE = Color.get(-1, 5);
	public static final int YELLOW = Color.get(-1, 550);
	public static final int MAGENTA = Color.get(-1, 505);
	public static final int CYAN = Color.get(-1, 55);

	public static int get(int a, int b, int c, int d) {
		return (get(d) << 24) + (get(c) << 16) + (get(b) << 8) + (get(a));
	}
	
	public static int get(int a, int bcd) {
		return get(a, bcd, bcd, bcd); // just a shortcut.
	}

	public static int get(int d) {
		if (d < 0) return 255;
		int r = d / 100 % 10;
		int g = d / 10 % 10;
		int b = d % 10;
		return r * 36 + g * 6 + b;
	}

}