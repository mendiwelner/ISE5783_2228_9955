package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.Triangle;
import java.util.List;

/**
 * Testing Triangle
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com Mendy Segal.
 *         211769955.Mendysegal490@gmail.com
 */
class TriangleTest {

	/**
	 * Test method for {@link geometries.Sphere#Sphere(double, primitives.Point)}.
	 */
	@Test
	void testConstructor() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Correct Triangle
		assertDoesNotThrow(() -> new Triangle(new Point(1, 2, 3), new Point(-2, -3, -4), new Point(5, 6, 7)),
				"Failed constructing a correct sphere");

		// TC02: p1 equals to p2
		assertThrows(IllegalArgumentException.class,
				() -> new Triangle(new Point(1, 2, 3), new Point(1, 2, 3), new Point(5, 5, 5)),
				"All points must be diffrent");

		// TC03: Points on the same line
		assertThrows(IllegalArgumentException.class,
				() -> new Triangle(new Point(1, 2, 3), new Point(2, 3, 4), new Point(4, 5, 6)),
				"The points cannot be on the same line");
	}

	/**
	 * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here

		Point[] pts = { new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0) };
		Triangle trgle = new Triangle(pts[0], pts[1], pts[2]);
		// ensure there are no exceptions
		assertDoesNotThrow(() -> trgle.getNormal(new Point(0, 0, 1)), "Failed calculating a correct normal");
		// generate the test result
		Vector result = trgle.getNormal(new Point(0, 0, 1));
		// ensure |result| = 1
		assertEquals(1, result.length(), 0.00000001, "Triangle's normal is not a unit vector");
		// ensure the result is orthogonal to all the edges
		for (int i = 0; i < 3; ++i)
			assertEquals(0, result.dotProduct(pts[i].subtract(pts[i == 0 ? 2 : i - 1])), 0.00000001,
					"Triangle's normal is not orthogonal to one of the edges");
	}

	/**
	 * Test method for
	 * {@link geometries.Triangle#findIntersections(primitives.Ray)}.
	 */
	@Test
	void testFindIntersections() {

		Triangle trgle = new Triangle(new Point(0, 0, 1), new Point(5, 0, 1), new Point(0, 5, 1));
		// ============ Equivalence Partitions Tests ==============
		// TC01: intersection in the Triangle
		Ray ray1 = new Ray(new Point(1, 1, 0), new Vector(0, 0, 1));
		assertEquals(List.of(new Point(1, 1, 1)), trgle.findIntersections(ray1), "This point is not the correct point");

		// TC02: intersection outside of the Triangle, against one of the ribs
		Ray ray2 = new Ray(new Point(-1, 1, -1), new Vector(0, 0, 1));
		assertNull(trgle.findIntersections(ray2), "There should be no intersections");

		// TC03: intersection outside of the Triangle, against one of the vertexes
		Ray ray3 = new Ray(new Point(-1, -1, -1), new Vector(0, 0, 1));
		assertNull(trgle.findIntersections(ray3), "There should be no intersections");

		// =============== Boundary Values Tests ==================
		// TC01: intersection on one of the ribs
		Ray ray4 = new Ray(new Point(0, 1, -1), new Vector(0, 0, 1));
		assertNull(trgle.findIntersections(ray4), "There should be no intersections");

		// TC02: intersection on one of the vertexes
		Ray ray5 = new Ray(new Point(0, 0, -1), new Vector(0, 0, 1));
		assertNull(trgle.findIntersections(ray5), "There should be no intersections");

		// TC03: intersection on the continued of the one of the ribs
		Ray ray6 = new Ray(new Point(-1, 0, -1), new Vector(0, 0, 1));
		assertNull(trgle.findIntersections(ray6), "There should be no intersections");

	}
}
