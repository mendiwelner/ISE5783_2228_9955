/**
 * 
 */
package unittests.primitives;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import primitives.*;
import org.junit.jupiter.api.Test;

import geometries.Polygon;

/**
 * @author mendy
 *
 */

public class VectorTest {

	/** Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}. */
	@Test
	public void testConstructor() {
		// ============ Equivalence Partitions Tests ==============

		// TC01: Correct vector
		assertDoesNotThrow(() -> new Vector(1, 1, 1), //
				"Failed constructing a correct polygon");
		// TC02: Correct vector
		assertDoesNotThrow(() -> new Vector(-1, -1, -1), //
				"Failed constructing a correct polygon");

		// =============== Boundary Values Tests ==================
		// TC03: zero vector, wrong!
		assertThrows(IllegalArgumentException.class, //
				() -> new Vector(0, 0, 0), //
				"ERROR: zero vector throws wrong exception");
	}

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	void testAddVector() {
		Vector v1 = new Vector(1, 2, 3);
		// ============ Equivalence Partitions Tests ==============
		// TC04: Correct adding vectors
		assertEquals(new Vector(2, 4, 6), v1.add(new Vector(1, 2, 3)), "(1,2,3) + (-2,-4,-6) must be (-1,-2,-3)");
		// TC05: Correct adding vectors
		assertEquals(new Vector(-1, -2, -3), v1.add(new Vector(-2, -4, -6)), "(1,2,3) + (-2,-4,-6) must be (-1,-2,-3)");

		// =============== Boundary Values Tests ==================
		// TC06: Getting zero vector
		assertThrows(IllegalArgumentException.class, () -> v1.add(new Vector(-1, -2, -3)),
				"This should be exeption of vector(0,0,0)");
	}

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	void testSubtractVector() {
		Vector v1 = new Vector(1, 2, 3);
		// ============ Equivalence Partitions Tests ==============
		// TC07: Correct subtracting vectors
		assertEquals(new Vector(2, 4, 6), v1.subtract(new Vector(-1, -2, -3)), "(1,2,3) - (-1,-2,-3) must be (2,4,6)");
		// TC08: Correct subtracting vectors
		assertEquals(new Vector(-1, -2, -3), v1.subtract(new Vector(2, 4, 6)), "(1,2,3) - (2,4,6) must be (-1,-2,-3)");

		// =============== Boundary Values Tests ==================
		// TC09: Getting zero vector
		assertThrows(IllegalArgumentException.class, () -> v1.subtract(new Vector(1, 2, 3)),
				"This should be exeption of vector(0,0,0)");
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	void testScale() {
		Vector v1 = new Vector(1, 2, 3);
		// ============ Equivalence Partitions Tests ==============
		// TC10: Correct scaling
		assertEquals(new Vector(2, 4, 6), v1.scale(2), "2*(1,2,3) must be (2,4,6)");
		// TC11: Correct scaling
		assertEquals(new Vector(1, 2, 3), v1.scale(1), "1*(1,2,3) must be (1,2,3)");
		// TC12: Correct scaling
		assertEquals(new Vector(-1, -2, -3), v1.scale(-1), "-1*(1,2,3) must be (-1,-2,-3)");
		// TC13: Correct scaling
		assertEquals(new Vector(-2, -4, -6), v1.scale(-2), "-2*(1,2,3) must be (-2,-4,-6)");
		
		// =============== Boundary Values Tests ==================
		// TC14: Getting zero vector
		assertThrows(IllegalArgumentException.class, () -> v1.scale(0),
				"0*(1,2,3) should be exeption of vector(0,0,0)");
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	void testDotProduct() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	void testCrossProduct() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	void testLengthSquared() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	void testLength() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	void testNormalize() {
		fail("Not yet implemented");
	}

}
