
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
/*
A free software for identifying the stars in the nightsky
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
import java.awt.Point;
import java.util.ArrayList;


public class TestingClass {

	boolean[][] tstMatrix;
	
	public TestingClass() {
		tstMatrix = new boolean[7][6];
		tstMatrix[1][1] = true;
		tstMatrix[2][3] = true;	
		tstMatrix[4][3] = true;
		tstMatrix[5][5] = true;
		
		PointMatrixOperations pmo = new PointMatrixOperations(tstMatrix);
		ArrayList<MyPoint> points = pmo.getPoints();
		QuadGenerator qg = new QuadGenerator(tstMatrix,points, 0.5);
	}
	
	public void testBitmapLoading(){
		BitmapLoader bl = new BitmapLoader("oneWhitePoint.bmp");
		System.out.println("Number of white points in bitmap: " + bl.countWhitePoints());
		//bl.printNonZero();
	}
	
	public static void main(String[] args){
//		TestingClass t = new TestingClass();
//		t.testBitmapLoading();
//		StarCatalog sc = new StarCatalog();
//		System.out.println("Number Of Stars: " + sc.getNumberOfStars());
//		int star = 2;
//		System.out.println("Printing star: " +star);
//		
//		System.out.println(sc.getStarByCatalogNumber(star));
//		double PI = Math.PI;
//		double[] spherePoint = {0,PI/2,10};
//		ProjectorTest pt = new ProjectorTest(spherePoint);
//		pt.printBasis();
//		double[] pktAufEbene = {-1,-2,-0.1};
//		pt.getIntersectionWithPlane(pktAufEbene);
//		
//		System.out.println("Bin durch....");
	}

}
