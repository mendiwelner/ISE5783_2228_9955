package unittests.renderer;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.*;
import renderer.Camera;
import primitives.*;

public class IntegrationTest {
	
	@Test
	void testSphere() {
		// ============ Equivalence Partitions Tests ==============
		Camera camera = new Camera(new Point(0,0,0),new Vector(0,0,-1),new Vector(0,1,0)).setVPDistance(1);
		Sphere sphere = new Sphere(1,new Point(0,0,-3));
		LinkedList<Ray> rayList = pixelRays(camera.setVPSize(3, 3),3,3);
		assertEquals(2,countIntersections(rayList,sphere),"wrong amount of pixel intersections");
		
			
	}
	
	private LinkedList<Ray> pixelRays(Camera camera, int nX,int nY){
		
		LinkedList<Ray> rays = new LinkedList<Ray>();
		for(int i = 0; i < nX; i++)
			for(int j = 0; j < nY; j++)
				rays.add(camera.constructRay(nX, nY, j, i));
		
		return rays;
	}

	
	private int countIntersections(LinkedList<Ray> rays,Sphere sphere) {
		
		int counter = 0;
		List<Point> intersections = new LinkedList();
		for(Ray ray:rays) {
			intersections = sphere.findIntsersections(ray);
			if (intersections != null)
				counter += intersections.size();
		}
		
		return counter;		
	}
}
