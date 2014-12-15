
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
import java.awt.Point;
import java.util.ArrayList;

public class PointMatrixOperations {

	private ArrayList<MyPoint> points;
	private boolean[][] matrix;
	
	public PointMatrixOperations(boolean[][] matrix) {
		points = new ArrayList<MyPoint>();
		this.matrix = matrix;
		makePointsFromMatrix();
	}
	
	private void makePointsFromMatrix(){
		// TODO: Enhance the point creation method
		// by finding the barycentre of connected points
		int rows = matrix.length;
		System.out.println("Rows: " + rows);
		int columns = matrix[0].length;
		System.out.println("Columns: " + columns);
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				if(matrix[i][j]){
					points.add(new MyPoint(i,j));
					System.out.println("Added point: (" +i +", "+j+")" );
				}
			}
		}
	}
	
	public ArrayList<MyPoint> getPoints(){
		return points;
	}

}
