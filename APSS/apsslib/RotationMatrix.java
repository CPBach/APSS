package apsslib;

public class RotationMatrix {
	
	double[] rotationAxis;
	double[][] rm;
	double angle;
	
	
	public RotationMatrix(double[] rotationAxis, double angle){
		this.rotationAxis = rotationAxis;
		this.angle = angle;
		
		// Use Rodriguez formula to compute rotation matrix
		// We follow the outline given in:
		// @see http://mathworld.wolfram.com/RodriguesRotationFormula.html
		
		double w_x = rotationAxis[0];
		double w_y = rotationAxis[1];
		double w_z = rotationAxis[2];
		
		double c = Math.cos(angle);
		double s = Math.sin(angle);
		
		// Compute rotation matrix
		double[][] rm = { 
				{c+w_x*w_x*(1-c), w_x*w_y*(1-c)-w_z*s,w_y*s+w_x*w_z*(1-c)},
				{w_z*s+w_x*w_y*(1-c),c+w_y*w_y*(1-c),-w_x*s+w_y*w_z*(1-c)},
				{-w_y*s+w_x*w_z*(1-c),w_x*s+w_y*w_z*(1-c),c+w_z*w_z*(1-c)}};
				
		this.rm = rm;
		
		
	}
	
	
	public double[] rotateVector(double[] vector){
		double[] tmp = new double[3];
		for(int i=0;i<3;i++){
			double tmp_val=0;
			for(int j=0;j<3;j++){
				tmp_val+= rm[i][j] * vector[j];
			}
			tmp[i] = tmp_val;
		}
		return tmp;
	}

}
