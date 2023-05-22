/**
 * 
 */
package unittests.renderer;

import static org.junit.jupiter.api.Assertions.*;

import primitives.*;
import renderer.*;

import org.junit.jupiter.api.Test;

/**
 * @author mendy
 *
 */
class ImageWriterTest {

	/**
	 * Test method for {@link renderer.ImageWriter#ImageWriter(java.lang.String, int, int)}.
	 */
	@Test
	void testImageWriter() {
		
	}

	/**
	 * Test method for {@link renderer.ImageWriter#getNy()}.
	 */
	@Test
	void testGetNy() {
		
	}

	/**
	 * Test method for {@link renderer.ImageWriter#getNx()}.
	 */
	@Test
	void testGetNx() {
		
	}

	/**
	 * Test method for {@link renderer.ImageWriter#writeToImage()}.
	 */
	@Test
	void testWriteToImage() {
		Color yello = new Color(150,150,50);
		Color red = new Color(150,50,50);
		ImageWriter imageWriter = new ImageWriter("newImage", 800, 500);
		for(int i = 0; i < 800; i++) {
			for(int j = 0; j < 500; j++) {
				if(i % 50 == 0 || j % 50 == 0)
					imageWriter.writePixel(i,j, red);
				else
					imageWriter.writePixel(i, j, yello);
			}
				
		}
			
		
		
		
		
		
		imageWriter.writeToImage();
		
	}

	/**
	 * Test method for {@link renderer.ImageWriter#writePixel(int, int, primitives.Color)}.
	 */
	@Test
	void testWritePixel() {
	}

}
