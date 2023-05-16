package unittests.renderer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.*;
import renderer.Camera;
import primitives.*;

/**
 * Testing Camera Class
 * 
 * @author Mendy Welner 209272228. mendiwell@gmail.com 
 * 		   Mendy Segal. 211769955.Mendysegal490@gmail.com
 *
 */
public class IntegrationTest {

	@Test
	void testSphere() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: sphere is across the central pixel of view plane - 2 intersections
		Camera camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1);
		Sphere sphere = new Sphere(1, new Point(0, 0, -3));
		LinkedList<Ray> rayList = pixelRays(camera.setVPSize(3, 3), 3, 3);
		assertEquals(2, countIntersections(rayList, sphere), "wrong amount of pixel intersections");
		

		// TC02: sphere is across the whole view plane - 18 intersections
		sphere = new Sphere(2.5, new Point(0, 0, -2.5));
		camera = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1);
		rayList = pixelRays(camera.setVPSize(3, 3), 3, 3);
		assertEquals(18, countIntersections(rayList, sphere), "wrong amount of pixel intersections");

		// TC03: sphere is across the view plane - 10 intersections
		sphere = new Sphere(2, new Point(0, 0, -2));
		assertEquals(10, countIntersections(rayList, sphere), "wrong amount of pixel intersections");

		// TC04: view plane is inside sphere - 18 intersections
		sphere = new Sphere(4, new Point(0, 0, -2));
		assertEquals(9, countIntersections(rayList, sphere), "wrong amount of pixel intersections");

		// TC05: sphere is behind view plane - 0 intersections
		sphere = new Sphere(0.5, new Point(0, 0, 1));
		assertEquals(0, countIntersections(rayList, sphere), "wrong amount of pixel intersections");

	}

	@Test
	void testPlane() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: plane is parallel to view plane - 9 intersections
		Camera camera = new Camera(new Point(0, 0, 1), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1);
		Plane plane1 = new Plane(new Point(0, 0, -2), new Vector(0, 0, 1));
		LinkedList<Ray> rayList = pixelRays(camera.setVPSize(3, 3), 3, 3);
		assertEquals(9, countIntersections(rayList, plane1), "wrong amount of pixel intersections");

		// TC02: plane is diagonally near the view plane - 9 intersections
		Plane plane2 = new Plane(new Point(0, 0, -2), new Vector(0, -1, 5));
		assertEquals(9, countIntersections(rayList, plane2), "wrong amount of pixel intersections");

		// TC03: plane is diagonally near the view plane - 9 intersections
		Plane plane3 = new Plane(new Point(0, 0, -8), new Vector(0, -2, -1));
		assertEquals(6, countIntersections(rayList, plane3), "wrong amount of pixel intersections");

	}

	@Test
	void testTriangle() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Triangle is across to central pixel - 1 intersection
		Camera camera = new Camera(new Point(0, 0, 1), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1);
		Triangle trg1 = new Triangle(new Point(0, 1, -2), new Point(1, -1, -2), new Point(-1, -1, -2));
		LinkedList<Ray> rayList = pixelRays(camera.setVPSize(3, 3), 3, 3);
		assertEquals(1, countIntersections(rayList, trg1), "wrong amount of pixel intersections");

		// TC02: Triangle is across to view plane - 2 intersections
		Triangle trg2 = new Triangle(new Point(0, 20, -2), new Point(1, -1, -2), new Point(-1, -1, -2));
		assertEquals(2, countIntersections(rayList, trg2), "wrong amount of pixel intersections");

	}

	private LinkedList<Ray> pixelRays(Camera camera, int nX, int nY) {
		LinkedList<Ray> rays = new LinkedList<Ray>();
		for (int i = 0; i < nX; i++)
			for (int j = 0; j < nY; j++)
				rays.add(camera.constructRay(nX, nY, j, i));

		return rays;
	}

	private int countIntersections(LinkedList<Ray> rays, Intersectable shape) {
		int counter = 0;
		List<Point> intersections = new LinkedList<Point>();
		for (Ray ray : rays) {
			intersections = shape.findIntsersections(ray);
			if (intersections != null)
				counter += intersections.size();
		}

		return counter;
	}
}