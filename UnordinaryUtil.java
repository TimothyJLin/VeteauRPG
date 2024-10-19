public class UnordinaryUtil {
	double rotation;
	double xPosition;
	double yPosition;
	public UnordinaryUtil (double rotation, double xPosition, double yPosition) {
		this.rotation=rotation;
		this.xPosition=xPosition;
		this.yPosition=yPosition;
	}
	
	public double forwardX(double length) {
		return xPosition+Math.cos(Math.toRadians(length))*length;
	}
		
	public double forwardY(double length) {
		return yPosition+Math.sin(Math.toRadians(length))*length;
	}
}