package main00;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
	public static void main(String[] args) {
		Frame f = new Frame();
		f.addWindowListener( new WindowAdapter() {
		    public void windowOpened( WindowEvent e ){
		        GUI_Working.log_mail.requestFocus();
		    }
		}); 
		f.setVisible(true);
	}
}
