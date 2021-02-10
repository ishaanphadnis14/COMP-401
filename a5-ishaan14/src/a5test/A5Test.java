package a5test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import a5.*;

public class A5Test {
	// Initialize different pixel amounts.
	Pixel red = new ColorPixel(1, 0, 0);
	Pixel green = new ColorPixel(0, 1, 0);
	Pixel blue = new ColorPixel(0, 0, 1);	
	
	// Valid Pixel 2d Arrays of 5*5.
	Pixel[][] redPicture = { { red, red, red, red, red }, { red, red, red, red, red },
			{ red, red, red, red, red }, { red, red, red, red, red }, { red, red, red, red, red }};
	
	// Valid Immutable Pixel Array Picture initializations.
	Picture redImmutablePicture = new ImmutablePixelArrayPicture(redPicture, "red immutable picture");
	
	// Valid Mutable Pixel Array Picture initializations.
	Picture redMutablePicture = new MutablePixelArrayPicture(redPicture, "red immutable picture");
	
	static public String[] getTestNames() {
		String[] test_names = new String[10];
		
		test_names[0] = "testSampler";
		test_names[1] = "testSubPicturePaint";
		test_names[2] = "testSubPictureCaption";
		test_names[3] = "testSubPictureGetPixel";
		test_names[4] = "testZigZag";
		test_names[5] = "testWindow";
		test_names[6] = "testTile";
		test_names[7] = "testExtract";
		test_names[8] = "testImmutablePaint";
		test_names[9] = "testMutablePaint";
		
		return test_names;
	}
	
