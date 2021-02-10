package a6;

public class RegionImpl implements Region {
	
	private int left; 
	private int top; 
	private int right; 
	private int bottom; 
	
	
	public RegionImpl(int left, int top, int right, int bottom) {
		if (left > right || top > bottom) {
			throw new IllegalArgumentException("illegal region geometry");
		}
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}

	@Override
	public int getTop() {
		// TODO Auto-generated method stub
		return top;
	}

	@Override
	public int getBottom() {
		// TODO Auto-generated method stub
		return bottom;
	}

	@Override
	public int getLeft() {
		// TODO Auto-generated method stub
		return left;
	}

	@Override
	public int getRight() {
		// TODO Auto-generated method stub
		return right;
	}

	@Override
	public Region intersect(Region other) throws NoIntersectionException {
		// TODO Auto-generated method stub
		if ((other == null) || other.getLeft() > getRight() || other.getRight() < getLeft() || 
			other.getTop() > getBottom() ||other.getBottom() < getTop()) {
				throw new NoIntersectionException();
		}
			int inter_left = other.getLeft() > getLeft() ? other.getLeft() : getLeft();
			int inter_right = other.getRight() < getRight() ? other.getRight() : getRight();
			int inter_top = other.getTop() > getTop() ? other.getTop() : getTop();
			int inter_bottom = other.getBottom() < getBottom() ? other.getBottom() : getBottom();
			
			return new RegionImpl(inter_left, inter_top, inter_right, inter_bottom);
	}

	@Override
	public Region union(Region other) {
		// TODO Auto-generated method stub
		if (other == null) {
			return this;
		}
		
		int uni_left = other.getLeft() < getLeft() ? other.getLeft() : getLeft();
		int uni_right = other.getRight() > getRight() ? other.getRight() : getRight();
		int uni_top = other.getTop() < getTop() ? other.getTop() : getTop();
		int uni_bottom = other.getBottom() > getBottom() ? other.getBottom() : getBottom();
		
		return new RegionImpl(uni_left, uni_top, uni_right, uni_bottom);
	}

}
