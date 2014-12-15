
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

public class AcceptedStar {
	
	private double b1,b2,lambda;
	private int mag=0;

	public AcceptedStar(double b1,double b2, double lambda, int mag) {
		this.b1 = b1;
		this.b2 = b2;
		this.lambda = lambda;
		this.mag = mag;
	}
	
	public double getB1(){
		return b1;
	}
	
	public double getB2(){
		return b2;
		
	}
	
	public double getLambda(){
		return lambda;
	}

	public int getMag(){
		return mag;
	}
}
