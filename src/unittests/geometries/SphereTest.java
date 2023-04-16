/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import primitives.*;
import geometries.Sphere;
import org.junit.jupiter.api.Test;


/**
 * @author Mendy&Mendy
 * Mendy Segal. 211769955. Mendysegal490@gmail.com
 * Mendy Welner 209272228. mendiwell@gmail.com
 */
class SphereTest {

	
	/**
	 * Test method for {@link geometries.Sphere#Sphere(double, primitives.Point)}.
	 */
	@Test
	void testConstructor() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Correct sphere with radius 5
		assertDoesNotThrow(() -> new Sphere(5 ,new Point(1,2,3))
				,"Failed constructing a correct sphere");
		
		// TC02: Correct sphere with radius 2.5
		assertDoesNotThrow(() -> new Sphere(2.5 ,new Point(1,2,3))
				,"Failed constructing a correct sphere");
		
		// TC03: Wrong sphere with radius 0 (all points are the same)
		assertThrows(IllegalArgumentException.class,()-> new Sphere(0 ,new Point(1,2,3))
				,"The radius of a sphere must be greater then 0");
		
		// TC04: Wrong sphere with radius -5
		assertThrows(IllegalArgumentException.class,() -> new Sphere(-5 ,new Point(1,2,3))
				,"The radius of a sphere must be greater then 0");
	}

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Testing a correct getNormal to sphere
		Sphere sphere = new Sphere(5 ,new Point(0,0,1));
		assertEquals(new Vector(0,0,1), sphere.getNormal(new Point(0,0,5))
				,"normal((0,0,5)-(0,0,1)) must be equal to (0,0,1)");
	}	
}