	@Test
	public void testSampler() {
		// The sample method of a Picture object creates a Pixel 
		// iterator that produces Pixel objects from the picture. 
		// The iterator begins with the specified initial pixel location 
		// and then samples left to right the specified dx columns and then 
		// top to bottom by the specified dy rows.
		Pixel[][] parray = new Pixel[3][3];
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				parray[i][j] = new ColorPixel(0, 0, 0);
			}
		}
		
		Picture picture = new MutablePixelArrayPicture(parray, "Picture");
		Iterator<Pixel> iter = picture.sample(0, 0, 2, 2);
		
		assertTrue(iter.hasNext());
		assertEquals(parray[0][0], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(parray[2][0], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(parray[0][2], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(parray[2][2], iter.next());
		assertFalse(iter.hasNext());	
	}
	
	@Test
	public void testSubPicturePaint() {
		// The paint operations are delegated to the underlying source picture 
		// object and the result of a paint operation for a SubPicture will either 
		// be itself if the underlying source Picture object is mutable with respect 
		// to pixel information or a new SubPicture object if the underlying source 
		// Picture object is immutable with respect to pixel information.
		// 1. Test immutable source picture.
		SubPicture subImmuPic = redImmutablePicture.extract(1, 1, 2, 3);
		Picture pic1 = subImmuPic.paint(1, 1, blue);
		assertNotEquals(pic1, subImmuPic);
		
		// 2. Test mutable source picture.
		SubPicture subMutPic = redMutablePicture.extract(1, 1, 2, 3);
		subMutPic.paint(1, 1, blue);
		Picture pic2 = subMutPic.paint(1, 1, blue);
		assertEquals(pic2, subMutPic);

	}
	
	@Test
	public void testSubPictureGetPixel() {
		// The getPixel method of a SubPicture will translate the (x,y) coordinates 
		// provided by xOffset and yOffset and retrieve the corresponding pixel from the source picture. 
		Pixel[][] parray = new Pixel[5][5];
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				parray[i][j] = new ColorPixel(0, 0, 0);
			}
		}
		
		Picture sourcePic = new MutablePixelArrayPicture(parray, "Source Picture");
		SubPicture subPic = sourcePic.extract(1, 1, 2, 3);
		
		assertEquals(sourcePic.getPixel(1, 1), subPic.getPixel(0, 0));
		assertEquals(sourcePic.getPixel(2, 1), subPic.getPixel(1, 0));
		assertEquals(sourcePic.getPixel(1, 2), subPic.getPixel(0, 1));
		assertEquals(sourcePic.getPixel(2, 2), subPic.getPixel(1, 1));
		assertEquals(sourcePic.getPixel(1, 3), subPic.getPixel(0, 2));
		assertEquals(sourcePic.getPixel(2, 3), subPic.getPixel(1, 2));	
	}
	
	@Test
	public void testSubPictureCaption() {
		// A SubPicture created as a result of the extract() method on a 
		// Picture will start with a caption that is the same as the source picture.
		Pixel[][] parray = new Pixel[5][5];
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				parray[i][j] = new ColorPixel(0, 0, 0);
			}
		}
		
		Picture sourcePic = new MutablePixelArrayPicture(parray, "Source Picture");
		SubPicture subPic = sourcePic.extract(0, 0, 2, 3);
		assertEquals(sourcePic.getCaption(), subPic.getCaption());
		
		// A SubPicture maintains its own caption information independently 
				// of the underlying source Picture.
				subPic.setCaption("SubPicture");
				assertNotEquals(sourcePic.getCaption(), subPic.getCaption());
		
	}
	
	@Test
	public void testZigZag() {
		Pixel[][] parray = new Pixel[3][3];
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				parray[i][j] = new ColorPixel(0, 0, 0);
			}
		}
		
		Picture picture = new MutablePixelArrayPicture(parray, "Picture for zig zag");
		Iterator<Pixel> iter = picture.zigzag();
		
		assertTrue(iter.hasNext());
		assertEquals(parray[0][0], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(parray[1][0], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(parray[0][1], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(parray[0][2], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(parray[1][1], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(parray[2][0], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(parray[2][1], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(parray[1][2], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(parray[2][2], iter.next());
		assertFalse(iter.hasNext());	
	}
	
	@Test
	public void testWindow() {
		Pixel[][] parray = new Pixel[3][3];
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				parray[i][j] = new ColorPixel(0, 0, 0);
			}
		}
		
		Picture picture = new MutablePixelArrayPicture(parray, "Picture for window");
		Iterator<SubPicture> iter = picture.window(2, 2);
		
		assertTrue(iter.hasNext());
		SubPicture next = iter.next();
		assertEquals(picture.extract(0, 0, 2, 2).getPixel(0, 0), next.getPixel(0, 0));
		assertEquals(picture.extract(0, 0, 2, 2).getPixel(0, 1), next.getPixel(0, 1));
		assertEquals(picture.extract(0, 0, 2, 2).getPixel(1, 0), next.getPixel(1, 0));
		assertEquals(picture.extract(0, 0, 2, 2).getPixel(1, 1), next.getPixel(1, 1));
		assertTrue(iter.hasNext());
		next = iter.next();
		assertEquals(picture.extract(1, 0, 2, 2).getPixel(0, 0), next.getPixel(0, 0));
		assertEquals(picture.extract(1, 0, 2, 2).getPixel(0, 1), next.getPixel(0, 1));
		assertEquals(picture.extract(1, 0, 2, 2).getPixel(1, 0), next.getPixel(1, 0));
		assertEquals(picture.extract(1, 0, 2, 2).getPixel(1, 1), next.getPixel(1, 1));
		assertTrue(iter.hasNext());
		next = iter.next();
		assertEquals(picture.extract(0, 1, 2, 2).getPixel(0, 0), next.getPixel(0, 0));
		assertEquals(picture.extract(0, 1, 2, 2).getPixel(0, 1), next.getPixel(0, 1));
		assertEquals(picture.extract(0, 1, 2, 2).getPixel(1, 0), next.getPixel(1, 0));
		assertEquals(picture.extract(0, 1, 2, 2).getPixel(1, 1), next.getPixel(1, 1));
		assertTrue(iter.hasNext());
		next = iter.next();
		assertEquals(picture.extract(1, 1, 2, 2).getPixel(0, 0), next.getPixel(0, 0));
		assertEquals(picture.extract(1, 1, 2, 2).getPixel(0, 1), next.getPixel(0, 1));
		assertEquals(picture.extract(1, 1, 2, 2).getPixel(1, 0), next.getPixel(1, 0));
		assertEquals(picture.extract(1, 1, 2, 2).getPixel(1, 1), next.getPixel(1, 1));
		assertFalse(iter.hasNext());	
	}
	
	@Test
	public void testTile() {
	Pixel[][] parray = new Pixel[5][5];
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				parray[i][j] = new ColorPixel(0, 0, 0);
			}
		}
		
		Picture picture = new MutablePixelArrayPicture(parray, "Picture for tile");
		Iterator<SubPicture> iter = picture.tile(2, 2);
		
		assertTrue(iter.hasNext());
		SubPicture next = iter.next();
		assertEquals(picture.extract(0, 0, 2, 2).getPixel(0, 0), next.getPixel(0, 0));
		assertEquals(picture.extract(0, 0, 2, 2).getPixel(0, 1), next.getPixel(0, 1));
		assertEquals(picture.extract(0, 0, 2, 2).getPixel(1, 0), next.getPixel(1, 0));
		assertEquals(picture.extract(0, 0, 2, 2).getPixel(1, 1), next.getPixel(1, 1));
		assertTrue(iter.hasNext());
		next = iter.next();
		assertEquals(picture.extract(2, 0, 2, 2).getPixel(0, 0), next.getPixel(0, 0));
		assertEquals(picture.extract(2, 0, 2, 2).getPixel(0, 1), next.getPixel(0, 1));
		assertEquals(picture.extract(2, 0, 2, 2).getPixel(1, 0), next.getPixel(1, 0));
		assertEquals(picture.extract(2, 0, 2, 2).getPixel(1, 1), next.getPixel(1, 1));
		assertTrue(iter.hasNext());
		next = iter.next();
		assertEquals(picture.extract(0, 2, 2, 2).getPixel(0, 0), next.getPixel(0, 0));
		assertEquals(picture.extract(0, 2, 2, 2).getPixel(0, 1), next.getPixel(0, 1));
		assertEquals(picture.extract(0, 2, 2, 2).getPixel(1, 0), next.getPixel(1, 0));
		assertEquals(picture.extract(0, 2, 2, 2).getPixel(1, 1), next.getPixel(1, 1));
		assertTrue(iter.hasNext());
		next = iter.next();
		assertEquals(picture.extract(2, 2, 2, 2).getPixel(0, 0), next.getPixel(0, 0));
		assertEquals(picture.extract(2, 2, 2, 2).getPixel(0, 1), next.getPixel(0, 1));
		assertEquals(picture.extract(2, 2, 2, 2).getPixel(1, 0), next.getPixel(1, 0));
		assertEquals(picture.extract(2, 2, 2, 2).getPixel(1, 1), next.getPixel(1, 1));
		assertFalse(iter.hasNext());	
	}
	
	@Test
	public void testExtract() {
		Pixel[][] parray = new Pixel[3][3];
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				parray[i][j] = new ColorPixel(0, 0, 0);
			}
		}
		
		Picture picture = new MutablePixelArrayPicture(parray, "Picture for test extract");
		SubPicture subPic = picture.extract(1, 1, 2, 2);
		
		assertEquals(subPic.getPixel(0, 0), picture.getPixel(1, 1));
		assertEquals(subPic.getPixel(1, 0), picture.getPixel(2, 1));
		assertEquals(subPic.getPixel(0, 1), picture.getPixel(1, 2));
		assertEquals(subPic.getPixel(1, 1), picture.getPixel(2, 2));
	}
	
	@Test
	public void testImmutablePaint() {
		// Should return a new Mutable Picture with painted pixel(s).
		// Original picture should remain the same.
		Picture pic = redImmutablePicture.paint(1, 1, blue);
		assertNotEquals(pic, redImmutablePicture);
		assertEquals(redImmutablePicture.getPixel(1, 1), red);
		assertEquals(pic.getPixel(1, 1).getRed(), 0, 0.0000001);
		assertEquals(pic.getPixel(1, 1).getGreen(), 0, 0.0000001);
		assertEquals(pic.getPixel(1, 1).getBlue(), 1, 0.0000001);
	}
	
	@Test
	public void testMutablePaint() {
		Picture pic = redMutablePicture.paint(1, 1, blue);
		assertEquals(pic, redMutablePicture);
		assertEquals(pic.getPixel(1, 1).getRed(), 0, 0.0000001);
		assertEquals(pic.getPixel(1, 1).getGreen(), 0, 0.0000001);
		assertEquals(pic.getPixel(1, 1).getBlue(), 1, 0.0000001);
		assertEquals(redMutablePicture.getPixel(1, 1).getRed(), 0, 0.0000001);
		assertEquals(redMutablePicture.getPixel(1, 1).getGreen(), 0, 0.0000001);
		assertEquals(redMutablePicture.getPixel(1, 1).getBlue(), 1, 0.0000001);
	}
}

