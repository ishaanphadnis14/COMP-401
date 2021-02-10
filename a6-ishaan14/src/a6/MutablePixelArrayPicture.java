package a6;

public class MutablePixelArrayPicture extends PixelArrayPicture implements Picture {

	/*use of super in the constructor signifies that parray and caption are 
	being inherited from an upper class*/
	public MutablePixelArrayPicture(Pixel[][] parray, String caption) {
		super(parray, caption);
	}
	
	//all methods that follow are from original repository
	@Override
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

		_pixels[x][y] = _pixels[x][y].blend(p,  factor);
		
		return this;
	}

}
