package cz.kamenitxan.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerReciever {
	ServerSocket server = null;
	private static ArrayList<String> requests = new ArrayList<>();

	public void listenSocket(){
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(9999);

			while(true){
				Socket socket = serverSocket.accept();
				XmlProcesor client = new XmlProcesor(socket, Main.isCleanStart());
				new Thread(client).start();
			}
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + 9999 + ", " + e.getMessage());
		}
		try {
			serverSocket.close();
		} catch (IOException e) {
			System.err.println("Could not close server socket." + e.getMessage());
		}

	}

	public static ArrayList<String> getRequests() {
		return requests;
	}

	public static void addRequest(String request) {
		requests.add(request);
	}
}
