package a5;

public class SubPictureImpl extends PictureImpl implements SubPicture {
  
  private Picture source;
  private int xoffset;
  private int yoffset;
  private int width;
  private int height;
  private String caption;
  
  /*constructor checks for any illegal values in Subpicture geometry and initializes all
  necessary values */
  public SubPictureImpl(Picture source, int xoffset, int yoffset, int width, int height) {
	  
	
    //super is used here to signify that the getter for caption is inherited from a superior class
	super(checkSource(source).getCaption());
    
    if (xoffset < 0 || xoffset >= source.getWidth() || yoffset < 0 || yoffset >= source.getHeight()) {
    	 throw new IllegalArgumentException("subpicture geometry is illegal");
    }
    if (width <= 0 || xoffset + width > source.getWidth() || height <= 0 || yoffset + height > source.getHeight()) {
    	throw new IllegalArgumentException("subpicture geometry is illegal");
    }
    
    this.source = source;
    this.xoffset = xoffset;
    this.yoffset = yoffset;
    this.width = width;
    this.height = height;
    this.caption = source.getCaption();
  }
  
  /*all methods below are from the original downloaded project and are unique to only SubPictureImpl, 
  which is why they don't appear in PictureImpl*/
  private static Picture checkSource(Picture source) {
    if (source == null) {
      throw new IllegalArgumentException("source picture is null");
    }
    return source;
  }
  
  public int getWidth() {
    return width;
  }
  
  public int getHeight() {
    return height;
  }
  
  public Pixel getPixel(int x, int y) {
    return source.getPixel(xoffset + x, yoffset + y);
  }
  

  public Picture paint(int x, int y, Pixel p, double factor){
    if ((x < 0) || (x >= getWidth()) || (y < 0) || (y >= getHeight())) {
      throw new IllegalArgumentException("x/y out of bounds");
    }
    
    if (p == null) {
      throw new IllegalArgumentException("pixel is null");
    }
    
    if ((factor < 0.0) || (factor > 1.0)) {
      throw new IllegalArgumentException("factor out of bounds");
    }
    
    //Check mutability of the source picture
    Picture result = source.paint(xoffset + x, yoffset + y, p, factor);  
    if (source == result) {
      source = result;
      return this;
    }
    //if not mutable, than return Subpicture object
    SubPicture temp = result.extract(getXOffset(), getYOffset(), getWidth(), getHeight());
    temp.setCaption(getCaption());
    return temp;
  }
  

  

  public int getXOffset() {
    return xoffset;
  }
  
  public int getYOffset() {
    return yoffset;
  }
  
  public Picture getSource() {
    return source;
  }
}