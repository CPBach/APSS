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
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class BitmapLoader {
	/**
	 * This class will be used for getting a bitmap to a matrix.
	 * Then we can use some sophisticated algorithm to
	 * do the quad matching on the matrix...
	 * TODO: Implement the clever algorithm.
	 */
	
	private int[][] loadedBitmap;
	private int height;
	private int width;
	
	
	public BitmapLoader(String filename) {
		loadedBitmap = readFromFile(filename);
	}
	
	
	private int[][] readFromFile(String filename){
		BufferedImage img;
		try{
			img = ImageIO.read(new File(filename));
		}catch(Exception e){
			System.err.println("Error in reading File " + filename);
			return null;
		}
		// you should stop here
		
		height = img.getHeight();
		width = img.getWidth();
		int[][] bw = new int[height][width];
		
		
		// Do grayscaling with bitmap
		for(int x=0; x<width; x++){
		  for(int y=0; y<height; y++){
		     int color = img.getRGB(x,y);
		     int red,green,blue;
		     int bw_tmp = 0;
		     red = (color>>16) & 0xff;
		     green = (int)(color>>8) & 0xff;
		     blue = (int)(color)& 0xff;
		     bw_tmp += red;
		     bw_tmp += green;
		     bw_tmp += blue;
		     bw_tmp /=3;
		     bw[y][x] =  bw_tmp;
		  }
		}
		
		return bw;
	}
	
	public int countWhitePoints(){
		int wp = 0;
		for(int y = 0; y<height; y++){
			for(int x =0;x<width;x++){
				if (loadedBitmap[y][x]==255){
					wp++;
				}
			}
		}
		return wp;
	}
	
	public void printNonZero(){
		for(int y = 0; y<height; y++){
			for(int x =0;x<width;x++){
				if (loadedBitmap[y][x]!=0){
					System.out.println(
							"Y: " + y + " X: " + x + " val: " 
					        + loadedBitmap[y][x]);
				}
			}
		}
	}

}
