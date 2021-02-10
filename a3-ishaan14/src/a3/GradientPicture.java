package a3;

public class GradientPicture implements Picture {
	private int width;
	private int height;
	private Pixel UL;
	private Pixel UR;
	private Pixel LL;
	private Pixel LR;
	public GradientPicture(int width, int height, Pixel upper_left, Pixel upper_right, Pixel lower_left, Pixel lower_right) {
		if (width == 0 || height == 0) {
			throw new IllegalArgumentException("Width/Height can't = 0");
		} 
		if (width < 0 || height < 0) {
			throw new IllegalArgumentException("Width/Height can't = Negative");
		}
		if (upper_left == null) {
			throw new IllegalArgumentException("Null");
		}
		if (upper_right == null) {
			throw new IllegalArgumentException("Null");
		}
		if (lower_left == null) {
			throw new IllegalArgumentException("Null");
		}
		if (lower_right == null) {
			throw new IllegalArgumentException("Null");
		}
		this.width = width;
		this.height = height;
		this.UL = upper_left;
		this.UR = upper_right;
		this.LL = lower_left;
		this.LR = lower_right;
	}
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		// TODO Auto-generated method stub
		if (x < 0 || x > width) {
			throw new IllegalArgumentException("Out of Range");
		}
		if (y < 0 || y > height) {
			throw new IllegalArgumentException("Out of Range");
		}
		if (x == width) {
			throw new IllegalArgumentException("Out of Range");
		}
		if (y == height) {
			throw new IllegalArgumentException("Out of Range");
		}
		double factorx = (1.0 / (getWidth()-1.0)) * x;
		double factory = (1.0 / (getHeight()-1.0)) * y;
		Pixel start_of_row = UL.blend(UR, factorx);
		Pixel end_of_row = LL.blend(LR, factorx);
		Pixel middle_of_row = start_of_row.blend(end_of_row, factory);
		
		return middle_of_row;
		
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		// TODO Auto-generated method stub
		Pixel [][] pixel_array = new Pixel [getWidth()][getHeight()];
		
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < pixel_array[0].length; j++ ) {
				pixel_array[i][j] = getPixel(i, j);
			}
		}
		
		MutablePixelArrayPicture place = new MutablePixelArrayPicture(pixel_array);
		return place.paint(x, y, p, factor);
	}

}
