package a3;

public class TransformedPicture implements Picture {
	
	private PixelTransformation xform;
	private Picture source;
	
	public TransformedPicture (Picture source, PixelTransformation xform) {
		this.source = source;
		this.xform = xform;
	}


	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return source.getWidth();
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return source.getHeight();
	}

	@Override
	public Pixel getPixel(int x, int y) {
		// TODO Auto-generated method stub
		return xform.transform(source.getPixel(x, y));
	}


	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		// TODO Auto-generated method stub
		return source.paint(x,  y,  p, factor);
	}


}

