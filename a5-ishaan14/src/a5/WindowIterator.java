package a5;

import java.util.Iterator;

public class WindowIterator implements Iterator<SubPicture> {
	private Picture _source;
	private int window_width;
	private int window_height;
	private int x;
	private int y;
	
	/* Constructor checks subpicture geometry for any illegal values and initializes all
	 necessary variables */	
	public WindowIterator(Picture source, int window_width, int window_height) {
		if (source == null) {
			throw new IllegalArgumentException ("picture is null");
		}
		if (window_width > source.getWidth() || window_height > source.getHeight() || window_width < 1 ||window_height < 1) {
			throw new IllegalArgumentException ("tile geometry illegal");
		}
		_source = source;
		this.window_width = window_width;
		this.window_height = window_height;
		x = 0;
		y = 0;
	}
	
	/*Checks if current y value can move any farther down. If 
	  so, the iteration continues.*/
	@Override
	public boolean hasNext() {
		return (y <= _source.getHeight() - window_height );
	}
	
	/* Produces the according subpicture by moving to the left to right using x
	  and top to bottom using y*/
	@Override
	public SubPicture next() {
		SubPicture subpic = _source.extract(x, y, window_width, window_height);
		if (x + window_width < _source.getWidth()) {
			x += 1;
		} else {
			x = 0;
			y += 1;
		}
		
		return subpic;
	}

}
