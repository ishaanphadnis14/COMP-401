package a4test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import a4.*;

public class A4Test {
		
	static public String[] getTestNames() {
		String[] test_names = new String[11];
		
		test_names[0] = "exampleTest";
		test_names[1] = "testMutablePictureEquality";
		test_names[2] = "testImmutablePictureEquality";
		test_names[3] = "testMutablePictureEquality2";
		test_names[4] = "testImmutablePictureEquality2";
		test_names[5] = "testMutablePictureEquality3";
		test_names[6] = "testImmutablePictureEquality3";
		test_names[7] = "testMutablePictureEquality4";
		test_names[8] = "testImmutablePictureEquality4";
		test_names[9] = "testPicturePaintMethod";
		test_names[10] = "testPicturePaintMethod2";
		
	
		
		return test_names;
	}
		
	@Test
	public void exampleTest() {
			Pixel[][] parray = new Pixel[3][3];
			
			for (int x = 0; x < 3; x++) {
				for (int y = 0; y <3; y++) {
					parray[x][y] = new ColorPixel(0, 0, 0);
				}
			}
			
			Picture picture = new MutablePixelArrayPicture(parray, "My Caption");
			
			Iterator<Pixel> iter = picture.sample(0,  0,  2,  2);
			
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
	public void testMutablePictureEquality() {
		Picture p1 = new MutablePixelArrayPicture(new Pixel[][] {{Pixel.BLACK}}, "");
		Picture p2 = new MutablePixelArrayPicture(new Pixel[][] {{Pixel.BLACK}}, "");
		assertEquals(p1.getPixel(0, 0), p2.getPixel(0, 0));
	}
	
	@Test
	public void testImmutablePictureEquality() {
		Picture p1 = new ImmutablePixelArrayPicture(new Pixel[][] {{Pixel.BLACK}}, "");
		Picture p2 = new ImmutablePixelArrayPicture(new Pixel[][] {{Pixel.BLACK}}, "");
		assertEquals(p1.getPixel(0, 0), p2.getPixel(0, 0));
	}
	
	
	
	@Test
	public void testMutablePictureEquality2() {
		Picture p1 = new MutablePixelArrayPicture(new Pixel[][] {{Pixel.WHITE}}, "");
		Picture p2 = new MutablePixelArrayPicture(new Pixel[][] {{Pixel.WHITE}}, "");
		assertEquals(p1.getPixel(0, 0), p2.getPixel(0, 0));
	}
	@Test
	public void testImmutablePictureEquality2() {
		Picture p1 = new ImmutablePixelArrayPicture(new Pixel[][] {{Pixel.WHITE}}, "");
		Picture p2 = new ImmutablePixelArrayPicture(new Pixel[][] {{Pixel.WHITE}}, "");
		assertEquals(p1.getPixel(0, 0), p2.getPixel(0, 0));
	}
	@Test
	public void testMutablePictureEquality3() {
		Picture p1 = new MutablePixelArrayPicture(new Pixel[][] {{Pixel.WHITE}}, "");
		Picture p2 = new MutablePixelArrayPicture(new Pixel[][] {{Pixel.WHITE}}, "");
		assertEquals(p1.getPixel(0, 0), p2.getPixel(0, 0));
	}
	@Test
	public void testImmutablePictureEquality3() {
		Picture p1 = new ImmutablePixelArrayPicture(new Pixel[][] {{Pixel.WHITE}}, "");
		Picture p2 = new ImmutablePixelArrayPicture(new Pixel[][] {{Pixel.WHITE}}, "");
		assertEquals(p1.getPixel(0, 0), p2.getPixel(0, 0));
	}
	@Test
	public void testMutablePictureEquality4() {
		Picture p1 = new MutablePixelArrayPicture(new Pixel[][] {{Pixel.WHITE}}, "");
		Picture p2 = new MutablePixelArrayPicture(new Pixel[][] {{Pixel.WHITE}}, "");
		assertEquals(p1.getPixel(0, 0), p2.getPixel(0, 0));
	}
	@Test
	public void testImmutablePictureEquality4() {
		Picture p1 = new ImmutablePixelArrayPicture(new Pixel[][] {{Pixel.WHITE}},  "");
		Picture p2 = new ImmutablePixelArrayPicture(new Pixel[][] {{Pixel.WHITE}}, "");
		assertEquals(p1.getPixel(0, 0), p2.getPixel(0, 0));
	}
		@Test
	public void testPicturePaintMethod() {
		Pixel [][] grayArray = new Pixel [1][1];
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 1; j++) {
				grayArray[i][j] = new GrayPixel(1.0);
			}	
		}
		Picture p = new ImmutablePixelArrayPicture (grayArray, "picture");
		Picture paint = p.paint (0, 0, p);
		assertNotEquals(p, paint);
	}
	
	@Test
	public void testPicturePaintMethod2() {
		Pixel [][] grayArray = new Pixel [5][10];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 10; j++) {
				grayArray[i][j] = new GrayPixel(1.0);
			}	
		}
		Picture p = new ImmutablePixelArrayPicture (grayArray, "picture");
		Picture paint = p.paint (0, 0, p);
		assertNotEquals(p, paint);
	}

}

