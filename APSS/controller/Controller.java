
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
package controller;

import java.util.concurrent.Delayed;

import apsslib.LibraryController;
import gui.MainWindow;



public class Controller {
	/**
	 * This class is the link between the GUI and the
	 * plate solving framework.
	 */

	private static Controller ctrl;
	private MainWindow mw;
	private ListenerClass listenerClass;
	private LibraryController libraryController;
	
	double ra = 0;
	double dec = 0;
	double projDist = 200;
	int mag = 500;
	int rot = 0;
	
	
	public Controller() {
		listenerClass = new ListenerClass(this);
		mw = new MainWindow(listenerClass);

	}
	
	public static void main(String[] args){
		ctrl = new Controller();
		ctrl.mainRoutine();
	}
	
	public void mainRoutine(){
		// Library Controller initialization
		libraryController = new LibraryController();
		reCalcAndPaint();
	}
	
	private void reCalcAndPaint(){
		int[][] matrix;
		String rightAscension,declination,rotation;
		matrix = libraryController.generateMatrix(ra, dec, projDist, mag, rot);
		rightAscension = libraryController.getRightAscension();
		declination = libraryController.getDeclination();
		mw.setLabels(rightAscension,declination);
		mw.scanvas.setMatrix(matrix);
	}

	public void ascensionChanged(int val) {
		// Ascension arrives in form: 0..2400
		// has to be turned to 0..2*PI
		
		double new_val = ((double)val)/100;
		
		ra = new_val * Math.PI/12;
		reCalcAndPaint();
		
	}

	public void declinationChanged(int val) {
		/**
		 * Gets called when declination gets changed
		 * in GUI.
		 * 
		 * @param val	Declination in degree from -90deg .. +90deg
		 */
		// Declination arrives in form: -90..90
		// has to be turned to -pi to +pi
		dec = val * Math.PI/180;
		reCalcAndPaint();
		
	}

	public void magnitudeChanged(int val) {
		/**
		 * Gets called when magnitude gets changed
		 * in GUI.
		 * 
		 * @param val	Magnitude with values from 0..1000
		 */
		this.mag = val;
		reCalcAndPaint();
	}

	public void rotationChanged(int val) {
		this.rot = val;
		reCalcAndPaint();
		
	}
	
	public void projDistChanged(int val){
		this.projDist = val;
		reCalcAndPaint();
	}



}
