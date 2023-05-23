/**
 * 
 */
package lighting;

import primitives.*;

/**
 * @author mendy
 *
 */



public class AmbientLight {
	public final static AmbientLight NONE = new AmbientLight(Color.BLACK,Double3.ZERO);
	private final Color intensity;
	
	AmbientLight(Color color,Double3 kA){
		intensity = color.reduce(kA);
		
	}
	
	public Color getIntensity() {
		return intensity;
	}
	

}
