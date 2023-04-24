/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import primitives.*;
import org.junit.jupiter.api.Test;

import geometries.Plane;

/**
 * @author Mendy&Mendy. Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955. Mendysegal490@gmail.com
 */
class PlaneTest {

	/**
	 * Test method for
	 * {@link geometries.Plane#Plane(primitives.Point, primitives.Vector)}.
	 */
	@Test
	void testConstructor() {
		// ============ Equivalence Partitions Tests ==============

		// TC01: Correct plane
		assertDoesNotThrow(() -> new Plane(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0)),
				"Failed constructing a correct polygon");

		// =============== Boundary Values Tests ==================
		// TC02: wrong plane - point 1 and 2 equal
		assertThrows(IllegalArgumentException.class,
				() -> new Plane(new Point(0, 0, 1), new Point(0, 0, 1), new Point(1, 0, 0)));

		// TC03: wrong plane - all points on the same vector
		assertThrows(IllegalArgumentException.class,
				() -> new Plane(new Point(0, 0, 1), new Point(0, 0, 2), new Point(0, 0, 3)));

	}

	/**
	 * Test method for {@link geometries.Plane#getNormal()}.
	 */
	@Test
	void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here - using a quad
		Point p1 = new Point(0, 0, 1);
		Point p2 = new Point(0, 1, 0);
		Point p3 = new Point(1, 0, 0);
		Plane pl = new Plane(p1, p2, p3);
		// ensure there are no exceptions
		assertDoesNotThrow(() -> pl.getNormal());
		// generate the test result
		Vector result = pl.getNormal();
		// ensure |result| = 1
		assertEquals(1, result.length(), 0.00000001, "Polygon's normal is not a unit vector");
		// ensure the result is orthogonal to all the edges
		assertEquals(0, result.dotProduct(p1.subtract(p2)), 0.00000001,
				"Polygon's normal is not orthogonal to one of the edges");
		assertEquals(0, result.dotProduct(p1.subtract(p3)), 0.00000001,
				"Polygon's normal is not orthogonal to one of the edges");
		assertEquals(0, result.dotProduct(p2.subtract(p3)), 0.00000001,
				"Polygon's normal is not orthogonal to one of the edges");
	}

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormalPoint() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here - using a quad
		Point p1 = new Point(0, 0, 0);
		Point p2 = new Point(1, 0, 0);
		Point p3 = new Point(0, 1, 0);
		Point p = new Point(2, 1, 0);
		Plane pl = new Plane(p1, p2, p3);
		// ensure there are no exceptions
		assertDoesNotThrow(() -> pl.getNormal(p));
		// generate the test result
		Vector result = pl.getNormal(p);
		// ensure |result| = 1
		assertEquals(1, result.length(), 0.00000001, "Polygon's normal is not a unit vector");
		// ensure the result is orthogonal to all the edges
		assertEquals(0, result.dotProduct(p1.subtract(p2)), 0.00000001,
				"Polygon's normal is not orthogonal to one of the edges");
		assertEquals(0, result.dotProduct(p1.subtract(p3)), 0.00000001,
				"Polygon's normal is not orthogonal to one of the edges");
		assertEquals(0, result.dotProduct(p2.subtract(p3)), 0.00000001,
				"Polygon's normal is not orthogonal to one of the edges");
	}

	/**
	 * Test method for
	 * {@link geometries.Plane#testFindIntsersections(primitives.Point)}.
	 */
	@Test
	void testFindIntersections() {
		Plane plane = new Plane(new Point(1, 0, 0),new Vector(0, 0, 1));
		// ============ Equivalence Partitions Tests ==============
		// TC01: Testing ray intersects the plane
		Ray ray1 = new Ray(new Point(1, -1, -1), new Vector(0, 1, 1));
		assertEquals(new Point(1, 0, 0), plane.findIntsersections(ray1));
		assertEquals(1, plane.findIntsersections(ray1).size(), "Faild");

		// TC02: Testing ray does not intersects the plane
		Ray ray2 = new Ray(new Point(0, 1, 1), new Vector(0, 1, 1));
		assertEquals(null, plane.findIntsersections(ray2), "Faild");

		// =============== Boundary Values Tests ==================
		// group 1 - ray parallel to the plane, no intersections
		// TC01: ray included in the plane
		Ray ray3 = new Ray(new Point(0, 0, 0), new Vector(0, 1, 0));
		assertEquals(null, plane.findIntsersections(ray3), "Faild");

		// TC02: ray does not included in the plane
		Ray ray4 = new Ray(new Point(0, 0, 1), new Vector(0, 1, 0));
		assertEquals(null, plane.findIntsersections(ray4), "Faild");

		// group 2 - ray is orthogonal to the plane
		// TC01: ray starts in the plane - no intersections
		Ray ray5 = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
		assertEquals(null, plane.findIntsersections(ray5), "Faild");

		// TC02: ray starts before the plane - one intersections
		Ray ray6 = new Ray(new Point(0, 0, -1), new Vector(0, 0, 1));
		assertEquals(new Point(0, 0, 0), plane.findIntsersections(ray6));
		assertEquals(1, plane.findIntsersections(ray6).size(), "Faild");

		// TC03: ray starts after the plane - no intersections
		Ray ray7 = new Ray(new Point(0, 0, 1), new Vector(0, 0, 1));
		assertEquals(null, plane.findIntsersections(ray7), "Faild");

		// group 3 - ray is not orthogonal and not parallel, no intersections
		// TC01: ray starts from the plane
		Ray ray8 = new Ray(new Point(0, 0, 0), new Vector(0, 1, 1));
		assertEquals(null, plane.findIntsersections(ray8), "Faild");

		// TC02: ray starts from the plane
		Ray ray9 = new Ray(new Point(0, 0, 1), new Vector(0, 1, 1));
		assertEquals(null, plane.findIntsersections(ray9), "Faild");
		
		// TC03: ray starts from the reference point of the plane
		Ray ray10 = new Ray(new Point(0, 0, 0), new Vector(0, 1, 1));
		assertEquals(null, plane.findIntsersections(ray10), "Faild");

	}

}
