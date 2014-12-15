
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

package apsslib;/*


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
import java.util.Iterator;

public class Graph {

	ArrayList<Node> nodes;

	public Graph() {
		nodes = new ArrayList<Node>();
	}

	public boolean addNode(Node n) {
		if (!isPresent(n)) {
			System.out.println("Adding node to graph: " + n.getPoint());
			nodes.add(n);
			return true;
		}

		return false;
	}

	public Node getRandomNode() {
		Randomizer.init();
		int rnd = Randomizer.randInt(0, nodes.size() - 1);
		return nodes.get(rnd);
	}

	private boolean isPresent(Node n) {
		for (Iterator<Node> i = nodes.iterator(); i.hasNext();) {
			if (n.equals(i.next())) {
				return true;
			}
		}
		return false;
	}

}
