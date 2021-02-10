package a3;

public class MutablePixelArrayPicture implements Picture {
	
	//private int width;
	//private int height;
	private Pixel [][] pixel_array;

	public MutablePixelArrayPicture(Pixel[][] pixel_array) {
		// TODO Auto-generated constructor stub
		if (pixel_array == null) {
			throw new IllegalArgumentException("Null");
		}
		int width = pixel_array.length;
		if (width == 0) {
			throw new IllegalArgumentException("Width can't = 0");
		}	
		for (int i = 0; i < width; i++) {
			if (pixel_array[i] == null) {
				throw new IllegalArgumentException("Column is null");
			}
		}
		int height = pixel_array[0].length;
		
		if (height == 0) {
			throw new IllegalArgumentException("Height can't = 0");
		}	
		for (int i = 0; i < width; i++) {
			if (pixel_array[i].length != height) {
				throw new IllegalArgumentException("Columns don't match");
			}
		}
		
		for (int i = 0; i < pixel_array.length; i++) {
			for (int j = 0; j < pixel_array[0].length; j++) {
				if (pixel_array[i][j] == null) {
					throw new IllegalArgumentException("Null");
				}
			}
		}	
		this.pixel_array = new Pixel [pixel_array.length][pixel_array[0].length];
		this.pixel_array = pixel_array;
	}

	
	public MutablePixelArrayPicture(int width, int height, Pixel initial_value) {
		// TODO Auto-generated constructor stub
		if (width < 1 || height < 1) {
			throw new IllegalArgumentException("Width/Height can't = 0");
		}
		
		this.pixel_array = new Pixel [width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				pixel_array[i][j] = initial_value;
			}
		} 
		}


	public MutablePixelArrayPicture(int width, int height) {
		// TODO Auto-generated constructor stub
		if (width < 1 || height < 1) {
			throw new IllegalArgumentException("Width/Height can't = 0");
		}

		this.pixel_array = new Pixel [width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				pixel_array[i][j] = new GrayPixel(0.5);
			}
		}
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return this.pixel_array.length;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return this.pixel_array[0].length;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		// TODO Auto-generated method stub
		if (x < 0 || x > pixel_array.length) {
			throw new IllegalArgumentException("Out of Range");
		}
		if (y < 0 || y > pixel_array[0].length) {
			throw new IllegalArgumentException("Out of Range");
		}
		return pixel_array [x][y];
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
			throw new IllegalArgumentException("Not in Bounds");
		}
		// TODO Auto-generated method stub
		pixel_array[x][y] = pixel_array[x][y].blend(p, factor);
		
		return this;
	}
}



