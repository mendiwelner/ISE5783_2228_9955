/**
 * 
 */
package unittests.primitives;

import static primitives.Util.isZero;
import static org.junit.jupiter.api.Assertions.*;
import primitives.*;
import org.junit.jupiter.api.Test;


/**
 * @author Mendy&Mendy
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
		Vector v = new Vector(1, 2, 3);
		// ============ Equivalence Partitions Tests ==============
		// TC01: Correct adding vectors
		assertEquals(new Vector(2, 4, 6), v.add(new Vector(1, 2, 3)), "(1,2,3) + (-2,-4,-6) must be (-1,-2,-3)");
		// TC02: Correct adding vectors
		assertEquals(new Vector(-1, -2, -3), v.add(new Vector(-2, -4, -6)), "(1,2,3) + (-2,-4,-6) must be (-1,-2,-3)");
		
		
		// =============== Boundary Values Tests ==================
		// TC03: Getting the zero vector
		assertThrows(IllegalArgumentException.class, () -> v.add(v.scale(-1)),
				"This should be exeption of vector(0,0,0)");
	}

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	void testSubtractVector() {
		Vector v = new Vector(1, 2, 3);
		// ============ Equivalence Partitions Tests ==============
		// TC01: Correct subtracting vectors
		assertEquals(new Vector(2, 4, 6), v.subtract(new Vector(-1, -2, -3)), "(1,2,3) - (-1,-2,-3) must be (2,4,6)");
		// TC02: Correct subtracting vectors
		assertEquals(new Vector(-1, -2, -3), v.subtract(new Vector(2, 4, 6)), "(1,2,3) - (2,4,6) must be (-1,-2,-3)");

		// =============== Boundary Values Tests ==================
		// TC03: Getting the zero vector
		assertThrows(IllegalArgumentException.class, () -> v.subtract(v),
				"This should be exeption of vector(0,0,0)");
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	void testScale() {
		Vector v = new Vector(1, 2, 3);
		// ============ Equivalence Partitions Tests ==============
		// TC01: Correct positive scaling
		assertEquals(new Vector(2, 4, 6), v.scale(2), "2*(1,2,3) must be (2,4,6)");
		// TC02: Correct negative scaling
		assertEquals(new Vector(-1, -2, -3), v.scale(-1), "-1*(1,2,3) must be (-1,-2,-3)");
		// TC03: Correct negative scaling less then 1
		assertTrue(isZero(v.scale(2.5d).length() -  new Vector(2.5d,5d,7.5d).length()));
		
		// =============== Boundary Values Tests ==================
		// TC04: Getting the zero vector
		assertThrows(IllegalArgumentException.class, () -> v.scale(0),
				"0*(1,2,3) should be exeption of vector(0,0,0)");
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	void testDotProduct() {
		Vector v = new Vector(1, 2, 3);
		// ============ Equivalence Partitions Tests ==============
		// TC01: Correct Dot Product value
		assertEquals(32,v.dotProduct(new Vector(4 ,5 ,6)),"ERROR: dotProduct() wrong value");
		
		// =============== Boundary Values Tests ==================
		// TC02: orthogonal vectors
		assertEquals(0,v.dotProduct(new Vector(0 ,3 ,-2)),"ERROR: dotProduct() for orthogonal vectors is not zero");
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	void testCrossProduct() {
		Vector v1 = new Vector(1, 2, 3);
		Vector v2 = new Vector(0, 3, -2);
		// ============ Equivalence Partitions Tests ==============
		// TC01: Length of cross product between v1 and v2 should be same as mult them 
		assertTrue(isZero(v1.crossProduct(v2).length() - v1.length() * v2.length())
				,"ERROR: crossProduct() wrong result length");
				
		// =============== Boundary Values Tests ==================
		// TC02: Cross product of parallel vectors
		assertTrue(isZero(v1.crossProduct(v2).dotProduct(v1)),
				"ERROR: crossProduct() result is not orthogonal to its operands");
		// This test is the continuation of the privies test
		assertTrue(isZero(v1.crossProduct(v2).dotProduct(v2))
				,"ERROR: crossProduct() result is not orthogonal to its operands");
		//TC03: Cross product of parallel vectors
		assertThrows(Exception.class,() -> v1.crossProduct(new Vector(-2 ,-4 ,-6))
				,"ERROR: crossProduct() for parallel vectors must throw an exception");
		
	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	void testLengthSquared() {
		// ============ Equivalence Partitions Tests ==============
		//TC01: Testing vector with negative values
		assertEquals(14,new Vector(-1 ,-2 ,-3).lengthSquared(),"ERROE: wrong result length squared");
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	void testLength() {
		// ============ Equivalence Partitions Tests ==============
		//TC02: Testing vector with negative values
		assertEquals(Math.sqrt(14),new Vector(-1 ,-2 ,-3).length(),"ERROE: wrong result length squared");
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	void testNormalize() {
		Vector v = new Vector(1,2,3);
		Vector u = v.normalize();
		// ============ Equivalence Partitions Tests ==============
		//TC01: Testing normalized positive vector
		assertTrue(isZero(v.normalize().length() - 1)
				,"ERROR: the normalized vector is not a unit vector");
		//TC02: Testing normalized negative vector
		assertTrue(isZero(new Vector(-10 ,-20 ,-30).normalize().length() - 1)
				,"ERROR: the normalized vector is not a unit vector");
		
		// =============== Boundary Values Tests ==================
		//TC03: Normalized vector to v must be parallel to v
		assertThrows(Exception.class,() -> v.crossProduct(u)
				,"ERROR: the normalized vector is not parallel to the original one");
		//TC04: Normalized vector to v must be in the same direction as v
		assertTrue(v.dotProduct(u) >= 0,"ERROR: the normalized vector is opposite to the original one");
	}
}
