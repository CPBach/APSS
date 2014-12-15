
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
	
public class Star{
		/*Real*4 XNO		Catalog number of star
		Real*8 SRA0		B1950 Right Ascension (radians)
		Real*8 SDEC0		B1950 Declination (radians)
		Character*2 IS		Spectral type (2 characters)
		Integer*2 MAG		V Magnitude * 100
		Real*4 XRPM		R.A. proper motion (radians per year)
		Real*4 XDPM		Dec. proper motion (radians per year)
		 */
		// TODO: Assure that the following construction becomes architecture independent
		public float 	XNO;
		public double 	SRAO;
		public double 	SDECO;
		public char[]  	IS;
		public short 	MAG;
		public float	XRPM;
		public float	XDPM;
		
		public Star(){
			IS = new char[2];
		}

		@Override
		public String toString() {
			/* First star in catalogue (Yale Bright stars)
			 * BSC_number  RA2000       Dec2000       Mag Type
  			   000001  00:05:09.900 +45:13:45.00   6.70  A1
			 */
			String tmp = 
					"BSC_number: " + XNO + "\n"
					+ "RA2000: " + SRAO + "\n"
					+ "Dec2000: " + SDECO + "\n"
					+ "Mag: " + ((float)MAG)/100 + "\n"
					+ "Type: " + IS[0] + IS[1] + "\n";
			return tmp;
		}
				
	}