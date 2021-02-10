package a5;

import java.util.Iterator;

public class SampleIterator implements Iterator<Pixel> {
	
	private Picture _source;
	private int _init_x;
	private int _init_y;
	private int x;
	private int y;
	private int _dx;
	private int _dy;
	private int max_x;
	private int max_y;
	private boolean bool;
	
	/*constructor checks picture geometry for any illegal values and then initializes 
	 all necessary variables */
	public SampleIterator(Picture source, int init_x, int init_y, int dx, int dy) {
		 if (source == null) {
			throw new IllegalArgumentException("picture is null");
		 }
		 if ((init_x < 0) || (init_x > source.getWidth()) || (init_y < 0) || (init_y > source.getHeight())) {
			throw new IllegalArgumentException("illegal init_x or init_y");
		 }
		 if ((dx < 0) || (dy < 0) || dx > source.getWidth() || dy > source.getHeight()) {
			throw new IllegalArgumentException("dx/dy is negative");
		 }
		 
		 this._source = source;
		 this._dx = dx;
		 this._dy = dy;
		 this._init_x = init_x;
		 this.x = init_x;
		 this.y = init_y;
		 this.max_x = source.getWidth() - 1;
		 this.max_y = source.getHeight() - 1;
		 this.bool = true;
	}
	
	/*checks if current y value is less than or equal to total number 
	of rows. If so, iteration continues*/
	@Override
	public boolean hasNext() {
		return ((y <= max_y) && bool);
	}
	
	/* produces Pixel objects by moving to the right of a row by dx
	and then down by dy after reaching end of the row */
	@Override
	public Pixel next() {
		if (!hasNext()) {
			throw new RuntimeException ("no pixel");
		}
		Pixel p = _source.getPixel(x, y);
		x += _dx;
		
		if (x > max_x) {
			x = _init_x;
			y += _dy;
			if (y > max_y) {
				y -= _dy;
				bool = false;
			}
		}
		return p;
	}

}
