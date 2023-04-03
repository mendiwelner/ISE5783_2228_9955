/**
 * 
 */
package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;
import primitives.*;
import org.junit.jupiter.api.Test;

/**
 * Tests for class {@link primitives.Point}.
 * 
 * @author mendy
 *
 */
class PointTest {

	/**
	 * Test method for {@link primitives.Point#add(primitives.Vector)}.
	 */
	@Test
	void testAdd() {
		Point p1 = new Point(1, 2, 3);
		assertEquals(new Point(0,0,0) , p1.add(new Vector(-1,-2,-3))
				,"Point(1,2,3) + Vector(-1,-2,-3) must be (0,0,0)");
		assertNotEquals(new Point(2,4,5) , p1.add(new Vector(1,2,3))
				,"Point(1,2,3) + Vector(1,2,3) must be (2,4,6)");		
	}

	/**
	 * Test method for {@link primitives.Point#subtract(primitives.Point)}.
	 */
	@Test
	void testSubtract() {
		Point p1 = new Point(1, 2, 3);
		assertEquals(new Vector(1,2,3),p1.subtract(new Point(0,0,0))
				,"Point(1,2,3) + Point(0,0,0) must be (1,2,3)");
		assertEquals(new Vector(-1,-2,-3),p1.subtract(new Vector(2,4,6))
				,"Point(1,2,3) - Vector(2,4,6) must be (-1,-2,-3)");
	}

	/**
	 * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
	 */
	@Test
	void testDistanceSquared() {
		Point p1 = new Point(1, 2, 3);
		assertEquals(27 , p1.distanceSquared(new Point(4,5,6))
				,"(1-4)^2 + (2-5)^2 + (3-6)^2 must be equals to 27");
	}

	/**
	 * Test method for {@link primitives.Point#distance(primitives.Point)}.
	 */
	@Test
	void testDistance() {
		Point p1 = new Point(1, 2, 3);
		assertEquals(0 , p1.distance(new Point(1,2,3))
				,"(1-1)^2 + (2-2)^2 + (3-3)^2 must be equals to 0");
	}

}
