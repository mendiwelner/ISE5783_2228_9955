/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import primitives.*;
import geometries.Sphere;
import org.junit.jupiter.api.Test;

import primitives.Vector;

/**
 * @author meniw
 *
 */
class SphereTest {

	
	/**
	 * Test method for {@link geometries.Sphere#Sphere(double, primitives.Point)}.
	 */
	@Test
	void testSphere() {
		// ============ Equivalence Partitions Tests ==============
		assertDoesNotThrow(() -> new Sphere(5 ,new Point(1,2,3))
				,"Failed constructing a correct sphere");
		
		assertDoesNotThrow(() -> new Sphere(2.5 ,new Point(1,2,3))
				,"Failed constructing a correct sphere");
		
		assertThrows(IllegalArgumentException.class,()-> new Sphere(0 ,new Point(1,2,3))
				,"The radius of a sphere must be greater then 0");
		
		assertThrows(IllegalArgumentException.class,() -> new Sphere(-5 ,new Point(1,2,3))
				,"The radius of a sphere must be greater then 0");
	}

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		fail("Not yet implemented");
	}
}
