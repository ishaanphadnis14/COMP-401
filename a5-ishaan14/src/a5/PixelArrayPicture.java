package a5;

//this class is abstract because it contains abstract methods
abstract public class PixelArrayPicture extends PictureImpl implements Picture {
	
	protected Pixel[][] pixel;
	
	/*super is used in constructor to signify that caption is being inherited from a superior class. The constructor 
	also checks to make sure caption isn't null initializes the according Pixel array 
	through a private constructor*/
	public PixelArrayPicture(Pixel[][] parray, String caption) {
		super(caption);
		if (caption == null) {
			throw new IllegalArgumentException("caption is null");
	  }
		
		pixel = copyPixelArray(parray);
	}
	
	/*all methods below are from the original downloaded project and 
	are common between ImmutablePixelArray and MutablePixelArray, 
	thereby allowing the use of inheritance while extending the
	PictureImpl class*/
	public int getWidth() {
		
		return pixel.length;
	}
		  
	public int getHeight() {
		return pixel[0].length;
	}
	
	public Pixel getPixel(int x, int y) {
	if ((x < 0) || (x >= getWidth()) || (y < 0) || (y >= getHeight())) {
		throw new IllegalArgumentException("x or y out of bounds");
	}
		return pixel[x][y];
	}

	private static Pixel[][] copyPixelArray(Pixel[][] pixel_array) {
		
		if (pixel_array == null) {
			throw new IllegalArgumentException("pixel_array is null");
		}
		int width = pixel_array.length;
		
		if (width == 0) {
			throw new IllegalArgumentException("pixel_array has illegal geometry");			
		}
		
		for (int x=0; x<width; x++) {
			if (pixel_array[x] == null) {
				throw new IllegalArgumentException("pixel_array includes null columns");			
			}
		}
		
		int height = pixel_array[0].length;
		if (height == 0) {
			throw new IllegalArgumentException("pixel_array has illegal geometry");						
		}
		
		for (int x=0; x<width; x++) {
			if (pixel_array[x].length != height) {
				throw new IllegalArgumentException("Columns in picture are not all the same height.");			
			}
		}

		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				if (pixel_array[x][y] == null) {
					throw new IllegalArgumentException("pixel_array includes null pixels");								
				}
			}
		}
		
		Pixel[][] copy = new Pixel[width][height];
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				copy[x][y] = pixel_array[x][y];
			}
		}

		return copy;
}
}
