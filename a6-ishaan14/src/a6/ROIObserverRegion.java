package a6;

public class ROIObserverRegion {
	
	public ROIObserver observer;
	public Region region;
	
	public ROIObserverRegion(ROIObserver o, Region r) {
		observer = o;
		region = r;
	}

	public ROIObserver getObserver() {
		return observer;
	}

	public Region getRegion() {
		return region;
	}
}
