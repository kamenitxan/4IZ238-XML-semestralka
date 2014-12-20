package cz.kamenitxan.server;


public class Main {
	private static boolean cleanStart = false;
	public static void main(String[] args) {
		if (args.length > 0) {
			if (args[0].equals("clean")) {
				System.out.println("Čistý start serveru: Uložené požadavky nejsou načteny.");
				cleanStart = true;
			}
		}
		ServerReciever reciever = new ServerReciever();
		System.out.println("Server běží");
		reciever.listenSocket();
	}

	public static boolean isCleanStart() {
		return cleanStart;
	}
}
