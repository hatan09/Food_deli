package main00;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public abstract class SendData {
	static String serverIP = "277.21.13.45";
    static int port = 2404;
    
    public static void setIP(String ip) {
    	if(ip.isEmpty()) {
    		serverIP = "192.168.1.9";
    	}
    	else {
    		serverIP = ip;
    	}
    }
    
    public static boolean send() {
    	try {
    		System.out.println("Ket noi toi " + serverIP + " on port " + port);
    		Socket client = new Socket(serverIP, port);
    		System.out.println("Just connected to " + client.getRemoteSocketAddress());
    		OutputStream outToServer = client.getOutputStream();
    		DataOutputStream out = new DataOutputStream(outToServer);
    		out.writeUTF("new order");
    		client.close();
    		return true;
    	}
    	catch(IOException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
}
