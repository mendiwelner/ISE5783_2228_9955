/**
 * 
 */
package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;
import primitives.*;
import org.junit.jupiter.api.Test;

/**
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 *
 */
class RayTest {

	/**
	 * Test method for {@link primitives.Ray#getPoint(double)}.
	 */
	@Test
	void testGetPoint() {
		Ray ray = new Ray(new Point(0,0,1),new Vector(0,0,2));
		// ============ Equivalence Partitions Tests ==============
		// TC01: Positive scalar
		assertEquals(new Point(0,0,2),ray.getPoint(1),"ERROR. wrong point");	
		// TC02: Negative scalar
		assertEquals(new Point(0,0,-1),ray.getPoint(-2),"ERROR. wrong point");
		// =============== Boundary Values Tests ==================
		// TC03: Zero scalar
		assertEquals(new Point(0,0,1),ray.getPoint(0),"ERROR. wrong point");
	}

}
