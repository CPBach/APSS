
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
package gui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.Raster;
import java.awt.image.DataBuffer;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;

public class SpaceCanvas extends Canvas{

	private int[][] matrix = null; 
	
	BufferedImage bufferedImage;
	DataBuffer dataBuffer;
	Raster raster;
	
	
	public SpaceCanvas() {
		// TODO Auto-generated constructor stub
		
		// Comment the following out: This is only for thest purposes
		// dataBuffer = new DataBuffer()
		
	}
	
	public void setMatrix(int[][] matrix){
		// SHould have dimensions 500x500
		this.matrix = matrix;
		this.repaint();
	}
	
	

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		if(matrix != null){
			Graphics2D g2d = (Graphics2D) g;
			Color color;
			for(int i=0;i<500;i++){
				for(int j=0;j<500;j++){
					color = new Color(matrix[i][j]);
					g2d.setColor(color);;
					g2d.drawLine(i,j,i,j);
				}
			}


		}
	}
}
