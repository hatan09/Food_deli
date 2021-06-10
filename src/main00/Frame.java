package main00;

import javax.swing.*;

public class Frame extends JFrame{
	public Frame() {
		String ip;
		Object obj;
		obj = JOptionPane.showInputDialog("IP: (blank = localhost)");
		ip = (obj == null)? "" : obj.toString();
		SendData.setIP(ip);
		database.Conector.setIP(ip);
		setTitle("KFC Delivery Food" + "_" + Version.getVersion());
        setSize(1313, 850);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        GUI_Working p = new GUI_Working();
        add(p);
	}
}
