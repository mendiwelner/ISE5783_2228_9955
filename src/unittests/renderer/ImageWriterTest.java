/**
 * 
 */
package unittests.renderer;

import primitives.*;
import renderer.*;

import org.junit.jupiter.api.Test;

/**
 * @author mendy
 */
class ImageWriterTest {

	/**
	 * Test method for {@link renderer.ImageWriter#writeToImage()}.
	 */
	@Test
	void testWriteToImage() {
		final int width = 800;
		final int height = 500;
		final int step = 50;
		final Color yellow = new Color(240, 250, 50);
		final Color red = new Color(200, 50, 50);
		ImageWriter imageWriter = new ImageWriter("newImage", width, height);
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
					imageWriter.writePixel(i, j, i % step == 0 || j % step == 0 ? red : yellow);
		imageWriter.writeToImage();
	}
}
