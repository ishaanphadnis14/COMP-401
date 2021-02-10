package a5;

import java.util.Iterator;

public class TileIterator implements Iterator<SubPicture> {
	
	private Picture source;
	private int tile_width;
	private int tile_height;
	private int x;
	private int y;
	
	
	/* Constructor checks subpicture geometry for any illegal values and initializes all
	 necessary variables */	
	public TileIterator(Picture source, int tile_width, int tile_height) {
		if (source == null) {
			throw new IllegalArgumentException ("picture is null");
		}
		if (tile_width > source.getWidth() || tile_height > source.getHeight() || tile_width <= 0 ||tile_height <= 0) {
			throw new IllegalArgumentException ("tile geometry illegal");
		}
		this.source = source;
		this.tile_width = tile_width;
		this.tile_height = tile_height;
		x = 0;
		y = 0;
	}
	
	/*Checks if current y value can move any farther down. If 
	  so, the iteration continues */
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return (y <= source.getHeight() - tile_height);
	}
	
	/*Produces a subpicture of non-overlapping tilings from left to right
	and top to bottom. Partial tiles are not created, so if dimensions are not 
	a multiple of tile_width or tile_height, the last tile may not extend all
	the way to the end of the row/edge */
	@Override
	public SubPicture next() {
		SubPicture subpic = source.extract(x, y, tile_width, tile_height);
		x = x +  tile_width;
		
		if(x > source.getWidth() - tile_width) {
			x = 0;
			y += tile_height;
		}
		
		return subpic;
	}

}
