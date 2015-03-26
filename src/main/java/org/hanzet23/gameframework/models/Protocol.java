package main.java.org.hanzet23.gameframework.models;


public class Protocol {
	
	private Network network = null;
	
	public Protocol(int serverPort, String serverName) {
		this.network = new Network(serverPort, serverName);
	}
	
	/**
	 * Send output to the server and receiving the input
	 * 
	 * @param command
	 */
	public synchronized void sendOutput(String command) {
		// Send output to server
		Command.printClientLine(command);
		this.network.sendCommand(command);
	}

	/**
	 * Checks if the received string contains errors
	 * 
	 * @param line
	 * @return
	 */
	/*boolean checkForErrors(String line) {
		String error = null;
		int length = 0; 
		if (line != null)
			 length = line.length();
		
		if (length > 3) {
			error = line.substring(0, 3);
		}

		// There was an error
		if ((error != null && error.equalsIgnoreCase("ERR")) || length == 0) {
			printServerLine("There was an error: " + line);
			return true;
		}
		return false;
	}*/

	/**
	 * Parse the string that includes a list and return an array
	 * 
	 * @param line
	 * @return
	 */
	public String[] parseList(String line) {
		int firstBracket = line.indexOf('[') + 1;
		int lastBracket = line.indexOf(']');
		
		// Get the string without the brackets
		String newLine = line.substring(firstBracket, lastBracket);		
		
		// Remove the quotation marks
		newLine = newLine.replace("\"", "");
		
		if (newLine == null) {
			Command.printClientLine("The parsed list is empty!");
			return null;
		}

		// Split the string with comma's
		String[] result = newLine.split("\\s*,\\s*");
		
		return result;
	}
	
	private void parseMap(String line) {
		
	}

}
