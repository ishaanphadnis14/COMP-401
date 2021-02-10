package a6;

import java.util.ArrayList;
import java.util.List;

public class ObservablePictureImpl implements ObservablePicture {
	
	private Picture pic;
	private ArrayList<ROIObserverRegion> observers;
	private boolean suspended;
	private Region changed_region;
	private String caption;
	private int left;
	private int right;
	private int top;
	private int bottom;
	
	public ObservablePictureImpl(Picture p) {
		if (p == null) {
			throw new IllegalArgumentException("Picture is null");
		}
		this.pic = p;
		observers = new ArrayList<ROIObserverRegion>();
		changed_region = null;	
		suspended = false;
		left = 0;
		right = 0;
		top = 0;
		bottom = 0;
			
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return this.pic.getWidth();
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return this.pic.getHeight();
	}

	@Override
	public Pixel getPixel(int x, int y) {
		// TODO Auto-generated method stub
		return this.pic.getPixel(x,y);
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		// TODO Auto-generated method stub
		this.pic = pic.paint(x, y, p);
		Region region = new RegionImpl(x, y, x, y);
		changed_region = region.union(changed_region);
		notifyObservers();
		return this;
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		// TODO Auto-generated method stub
		this.pic = pic.paint(x, y, p, factor);
		Region region = new RegionImpl(x, y, x, y);
		changed_region = region.union(changed_region);
		notifyObservers();
		return this;
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		// TODO Auto-generated method stub
		this.pic = pic.paint(ax, ay, bx, by, p);
		
		if (ax < bx) {
			left = ax;
		} else {
			left = bx;
		}
		if (ax > bx) {
			right = ax;
		} else {
			right = bx;
		}
		if (ay < by) {
			top = ay;
		} else {
			top = by;
		}
		if (ay > by) {
			bottom = ay;
		} else {
			bottom = by;
		}
		
		Region region = new RegionImpl(left, top, right, bottom);
		changed_region = region.union(changed_region);
		notifyObservers();
		return this;
		
		
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		// TODO Auto-generated method stub
		this.pic = pic.paint(ax, ay, bx, by, p, factor);
		
		if (ax < bx) {
			left = ax;
		} else {
			left = bx;
		}
		if (ax > bx) {
			right = ax;
		} else {
			right = bx;
		}
		if (ay < by) {
			top = ay;
		} else {
			top = by;
		}
		if (ay > by) {
			bottom = ay;
		} else {
			bottom = by;
		}
		
		Region region = new RegionImpl(left, top, right, bottom);
		changed_region = region.union(changed_region);
		notifyObservers();
		return this;
		
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		// TODO Auto-generated method stub
		radius = (int) radius;
		this.pic = pic.paint(cx, cy, radius, p);
		Region region = new RegionImpl(cx - (int) radius, cy - (int) radius, cx + (int) radius, cy + (int) radius);
		changed_region = region.union(changed_region);
		notifyObservers();
		return this;
		
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		// TODO Auto-generated method stub
		this.pic = pic.paint(cx, cy, radius, p, factor);
		Region region = new RegionImpl(cx - (int) radius, cy - (int) radius, cx + (int) radius, cy + (int) radius);
		changed_region = region.union(changed_region);
		notifyObservers();
		return this;
	}

	@Override
	public Picture paint(int x, int y, Picture p) {
		// TODO Auto-generated method stub
		this.pic = pic.paint(x, y, p);
		Region region = new RegionImpl(x, y, x + p.getWidth() - 1, y + p.getHeight() - 1);
		changed_region = region.union(changed_region);
		notifyObservers();
		return this;
	}

	@Override
	public Picture paint(int x, int y, Picture p, double factor) {
		// TODO Auto-generated method stub
		this.pic = pic.paint(x, y, p, factor);
		Region region = new RegionImpl(x, y, x + p.getWidth() - 1, y + p.getHeight() - 1);
		changed_region = region.union(changed_region);
		notifyObservers();
		return this;
	}

	@Override
	public String getCaption() {
		// TODO Auto-generated method stub
		return this.pic.getCaption();
	}

	@Override
	public void setCaption(String caption) {
		// TODO Auto-generated method stub		
		this.pic.setCaption(caption);

	}

	@Override
	public SubPicture extract(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return new SubPictureImpl(this, x, y, width, height);
	}

	@Override
	public void registerROIObserver(ROIObserver observer, Region r) {
		// TODO Auto-generated method stub
		if(r == null || observer == null){
			throw new IllegalArgumentException("ROIObserver/Region is null");
		}
		observers.add(new ROIObserverRegion(observer,r));

	}

	@Override
	public void unregisterROIObservers(Region r) {
		// TODO Auto-generated method stub
		if(r == null){
			throw new IllegalArgumentException("Region is Null");
		}
		ArrayList<ROIObserverRegion> keeper = new ArrayList<ROIObserverRegion>();
		for(int i = 0; i < observers.size(); i++ ){
			try{
				observers.get(i).getRegion().intersect(r);
			}catch(NoIntersectionException e){
				keeper.add(observers.get(i));
			}
		} 
		observers = keeper;

	}

	@Override
	public void unregisterROIObserver(ROIObserver observer) {
		// TODO Auto-generated method stub
		if(observer == null){
			throw new IllegalArgumentException("ROIObserver is null");
		}
		ArrayList<ROIObserverRegion> keeper = new ArrayList<ROIObserverRegion>();
		for (int i = 0; i < observers.size(); i++ ){
			if (observers.get(i).getObserver() != observer) {
				keeper.add(observers.get(i));
			}
		} 
		observers = keeper;
	}

	@Override
	public ROIObserver[] findROIObservers(Region r) {
		// TODO Auto-generated method stub
		if(r == null){
			throw new IllegalArgumentException();
		}
		ArrayList<ROIObserverRegion> keeper = new ArrayList<ROIObserverRegion>();
		for(int i = 0; i < observers.size(); i++ ){
			try{
				observers.get(i).getRegion().intersect(r);
				keeper.add(observers.get(i));
			} catch(NoIntersectionException e){
			}
		} 
		ROIObserver[] observerArray = new ROIObserver[keeper.size()];
		for (int x = 0; x < keeper.size(); x++) {
			observerArray[x] = keeper.get(x).getObserver();
		}
		return observerArray;
	}

	@Override
	public void suspendObservable() {
		// TODO Auto-generated method stub
		suspended = true;

	}

	@Override
	public void resumeObservable() {
		// TODO Auto-generated method stub
		suspended = false;
		notifyObservers();
		
	}
	

	private void notifyObservers() {
		if (suspended == true || changed_region == null) {
			return;
		}
			for (int i = 0; i < observers.size(); i++) {
				try {
					Region intersect = observers.get(i).getRegion().intersect(changed_region);
					observers.get(i).getObserver().notify(this, intersect);
				} catch (NoIntersectionException e) {
				}
			}
			changed_region = null;
		}
	}

