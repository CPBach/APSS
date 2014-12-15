
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

import java.util.ArrayList;

public class Node {

	private ArrayList<Node> neighbours;
	// This is the Node identifier
	private MyPoint nodePoint;
	
	public Node(MyPoint n) {
		neighbours = new ArrayList<Node>();
		nodePoint = n;
	}
	
	public void addNeighbour(Node neighbour){
		for(int i=0;i<neighbours.size();i++){
			if (neighbours.get(i).equals(neighbour))
				return;
		}
		// Bidirectional addition
		neighbours.add(neighbour);
		neighbour.addNeighbour(this);
	}
	
	public MyPoint getPoint(){
		return nodePoint;
	}
	
	public Node getRandomNeighbour(){
		int neighbLength = neighbours.size();
		if(neighbLength < 1){
			return null;
		}
		Randomizer.init();
		int rnd = Randomizer.randInt(0, neighbours.size()-1);
		return neighbours.get(rnd);
	}
	

	// 2 Nodes are equal if their corresponding 
	// points are the same.
	
	@Override
	public boolean equals(Object arg0) {
		Node n = (Node) arg0;
		return this.nodePoint.equals(n.nodePoint);
	}


}
