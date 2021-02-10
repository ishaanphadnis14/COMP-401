package a5;

import java.util.Iterator;

public class ZigZagIterator implements Iterator<Pixel> {
	
	 private Picture source;
	 private int x;
	 private int y;
	 
		
	/* Constructor checks picture for null and initializes all
	necessary variables */
	public ZigZagIterator(Picture source) {
		if (source == null) {
			throw new IllegalArgumentException("picture is null");
		}
		this.source = source;
		this.x = 0;
		this.y = 0;
	}
	 
	/*Checks if current x value is less than the picture width and the current y
	  is less than the picture height. If so, iteration continues*/
	@Override
	public boolean hasNext() {
		return (x < source.getWidth()) && (y < source.getHeight());
	}
	
	/*Produces pixels of the specified picture in a zig-zag order. The algorithm to creating this  
	 pattern is shown below through the various if statements that are needed to increment x and y
	 accordingly in order to create the zig-zag*/
	@Override
	public Pixel next() {
		
		Pixel pic = source.getPixel(x, y);
	    
	    if ((x + y) % 2 == 0) {	      
	    	if (x == source.getWidth() - 1) {
	    		y += 1;
	    	} else if (y == 0) {
	    		x += 1;
	    	} else {
	    		y -= 1;
	    		x += 1;
	    	}   
	    } else if (y == source.getHeight() - 1) {
	      x += 1;
	    } else if (x == 0) {
	      y += 1;
	    } else {
	      y += 1;
	      x -= 1;
	    }
	     return pic;
	  }
}