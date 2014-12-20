
/*
APSS - Another Plate solving software
A free software for identifying the stars in the nightsky.
Copyright (C) Stefan Babel

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License 2
as published by the Free Software Foundation.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/

package apsslib;
import Jama.Matrix;

public class ProjectorTest {
	
	final double PI = Math.PI;
	
	private final double[] initialRefPoint = {0,0,0};
	
	// We're centering all around the referencePoint/reference Star
	private double[] referencePoint;
	private double[] refPointSphere;
	// Contains the basis for the plane where we're going to project other stars
	private double[][] basis = {{0,0,0},{0,0,0}};
	

	public ProjectorTest(double[] spherePoint) {
		// Initial Ref Point: (x,y,z) = (r,0,0)
		initialRefPoint[0] = spherePoint[2];
		
		refPointSphere = spherePoint;
		referencePoint = sphereToEuclid(spherePoint);
		//generateOrthonormalbasis();
		//genOrthonormalBasisEnhanced();
		genOrthonormalBasisEnhanced2();
	}
	
	public double getRightAscension(){
		return refPointSphere[0];
	}
	public double getDeclination(){
		return refPointSphere[1];
	}
	public void printBasis(){
		System.out.println(
				"Reference point is: (" 
						+ referencePoint[0]+", " 
						+ referencePoint[1] + ", " 
						+ referencePoint[2] +").");
		System.out.println("Basis-vector1: " + basis[0][0] + " "+ basis[0][1]  + " " + basis[0][2]);
		System.out.println("Basis-vector2: " + basis[1][0] + " "+ basis[1][1]  + " " + basis[1][2]);
	}
	
	private void genOrthonormalBasisEnhanced2(){
		double[] fixed_b1 = {0,1,0};
		double[] fixed_b2 = {0,0,1};
		
		// DO we really need to norm the cross product?
		// TODO: Ckech that
		double[] rotAxis = normVector(cross_product(initialRefPoint, referencePoint));
		double rotAngle = Math.acos(dot_product(initialRefPoint, referencePoint)/
									norm(initialRefPoint) / norm(referencePoint));
		RotationMatrix rm = new RotationMatrix(rotAxis,rotAngle);
		// Are vectors normed to 1 after rotation?
		// TODO: CCHECK
		basis[0] = normVector(rm.rotateVector(fixed_b1));
		basis[1] = normVector(rm.rotateVector(fixed_b2));
		
		
		/* Helping text
		double[][] array = {{basis[0][0],basis[1][0], -euclidPoint[0]},
				{basis[0][1],basis[1][1], -euclidPoint[1]},
				{basis[0][2],basis[1][2], -euclidPoint[2]}};
		Matrix A = new Matrix(array);
		double[][] loes = {{-referencePoint[0]},{-referencePoint[1]},{-referencePoint[2]}};
		Matrix b = new Matrix(loes);
		Matrix x = A.solve(b);
		*/
		
	}
	
	private void genOrthonormalBasisEnhanced(){
		double[] b1 = new double[3];
		double[] b2 = new double[3];
		
		
		b1[0] = refPointSphere[0]+PI/2;;
		b1[1] = refPointSphere[1];
		b1[2] = refPointSphere[2];

		b2[0] = refPointSphere[0];
		b2[1] = refPointSphere[1]+PI/2;
		b2[2] = refPointSphere[2];
		
		
		basis[0] = normVector(sphereToEuclid(b1));
		basis[1] = normVector(sphereToEuclid(b2));
		printBasis();
	}
	
	@Deprecated
	private void generateOrthonormalbasis(){
		/** 
		 * Main method for generating the
		 * orthonormal basis. (Pretty stupid)
		 * 
		 */
		// Try 3 rotations, take the one with biggest distance
		double[] v_x, v_y,v_z;
		double d_x,d_y,d_z,d;
		v_x = rotate(referencePoint,'x');
		v_y = rotate(referencePoint,'y');
		v_z = rotate(referencePoint,'z');
		
		d_x = distance(referencePoint,v_x);
		d_y = distance(referencePoint,v_y);
		d_z = distance(referencePoint,v_z);
		
		/* Take the one which is away most.
		 This way we avoid rotating
		 "the x-axis around the x-axis".
		 Therefore we can expect linear independence,
		 but we end up with chaotic representation
		 when changing our reference point.
		 TODO: fix this problem.
		 */
		
		d = Math.max(Math.max(d_x, d_y), d_z);
		
		if(d==d_x){
			basis[0] = v_x;
		}else if(d==d_y){
			basis[0] = v_y;
		}else if(d==d_z){
			basis[0] = v_z;
		}else{
			System.err.println("Can this even go wrong??");
		}
		
		basis[1] = cross_product(basis[0], referencePoint);
		basis[0] = normVector(basis[0]);
		basis[1] = normVector(basis[1]);
		
	}
	
	@Deprecated
	public double[] getIntersectionWithPlane(double[] euclidPoint){
		// Solve linear system
		// Solves the intersection of a plane with a vector (GERADE im Raum)
		double[][] array = {{basis[0][0],basis[1][0], -euclidPoint[0]},
							{basis[0][1],basis[1][1], -euclidPoint[1]},
							{basis[0][2],basis[1][2], -euclidPoint[2]}};
		Matrix A = new Matrix(array);
		double[][] loes = {{-referencePoint[0]},{-referencePoint[1]},{-referencePoint[2]}};
		Matrix b = new Matrix(loes);
		Matrix x = A.solve(b);
		return printPlaneIntersection(x.get(0, 0), x.get(1,0), x.get(2, 0));	
	}
	
	public double[] getIntersectionWithPlaneBasisScalars(double[] euclidPoint){
		/**
		 * Uses the pre-calculated orthonormal basis and euclidPoint
		 * to calculate the point of intersection between the plane
		 * spanned by the orthonormal basis and the line
		 * spanned by the euclidPoint.
		 * 
		 * Lambda is the scalar for the euclidPoint. If it is
		 * calculated as less than 1 then the point lies
		 * over the plane and thus is in a valid radius.
		 * 
		 * @see documentation
		 * 
		 * @param euclidPoint	Point given in xyz-coordinates
		 * @return				The scalars for of the basis
		 */
		double scalar1,scalar2,lambda;
		double[][] array = {{basis[0][0],basis[1][0], -euclidPoint[0]},
				{basis[0][1],basis[1][1], -euclidPoint[1]},
				{basis[0][2],basis[1][2], -euclidPoint[2]}};
		Matrix A = new Matrix(array);
		double[][] loes = {{-referencePoint[0]},{-referencePoint[1]},{-referencePoint[2]}};
		Matrix b = new Matrix(loes);
		Matrix x = A.solve(b);
		
		scalar1 = x.get(0, 0);
		scalar2 = x.get(1, 0);
		lambda = x.get(2, 0);
		if(lambda<0 || lambda>1){
			return null;
		}
		else{
			double[] res = {scalar1,scalar2,lambda};
			return res;
		}
	}
	
	@Deprecated
	private double[] printPlaneIntersection(double t, double s, double lambda){
		double x,y,z;
		if (lambda<0) return null;
		if(lambda<1){
			System.out.println("Lambda is less then 1!: " + lambda);
			System.out.println("First basis vector scalar is: " + t);
			System.out.println("Second basis vector scalar is: " + s);
		}
		x = (referencePoint[0]+t* basis[0][0] + s*basis[1][0]);
		y = (referencePoint[1] +t* basis[0][1] + s*basis[1][1]);
		z = (referencePoint[2] +t* basis[0][2] + s*basis[1][2]);
		//System.out.println("x-coord: " + x);
		//System.out.println("y-coord: " +y);
		//System.out.println("z-coord: " +z);
		double[] res = {x,y,z};
		return res;
		
	}
	
	public boolean checkNeighborhoodBySphere(double[] s, double radius){
		// s is a Star in spherical coordinates
		// This function checks whether s in in sphere around reference star
		
		if(distance(sphereToEuclid(s),referencePoint) < radius){
			return true;
		}
		return false;
		
	}
	
	@Deprecated
	public boolean checkNeighbourhoodByXYZ(double[] xyz, double radius){
		if(distance(xyz,referencePoint) < radius){
			return true;
		}
		return false;
	}
	
	private double distance(double[] v1,double[] v2){
		double[] v_tmp = {v1[0]-v2[0],v1[1]-v2[1],v1[2]-v2[2]};
		return norm(v_tmp);
	}
	
	private double norm(double[] vector){
		// TODO: Assure that vector has always size 3
		return Math.sqrt(vector[0]*vector[0]+vector[1]*vector[1]+vector[2]*vector[2]);
	}
	
	private double[] normVector(double[] v){
		/**
		 * Norms the vector to unit length 1.
		 * 
		 * @param v		A 3x1 vector
		 * @return		A 3x1 vector with unit length 1.	
		 */
		double norm = norm(v);
		double[] v_tmp = { v[0]/norm, v[1] / norm, v[2]/norm};
		return v_tmp;
	}
	
	public double[] sphereToEuclid(double[] vector){
		/**
		 * Convert a vector given in spherical cooridnates
		 * to a vector in euclidian coordinates.
		 * 
		 * Use the following convention:
		 * vector[0] = right ascension given in radians from [0,2*PI]
		 * vector[1] = declination in radians from [-PI/2. +PI/2]
		 * vector[2] = radius from [0, \infty]
		 * 
		 * @param vector	A vector in spherical coordinates
		 * @return 			A vector in xyz coordinates
		 */
		/* Convention here is:
		 * vector[0] = Right ascension in radians from [0, 2*Pi]
		 * vector[1] = Declination in radians from [-Pi/2, + Pi/2]
		 * vector[2] = radius from [0, \infty)
		 */
		double x,y,z;
		double phi = vector[0];
		double theta = vector[1];
		double r = vector[2];
		double theta_mod = -theta + PI/2;
		
		x = r * Math.sin(theta_mod) * Math.cos(phi);
		y = r * Math.sin(theta_mod) * Math.sin(phi);
		z = r*Math.cos(theta_mod);
		
		double[] retVec = {x,y,z};
		
		return retVec;
		
	}
	
	private double[] rotate(double[] vector, char axis){
		/**
		 * This method rotates a vector around a given axis.
		 * It is needed to create an orthonormal basis
		 * for the plane.
		 * 
		 * @param vector	A vector given in xyz-coordinates.
		 * @param axis		A char which symbolizes around which axis to rotate
		 * 
		 * @return 			The rotated vector in xyz-coordinates.
		 */
		double[] res = {0,0,0};
		double[][] xRotMat = {{1,0,0},{0,0,-1},{0,1,0}};
		double[][] yRotMat = {{0,0,1},{0,1,0},{-1,0,0}};
		double[][] zRotMat = {{0,-1,0},{1,0,0},{0,0,1}};
		double[][] mat;
		switch(axis){
			case 'x':
				mat = xRotMat;
				break;
			case 'y':
				mat= yRotMat;
				break;
			case 'z':
				mat = zRotMat;
				break;
			default:
				// TODO: Should catch this error
				mat = null;
				
		}
		
		for(int i=0;i<3;i++){
			double tmp = 0;
			for(int j=0;j<3;j++){
				tmp+= mat[i][j]*vector[j];
			}
			res[i] = tmp;
		}
		
		return res;
	}

	
	private double[] projection(double[] vecToProject){
		/**
		 * This method projects a vector onto the plane
		 * given by the pre-calculated basis.
		 * (Projection does no include the referencePoint!)
		 * 
		 * 
		 * @see ProjectionOrthonormal
		 * 
		 * @param vecToProject	A vector in xyz-coordinates which gets projected.
		 * @return 				A vector in xyz-coordinates which lies on the plane given by the basis.
		 */
		// b1,b2 need to be orthonormal basis for the following to work!
		double[] projection = {0,0,0};
		double dp1 = dot_product(basis[0],vecToProject);
		double dp2 = dot_product(basis[1],vecToProject);
		for(int i=0;i<3;i++){
			projection[i] = dp1 * basis[0][i] + dp2*basis[1][i];
		}
		return projection;
		
	}

	private double dot_product(double[] v1, double[] v2){
		return v1[0]*v2[0]+v1[1]*v2[1]+v1[2]*v2[2];
	}
	private double[] cross_product(double[] v1, double[] v2){
		double[] res = {0,0,0};
		
		res[0] = v1[1]*v2[2] - v1[2]*v2[1];
		res[1] = v1[2]*v2[0] - v1[0]*v2[2];
		res[2] = v1[0]*v2[1] - v1[1]*v2[0];
		
		return res;
	}

}
