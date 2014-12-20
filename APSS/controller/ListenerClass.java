
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JFrame;
import javax.swing.JScrollBar;

public class ListenerClass extends JFrame implements ActionListener, AdjustmentListener{

	private Controller ctrl;
	
	public ListenerClass(Controller ctrl) {
		this.ctrl = ctrl;
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		// TODO Auto-generated method stub
		JScrollBar bar = (JScrollBar) e.getSource();
		String name = bar.getName();
		int val = bar.getValue();
		if(name=="scrlAscension"){
			System.out.println("RA from scrollbar:" + val);
			ctrl.ascensionChanged(val);
		}
		else if(name =="scrlDeclination"){
			System.out.println("dec from scrollbar:" + val);
			if(val == 45){
				System.out.println("Break it down");
			}
			ctrl.declinationChanged(val);
			
		}
			
		else if(name =="scrlMag")
			ctrl.magnitudeChanged(val);
		else if(name =="scrlRot")
			ctrl.rotationChanged(val);
		else if(name =="scrlProj")
			ctrl.projDistChanged(val);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
