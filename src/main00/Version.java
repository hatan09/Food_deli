package main00;

import javax.swing.JOptionPane;

public abstract class Version {
	private static String version = "1.0";
	
	public static String getVersion() {
		return version;
	}
	
	public static void checkForUpdates() {
		String cur_version = MySQLCommunication.getVersion();
		if(version.equals(cur_version)) {
			JOptionPane.showMessageDialog(null, "Your app is running the latest version.\n"
					+ 							"                   Current: " + version);
		}
		else {
			JOptionPane.showMessageDialog(null, "Your app is outdated. Please head to our website for the newer version.\n"
					+ 							"                   Current: " + version + "\n"
					+							"                  Lasetest: " + cur_version);
		}
	}
}
