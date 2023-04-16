/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Cylinder;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * @author Mendy&Mendy 
 * Mendy Segal. 211769955. Mendysegal490@gmail.com
 * Mendy Welner 209272228. mendiwell@gmail.com
 *
 */
class CylinderTest {

	/**
	 * Test method for {@link geometries.Cylinder#Cylinder(double, primitives.Ray, double)}.
	 */
	@Test
	void testConstructor() {
		Ray axisRay = new Ray(new Point(1,0,0),new Vector(0,0,1));
		// ============ Equivalence Partitions Tests ==============
		// TC01: Correct Cylinder with radius 5 and height 10
		assertDoesNotThrow(() -> new Cylinder(5,axisRay,10) ,"Failed constructing a correct cylinder");
		
		// TC02: Correct Cylinder with radius 2.5 and height 8.5
		assertDoesNotThrow(() -> new Cylinder(2.5,axisRay,8.5) ,"Failed constructing a correct cylinder");
		
		// TC03: Wrong Cylinder with radius -5 and height 10
		assertThrows(IllegalArgumentException.class ,() -> new Cylinder(-5,axisRay,10) 
				,"Failed constructing a correct cylinder");
		
		// TC04: Wrong Cylinder with radius 5 and height -10
		assertThrows(IllegalArgumentException.class ,() -> new Cylinder(5,axisRay,-10) 
				,"Failed constructing a correct cylinder");
		
		// TC05: Wrong Cylinder with radius 5 and height 0
		assertThrows(IllegalArgumentException.class ,() -> new Cylinder(5,axisRay,0) 
				,"Failed constructing a correct cylinder");
	}
	
	/**
	 * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		Ray axisRay = new Ray(new Point(0,0,0),new Vector(0,0,1));
		Cylinder cyl = new Cylinder(1 , axisRay , 3);
		// ============ Equivalence Partitions Tests ==============
		//TC01: Point is between the bases
		assertEquals(new Vector(1,0,0),cyl.getNormal(new Point(1,0,1)));
		
		//TC02: Point is at the top base
		assertEquals(new Vector(0,0,1),cyl.getNormal(new Point(1,0,3)));
		
		//TC03: Point is at the bottom base
		assertEquals(new Vector(0,0,-1),cyl.getNormal(new Point(1,2.5,0)));
		
		// =============== Boundary Values Tests ==================
		//TC04: Point is at the top center base
		assertEquals(new Vector(0,0,1),cyl.getNormal(new Point(0,0,3)));
		
		//TC05: Point is at the bottom center base
		assertEquals(new Vector(0,0,-1),cyl.getNormal(new Point(0,0,0)));
		
		// Point is at the edge top base
		//assertEquals(new Vector(0,0,1),cyl.getNormal(new Point(1,0,3)));
		
		// Point is at the edge bottom base
		//assertEquals(new Vector(0,0,-1),cyl.getNormal(new Point(0,0,0)));
	}
}
		
