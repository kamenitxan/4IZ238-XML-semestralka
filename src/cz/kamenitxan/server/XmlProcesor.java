package cz.kamenitxan.server;


import java.io.DataInputStream;
import java.net.Socket;

public class XmlProcesor implements Runnable {
	Socket clientSocket = null;
	static int i = 0;

	public XmlProcesor(Socket clientSocket) {
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
	}

	static String convertStreamToString(java.io.InputStream is) {
		try(java.util.Scanner s = new java.util.Scanner(is)) {
			return s.useDelimiter("\\A").hasNext() ? s.next() : "";
		}
	}
}
