package primitives;

public class Material {
	public Double3 kD = Double3.ZERO, kS = Double3.ZERO;
	public int nShininess;
	
	public Material setKD(Double3 kD) {
		this.kD = kD;
		return this;
	}
	
	public Material setKD(double kD) {
		this.kD = new Double3(kD);
		return this;
	}
	
	public Material setKS(Double3 kS) {
		this.kS = kS;
		return this;
	}
	
	public Material setKS(double kS) {
		this.kS = new Double3(kS);
		return this;
	}	
}
