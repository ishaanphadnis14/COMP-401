package a5;

import java.util.Iterator;

public class MutablePixelArrayPicture extends PixelArrayPicture implements Picture {
	
	
	public MutablePixelArrayPicture(Pixel[][] parray, String caption) {
		/*use of super in the constructor signifies that parray and caption are 
		being inherited from a superior class*/
		super(parray, caption);
	}
	
	/*all methods below are from the original downloaded project and are unique to only MutablePixel, 
	which is why they don't appear in PixelArrayPicture or PictureImpl*/
	public Picture paint(int x, int y, Pixel p, double factor) {
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new IllegalArgumentException("x or y out of bounds");
		}
		
		if (p == null) {
			throw new IllegalArgumentException("Pixel p is null");
		}
		
		if (factor < 0.0 || factor > 1.0) {
			throw new IllegalArgumentException("factor is out of bounds");
		}

		pixel[x][y] = pixel[x][y].blend(p,  factor);
		
		return this;
	}
}
