
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
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.EOFException;

public class StarCatalog {

	// We currently only support the Yale bright star catalog
	final String catFileName = "BSC5";
	private ArrayList<Star> stars;

	public StarCatalog() {
		stars = new ArrayList<Star>();
		loadCatalog();
	}

	private void loadCatalog() {
		DataInputStream in = null;
		boolean EOF = false;
		try {
			in = new DataInputStream(new BufferedInputStream(
					new FileInputStream(catFileName)));
		} catch (Exception e) {
			System.err.println("Error reading file: " + catFileName);
		}

		// Drop first 28 bytes (skip header)
		// 28 = 7*4;
		for (int i = 1; i <= 7; i++) {
			try {
				in.readFloat();
			} catch (Exception e) {
				System.err.println("Error reading on file...");
			}
		}

		while (!EOF) {
			Star tmpStar = new Star();
			// Byte swapper is used for big-endian to little endian conversion
			try {
				tmpStar.XNO = ByteSwapper.swap(in.readFloat());
				tmpStar.SRAO = ByteSwapper.swap(in.readDouble());

				tmpStar.SDECO = ByteSwapper.swap(in.readDouble());
				short tmp = ByteSwapper.swap(in.readShort());
				tmpStar.IS[1] = (char) (tmp >> 8);
				tmpStar.IS[0] = (char) (tmp & 0xff);
				tmpStar.MAG = ByteSwapper.swap(in.readShort());
				tmpStar.XRPM = ByteSwapper.swap(in.readFloat());
				tmpStar.XDPM = ByteSwapper.swap(in.readFloat());
			} catch (Exception e) {
				if (e instanceof EOFException) {
					break;
				}
				System.out.println("Error reading Starfile... "
						+ "Probably not because of EOFException...");
			}
			stars.add(tmpStar);
		}

	}

	public Star getStarByCatalogNumber(int catNmb) {
		return stars.get(catNmb - 1);
	}

	public ArrayList<Star> getStars() {
		return stars;
	}

	public int getNumberOfStars() {
		return stars.size();
	}

}
