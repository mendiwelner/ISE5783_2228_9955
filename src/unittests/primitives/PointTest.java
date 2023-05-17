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
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 *
 */
class PointTest {

	/**
	 * Test method for {@link primitives.Point#Point(double, double, double)}.
	 */
	@Test
	public void testConstructor() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Correct point
		assertDoesNotThrow(() -> new Point(1, 1, 1), //
				"Failed constructing a correct Point");
		// TC02: Correct point
		assertDoesNotThrow(() -> new Point(-1, -1, -1), //
				"Failed constructing a correct Point");
		// TC03: Correct Zero point
		assertDoesNotThrow(() -> new Point(0, 0, 0), //
				"ERROR: Zero point should be correct");
	}

	/**
	 * Test method for {@link primitives.Point#add(primitives.Vector)}.
	 */
	@Test
	void testAdd() {
		Point p1 = new Point(1, 2, 3);
		// ============ Equivalence Partitions Tests ==============
		// TC01: Add a negative vector to point(1,2,3)
		assertEquals(new Point(0, 0, 0), p1.add(new Vector(-1, -2, -3)),
				"Point(1,2,3) + Vector(-1,-2,-3) must be (0,0,0)");
		// TC02: Add the opposite point
		assertDoesNotThrow(() -> p1.add(new Vector(-1, -2, -3)), "This should be a correct point of (0,0,0)");
	}

	/**
	 * Test method for {@link primitives.Point#subtract(primitives.Point)}.
	 */
	@Test
	void testSubtract() {
		Point p1 = new Point(1, 2, 3);
		// ============ Equivalence Partitions Tests ==============
		// TC01: Subtract point(0,0,0) from point(1,2,3)
		assertEquals(new Vector(1, 2, 3), p1.subtract(new Point(0, 0, 0)),
				"Point(1,2,3) + Point(0,0,0) must be (1,2,3)");
		// TC02: Subtract vector(2,4,6) from point(1,2,3)
		assertEquals(new Vector(-1, -2, -3), p1.subtract(new Vector(2, 4, 6)),
				"Point(1,2,3) - Vector(2,4,6) must be (-1,-2,-3)");

		// =============== Boundary Values Tests ==================
		// TC03: Subtract point(1,2,3) from point(1,2,3)
		assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1), "This should be exeption of vector(0,0,0)");

	}

	/**
	 * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
	 */
	@Test
	void testDistanceSquared() {
		Point p1 = new Point(1, 2, 3);
		// ============ Equivalence Partitions Tests ==============
		// TC01: Test Distance Squared of point(4,5,6)
		assertEquals(27, p1.distanceSquared(new Point(4, 5, 6)), 0.000001,
				"(1-4)^2 + (2-5)^2 + (3-6)^2 must be equals to 27");
	}

	/**
	 * Test method for {@link primitives.Point#distance(primitives.Point)}.
	 */
	@Test
	void testDistance() {
		Point p1 = new Point(1, 2, 3);
		// ============ Equivalence Partitions Tests ==============
		// TC1: Test Distance of point(1,2,3)
		assertEquals(0, p1.distance(new Point(1, 2, 3)), "sqrt((1-1)^2 + (2-2)^2 + (3-3)^2) must be equals to 0");
	}
}
