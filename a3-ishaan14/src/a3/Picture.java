package a3;

public interface Picture {
	
	// Getters for the dimensions of a picture.
	// Width refers to the number of columns and 
	// height is the number of rows.
	
	public int getWidth();
	public int getHeight();
	

	// getPixel(x, y) retrieves the pixel at position (x,y) in the
	// picture. The coordinate (0,0) is the upper left
	// corner of the picture. The coordinate (getWidth()-1, getHeight()-1)
	// is the lower right of the picture. An IllegalArgumentException
	// is thrown if x or y are not in range.
	
	public Pixel getPixel(int x, int y);
	
	// The various forms of the paint() method return a new
	// picture object based on this picture with certain pixel
	// positions "painted" with a new value.
	
	// paint(int x, int y, Pixel p) paints the pixel at
	// position (x,y) with the pixel value p. The second 
	// form includes a factor parameter that specifies a
	// blending factor. A factor of 0.0 leaves the specified
	// pixel unchanged. A factor of 1.0 results in replacing
	// the value with the specified pixel value. Values between
	// 0.0 and 1.0 blend proportionally.
	
	default public Picture paint(int x, int y, Pixel p) {
		return paint(x, y, p, 1.0);
	}
	public Picture paint(int x, int y, Pixel p, double factor);
	
	
	// paint(int ax, int ay, int bx, int by, Pixel p) paints the
	// rectangular region defined by the positions (ax, ay) and
	// (bx, by) with the specified pixel value. The second form
	// should blend with the specified factor as previously described.
	
	default public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		return paint (ax, ay, bx, by, p, 1.0);
		
	}
	default public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if (ax > getWidth() - 1 || ay > getWidth() - 1 || bx > getWidth() - 1 || by > getWidth() - 1) {
			throw new RuntimeException("Out of Bounds");
		}
		if (p == null) {
			throw new IllegalArgumentException("Null");
		}
		if (factor > 1 || factor < 0) {
			throw new IllegalArgumentException("Null");
		}
		int maxX = 0;
		int maxY = 0;
		int minX = 0;
		int minY = 0;
		
		if (ax < bx) {
			maxX = bx;
			minX = ax;
		} else {
			maxX = ax;
			minX = bx;
		}
		
		if (ay < by) {
			maxY = by;
			minY = ay;
		} else {
			maxY = ay;
			minY = by;
		}
		
		if (minX < 0) {
			minX = 0;
		} 
		
		if (minY < 0) {
			minY = 0;
		} 
		
		Picture pic = this;
		
		for (int i = minX; i <= maxX; i++) {
			for (int j = minY; j <= maxY; j++) {
				pic = pic.paint(i, j, p, factor);
			}
		}
		return pic;
	}

	// paint(int cx, int cy, double radius, Pixel p) sets all pixels in the
	// picture that are within radius distance of the coordinate (cx, cy) to the
	// Pixel value p.  Only positive values of radius should be allowed. Any
	// value of cx and cy should be allowed (even if negative or otherwise
	// outside of the boundaries of the frame). 
	
	// Calculate the distance of a particular (x,y) position to (cx,cy) with
	// the expression: Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy))	

	// The second form with factor, blends as previously described.
	
	default public Picture paint(int cx, int cy, double radius, Pixel p) {
		return paint (cx, cy, radius, p, 1.0);
	}
	default public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if(radius < 0) {
		throw new RuntimeException("Radius cannot be less than 0");
	}
		if (p == null) {
			throw new IllegalArgumentException("Null");
		}
		if (factor > 1 || factor < 0) {
			throw new IllegalArgumentException("Null");
		}
		int maxX = (int)(cx + (radius + 1));
		int maxY = (int)(cy + (radius + 1));
		int minX = (int)(cx - (radius + 1));
		int minY = (int)(cy - (radius + 1));
		
		if (minX < 0) {
			minX = 0;
		} 
		if (maxX > (getWidth() - 1)) {
			maxX = getWidth();
		}
		if (minY < 0) {
			minY = 0;
		}
		if (maxX >= (getHeight() - 1)) {
			maxY = getHeight();
		}
		
		Picture pic = this;
		for (int i = minX; i < maxX; i++) {
			for (int j = minY; j < maxY; j++) {
				if ((Math.sqrt((cx-i) * (cx-i) + (cy - j) * (cy - j))) <= radius) {
					pic = pic.paint(i, j, p, factor);
				}
			}
		}
		return pic;
	}
		
}
