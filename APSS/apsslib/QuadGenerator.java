
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
import java.util.Iterator;
import java.util.HashSet;
public class QuadGenerator {

	ArrayList<MyPoint> points;
	HashSet<Quad> quads;
	
	final int MAKENODESTRIALS=100;
	final int MAKEQUADSTRIALS=100;
	final int SEARCHMAXPROJECTORS = 100;
	final int projectorTrials = 200;
	final double MIN_DISTANCE;
	
	
	public QuadGenerator(boolean[][] matrix, ArrayList<MyPoint> points, double accFactor) {
		Randomizer.init();
		if (accFactor<0 || accFactor > 1){
			System.err.println("ERROR!!! accFactor is a wrong number");
		}
		int rows = matrix.length;
		int columns = matrix[0].length;
		double dist = Math.sqrt((rows-1)*(rows-1) + (columns-1)*(columns-1));
		System.out.println("Distance across field:" + dist);
		MIN_DISTANCE = accFactor * dist;
		System.out.println("Min-Distance: " + MIN_DISTANCE);
		this.points = points;
		quads = new HashSet<Quad>();
		createQuads();
	}
	
	private void createQuads(){
	
		// Create graph first ( add all nodes )
		Graph graph = new Graph();
		for(Iterator<MyPoint> i=points.iterator(); i.hasNext(); ){
			graph.addNode(new Node(i.next()));
		}
		// Generate edges between nodes, which are sufficiently far away
		for(int i=1; i<=MAKENODESTRIALS;i++){
			Node v = graph.getRandomNode();
			Node w = graph.getRandomNode();
			if(v!=w){
				double dist = pointDist(v.getPoint(),w.getPoint());
				if(dist>=MIN_DISTANCE){
					v.addNeighbour(w);
				}
			}
		}
		
		for(int i=1;i<=MAKEQUADSTRIALS;i++){
			Node a = graph.getRandomNode();
			Node b = a.getRandomNeighbour();
			if(a == null || b == null) continue;
			MyPoint aPoint = a.getPoint();
			MyPoint bPoint = b.getPoint();

			MyPoint projector1 = null;
			MyPoint projector2 = null;
			MyPoint midPoint = new MyPoint((aPoint.x + bPoint.x)/2,(aPoint.y + bPoint.y)/2);
			
			for(int j=1;j<SEARCHMAXPROJECTORS;j++){
				projector1 = getProjectorInNeighbourhood(midPoint, aPoint.distance(bPoint)/2);
				projector2 = getProjectorInNeighbourhood(midPoint, aPoint.distance(bPoint)/2);
				if(projector1!=projector2 && projector1!=null && projector2!=null){
					if(aPoint.distance(projector1) < aPoint.distance(projector2)){
						Quad q = new Quad(aPoint,bPoint,projector1,projector2);
						quads.add(q);
					}
					else{
						Quad q = new Quad(aPoint,bPoint,projector2,projector1);
						quads.add(q);
						
					}
				}
			}
		}
		System.out.println("Size of quads: " + quads.size());
		for(Iterator<Quad> i= quads.iterator(); i.hasNext();){
			System.out.println(i.next());
		}
		
	}
	
	private MyPoint getProjectorInNeighbourhood(Point point, double dist){
		int i=1;
		while(i<=projectorTrials){
			int rnd = Randomizer.randInt(0, points.size()-1);
			// rnd-Point lies in circle around point
			boolean cond = point.distance(points.get(rnd))<dist;
			if (cond){
				return points.get(rnd);
			}
		}
		return null;
		
	}
	
	private double pointDist(Point a, Point b){
		return a.distance(b);
	}

}
