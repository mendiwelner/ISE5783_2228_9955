/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.Sphere;
import geometries.Tube;
import primitives.Point;
import primitives.Vector;
import static primitives.Util.isZero;

/**
 * @author Mendy&Mendy
 *
 */
class TubeTest {

	/**
	 * Test method for {@link geometries.Tube#Tube(double, primitives.Ray)}.
	 */
	@Test
	void testConstructor() {
		// ============ Equivalence Partitions Tests ==============
		Ray axisRay = new Ray(new Point(1,0,0),new Vector(0,0,1));
		// TC01: Correct Tube with radius 5
		assertDoesNotThrow(() -> new Tube(5,axisRay) ,"Failed constructing a correct Tube");
		
		// TC02: Correct Tube with radius 2.5
		assertDoesNotThrow(() -> new Tube(2.5,axisRay) ,"Failed constructing a correct Tube");
		
		// TC03: Wrong Tube with radius -3
		assertThrows(IllegalArgumentException.class, () -> new Tube(-3,axisRay) ,"The radius must be greater then 0");
		
		// TC04: Wrong Tube with radius 0
		assertThrows(IllegalArgumentException.class , ()-> new Tube(0,axisRay),"The radius cannot be 0");
	}

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
        Ray ray = new Ray(new Point(0, 2, 2), new Vector(1, 0, 0));
        Tube tube = new Tube(5,ray);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Wrong normal calculation (in case the point is not across the ray.p0)
        assertEquals(new Vector(0 ,-Math.sqrt(1/2d) ,-Math.sqrt(1/2d)), tube.getNormal(new Point(1,1,1))
                ,"getNormal does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC02: Wrong normal calculation (in case the point is across the ray.p0)
        assertEquals(new Vector(0 ,-Math.sqrt(1/2d) ,-Math.sqrt(1/2d)), tube.getNormal(new Point(0, 1, 1))
                ,"getNormal does not work correctly (Boundary test)");
	}
}
