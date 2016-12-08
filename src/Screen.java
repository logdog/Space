
public class Screen {

	protected int[] pixels;
	private int width, height;
	
	public Screen(int width, int height) {
		this.pixels = new int[width * height];
		this.width = width;
		this.height = height;
	}
	
	public void clear() {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	
	public void drawRect(int x, int y, int w, int h, int col) {
		for (int yy = 0; yy < h; yy++) {
			int yp = yy + y;
			if (yp < 0 || yp >= height)
				continue;
			for (int xx = 0; xx < w; xx++) {
				int xp = xx + x;
				if (xp < 0 || xp >= width)
					continue;

				if (xx == 0 || yy == 0 || xx == w - 1 || yy == h - 1)
					pixels[xp + yp * width] = col;
			}
		}
	}
	
	public void fillRect(int x, int y, int w, int h, int col) {
		for (int yy = 0; yy < h; yy++) {
			int yp = yy + y;
			if (yp < 0 || yp >= height)
				continue;
			for (int xx = 0; xx < w; xx++) {
				int xp = xx + x;
				if (xp < 0 || xp >= width)
					continue;

				if (col != 0xff00ff)
					pixels[xp + yp * width] = col;
			}
		}
	}
	
}
