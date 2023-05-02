package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Cylinder;
import primitives.*;

/**
 * Testing Cylinder
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com
 *         Mendy Segal. 211769955. Mendysegal490@gmail.com 
 *
 */
class CylinderTest {

	/**
	 * Test method for
	 * {@link geometries.Cylinder#Cylinder(double, primitives.Ray, double)}.
	 */
	@Test
	void testConstructor() {
		Ray axisRay = new Ray(new Point(1, 0, 0), new Vector(0, 0, 1));
		// ============ Equivalence Partitions Tests ==============
		// TC01: Correct Cylinder with radius 5 and height 10
		assertDoesNotThrow(() -> new Cylinder(5, axisRay, 10), "Failed constructing a correct cylinder");

		// TC02: Correct Cylinder with radius 2.5 and height 8.5
		assertDoesNotThrow(() -> new Cylinder(2.5, axisRay, 8.5), "Failed constructing a correct cylinder");

		// TC03: Wrong Cylinder with radius -5 and height 10
		assertThrows(IllegalArgumentException.class, () -> new Cylinder(-5, axisRay, 10),
				"Failed constructing a correct cylinder");

		// TC04: Wrong Cylinder with radius 5 and height -10
		assertThrows(IllegalArgumentException.class, () -> new Cylinder(5, axisRay, -10),
				"Failed constructing a correct cylinder");

		// TC05: Wrong Cylinder with radius 5 and height 0
		assertThrows(IllegalArgumentException.class, () -> new Cylinder(5, axisRay, 0),
				"Failed constructing a correct cylinder");
	}

	/**
	 * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
<<<<<<< HEAD
		Ray axisRay = new Ray(new Point(0,0,0),new Vector(0,0,1));
		Cylinder cyl = new Cylinder(1 , axisRay , 3);
=======
		Ray axisRay = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
		Cylinder cyl = new Cylinder(1, axisRay, 3);
>>>>>>> branch 'master' of https://github.com/mendiwelner/ISE5783_2228_9955.git
		// ============ Equivalence Partitions Tests ==============
<<<<<<< HEAD
		//TC01: Point is between the bases
		assertEquals(new Vector(1,0,0),cyl.getNormal(new Point(1,0,1)));
		
		//TC02: Point is at the top base
		assertEquals(new Vector(0,0,1),cyl.getNormal(new Point(1,0,3)));
		
		//TC03: Point is at the bottom base
		assertEquals(new Vector(0,0,-1),cyl.getNormal(new Point(1,0,0)));
		
=======
		// TC01: Point is between the bases
		assertEquals(new Vector(1, 0, 0), cyl.getNormal(new Point(1, 0, 1)));

		// TC02: Point is at the top base
		assertEquals(new Vector(0, 0, 1), cyl.getNormal(new Point(1, 0, 3)));

		// TC03: Point is at the bottom base
		assertEquals(new Vector(0, 0, -1), cyl.getNormal(new Point(0, 1, 0)));

>>>>>>> branch 'master' of https://github.com/mendiwelner/ISE5783_2228_9955.git
		// =============== Boundary Values Tests ==================
<<<<<<< HEAD
		//TC04: Point is at the top center base
		assertEquals(new Vector(0,0,1),cyl.getNormal(new Point(0,0,3)));
		
		//TC05: Point is at the bottom center base
		assertEquals(new Vector(0,0,-1),cyl.getNormal(new Point(0,0,0)));
=======
		// TC04: Point is at the top center base
		assertEquals(new Vector(0, 0, 1), cyl.getNormal(new Point(0, 0, 3)));

		// TC05: Point is at the bottom center base
		assertEquals(new Vector(0, 0, -1), cyl.getNormal(new Point(0, 0, 0)));

>>>>>>> branch 'master' of https://github.com/mendiwelner/ISE5783_2228_9955.git
		
		// Point is at the edge top base
		// assertEquals(new Vector(0,0,1),cyl.getNormal(new Point(1,0,3)));

		// Point is at the edge bottom base
		// assertEquals(new Vector(0,0,-1),cyl.getNormal(new Point(0,0,0)));
	}
	
	void testFindIntsersections() {
		fail("Not yet implemented");
	}
	
	
}
