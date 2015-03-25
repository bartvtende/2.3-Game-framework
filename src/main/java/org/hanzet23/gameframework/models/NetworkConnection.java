package main.java.org.hanzet23.gameframework.models;

import java.io.BufferedReader;
import java.io.IOException;

public class NetworkConnection extends Thread {
	
	private BufferedReader input = null;
	private Protocol protocol = null;
	
	public NetworkConnection(BufferedReader input) {
		this.input = input;
		this.protocol = new Protocol();
	}
	
	@Override
	public void run() {
		try {
			String line;
			while ((line = this.input.readLine()) != null) {
	            protocol.receivedMessage(line);
        	}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
