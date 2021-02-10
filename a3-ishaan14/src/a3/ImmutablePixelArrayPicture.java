package a3;

public class ImmutablePixelArrayPicture implements Picture {
	
	private Pixel[][] pixel_array;
	
	public ImmutablePixelArrayPicture(Pixel[][] pixel_array) {
		if (pixel_array == null) {
			throw new IllegalArgumentException("Null");
		}

		if (pixel_array.length == 0) {
			throw new IllegalArgumentException("Width can't = 0");
		}	
		for (int i = 0; i < pixel_array.length; i++) {
			if (pixel_array[i] == null) {
				throw new IllegalArgumentException("Column is null");
			}
		}
		
		if (pixel_array[0].length == 0) {
			throw new IllegalArgumentException("Height can't = 0");
		}	
		for (int i = 0; i < pixel_array.length; i++) {
			if (pixel_array[i].length != pixel_array[0].length) {
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
		this.pixel_array = pixel_array;
	}
	public ImmutablePixelArrayPicture(int width, int height, Pixel initial_value) {
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
	public ImmutablePixelArrayPicture(int width, int height) {
		if (width <= 0 || height <= 0) {
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
		return pixel_array.length;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return pixel_array[0].length;
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
		return pixel_array[x][y];
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		Pixel[][] new_pixelArray = new Pixel[pixel_array.length][];
		for (int i = 0; i < pixel_array.length; i++) {
			new_pixelArray[i] = pixel_array[i].clone();
		}
		new_pixelArray[x][y] = new_pixelArray[x][y].blend(p, 1.0);
		return new MutablePixelArrayPicture(new_pixelArray);
		
	}
	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		// TODO Auto-generated method stub
		if (x < 0 || x >= pixel_array.length) {
			throw new IllegalArgumentException("Out of Range");
		}
		if (y < 0 || y >= pixel_array[0].length) {
			throw new IllegalArgumentException("Out of Range");
		}
		if (p == null) {
			throw new IllegalArgumentException("Null");
		}
		// TODO Auto-generated method stub
		Pixel[][] newArr = this.pixel_array;
		newArr[x][y] = p.blend(newArr[x][y], factor);
		Picture place = new MutablePixelArrayPicture(newArr);
		return place;
	}
	


}
