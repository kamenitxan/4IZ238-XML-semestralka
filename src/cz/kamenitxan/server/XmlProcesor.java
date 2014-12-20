package cz.kamenitxan.server;

import cz.kamenitxan.klient.Request;

import java.io.DataInputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class XmlProcesor implements Runnable {
	Socket clientSocket = null;
	static int i = 0;
	private Map<Integer, Request> requests = new HashMap<>();

	public XmlProcesor(Socket clientSocket, boolean cleanStart) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		try {
			DataInputStream in = new DataInputStream(clientSocket.getInputStream());
			processXml(convertStreamToString(in));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void processXml(String xml) {
		i++;
		System.out.println(i + xml);
		ServerReciever.addRequest(xml);
	}

	static String convertStreamToString(java.io.InputStream is) {
		try(java.util.Scanner s = new java.util.Scanner(is)) {
			return s.useDelimiter("\\A").hasNext() ? s.next() : "";
		}
	}
}
