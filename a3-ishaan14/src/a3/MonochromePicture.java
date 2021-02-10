package a3;

public class MonochromePicture implements Picture {
	private int width;
	private int height;
	private Pixel value;

	public MonochromePicture(int width, int height, Pixel value) {
		// TODO Auto-generated constructor stub
		if (width == 0 || height == 0) {
			throw new IllegalArgumentException("Width/Height can't = 0");
		} 
		if (width < 0 || height < 0) {
			throw new IllegalArgumentException("Width/Height can't be Negative");
		}
		if (value == null) {
			throw new IllegalArgumentException("Null");
		}
		this.width = width;
		this.height = height;
		this.value = value;
		
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return this.height;
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
		return value;
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


	