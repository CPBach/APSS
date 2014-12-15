
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

import java.util.HashSet;
import java.awt.Point;

public class Quad {

	// weak = 1 common star
	private HashSet<Quad> weakNeighbours;
	// strong = 2 common stars
	private HashSet<Quad> strongNeighbours;
	// extreme = 3 common stars
	private HashSet<Quad> extremeNeighbours;
	
	
	private MyPoint startPoint;
	private MyPoint endPoint;
	private MyPoint firstProjected;
	private MyPoint secondProjected;
	
	private double firstProjection = -1;
	private double secondProjection = -1;
	
	public Quad(MyPoint startPoint,MyPoint endPoint,
			MyPoint firstProjected, MyPoint secondProjected) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.firstProjected = firstProjected;
		this.secondProjected = secondProjected;
		
		weakNeighbours = new HashSet<Quad>();
		strongNeighbours = new HashSet<Quad>();
		extremeNeighbours = new HashSet<Quad>();
		
		calculateProjections();
	}
	
	
	
	@Override
	public int hashCode() {
		// TODO: Think of a good hash function
		// TODO: This method really sucks, but it's
		//       a quick way to see if things work
		StringBuilder sb = new StringBuilder();
		
		sb.append(startPoint.x);
		sb.append(startPoint.y);
		sb.append(endPoint.x);
		sb.append(endPoint.y);
		sb.append(firstProjected.x);
		sb.append(firstProjected.y);
		sb.append(secondProjected.x);
		sb.append(secondProjected.y);
		return Integer.parseInt(sb.toString());
		
	}
	



	private void calculateProjections(){
		// Project firstProjected to
	}

	@Override
	public boolean equals(Object o) {
		// TODO: Verify correctness of this
		Quad q = (Quad) o;
		if(this.startPoint.equals(q.startPoint) 
				&& this.endPoint.equals(q.endPoint) 
				&& this.firstProjected.equals(q.firstProjected)
				&& this.secondProjected.equals(q.secondProjected))
			return true;
		else if(this.startPoint.equals(q.endPoint)
				&& this.endPoint.equals(q.startPoint)
				&& this.firstProjected.equals(q.secondProjected)
				&& this.secondProjected.equals(q.firstProjected))
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Start: " + startPoint + " End: " + endPoint + " FP: " + firstProjected +  " SP: " + secondProjected ;
	}
	
	
	
	
	
	

}
