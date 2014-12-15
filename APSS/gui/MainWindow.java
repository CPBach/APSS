
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

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Canvas;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.JScrollBar;

import java.awt.Scrollbar;

import javax.swing.JButton;
import javax.xml.bind.Marshaller.Listener;

import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import controller.ListenerClass;

public class MainWindow {

	public SpaceCanvas scanvas;
	
	private JFrame frmMain;
	private JScrollBar scrlRot,scrlAscension,scrlDeclination,scrlMag,scrlProjDistance;

	private MainWindow window;
	
	private ListenerClass lc;

	/**
	 * Create the application.
	 */
	public MainWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
					window.frmMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void paint500x500(int[][] matrix){
		
	}
	
	public MainWindow(ListenerClass lc) {
		this.lc = lc;
		initialize();
		addListeners();
		frmMain.setVisible(true);
	}
	

	public static void main(String[] args){
		
	}
	

	
	
	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frmMain = new JFrame("APSS - Another plate solving software");
		frmMain.setResizable(false);
		frmMain.setBounds(100, 100, 887, 640);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.getContentPane().setLayout(null);
		
		scanvas = new SpaceCanvas();
		scanvas.setBackground(Color.BLACK);
		scanvas.setBounds(10, 52, 500, 500);
		frmMain.getContentPane().add(scanvas);
		
		scrlDeclination = new JScrollBar();
		scrlDeclination.setMaximum(90);
		scrlDeclination.setMinimum(-90);

		scrlDeclination.setBounds(516, 52, 17, 500);
		scrlDeclination.setName("scrlDeclination");
		frmMain.getContentPane().add(scrlDeclination);
		
		JLabel lblProjectingDistance = new JLabel("Projecting distance: ");
		lblProjectingDistance.setBounds(567, 24, 133, 14);
		frmMain.getContentPane().add(lblProjectingDistance);
		
		scrlAscension = new JScrollBar();
		scrlAscension.setBlockIncrement(1);
		scrlAscension.setMaximum(24);

		scrlAscension.setOrientation(JScrollBar.HORIZONTAL);
		scrlAscension.setBounds(10, 558, 500, 14);
		scrlAscension.setName("scrlAscension");
		frmMain.getContentPane().add(scrlAscension);
		
		JLabel lblRotation = new JLabel("Rotation:");
		lblRotation.setBounds(566, 173, 79, 14);
		frmMain.getContentPane().add(lblRotation);
		
		scrlRot = new JScrollBar();


		scrlRot.setMinimum(-180);
		scrlRot.setMaximum(180);
		scrlRot.setOrientation(JScrollBar.HORIZONTAL);
		scrlRot.setBounds(566, 198, 215, 14);
		scrlRot.setName("scrlRot");
		frmMain.getContentPane().add(scrlRot);
		
		scrlMag = new JScrollBar();
		scrlMag.setMaximum(1000);

		scrlMag.setOrientation(JScrollBar.HORIZONTAL);
		scrlMag.setBounds(566, 248, 215, 14);
		scrlMag.setName("scrlMag");
		frmMain.getContentPane().add(scrlMag);
		
		JLabel lblMagnitude = new JLabel("Magnitude:");
		lblMagnitude.setBounds(566, 223, 79, 14);
		frmMain.getContentPane().add(lblMagnitude);
		
		scrlProjDistance = new JScrollBar();
		scrlProjDistance.setMaximum(1000);
		scrlProjDistance.setOrientation(JScrollBar.HORIZONTAL);
		scrlProjDistance.setBounds(567, 49, 214, 14);
		scrlProjDistance.setName("scrlProj");
		frmMain.getContentPane().add(scrlProjDistance);
		
		JLabel lblRightAscension = new JLabel("Right Ascension:");
		lblRightAscension.setBounds(10, 583, 106, 14);
		frmMain.getContentPane().add(lblRightAscension);
		
		JLabel lblRaNumber = new JLabel("0");
		lblRaNumber.setBounds(126, 583, 17, 14);
		frmMain.getContentPane().add(lblRaNumber);
		
		JLabel lblDeclination = new JLabel("Declination:");
		lblDeclination.setBounds(153, 583, 79, 14);
		frmMain.getContentPane().add(lblDeclination);
		
		JLabel lblDeclNumber = new JLabel("0");
		lblDeclNumber.setBounds(253, 583, 46, 14);
		frmMain.getContentPane().add(lblDeclNumber);
		
		JLabel lblNumberOfStars = new JLabel("Number of stars:");
		lblNumberOfStars.setBounds(315, 583, 106, 14);
		frmMain.getContentPane().add(lblNumberOfStars);
		
		JLabel lblProjDistanceArcmin = new JLabel("1234");
		lblProjDistanceArcmin.setBounds(710, 24, 40, 14);
		frmMain.getContentPane().add(lblProjDistanceArcmin);
		
		JLabel lblArcmin = new JLabel("arcmin");
		lblArcmin.setBounds(760, 24, 46, 14);
		frmMain.getContentPane().add(lblArcmin);
		
		JLabel lblStarNumber = new JLabel("0");
		lblStarNumber.setBounds(487, 583, 46, 14);
		frmMain.getContentPane().add(lblStarNumber);
		
		JLabel label_3 = new JLabel("0");
		label_3.setBounds(633, 223, 46, 14);
		frmMain.getContentPane().add(label_3);
		
		JLabel lblMag = new JLabel("mag");
		lblMag.setBounds(689, 223, 46, 14);
		frmMain.getContentPane().add(lblMag);
		
		JLabel lblBrightStarCatalog = new JLabel("Bright star catalog:");
		lblBrightStarCatalog.setBounds(10, 24, 117, 14);
		frmMain.getContentPane().add(lblBrightStarCatalog);
	}
	
	private void addListeners(){
		scrlMag.addAdjustmentListener(lc);
		scrlRot.addAdjustmentListener(lc);
		scrlAscension.addAdjustmentListener(lc);
		scrlDeclination.addAdjustmentListener(lc);
		scrlProjDistance.addAdjustmentListener(lc);
	}
}
