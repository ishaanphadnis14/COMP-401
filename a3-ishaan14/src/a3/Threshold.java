package a3;

public class Threshold implements PixelTransformation {
	
	private double threshold;
	
	public Threshold (double threshold) {
		if (threshold > 1.0 || threshold < 0.0) {
			throw new IllegalArgumentException("Not in Range");
			
		}
		this.threshold = threshold;
	}
	@Override
	public Pixel transform(Pixel p) {
		// TODO Auto-generated method stub
		if (p.getIntensity() > this.threshold) {
			return p.lighten(1.0);
		} else {
			return p.darken(1.0);
		}
	}

}
