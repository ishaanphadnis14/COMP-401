package a3;

public class VerticalStackPicture implements Picture {
	
	private Pixel[][] pic;
	
	public VerticalStackPicture(Picture top, Picture bottom) {
		if (top == null || bottom == null) {
			throw new IllegalArgumentException("Null");
		} 
		if (top.getWidth() != bottom.getWidth()) {
			throw new IllegalArgumentException("Null");
		}
		int tW = top.getWidth();
		int tH = top.getHeight();
		int bW = bottom.getWidth();
		int bH = bottom.getHeight();
		
		this.pic = new Pixel [tW][tH + bH];
		
		for (int i = 0; i < tW; i++) {
			for (int j = 0; j < tH; j++) {
				this.pic[i][j] = top.getPixel(i, j);
			}
		}
		
		for (int i = 0; i < bW; i++) {
			for (int j = 0; j < bH; j++) {
				this.pic[i][tW + j] = bottom.getPixel(i, j);
			}
		}
		
		}
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return pic.length;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return pic[0].length;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		// TODO Auto-generated method stub
		if (x < 0 || x >= getWidth()) {
			throw new IllegalArgumentException("Out of Range");
		}
		if (y < 0 || y >= getHeight()) {
			throw new IllegalArgumentException("Out of Range");
		}
		return pic[x][y];
	}
	
	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		// TODO Auto-generated method stub
		if (x < 0 || x >= getWidth()) {
			throw new IllegalArgumentException("Out of Range");
		}
		if (y < 0 || y >= getHeight()) {
			throw new IllegalArgumentException("Out of Range");
		}
		if (p == null) {
			throw new IllegalArgumentException("Null");
		}
		if (factor < 0.0 || factor > 1.0) {
			throw new IllegalArgumentException("Out of Range");
		}
		
		pic[x][y] = pic[x][y].blend(p, factor);
		return this;
	}
}
