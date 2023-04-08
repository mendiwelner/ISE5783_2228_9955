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
		assertDoesNotThrow(() -> new Sphere(3 ,new Point(1,2,3))
				,"Failed constructing a correct sphere");
		
		assertThrows(IllegalArgumentException.class,()-> new Sphere(0 ,new Point(1,2,3))
				,"The radius of a sphere must be greater then zero");
	}

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		fail("Not yet implemented");
	}
}
