package cz.kamenitxan.server;


public class Main {
	public static void main(String[] args) {
		ServerReciever reciever = new ServerReciever();
		System.out.println("Server běží");
		reciever.listenSocket();

	}
}
