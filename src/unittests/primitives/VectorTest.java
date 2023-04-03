/**
 * 
 */
package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;
import primitives.*;
import org.junit.jupiter.api.Test;

/**
 * @author mendy 
 *
 */
class VectorTest {

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	void testAddVector() {
		Vector v1 = new Vector(1,2,3);
		// what about subtract method in Vector class? 
		assertEquals(new Vector(-1,-2,-3),v1.add(new Vector(-2,-4,-6))
				,"(1,2,3) + (-2,-4,-6) must be (-1,-2,-3)");
		assertEquals(new Vector(3,6,9),v1.subtract(new Vector(-2,-4,-6))
				,"(1,2,3) - (-2,-4,-6) must be (3,6,9)");
		assertThrows(IllegalArgumentException.class,()-> v1.add(new Vector(-1,-2,-3))
				,"This should be exeption of vector(0,0,0)");
		assertThrows(IllegalArgumentException.class,()-> v1.subtract(v1)
				,"This should be exeption of vector(0,0,0)");		
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	void testScale() {
		Vector v1 = new Vector(1,2,3);
		assertEquals(new Vector(2,4,6),v1.scale(2),"2*(1,2,3) must be (2,4,6)");
		assertThrows(IllegalArgumentException.class,()-> v1.scale(0)
				,"0*(1,2,3) should be exeption of vector(0,0,0)");
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
