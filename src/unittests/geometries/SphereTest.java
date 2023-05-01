package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import primitives.*;
import geometries.Sphere;
import org.junit.jupiter.api.Test;

/**
 * @author Mendy Welner 209272228. mendiwell@gmail.com
 *         Mendy Segal. 211769955. Mendysegal490@gmail.com
 */
class SphereTest {

	/**
	 * Test method for {@link geometries.Sphere#Sphere(double, primitives.Point)}.
	 */
	@Test
	void testConstructor() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Correct sphere with radius 5
		assertDoesNotThrow(() -> new Sphere(5, new Point(1, 2, 3)), "Failed constructing a correct sphere");

		// TC02: Correct sphere with radius 2.5
		assertDoesNotThrow(() -> new Sphere(2.5, new Point(1, 2, 3)), "Failed constructing a correct sphere");

		// TC03: Wrong sphere with radius 0 (all points are the same)
		assertThrows(IllegalArgumentException.class, () -> new Sphere(0, new Point(1, 2, 3)),
				"The radius of a sphere must be greater then 0");

		// TC04: Wrong sphere with radius -5
		assertThrows(IllegalArgumentException.class, () -> new Sphere(-5, new Point(1, 2, 3)),
				"The radius of a sphere must be greater then 0");
	}

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Testing a correct getNormal to sphere
		Sphere sphere = new Sphere(5, new Point(0, 0, 0));
		assertEquals(new Vector(0, 0, 1), sphere.getNormal(new Point(0, 0, 5)),
				"normal((0,0,5)-(0,0,1)) must be equal to (0,0,1)");
	}
	/**
	 * Test method for {@link geometries.Sphere#testFindIntsersections(primitives.Point)}.
	 */
	@Test
	void testFindIntersections() {
		Sphere sphere = new Sphere(5, new Point(0, 0, 1));
		// ============ Equivalence Partitions Tests ==============
		// TC01: Testing one point of intersection, ray starts inside the sphere
		Ray ray1 = new Ray(new Point(1,1,2),new Vector(0,0,1));
		assertEquals(new Point(1,1,Math.sqrt(23) + 1),sphere.findIntsersections(ray1).get(0)
					,"This point is not the correct point");
		assertEquals(1,sphere.findIntsersections(ray1).size(),"The amount of intersections should be 1");
		
		// TC02: Testing two points of intersection, ray starts outside the sphere
		Ray ray2 = new Ray(new Point(1,1,-5),new Vector(0,0,1));
		assertTrue(sphere.findIntsersections(ray2).contains(new Point(1,1,Math.sqrt(23) + 1))
					,"The correct point is missing");
		assertTrue(sphere.findIntsersections(ray2).contains(new Point(1,1,-Math.sqrt(23) + 1))
					,"The correct point is missing");
		
		// TC03: Testing 0 points of intersection, ray does not touch sphere
		Ray ray3 = new Ray(new Point(1,1,-5),new Vector(0,1,0));
		assertEquals(null,sphere.findIntsersections(ray3),"There should be no intersections");
		
		// TC04: Testing 0 points of intersection, ray's directions opposite to sphere
		Ray ray4 = new Ray(new Point(1,1,-5),new Vector(0,0,-1));
		assertEquals(null,sphere.findIntsersections(ray4),"There should be no intersections");
		
		// =============== Boundary Values Tests ==================
		// group 1 - the ray cuts the center
		// TC01: Testing one point of intersection, ray starts in the center of the sphere
		Ray ray5 = new Ray(new Point(0,0,1),new Vector(0,0,1));
		assertEquals(new Point(0,0,6),sphere.findIntsersections(ray5).get(0)
					,"This point is not the correct point");
		assertEquals(1,sphere.findIntsersections(ray5).size()
					,"The amount of intersections should be 1");
		
		// TC02: Testing 0 point of intersection, ray starts in the edge of the sphere
		Ray ray6 = new Ray(new Point(0,0,6),new Vector(0,0,1));
		assertEquals(null,sphere.findIntsersections(ray6),"There should be no intersections");
		
		// TC03: Testing 1 point of intersection, ray starts in the edge and passing by the sphere
		Ray ray7 = new Ray(new Point(0,0,-4),new Vector(0,0,1));
		assertEquals(new Point(0,0,6),sphere.findIntsersections(ray7).get(0)
					,"This point is not the correct point");
		assertEquals(1,sphere.findIntsersections(ray7).size()
					,"The amount of intersections should be 1");
				
		// TC04: Testing 0 points of intersection, ray starts outside of the sphere
		Ray ray8 = new Ray(new Point(0,0,7),new Vector(0,0,1));
		assertEquals(null,sphere.findIntsersections(ray8),"There should be no intersections");
		
		// TC05: Testing 0 points of intersection, ray starts outside of the sphere
		Ray ray9 = new Ray(new Point(0,0,-5),new Vector(0,0,1));
		assertTrue(sphere.findIntsersections(ray9).contains(new Point(0,0,-4))
					,"The correct point is missing");
		assertTrue(sphere.findIntsersections(ray9).contains(new Point(0,0,6))
					,"The correct point is missing");
		
		// TC06: Testing one point of intersection, ray starts near the center of the sphere
		Ray ray10 = new Ray(new Point(0,0,2),new Vector(0,0,1));
		assertEquals(new Point(0,0,6),sphere.findIntsersections(ray10).get(0),"This point is not the correct point");
		assertEquals(1,sphere.findIntsersections(ray10).size(),"The amount of intersections should be 1");
		
		// group 2 - the ray starts on the edge of the sphere
		// TC01: Testing one point of intersection, ray pass the sphere
		Ray ray11 = new Ray(new Point(1,1,-Math.sqrt(23) + 1),new Vector(0,0,1));
		assertEquals(new Point(1,1,Math.sqrt(23) + 1),sphere.findIntsersections(ray11).get(0)
					,"This point is not the correct point");
		assertEquals(1,sphere.findIntsersections(ray11).size(),"The amount of intersections should be 1");
		
		// TC02: Testing 0 point of intersection, ray does not pass the sphere
		Ray ray12 = new Ray(new Point(1,1,Math.sqrt(23) + 1),new Vector(0,0,1));
		assertEquals(null,sphere.findIntsersections(ray12),"There should be no intersections");
		
		// group 3 - the ray tangent to the sphere
		// TC01: Testing 0 point of intersection, ray starts from the edge
		Ray ray13 = new Ray(new Point(0,5,1),new Vector(0,0,1));
		assertEquals(null,sphere.findIntsersections(ray13),"There should be no intersections");
		
		// TC02: Testing 0 point of intersection, ray passes the edge
		Ray ray14 = new Ray(new Point(0,5,0),new Vector(0,0,1));
		assertEquals(null,sphere.findIntsersections(ray14),"There should be no intersections");
		
		// TC03: Testing 0 point of intersection, ray does not passes the edge
		Ray ray15 = new Ray(new Point(0,5,2),new Vector(0,0,1));
		assertEquals(null,sphere.findIntsersections(ray15),"There should be no intersections");
		
		//group 4 - the ray is orthogonal to the diameter of sphere
		Ray ray16 = new Ray(new Point(0,-6,1),new Vector(0,0,1));
		assertEquals(null,sphere.findIntsersections(ray16),"There should be no intersections");
		
		
	}
}
