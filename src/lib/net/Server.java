package lib.net;

import java.net.*;
import java.io.IOException;

// import server.controller.MainController;
import lib.Console;
// import lib.net.ClientHandler;
// import lib.net.Clients;
// import server.lib.DB;

//=============================================================================
// ▼ Server
// ----------------------------------------------------------------------------
// Démarre une nouvelle session (ClientSession) pour chaque client connecté.
// Fonctionne actuellement en local pour les tests.
//=============================================================================
public class Server implements Runnable
{
	private ServerSocket listener;
	private boolean listening;

	//---------------------------------------------------------------------------
	// * Constructeur
	//---------------------------------------------------------------------------
	public Server(Integer port)
	{
		setListener(port);

		// System.out.println("\nServeur démarré!");
		// System.out.println("================\n");
	}

	//---------------------------------------------------------------------------
	// * Set listener
	//---------------------------------------------------------------------------
	private void setListener(Integer port)
	{
		try {
			listener = new ServerSocket(port, 0, InetAddress.getByName(null));
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + port.toString());
			System.exit(1);
		}
	}

	//---------------------------------------------------------------------------
	// * Listen
	// Créer un nouveau socket et un nouveau thread à chaque fois qu'un client
	// essaie de se connecter.
	//---------------------------------------------------------------------------
	public void run()
	{
		Console.printAsync("Serveur démarré!");

		listening = true;

		Socket socket;
		ClientHandler clientHandler;
		Thread clientThread;

		while(listening) {
			try {
				socket        = listener.accept();
				clientHandler = new ClientHandler(socket);
				clientThread  = new Thread(clientHandler);
				clientThread.start();
			} catch (IOException e) {
				// e.getMessage();
				// e.printStackTrace();
			}
		}

		try {
			// System.out.println("Fin serveur!");
			listener.close();
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	//---------------------------------------------------------------------------
	// * Log
	// Affiche un message sur la sortie standard.
	//---------------------------------------------------------------------------
	public void stop()
	{
		// System.out.println("Stop!");
		listening = false;
		try {
			listener.close();
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	//---------------------------------------------------------------------------
	// * Log
	// Affiche un message sur la sortie standard.
	//---------------------------------------------------------------------------
	// public void log(String message)
	// {
	// 	System.out.println(message);
	// }

	//---------------------------------------------------------------------------
	// * Main
	// Initialise le serveur et démarre l'écoute.
	//---------------------------------------------------------------------------
	// public static void main(String[] args) throws IOException
	// {
	// 	Server server = new Server();
	// 	// ConnexionBDD.connect(); // TODO RES-BDD : voir ConnexionBDD.java
	// 	server.listen();
	// }
}
