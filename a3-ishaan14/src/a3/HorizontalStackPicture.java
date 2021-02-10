package a3;

public class HorizontalStackPicture implements Picture {
	
	private Pixel[][] pic;
	
	public HorizontalStackPicture(Picture left, Picture right) {
		if (left == null || right == null) {
			throw new IllegalArgumentException("Null");
		} 
		if (left.getHeight() != right.getHeight()) {
			throw new IllegalArgumentException("Null");
		}
		
		int lW = left.getWidth();
		int rW = right.getWidth();
		int lH = left.getHeight();
		int rH = right.getHeight();
		this.pic = new Pixel [lW + rW][lH];
		
		for (int i = 0; i < lW; i++) {
			for (int j = 0; j < lH; j++) {
				this.pic[i][j] = left.getPixel(i, j);
			}
		}
		
		for (int i = 0; i < rW; i++) {
			for (int j = 0; j < rH; j++) {
				this.pic[i + lW][j] = right.getPixel(i, j);
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
		if (factor < 0 || factor > 1) {
			throw new IllegalArgumentException("Out of Range");
		}
		pic[x][y] = pic[x][y].blend(p,factor);
		return this;
		
	}
}

	
