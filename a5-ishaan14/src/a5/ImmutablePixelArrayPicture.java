package a5;

import java.util.Iterator;

public class ImmutablePixelArrayPicture extends PixelArrayPicture implements Picture {

	public ImmutablePixelArrayPicture(Pixel[][] parray, String caption) {
		/*use of super in the constructor signifies that parray and caption are 
		being inherited from superior class*/
		 super(parray, caption);
	}
	
	/*all methods below are from the original downloaded project and are unique to only ImmutablePixel, 
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

		return (new MutablePixelArrayPicture(pixel, getCaption())).paint(x, y, p,factor);
	}

	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		return copyAsImmutable(super.paint(ax, ay, bx, by, p, factor));
	}
	
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		return copyAsImmutable(super.paint(cx, cy, radius, p, factor));
	}
	
	public Picture paint(int x, int y, Picture p, double factor) {
		return copyAsImmutable(super.paint(x, y, p, factor));
	}
	
	private static Picture copyAsImmutable(Picture p) {
		if (p == null) {
			throw new IllegalArgumentException("Picture p is null");
		}
		
		Pixel[][] parray = new Pixel[p.getWidth()][p.getHeight()];
		for (int x=0; x<p.getWidth(); x++) {
			for (int y=0; y<p.getHeight(); y++) {
				parray[x][y] = p.getPixel(x, y);
			}
		}
		return new ImmutablePixelArrayPicture(parray, p.getCaption());		
	}
}
