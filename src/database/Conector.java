package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
	static String ip = "";
	    public static Connection getJDBCConnection(){
	        final String url = "jdbc:mysql://" + ip + ":3306/pdm";
	        System.out.println(url);
	        final String user = "pdm";
	        final String password = "root";

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            return DriverManager.getConnection(url,user,password);
	        } 
	        catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    public static void setIP(String ip) {
	    	if(!ip.isEmpty()) {
	    		Conector.ip = ip;
	    	}
	    	else {
	    		Conector.ip = "localhost";
	    	}
	    }
}
