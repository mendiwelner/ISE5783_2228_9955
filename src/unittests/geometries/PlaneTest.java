/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import primitives.*;
import org.junit.jupiter.api.Test;

import geometries.Plane;


/**
 * @author mendy
 *
 */
class PlaneTest {

	/**
	 * Test method for {@link geometries.Plane#Plane(primitives.Point, primitives.Vector)}.
	 */
	@Test
	void testPlane() {
		// ============ Equivalence Partitions Tests ==============
		
		
		
		// =============== Boundary Values Tests ==================
		assertThrows(IllegalArgumentException.class,()-> new Plane(new Point(1,2,3),new Vector(0,0,0)));		
	}

	/**
	 * Test method for {@link geometries.Plane#getNormal()}.
	 */
	@Test
	void testGetNormal() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormalPoint() {
		fail("Not yet implemented");
	}

}
