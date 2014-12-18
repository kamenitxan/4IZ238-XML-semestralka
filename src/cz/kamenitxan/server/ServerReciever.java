package cz.kamenitxan.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerReciever {
	ServerSocket server = null;

	public void listenSocket(){
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(9999);

			while(true){
				Socket socket = serverSocket.accept();
				XmlProcesor client = new XmlProcesor(socket);
				new Thread(client).start();
			}
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + 9999 + ", " + e.getMessage());
			System.exit(1);
		}
		try {
			serverSocket.close();
		} catch (IOException e) {
			System.err.println("Could not close server socket." + e.getMessage());
		}

	}
}
