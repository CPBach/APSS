
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

import java.util.ArrayList;
import java.util.Iterator;

public class LibraryController {
	StarCatalog sc;
	ArrayList<Star> stars;

	private final double SEARCHRADIUS = 250;
	private double R;

	public LibraryController() {
		initialize();
	}

	private void initialize() {
		loadStarCatalog();
	}

	private void loadStarCatalog() {
		sc = new StarCatalog();
		stars = sc.getStars();
	}

	public int[][] generateMatrix(double ra, double decl, double projDist,
			int mag, double rot) {
		// This generates the actual picture from the star catalogue
		ArrayList<AcceptedStar> acceptedStars = new ArrayList<AcceptedStar>();

		// Calculate the radius for the sphere where the stars will be put on
		R = Math.sqrt(projDist * projDist + SEARCHRADIUS * SEARCHRADIUS);

		// Set referencePoint and generate Orthonormalbasis
		double[] centerPoint = { ra, decl, projDist };
		ProjectorTest pt = new ProjectorTest(centerPoint);

		double[] sphereCoord = new double[3];
		double[] b1b2lambda = new double[3];

		for (Iterator<Star> i = stars.iterator(); i.hasNext();) {
			// Project every star to plane and check neighbourhood
			Star star = i.next();
			sphereCoord[0] = star.SRAO;
			sphereCoord[1] = star.SDECO;
			sphereCoord[2] = R;

			b1b2lambda = pt.getIntersectionWithPlaneBasisScalars(
					pt.sphereToEuclid(sphereCoord));

			if (b1b2lambda != null && star.MAG <= mag) {
				// Star is in neighbourhood, add it
				acceptedStars.add(new AcceptedStar(b1b2lambda[0],
						b1b2lambda[1], b1b2lambda[2], star.MAG));
			}

		}

		return acceptedStarsToMatrix(acceptedStars);

	}

	private int[][] acceptedStarsToMatrix(ArrayList<AcceptedStar> acceptedStars) {
		int[][] resMatrix = new int[500][500];
		for (Iterator<AcceptedStar> i = acceptedStars.iterator(); i.hasNext();) {
			AcceptedStar as = i.next();
			double b1 = as.getB1();
			double b2 = as.getB2();

			int b1Int = (int) b1 + 250;
			int b2Int = (int) b2 + 250;
			double mag = as.getMag();

			if (b1Int < 500 && b2Int < 500 && b1Int >= 0 && b2Int >= 0) {
				resMatrix[b1Int][b2Int] = 0xffffff;
			}
		}
		return resMatrix;
	}

}